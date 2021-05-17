package com.github.wz2cool.elasticsearch.test.dao;

import com.github.wz2cool.elasticsearch.repository.AbstractElasticsearchRepository;
import com.github.wz2cool.elasticsearch.test.mapper.StudentEsMapper;
import com.github.wz2cool.elasticsearch.test.model.StudentES;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;

@Repository
public class StudentEsDAO extends AbstractElasticsearchRepository<StudentES> {

    @Resource
    private StudentEsMapper studentEsMapper;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    public void save(StudentES... students) {
        studentEsMapper.saveAll(Arrays.asList(students));
    }

    public void deleteAll() {
        studentEsMapper.deleteAll();
    }

    @Override
    protected ElasticsearchTemplate getElasticsearchTemplate() {
        return elasticsearchTemplate;
    }
}
