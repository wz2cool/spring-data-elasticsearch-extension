package com.github.wz2cool.elasticsearch.test.query;

import com.github.wz2cool.elasticsearch.helper.JSON;
import com.github.wz2cool.elasticsearch.model.LogicPagingResult;
import com.github.wz2cool.elasticsearch.model.UpDown;
import com.github.wz2cool.elasticsearch.query.DynamicQuery;
import com.github.wz2cool.elasticsearch.query.LogicPagingQuery;
import com.github.wz2cool.elasticsearch.test.TestApplication;
import com.github.wz2cool.elasticsearch.test.dao.StudentEsDAO;
import com.github.wz2cool.elasticsearch.test.model.StudentES;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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
@SuppressWarnings("all")
public class ElasticsearchExtRepositoryTest {

    @Resource
    private StudentEsDAO studentEsDAO;

    @Before
    public void init() {

        // saveTestData();
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
        LogicPagingQuery<StudentES> query =
                LogicPagingQuery.createQuery(StudentES.class, StudentES::getId, SortOrder.ASC, UpDown.NONE)
                        .or(b -> b.rangeQuery(StudentES::getId).gt(3L).lt(6L))
                        .or(b -> b.rangeQuery(StudentES::getId).gt(10L).lt(15L))
                        .scoreMapping(StudentES::setScore)
                        .highlightMapping(StudentES::getName, StudentES::setNameHit);
        final LogicPagingResult<StudentES> studentESLogicPagingResult = studentEsDAO.selectByLogicPaging(query);
        System.out.println(JSON.toJSONString(studentESLogicPagingResult));
    }

    @Test
    public void testLogicPaging3() {
        LogicPagingQuery<StudentES> query =
                LogicPagingQuery.createQuery(StudentES.class, StudentES::getId, SortOrder.ASC, UpDown.NONE)
                        .and(false, b -> b.multiMatchQuery("aaa", StudentES::getName, StudentES::getNameHit)
                                .minimumShouldMatch("100%"))
                        .andGroup(g -> g
                                .and(b -> b.rangeQuery(StudentES::getId).gt(3L))
                                .and(b -> b.rangeQuery(StudentES::getId).lt(6L)))
                        .scoreMapping(StudentES::setScore)
                        .highlightMapping(StudentES::getName, StudentES::setNameHit);
        final LogicPagingResult<StudentES> studentESLogicPagingResult = studentEsDAO.selectByLogicPaging(query);
        System.out.println(JSON.toJSONString(studentESLogicPagingResult));
    }
}
