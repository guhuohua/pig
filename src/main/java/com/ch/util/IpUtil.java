package com.ch.util;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class IpUtil {
    /**
     * 获取Ip地址
     * @param request
     * @return
     */
    public  String getIpAdrress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }

    public static String getClientIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknow".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();

            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡获取本机配置的IP地址
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inetAddress.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
        if (null != ipAddress && ipAddress.length() > 15) {
            //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }




    /**
     * 解析ip地址
     *  
     * 设置访问地址为http://ip.taobao.com/service/getIpInfo.php
     * 设置请求参数为ip=[已经获得的ip地址]
     * 设置解码方式为UTF-8
     *  
     *
     * @param content   请求的参数 格式为：ip=192.168.1.101
     * @param encoding 服务器端请求编码。如GBK,UTF-8等
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getAddresses(String content, String encoding) throws UnsupportedEncodingException {
//设置访问地址
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php?";
// 从http://whois.pconline.com.cn取得IP所在的省市区信息
        String returnStr = this.getResult(urlStr, content, encoding);
        if (returnStr != null) {
// 处理返回的省市区信息
// System.out.println(returnStr);
            String[] temp = returnStr.split(",");
            if (temp.length < 3) {
                return "0";// 无效IP，局域网测试
            }

            String country = ""; //国家
            String area = ""; //地区
            String region = ""; //省份
            String city = ""; //市区
            String county = ""; //地区
            String isp = ""; //ISP公司
            for (int i = 0; i < temp.length; i++) {
                switch (i) {
                    case 2:
                        country = (temp[i].split(":"))[1].replaceAll("\"", "");
                        country = URLDecoder.decode(country, encoding);// 国家
                        break;
                    case 3:
                        area = (temp[i].split(":"))[1].replaceAll("\"", "");
                        area = URLDecoder.decode(area, encoding);// 地区
                        break;
                    case 4:
                        region = (temp[i].split(":"))[1].replaceAll("\"", "");
                        region = URLDecoder.decode(region, encoding);// 省份
                        break;
                    case 5:
                        city = (temp[i].split(":"))[1].replaceAll("\"", "");
                        city = URLDecoder.decode(city, encoding);// 市区
                        if ("内网IP".equals(city)) {
                            return "地址为：内网IP";
                        }
                        break;
                    case 6:
                        county = (temp[i].split(":"))[1].replaceAll("\"", "");
                        county = URLDecoder.decode(county, encoding);// 地区
                        break;
                    case 7:
                        isp = (temp[i].split(":"))[1].replaceAll("\"", "");
                        isp = URLDecoder.decode(isp, encoding); // ISP公司
                        break;
                }
            }


            return city;
        }
        return null;
    }


    /**
     * 访问目标地址并获取返回值
     *
     * @param urlStr   请求的地址
     * @param content  请求的参数 格式为：ip=192.168.1.101
     * @param encoding 服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(33000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("GET");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
// ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

}
