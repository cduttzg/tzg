package org.cdut.tzg.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.cdut.tzg.utils.MD5Utils.signByMd5;

/**
 * @author anlan
 * @date 2019/6/27 17:11
 */
public class CDUTUtils {

    private static String login_url = "http://202.115.133.173:805/Common/Handler/UserLogin.ashx";

    /**
     * 判断是否是成都理工大学的学生
     */
    public static boolean isStudent(String schoolNumber, String schoolPassword) {
        boolean flag = false;
        String[] ret = new String[3]; //初始为null，返回数组存储着成功与否以及cookie信息
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        //设置Cookie
        CookieStore cookieStore = new BasicCookieStore();
        httpClientBuilder.setDefaultCookieStore(cookieStore);
        //设置超时
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).build();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(login_url);
        httpPost.setConfig(requestConfig);
        //设置参数列表
        long sign = System.currentTimeMillis();
        String hash_pwd = signByMd5(schoolPassword);
        String signed_pwd = signByMd5(schoolNumber + Long.toString(sign) + hash_pwd);
        List<NameValuePair> para = new ArrayList<>();
        para.add(new BasicNameValuePair("Action", "Login"));
        para.add(new BasicNameValuePair("username", schoolNumber));
        para.add(new BasicNameValuePair("pwd", signed_pwd));
        para.add(new BasicNameValuePair("sign", Long.toString(sign)));
        //设置参数
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(para));
        } catch (UnsupportedEncodingException e) {
            System.out.println("登录设参数失败！");
            e.printStackTrace();
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String html = stream2String(entity.getContent());
            List<Cookie> cookies = cookieStore.getCookies();  //响应成功时，cookieStore里面已经存好cookies了
            //设置返回结果
            if (html.startsWith("0"))
                flag=true;
            else
                flag=false;
        } catch (ClientProtocolException e) {
            System.out.println("连接超时！");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }


    //输入流转字符串
    private static String stream2String(InputStream in){
        String CODING = "utf8";	//默认编码utf8
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {

            br = new BufferedReader(new InputStreamReader(in, CODING));
            String line = null;
            while(null!=(line = br.readLine())){
                sb.append(line+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    //根据cookie的名字，从所有的cookie中找到值。
    private static String getValueByName(List<Cookie> cookies, String name){
        String value = null;
        for(Cookie c:cookies){
            if(name.equals(c.getName())){
                value = c.getValue();
                break;
            }
        }
        return value;
    }
}
