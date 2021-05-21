package com.github.wz2cool.elasticsearch.test.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "test_example", type = "testExample")
public class TestExampleES {

    @Id
    private Long id;
    private String p1;
    private Integer p2;
    private Long p3;
    private Float p4;
    private Double p5;
    private Date p6;

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public Integer getP2() {
        return p2;
    }

    public void setP2(Integer p2) {
        this.p2 = p2;
    }

    public Long getP3() {
        return p3;
    }

    public void setP3(Long p3) {
        this.p3 = p3;
    }

    public Float getP4() {
        return p4;
    }

    public void setP4(Float p4) {
        this.p4 = p4;
    }

    public Double getP5() {
        return p5;
    }

    public void setP5(Double p5) {
        this.p5 = p5;
    }

    public Date getP6() {
        return p6;
    }

    public void setP6(Date p6) {
        this.p6 = p6;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
