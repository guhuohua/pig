package com.ch.entity;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@SolrDocument(solrCoreName = "collection1")
public class GoodsSolrSchema implements Serializable {

    @Field
    @Id
    private String id;

    @Field
    private Integer goodsId;

    @Field
    private String goodTitle;

    @Field
    private String goodName;

    @Field
    private BigDecimal originalPrice;

    @Field
    private BigDecimal presentPrice;

    @Field
    private Integer salesVolume;

    @Field
    private Integer categoryId;

    /**
     * 库存
     */
    @Field
    private Integer inventory;

    @Field
    private Integer shopId;

    @Field
    private String goodsSalesArea;

    @Field
    private String goodsImgUrl;

    @Field
    private Integer boutiqueSort;

    @Field
    private Integer newSort;

    @Field
    private Integer hotSort;

    @Field
    private Integer sort;

    @Field
    private  Integer status;

}
