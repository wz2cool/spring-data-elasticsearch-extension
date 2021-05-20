package com.github.wz2cool.elasticsearch.test.query;

import com.github.wz2cool.elasticsearch.core.HighlightResultMapper;
import com.github.wz2cool.elasticsearch.helper.JSON;
import com.github.wz2cool.elasticsearch.model.LogicPagingResult;
import com.github.wz2cool.elasticsearch.model.UpDown;
import com.github.wz2cool.elasticsearch.query.LogicPagingQuery;
import com.github.wz2cool.elasticsearch.test.TestApplication;
import com.github.wz2cool.elasticsearch.test.dao.StudentEsDAO;
import com.github.wz2cool.elasticsearch.test.model.StudentES;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = TestApplication.class)
public class ElasticsearchExtRepositoryTest {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Resource
    private StudentEsDAO studentEsDAO;

    @Before
    public void init() {
        studentEsDAO.deleteAll();
        saveTestData();
    }

    private void saveTestData() {
        List<StudentES> studentESList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            StudentES studentES = new StudentES();
            studentES.setId((long) i);
            studentES.setName("student" + i);
            studentES.setAge(20);
            studentESList.add(studentES);
        }
        studentEsDAO.save(studentESList.toArray(new StudentES[0]));
    }


    @Test
    public void testLogicPaging() {
        QueryBuilder idQuery = QueryBuilders.rangeQuery("id").lt(77);
        LogicPagingQuery<StudentES> query =
                LogicPagingQuery.createQuery(StudentES.class, StudentES::getId, SortOrder.ASC, UpDown.NONE);

        query.setQueryBuilder(idQuery);

        final LogicPagingResult<StudentES> studentESLogicPagingResult = studentEsDAO.selectByLogicPaging(query);
        System.out.println(JSON.toJSONString(studentESLogicPagingResult));
    }

    @Test
    public void testLogicPaging2() {

        final WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("name", "*stu*");
        LogicPagingQuery<StudentES> query =
                LogicPagingQuery.createQuery(StudentES.class, StudentES::getId, SortOrder.ASC, UpDown.UP)
                        .and(b -> b.termQuery(StudentES::getName, "frank"))
                        .and(b -> b.multiMatchQuery("test")
                                .field(StudentES::getName, 1.1f)
                                .field(StudentES::getNameHit, 11f))
                        .and(b -> b.rangeQuery(StudentES::getAge).gt(false, 1).lt(20))
                        .scoreMapping(StudentES::setScore)
                        .highlightMapping(StudentES::getName, StudentES::setNameHit);
        query.setLastStartPageId(8L);
        query.setLastEndPageId(9L);
        query.setQueryBuilder(wildcardQueryBuilder);

        final LogicPagingResult<StudentES> studentESLogicPagingResult = studentEsDAO.selectByLogicPaging(query);
        System.out.println(JSON.toJSONString(studentESLogicPagingResult));
    }
}
