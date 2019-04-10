package com.ch.entity;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@SolrDocument(solrCoreName = "shop")
public class GoodsSolrSchema implements Serializable {

    @Id
    @Field
    private Integer id;


    @Field
    private String title;

    @Field
    private String name;

    @Field
    private BigDecimal originalPrice;

    @Field
    private BigDecimal presentPrice;

    @Field
    private Integer salesVolume;

    @Field
    private Integer catrgoryId;

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
    private String goodsImgUrl;

    @Field
    private Integer boutiqueSort;

    @Field
    private Integer newSort;

    @Field
    private Integer hotSort;

}
