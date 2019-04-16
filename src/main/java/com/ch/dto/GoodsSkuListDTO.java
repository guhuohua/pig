package com.ch.dto;

import lombok.Data;

import java.util.List;

@Data
public class GoodsSkuListDTO {

    private Integer specificationId;

    private String specificationName;

    private List<SpecificationAttrDTO> specificationAttrDTOList;
}
