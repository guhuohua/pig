package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.CarDto;
import com.ch.entity.GoodsCar;

public interface ViewGoodsCarService {
    public ResponseResult addCar(Integer skuId ,Integer num,String openId ,Integer shopId);
    ResponseResult showCar(String openId);
    ResponseResult updateCar(CarDto[] carDtos);







}
