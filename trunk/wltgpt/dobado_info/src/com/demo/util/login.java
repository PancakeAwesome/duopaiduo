package com.demo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class login {
		public static void main(String[] args) {
			try {
				System.out.println(sendSms(null, null));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		private static final String KUAIDI_URL="http://www.dobado.cn/register/register_in";
		public static String sendSms(String com, String nu) throws IOException {  
		    Map<String, String> params = new HashMap<String, String>(); 
		    params.put("userphone", "15715194991");  
		    params.put("phone_code", "194991"); 
		    params.put("password", "15715194991"); 
		    params.put("password2", "15715194991");
		    params.put("password2", "15715194991"); 
		    return post(KUAIDI_URL, params);  
		}  
		  
		/** 
		* 基于HttpClient 4.3的通用POST方法 
		* 
		* @param url       提交的URL 
		* @param paramsMap 提交<参数，值>Map 
		* @return 提交响应 
		*/  
		  
		public static String post(String url, Map<String, String> paramsMap) {  
		    CloseableHttpClient client = HttpClients.createDefault();  
		    String responseText = "";  
		    CloseableHttpResponse response = null;  
		    try {  
		        HttpPost method = new HttpPost(url);  
		        if (paramsMap != null) {  
		            List<NameValuePair> paramList = new ArrayList<NameValuePair>();  
		            for (Map.Entry<String, String> param : paramsMap.entrySet()) {  
		                NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());  
		                paramList.add(pair);  
		            }  
		            method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));  
		        }  
		        response = client.execute(method);  
		        HttpEntity entity = response.getEntity();  
		        if (entity != null) {  
		            responseText = EntityUtils.toString(entity);  
		        }  
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    } finally {  
		        try {  
		            response.close();  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }  
		    }                                                                                                                                                                                                                                                                                                                       System.out.println(responseText);//此处打印在console后，会给出一个IP地址  
		        return responseText;  
		    }  
		
}
