package com.ch.util;

import com.alibaba.fastjson.JSON;
import com.ch.base.TransApi;
import com.ch.config.BaiduProperties;
import com.ch.model.TranslateResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaiduTranslateUtil {

    private static final Logger LOGGER = LogManager.getLogger(BaiduTranslateUtil.class);

    @Autowired
    BaiduProperties baiduProperties;


    public String translate(String param) {
        TransApi api = new TransApi(baiduProperties.getAccessKeyId(), baiduProperties.getAccessKeySecret());
        com.alibaba.fastjson.JSONObject transResult = api.getTransResult(param, "zh", "en");
        LOGGER.info(transResult);
        System.out.println(transResult);
        TranslateResult translateResult =  JSON.toJavaObject(transResult, TranslateResult.class);
        if (translateResult.getTrans_result().stream().findFirst().isPresent()) {
            return translateResult.getTrans_result().stream().findFirst().get().getDst();
        }
        return null;
    }

    public String translateFan(String param) {
        TransApi api = new TransApi(baiduProperties.getAccessKeyId(), baiduProperties.getAccessKeySecret());
        com.alibaba.fastjson.JSONObject transResult = api.getTransResult(param, "zh", "cht");
        LOGGER.info(transResult);
        System.out.println(transResult);
        TranslateResult translateResult =  JSON.toJavaObject(transResult, TranslateResult.class);
        if (translateResult.getTrans_result().stream().findFirst().isPresent()) {
            return translateResult.getTrans_result().stream().findFirst().get().getDst();
        }
        return null;
    }

}
