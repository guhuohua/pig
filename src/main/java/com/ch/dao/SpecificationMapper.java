package com.ch.dao;

import com.ch.dao.provider.SpecificationProvider;
import com.ch.entity.Specification;
import com.ch.entity.SpecificationExample;
import java.util.List;

import com.ch.model.SpecificationModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationMapper {
    int countByExample(SpecificationExample example);

    int deleteByExample(SpecificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Specification record);

    int insertSelective(Specification record);

    List<Specification> selectByExample(SpecificationExample example);

    Specification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Specification record, @Param("example") SpecificationExample example);

    int updateByExample(@Param("record") Specification record, @Param("example") SpecificationExample example);

    int updateByPrimaryKeySelective(Specification record);

    int updateByPrimaryKey(Specification record);

    @SelectProvider(type = SpecificationProvider.class, method = "getList")
    List<SpecificationModel> findPage(@Param("name") String name, @Param("shopId") Integer shopId);
}