package com.ch.dao;

import com.ch.dao.provider.SysGoodsAreaProvider;
import com.ch.dto.RecommendGoodsDTO;
import com.ch.entity.GoodsArea;
import com.ch.entity.GoodsAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsAreaMapper {
    int countByExample(GoodsAreaExample example);

    int deleteByExample(GoodsAreaExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsArea record);

    int insertSelective(GoodsArea record);

    List<GoodsArea> selectByExample(GoodsAreaExample example);

    GoodsArea selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsArea record, @Param("example") GoodsAreaExample example);

    int updateByExample(@Param("record") GoodsArea record, @Param("example") GoodsAreaExample example);

    int updateByPrimaryKeySelective(GoodsArea record);

    int updateByPrimaryKey(GoodsArea record);

    @SelectProvider(type = SysGoodsAreaProvider.class, method = "getList")
    List<RecommendGoodsDTO> list(@Param("title") String title,
                                 @Param("recommend") String recommend,
                                 @Param("status") Integer status, @Param("shopId") Integer shopId);
}