/**
 * Author: 常富文
 * Date:   2019/5/20 18:39
 * Description:
 */


package com.ch.dto;

public class GoodsCarDto {

    private  Integer[] ids;

    private  CarDto[] carDto;

    public CarDto[] getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDto[] carDto) {
        this.carDto = carDto;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
