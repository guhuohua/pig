package com.ch.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class IdUtil {

    public static String createIdByDate() {
        // 精确到毫秒
        SimpleDateFormat fmt = new SimpleDateFormat("(yyyyMMddHHmmssSSS)");
        String suffix = fmt.format(new Date());
        suffix = suffix + "-" + Math.round((Math.random() * 100000));
        return suffix;
    }


    public static String createIdByUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
