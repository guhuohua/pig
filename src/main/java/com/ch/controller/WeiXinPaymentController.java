package com.ch.controller;

import com.alibaba.fastjson.JSONObject;
import com.ch.base.BeanUtils;
import com.ch.base.ResponseResult;
import com.ch.dao.*;
import com.ch.dto.PaymentDto;
import com.ch.entity.*;
import com.ch.service.ForRecordService;
import com.ch.service.ViewShopNameService;
import com.ch.service.ViewUserInfoService;
import com.ch.util.*;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/pay")
@Slf4j
public class WeiXinPaymentController {

    private final String mch_id = "1512785241";//商户号
    private final String spbill_create_ip = "183.93.230.40";//终端IP
    private final String notify_url = "www.baidu.com";//通知地址
    private final String trade_type = "JSAPI";//交易类型
    private final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单API接口链接
    private final String key = "0EF1CDAFCC3327C1AF3B8D6CA37F9581"; // 商户支付密钥
    private final String appid = "wxf2f4ded6354f9ba7";
    @Autowired
    ViewShopNameService viewShopNameService;
    @Autowired
    PayInfoMapper payInfoMapper;
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    GoodsOrderMapper goodsOrderMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    GoodsCarMapper goodsCarMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ForRecordService forRecordService;
    @Autowired
    ViewUserInfoService viewUserInfoService;
    @Autowired
    FlowUtil flowUtil;
    @Autowired
    BaseIntegralMapper baseIntegralMapper;


