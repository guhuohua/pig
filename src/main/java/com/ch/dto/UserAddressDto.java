/**
 * Author: 常富文
 * Date:   2019/5/20 14:57
 * Description:
 */


package com.ch.dto;

import com.ch.entity.UserAddress;
import org.apache.tomcat.jni.Address;

public class UserAddressDto {
    private UserAddress[] records;

    public UserAddress[] getRecords() {
        return records;
    }

    public void setRecords(UserAddress[] records) {
        this.records = records;
    }
}
