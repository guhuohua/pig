package com.ch.dao.provider;

import com.ch.dto.NewsParam;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class BtViewNewsProvider {

    public String getNewsList(Map<String, Object> map) {
        //获取参数列表
        NewsParam newsParam = (NewsParam) map.get("newsParam");

        StringBuffer sb = new StringBuffer("select * from bt_view_news where 1=1");
        if (StringUtils.isNotEmpty(newsParam.getTitle())) {
            sb.append(" and title like '%").append(newsParam.getTitle()).append("%'");
        }
        if (newsParam.getNewCategoryId() !=null && newsParam.getNewCategoryId() >0) {
            sb.append(" and new_category_id = ").append(newsParam.getNewCategoryId());
        }
        if (newsParam.getStatus() !=null && newsParam.getStatus() ==0) {
            sb.append(" and status = ").append(newsParam.getStatus());
        }
        if (newsParam.getStatus() !=null && newsParam.getStatus() ==1) {
            sb.append(" and status = ").append(newsParam.getStatus());
        }
        sb.append(" order by create_time desc");
        return sb.toString();
    }
}
