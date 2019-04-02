package com.ch.dao;

import com.ch.entity.BtViewNewsEng;
import com.ch.entity.BtViewNewsFan;
import com.ch.entity.BtViewNewsFanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface BtViewNewsFanMapper {
    int countByExample(BtViewNewsFanExample example);

    int deleteByExample(BtViewNewsFanExample example);

    int deleteByPrimaryKey(String id);

    int insert(BtViewNewsFan record);

    int insertSelective(BtViewNewsFan record);

    List<BtViewNewsFan> selectByExample(BtViewNewsFanExample example);

    BtViewNewsFan selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BtViewNewsFan record, @Param("example") BtViewNewsFanExample example);

    int updateByExample(@Param("record") BtViewNewsFan record, @Param("example") BtViewNewsFanExample example);

    int updateByPrimaryKeySelective(BtViewNewsFan record);

    int updateByPrimaryKey(BtViewNewsFan record);

    @Select("select * from bt_view_news_fan where id = #{id}")
    BtViewNewsFan findById(@Param("id") String id);

    @Update("update bt_view_news_fan set status = #{status} where id = #{id}" )
    int updateStatus (@Param("id") String id,@Param("status") int status);

    @Update("update bt_view_news_fan set status = 0 where menu_id = #{menuId}")
    int updateUnpublished(@Param("menuId") String menuId);


}