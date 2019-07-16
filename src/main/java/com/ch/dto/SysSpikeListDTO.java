package com.ch.dto;

import lombok.Data;

@Data
public class SysSpikeListDTO {

    private Integer id;

    private String sn;

    private String title;

    private Long beginDate;

    private Long endDate;

    private Long presentPrice;

    private Long spikePrice;

    private Integer spikeNum;

    private Integer inventory;

    private Integer maxNum;
}
