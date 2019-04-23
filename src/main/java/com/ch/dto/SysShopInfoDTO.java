package com.ch.dto;

import com.ch.model.SysGoodAvdModel;
import lombok.Data;

import java.util.List;

@Data
public class SysShopInfoDTO {

    private Integer shopId;

    private String shopName;

    private String shopTel;

    private Long shopServiceBeginDate;

    private Long shopServiceEndDate;

    private Integer shopStatus;

    private String adminName;

    private String adminTel;

    private String adminPassword;

    private Integer adminStatus;

    private String appId;

    private String secret;

    private String mchIdd;

    private String key;

    private String backUrl;

    private List<SysGoodAvdModel> sysGoodAvdModels;

}
