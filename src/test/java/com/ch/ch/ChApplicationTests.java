

package com.ch.ch;


import com.ch.base.ResponseResult;
import com.ch.service.ViewOrderService;
import org.apache.solr.client.solrj.SolrClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChApplicationTests {

    @Autowired
    SolrClient solrClient;
    @Autowired
    ViewOrderService viewOrderService;





    @Test
    public void pay() {

        ResponseResult result = viewOrderService.manageOrder(1, "oIc5o5DYm6RilTDpwblWCiXXkhFM", 1);

        System.out.println(result.getData());
    }


}



