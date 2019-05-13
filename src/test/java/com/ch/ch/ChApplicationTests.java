

package com.ch.ch;



import com.alibaba.fastjson.JSON;
import com.ch.base.ResponseResult;
import com.ch.entity.GoodsSolrSchema;
import com.ch.service.SolrService;
import com.ch.service.ViewOrderService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChApplicationTests {

    @Autowired
    SolrClient solrClient;
    @Autowired
    SolrService solrService;





    @Test
    public void testUploadImage() {
        solrService.lowerShelf(20);
    }


}