    public static String md5Password(String key) {
        char[] hexDigits = {
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
            char[] str = new char[j * 2];
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


    @GetMapping("wxpay")
    @ResponseBody
    public ResponseResult payment(HttpServletRequest req, @RequestParam String orderId, @RequestParam Integer integralStatus, @RequestParam double money, @RequestParam Integer useIntegral) throws UnsupportedEncodingException, DocumentException {
        ResponseResult result1 = new ResponseResult();
        String openId = req.getHeader("openId");
        String token = req.getHeader("Authorization");
        boolean verify = TokenUtil.verify(token);
        Integer shopId = null;
        if (verify) {
            shopId = TokenUtil.getUserId(token);
        } else {
            result1.setCode(999);
            result1.setError("token已过期");
            result1.setError_description("请重新登录");
            return result1;
        }

        //获取店铺信息
        ShopMiniProgram shopMiniProgram = viewShopNameService.shopPayInfo(shopId);
        GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
        goodsOrder.setIntegralStatus(integralStatus);
        goodsOrderMapper.updateByPrimaryKey(goodsOrder);
        List<BaseIntegral> baseIntegrals = baseIntegralMapper.selectByExample(null);
        BaseIntegral baseIntegral = baseIntegrals.get(0);
        //  Shop shop = shopMapper.selectByPrimaryKey(shopId);
        JSONObject JsonObject = new JSONObject();
        String body = "test";
        body = new String(body.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        String nonce_str = UUIDHexGenerator.generate();//随机字符串
        String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String code = PayUtil.createCode(8);
        String out_trade_no = goodsOrder.getId();//商户订单号
        PaymentDto paymentPo = new PaymentDto();
        paymentPo.setAppid(shopMiniProgram.getAppId());
        paymentPo.setMch_id(shopMiniProgram.getMchIdd());
        paymentPo.setNonce_str(nonce_str);
        String newbody = new String(body.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);//以utf-8编码放入paymentPo，微信支付要求字符编码统一采用UTF-8字符编码
        paymentPo.setBody(newbody);
        paymentPo.setOut_trade_no(out_trade_no);
        if (1 == integralStatus) {
            UserInfo userInfo = viewUserInfoService.findOneByOpenId(openId);
            userInfo.setUseIntegral(userInfo.getUseIntegral() - useIntegral);
            userInfoMapper.updateByPrimaryKey(userInfo);
            paymentPo.setTotal_fee((goodsOrder.getOrderPrice() - money * 100) + "");
            goodsOrder.setOrderPrice(Long.parseLong(paymentPo.getTotal_fee()));
            goodsOrderMapper.updateByPrimaryKey(goodsOrder);
        } else {
            paymentPo.setTotal_fee(goodsOrder.getOrderPrice().toString());
        }
        paymentPo.setSpbill_create_ip(spbill_create_ip);
        paymentPo.setNotify_url(shopMiniProgram.getBackUrl());
        paymentPo.setTrade_type("JSAPI");
        paymentPo.setOpenid(openId);
        // 把请求参数打包成数组
        Map<String, Object> sParaTemp = new HashMap();
        sParaTemp.put("appid", paymentPo.getAppid());
        sParaTemp.put("mch_id", paymentPo.getMch_id());
        sParaTemp.put("nonce_str", paymentPo.getNonce_str());
        sParaTemp.put("body", paymentPo.getBody());
        sParaTemp.put("out_trade_no", paymentPo.getOut_trade_no());
        sParaTemp.put("total_fee", paymentPo.getTotal_fee());
        sParaTemp.put("spbill_create_ip", paymentPo.getSpbill_create_ip());
        sParaTemp.put("notify_url", paymentPo.getNotify_url());
        sParaTemp.put("trade_type", paymentPo.getTrade_type());
        sParaTemp.put("openid", paymentPo.getOpenid());
        // 除去数组中的空值和签名参数
        Map sPara = PayUtil.paraFilter(sParaTemp);
        String prestr = PayUtil.createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        StringBuilder stringSignTemp = new StringBuilder(prestr).append("&key=" + shopMiniProgram.getAppKey());
        String sign = md5Password(stringSignTemp.toString()).toUpperCase();
        //MD5运算生成签名
        paymentPo.setSign(sign);
        //打包要发送的xml
        String respXml = XmlUtil.messageToXML(paymentPo);
        // 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
        respXml = respXml.replace("__", "_");
        String param = respXml;
        //String result = SendRequestForUrl.sendRequest(url, param);//发起请求
        String result = PayUtil.httpRequest(url, "POST", param);
        System.out.println("请求微信预支付接口，返回 result：" + result);
        // 将解析结果存储在Map中
        Map map = new HashMap();
        InputStream in = new ByteArrayInputStream(result.getBytes());
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
        String result_code = map.get("result_code").toString();//返回状态码
        System.out.println("请求微信预支付接口，返回 code：" + return_code);
        System.out.println("请求微信预支付接口，返回 msg：" + return_msg);
        if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)) {
            // 业务结果
            String prepay_id = map.get("prepay_id").toString();//返回的预付单信息
            String nonceStr = UUIDHexGenerator.generate();
            JsonObject.put("nonceStr", nonceStr);
            JsonObject.put("package", "prepay_id=" + prepay_id);
            Long timeStamp = System.currentTimeMillis() / 1000;
            JsonObject.put("timeStamp", timeStamp + "");
            String aa = "appId=" + shopMiniProgram.getAppId() + "&nonceStr=" + nonceStr + "&package=prepay_id=" + prepay_id + "&signType=MD5&timeStamp=" + timeStamp + "&key=" + shopMiniProgram.getAppKey();
            //再次签名
            String paySign = md5Password(aa.trim()).toUpperCase();
            JsonObject.put("paySign", paySign);
            result1.setData(JsonObject);
            return result1;
        } else {
            result1.setCode(500);
            return result1;
        }
    }

    /**
     * 预支付时填写的 notify_url ，支付成功后的回调接口
     *
     * @param request
     */
    @RequestMapping("/weixin/paycallback")
    @ResponseBody
    public void paycallback(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> dataMap = XmlUtil.parseXML(request);
            //{"transaction_id":"4200000109201805293331420304","nonce_str":"402880e963a9764b0163a979a16e0002","bank_type":"CFT","openid":"oXI6G5Jc4D44y2wixgxE3OPwpDVg","sign":"262978D36A3093ACBE4B55707D6EA7B2","fee_type":"CNY","mch_id":"1491307962","cash_fee":"10","out_trade_no":"14913079622018052909183048768217","appid":"wxa177427bc0e60aab","total_fee":"10","trade_type":"JSAPI","result_code":"SUCCESS","time_end":"20180529091834","is_subscribe":"N","return_code":"SUCCESS"}
            if ("SUCCESS".equals(dataMap.get("return_code"))) {

                String xmlSuccess = "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>";
                response.getWriter().write(xmlSuccess);
                String transaction_id = (String) dataMap.get("transaction_id");
                String orderId = (String) dataMap.get("out_trade_no");
                Long total_fee = Long.valueOf(dataMap.get("total_fee").toString());
                GoodsOrder goodsOrder = goodsOrderMapper.selectByPrimaryKey(orderId);
                // UserInfo userInfo = userInfoMapper.selectByPrimaryKey(goodsOrder.getUserId());
                if (BeanUtils.isNotEmpty(goodsOrder)) {
                    if (goodsOrder.getOrderStatus() == 10) {
                        return;
                    }
                    goodsOrder.setPayDate(new Date());
                    goodsOrder.setPayPrice(total_fee);
                    goodsOrder.setOrderStatus(3);
                    goodsOrder.setPayId(transaction_id);
                    goodsOrderMapper.updateByPrimaryKey(goodsOrder);
                    OrderItemExample example = new OrderItemExample();
                    OrderItemExample.Criteria criteria = example.createCriteria();
                    criteria.andOrderIdEqualTo(orderId);
                    List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
                    for (OrderItem orderItem : orderItems) {
                        Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
                        if ("INTEGRAL".equals(goods.getGoodsType())) {
                            forRecordService.add(orderId);
                        }
                        GoodsCarExample example1 = new GoodsCarExample();
                        GoodsCarExample.Criteria criteria1 = example1.createCriteria();
                        criteria1.andSkuIdEqualTo(orderItem.getSkuAttrId());
                        goodsCarMapper.deleteByExample(example1);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
