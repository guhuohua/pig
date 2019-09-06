package com.ch.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel
public class RedPacketDTO {
    @ApiModelProperty(name = "id",value = "主键")
    private Integer id;

    @ApiModelProperty(name = "name",value = "红包名称")
    private String name;

    @ApiModelProperty(name = "stock",value = "红包个数")
    private Long stock;

    @ApiModelProperty(name = "sendBeginDate",value = "红包发放时间")
    private Long sendBeginDate;

    @ApiModelProperty(name = "sendEndDate",value = "红包发放结束时间")
    private Long sendEndDate;

    @ApiModelProperty(name = "faceValue",value = "红包面值")
    private BigDecimal faceValue;

    @ApiModelProperty(name = "goodsRange",value = "商品使用范围")
    private Integer goodsRange;

    @ApiModelProperty(name = "redPacketType",value = "红包类型")
    private String redPacketType;

    private List<RedPacketSet> redPacketSets;
}
