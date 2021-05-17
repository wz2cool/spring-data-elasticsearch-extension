package com.github.wz2cool.elasticsearch.test.dao;

import com.github.wz2cool.elasticsearch.repository.ElasticsearchExtensionRepository;
import com.github.wz2cool.elasticsearch.test.mapper.StudentEsMapper;
import com.github.wz2cool.elasticsearch.test.model.StudentES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class StudentEsDAO extends ElasticsearchExtensionRepository<StudentES> {

    @Resource
    private StudentEsMapper studentEsMapper;

    @Autowired
    public StudentEsDAO(ElasticsearchTemplate elasticsearchTemplate) {
        super(elasticsearchTemplate);
    }


}
