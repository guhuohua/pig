package com.ch.dao;

import com.ch.entity.GoodsSType;
import com.ch.entity.GoodsSTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsSTypeMapper {
    int countByExample(GoodsSTypeExample example);

    int deleteByExample(GoodsSTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSType record);

    int insertSelective(GoodsSType record);

    List<GoodsSType> selectByExample(GoodsSTypeExample example);

    GoodsSType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsSType record, @Param("example") GoodsSTypeExample example);

    int updateByExample(@Param("record") GoodsSType record, @Param("example") GoodsSTypeExample example);

    int updateByPrimaryKeySelective(GoodsSType record);

    int updateByPrimaryKey(GoodsSType record);
}