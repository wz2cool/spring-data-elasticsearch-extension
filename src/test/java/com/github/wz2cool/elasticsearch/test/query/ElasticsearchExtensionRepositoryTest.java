package com.github.wz2cool.elasticsearch.test.query;

import com.github.wz2cool.elasticsearch.test.TestApplication;
import com.github.wz2cool.elasticsearch.test.dao.StudentEsDAO;
import com.github.wz2cool.elasticsearch.test.model.StudentES;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = TestApplication.class)
public class ElasticsearchExtensionRepositoryTest {

    @Resource
    private StudentEsDAO studentEsDAO;

    @Test
    public void testLogicPaging() {
        StudentES studentES = new StudentES();
        studentES.setId(1L);
        studentES.setName("frank");
        studentES.setAge(20);
        studentEsDAO.save(studentES);
    }
}
