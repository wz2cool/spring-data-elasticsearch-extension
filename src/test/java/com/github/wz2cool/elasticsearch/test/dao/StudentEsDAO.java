package com.github.wz2cool.elasticsearch.test.dao;

import com.github.wz2cool.elasticsearch.model.LogicPagingResult;
import com.github.wz2cool.elasticsearch.query.LogicPagingQuery;
import com.github.wz2cool.elasticsearch.test.mapper.StudentEsQueryMapper;
import com.github.wz2cool.elasticsearch.test.model.StudentES;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;

@Repository
public class StudentEsDAO {

    @Resource
    private StudentEsQueryMapper studentEsMapper;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    public void save(StudentES... students) {
        studentEsMapper.saveAll(Arrays.asList(students));
    }

    public void deleteAll() {
        studentEsMapper.deleteAll();
    }

    public LogicPagingResult<StudentES> selectByLogicPaging(LogicPagingQuery<StudentES> logicPagingQuery) {
        return studentEsMapper.selectByLogicPaging(elasticsearchTemplate, logicPagingQuery);
    }
}
