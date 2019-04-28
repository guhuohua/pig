package com.ch.service;

import com.ch.base.ResponseResult;
import com.ch.dto.CarDto;

public interface ViewGoodsCarService {
    public ResponseResult addCar(Integer skuId, Integer num, String openId, Integer shopId);

    ResponseResult showCar(String openId, Integer shopId);

    ResponseResult updateCar(CarDto[] carDtos);

    ResponseResult deleCar(Integer[] ids);


}
