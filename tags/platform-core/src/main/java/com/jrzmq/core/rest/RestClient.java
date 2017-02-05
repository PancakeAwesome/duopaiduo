package com.jrzmq.core.rest;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.cookie.Cookie;

import com.jrzmq.core.utils.Base64Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RestClient {
    private String serverUrl;
    private String httpMethod;
    private String clientData;
    private final int DEFAULT_TIMEOUT = 20;
    private int connectTimeout;
    private int socketTimeout;
    private String sign;
    private String contentType = "application/json; charset=utf-8";
    
    private java.util.List<Cookie> cookies;

    public RestClient(String url, String method, String data) {
        this.serverUrl = url;
        this.httpMethod = method;
        this.clientData = Base64Util.encodeBase64(data);
        this.connectTimeout = this.DEFAULT_TIMEOUT;
        this.socketTimeout = this.DEFAULT_TIMEOUT;
    }

    public RestClient(String url) {
        this.serverUrl = url;
        this.httpMethod = "GET";
        this.clientData = null;
        this.connectTimeout = this.DEFAULT_TIMEOUT;
        this.socketTimeout = this.DEFAULT_TIMEOUT;
    }

    public RestClient(String url, String data) {
        this.serverUrl = url;
        this.httpMethod = "GET";
        this.clientData = Base64Util.encodeBase64(data);
        this.connectTimeout = this.DEFAULT_TIMEOUT;
        this.socketTimeout = this.DEFAULT_TIMEOUT;
    }

    public void setURL(String url) {
        this.serverUrl = url;
    }

    public void setMethod(String method) {
        this.httpMethod = method;
    }

    public void setData(String data) {
        this.clientData = data;
    }
    
    public void setBase64Data(String data) {
        this.clientData = Base64Util.encodeBase64(data);
    }

    public void setConnectTimeout(int connectTimeout) {
        if (0 < connectTimeout) {
            this.connectTimeout = connectTimeout;
        }
    }

    public void setSocketTimeout(int socketTimeout) {
        if (0 < socketTimeout) {
            this.socketTimeout = socketTimeout;
        }
    }

    public void setSign(String sign) {
		this.sign = sign;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public java.util.List<Cookie> getCookies(){
		return cookies;
	}

	public String execute() throws RestException {
        if (null == this.serverUrl) {
            return null;
        }
        String ret = this.httpExecute();
        return ret;
    }

    private DefaultHttpClient getHttpClient() {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        //    httpClient.getParams().setParameter("http.protocol.content-charset", "utf-8");
        if (0 < this.connectTimeout) {
            httpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(this.connectTimeout * 1000));
        }
        if (0 < this.socketTimeout) {
            httpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(this.socketTimeout * 1000));
        }
        return httpClient;
    }

    private String getURLWithData() throws UnsupportedEncodingException {
        if (this.clientData != null) {
            if (this.serverUrl.endsWith("?")) {
                return this.serverUrl + this.clientData;
            }
            return this.serverUrl + "?" + this.clientData;
        }
        return this.serverUrl;
    }

    private String httpExecute() throws RestException {
        String ret = null;
        DefaultHttpClient httpclient = this.getHttpClient();
        try {
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            if (this.httpMethod.equalsIgnoreCase("GET")) {
                HttpGet req = new HttpGet(this.getURLWithData());
                req.setHeader("Content-Type", contentType);
                if (null != sign)
                    req.setHeader("sign", sign);
                ret = httpclient.execute(req, responseHandler);
            } else if (this.httpMethod.equalsIgnoreCase("POST")) {
                HttpPost req = new HttpPost(this.serverUrl);
                req.setHeader("Content-Type", contentType);
                req.setHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
                if (null != sign)
                    req.setHeader("sign", sign);
                if (null != this.clientData) {
                    req.setEntity(new StringEntity(this.clientData, "UTF-8"));
                }
                ret = httpclient.execute(req, responseHandler);
            } else if (this.httpMethod.equalsIgnoreCase("PUT")) {
                HttpPut req = new HttpPut(this.serverUrl);
                req.setHeader("Content-Type", contentType);
                if (null != this.clientData) {
                    req.setEntity(new StringEntity(this.clientData, "UTF-8"));
                }
                ret = httpclient.execute(req, responseHandler);
            } else if (this.httpMethod.equalsIgnoreCase("DELETE")) {
                HttpDelete req = new HttpDelete(this.getURLWithData());
                req.setHeader("Content-Type", contentType);
                ret = httpclient.execute(req, responseHandler);
            }
            cookies = httpclient.getCookieStore().getCookies();
        } catch (ClientProtocolException e) {
            throw new RestException(e.getMessage(), e.getCause());
        } catch (IOException e) {
            throw new RestException(e.getMessage(), e.getCause());
        } catch (Exception e) {
            throw new RestException(e.getMessage(), e.getCause());
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return ret;
    }
}
