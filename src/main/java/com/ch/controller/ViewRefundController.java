/**
 * Author: 常富文
 * Date:   2019/4/24 9:10
 * Description: 小程序退款
 */


package com.ch.controller;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;


import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.ch.dao.GoodsOrderMapper;
import com.ch.dao.ShopMiniProgramMapper;
import com.ch.dto.RefoundDto;
import com.ch.entity.GoodsOrder;
import com.ch.entity.ShopMiniProgram;
import com.ch.service.ViewShopNameService;
import com.ch.util.*;
import com.sun.org.apache.bcel.internal.ExceptionConstants;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.util.Constants;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weixin")
public class ViewRefundController {

    @Autowired
    GoodsOrderMapper goodsOrderMapper;
    @Autowired
    ShopMiniProgramMapper shopMiniProgramMapper;
    @Autowired
    ViewShopNameService viewShopNameService;


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
    @Transactional
    public JSONObject refund(HttpServletRequest req,@RequestParam String orderId) {
        // Long orderId, String refundId, Long totalFee,
        // Long refundFee, String refundAccount

        // checkConfig(weixinProperties);
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        Integer shopId = TokenUtil.getUserId(token);

        GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);

        ShopMiniProgram shopMiniProgram = viewShopNameService.shopPayInfo(shopId);

        String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String code = PayUtil.createCode(8);

        String refundId = shopMiniProgram.getMchIdd() + today + code;

        RefoundDto refoundDto = new RefoundDto();
        refoundDto.setAppid(shopMiniProgram.getAppId());
        refoundDto.setMch_id(shopMiniProgram.getMchIdd());
        refoundDto.setNonce_str(RandomUtils.generateMixString(32));
        refoundDto.setOut_trade_no(goodsOrder.getPayId());
        refoundDto.setOut_refund_no(refundId);
        refoundDto.setTotal_fee(goodsOrder.getOrderPrice()+"");
        refoundDto.setRefund_fee( goodsOrder.getOrderPrice().toString());

        Map<String, Object> params = new TreeMap<>();
        params.put("appid", refoundDto.getAppid());
        params.put("mch_id", refoundDto.getMch_id());
        params.put("nonce_str",refoundDto.getNonce_str());
        //商户订单号和微信订单号二选一
        params.put("out_trade_no",refoundDto.getOut_trade_no());
        params.put("out_refund_no", refoundDto.getOut_refund_no());
        params.put("total_fee", refoundDto.getTotal_fee());
        params.put("refund_fee", refoundDto.getRefund_fee());
        params.put("refund_account", "REFUND_SOURCE_UNSETTLED_FUNDS");
        // 除去数组中的空值和签名参数
        Map sPara = PayUtil.paraFilter(params);
        String prestr = PayUtil.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        StringBuilder stringSignTemp=new StringBuilder(prestr);

        String sign = md5Password(stringSignTemp.toString()).toUpperCase();

        //MD5运算生成签名
        refoundDto.setSign(sign);



        try {

            String respXml = XmlUtil.messageToXML1(refoundDto);
            System.out.println(respXml);
            // 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
            respXml = respXml.replace("__", "_");
            String param = respXml;


            String xmlStr = doRefund("https://api.mch.weixin.qq.com/secapi/pay/refund", param);

            //加入微信支付日志
           // payWechatLogService.insertPayWechatLog(Constants.PAY_REFUND_RESULT_LOG, xmlStr);



            System.out.println("请求微信退款接口，返回 result："+xmlStr);
            // 将解析结果存储在Map中
            Map map = new HashMap();
            InputStream in=new ByteArrayInputStream(xmlStr.getBytes());
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
            System.out.println("请求微信预支付接口，返回 code：" + return_code);
            System.out.println("请求微信预支付接口，返回 msg：" + return_msg);
            JSONObject JsonObject = new JSONObject() ;

            if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)) {
                // 业务结果

                String prepay_id = map.get("prepay_id").toString();//返回的预付单信息
                String nonceStr = UUIDHexGenerator.generate();
                JsonObject.put("nonceStr", nonceStr);
                JsonObject.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                JsonObject.put("timeStamp", timeStamp + "");
                String aa = "appId=" + shopMiniProgram.getAppId() + "&nonceStr=" + nonceStr + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp;
                //再次签名
                String paySign = md5Password(aa.trim()).toUpperCase();
                JsonObject.put("paySign", paySign);
            }


            return JsonObject;





        } catch (Exception e) {

            System.out.println(e);
            return null;
        }
    }


    public String doRefund(String url, String data) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        FileInputStream is = new FileInputStream(new File("G:\\cert\\apiclient_cert.p12"));
        try {
            keyStore.load(is, "1512785241".toCharArray());
        } finally {
            is.close();
        }
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(
                keyStore,
                "1512785241".toCharArray())
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
