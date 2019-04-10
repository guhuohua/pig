package com.ch.entity;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.util.List;

@Data
@SolrDocument(solrCoreName = "shop")
public class GoodsSolrSchema implements Serializable {

    @Id
    @Field
    private Integer goodsId;

    @Field
    private String title;

    @Field
    private String name;

    @Field
    private Double price;

    @Field
    private Integer sale;

    /**
     * 库存
     */
    @Field
    private Integer inventory;

    @Field
    private Integer shopId;

    @Field
    private List<String> goodsSalesArea;

    @Field
    private List goodsSpecification;

    @Field
    private String img;

    @Field
    private Integer sort;

}
