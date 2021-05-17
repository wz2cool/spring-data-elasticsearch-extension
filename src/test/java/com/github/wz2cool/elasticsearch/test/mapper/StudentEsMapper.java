package com.github.wz2cool.elasticsearch.test.mapper;

import com.github.wz2cool.elasticsearch.test.model.StudentES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StudentEsMapper extends ElasticsearchRepository<StudentES, Long> {
}
