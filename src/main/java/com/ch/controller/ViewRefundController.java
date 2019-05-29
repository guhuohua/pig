/**
 * Author: 常富文
 * Date:   2019/4/24 9:10
 * Description: 小程序退款
 */


package com.ch.controller;

import com.alibaba.fastjson.JSONObject;
import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.config.WxRefundProperties;
import com.ch.dao.*;
import com.ch.dto.RefoundDto;
import com.ch.entity.*;
import com.ch.service.SolrService;
import com.ch.service.ViewShopNameService;
import com.ch.util.PayUtil;
import com.ch.util.RandomUtils;
import com.ch.util.TokenUtil;
import com.ch.util.XmlUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("weixin")
public class ViewRefundController {

    @Autowired
    GoodsOrderMapper goodsOrderMapper;
    @Autowired
    ShopMiniProgramMapper shopMiniProgramMapper;
    @Autowired
    ViewShopNameService viewShopNameService;
    @Autowired
    WxRefundProperties wxRefundProperties;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    SolrService solrService;


    public static String md5Password(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }


    @PostMapping("refund")
    @ResponseBody
    public ResponseResult refund(HttpServletRequest req, HttpServletResponse response, @RequestParam String orderId) {
        ResponseResult result1 = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);
        GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
        ShopMiniProgram shopMiniProgram = viewShopNameService.shopPayInfo(shopId);
        String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String code = PayUtil.createCode(8);
        RefoundDto refoundDto = new RefoundDto();
        refoundDto.setAppid(shopMiniProgram.getAppId());
        refoundDto.setMch_id(shopMiniProgram.getMchIdd());
        refoundDto.setNonce_str(RandomUtils.generateMixString(32));
        refoundDto.setOut_trade_no(goodsOrder.getId());
        if (BeanUtils.isNotEmpty(goodsOrder.getRefundId())){
            refoundDto.setOut_refund_no(goodsOrder.getRefundId());
        }else {
            String refundId = shopMiniProgram.getMchIdd() + today + code;
            refoundDto.setOut_refund_no(refundId);
            goodsOrder.setRefundId(refundId);
            goodsOrderMapper.updateByPrimaryKey(goodsOrder);
        }
        refoundDto.setTotal_fee(goodsOrder.getOrderPrice() + "");
        refoundDto.setRefund_fee(goodsOrder.getPayPrice().toString());
        Map<String, Object> params = new TreeMap<>();
        params.put("appid", refoundDto.getAppid());
        params.put("mch_id", refoundDto.getMch_id());
        params.put("nonce_str", refoundDto.getNonce_str());
        //商户订单号和微信订单号二选一
        params.put("out_trade_no", goodsOrder.getId());
        params.put("out_refund_no", refoundDto.getOut_refund_no());
        params.put("total_fee", refoundDto.getTotal_fee());
        params.put("refund_fee", refoundDto.getRefund_fee());
        //params.put("refund_account", "REFUND_SOURCE_UNSETTLED_FUNDS");
        // 除去数组中的空值和签名参数
        Map sPara = PayUtil.paraFilter(params);
        String prestr = PayUtil.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        StringBuilder stringSignTemp = new StringBuilder(prestr).append("&key=0EF1CDAFCC3327C1AF3B8D6CA37F9581");
        String sign = md5Password(stringSignTemp.toString()).toUpperCase();
        //MD5运算生成签名
        refoundDto.setSign(sign);
        try {
            String respXml = XmlUtil.messageToXML1(refoundDto);
            // 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
            respXml = respXml.replace("__", "_");
            String param = respXml;
            System.out.println(param);
            String xmlStr = doRefund("https://api.mch.weixin.qq.com/secapi/pay/refund", param, shopMiniProgram);
            //加入微信支付日志
            // payWechatLogService.insertPayWechatLog(Constants.PAY_REFUND_RESULT_LOG, xmlStr);
            System.out.println("请求微信退款接口，返回 result：" + xmlStr);
            // 将解析结果存储在Map中
            Map map = new HashMap();
            InputStream in = new ByteArrayInputStream(xmlStr.getBytes());
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            for (Element element : elementList) {
                map.put(element.getName(), element.getText());
            }
            // 返回信息
            String return_code = map.get("return_code").toString();//返回状态码
            String return_msg = map.get("return_msg").toString();//返回信息
            String result_code = map.get("result_code").toString();
           /* System.out.println("请求微信预支付接口，返回 code：" + return_code);
            System.out.println("请求微信预支付接口，返回 msg：" + return_msg);*/
            JSONObject JsonObject = new JSONObject();
            if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)) {
                if (goodsOrder.getOrderStatus() == 3) {
                    goodsOrder.setOrderStatus(10);
                    goodsOrderMapper.updateByPrimaryKey(goodsOrder);
                    OrderItemExample example = new OrderItemExample();
                    OrderItemExample.Criteria criteria = example.createCriteria();
                    criteria.andOrderIdEqualTo(goodsOrder.getId());
                    List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
                    for (OrderItem orderItem : orderItems) {
                        GoodsSku goodsSku = goodsSkuMapper.selectByPrimaryKey(orderItem.getSkuAttrId());
                        goodsSku.setInventory(goodsSku.getInventory() + orderItem.getNumber());
                        goodsSkuMapper.updateByPrimaryKey(goodsSku);
                        Goods goods = goodsMapper.selectByPrimaryKey(goodsSku.getGoodsId());
                        goods.setInventory(goods.getInventory() + orderItem.getNumber());
                        goodsMapper.updateByPrimaryKey(goods);
                        solrService.releaseGoods(goods.getId(),shopId);
                    }
                }
                return result1;
            }else {
                result1.setCode(500);
                return result1;
            }
        } catch (Exception e) {
            JSONObject JsonObject = new JSONObject();
            JsonObject.put("error", e);
            result1.setCode(500);
            result1.setData(JsonObject);
            return result1;
        }
    }


    public String doRefund(String url, String data, ShopMiniProgram shopMiniProgram) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream is = new FileInputStream(new File(wxRefundProperties.getCertificate()));
        try {
            keyStore.load(is, shopMiniProgram.getMchIdd().toCharArray());
        } finally {
            is.close();
        }
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(
                keyStore,
                shopMiniProgram.getMchIdd().toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER
        );
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        try {
            HttpPost httpost = new HttpPost(url); // 设置响应头信息
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();

                String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
