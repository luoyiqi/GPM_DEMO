package com.gpm.demo.sdk;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.ByteArrayEntity;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 网络连接的客户端
 * 
 * @author gavinwen
 */
public class WebRestClient {

    private static AsyncHttpClient client = new AsyncHttpClient();
    

    static {
        // 设置默认的超时时间
        client.setTimeout(3000);
    }

    /**
     * get请求
     * 
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void get(String url, RequestParams params,
            AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    /**
     * get请求
     * 
     * @param url
     * @param responseHandler
     */
    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
        client.get(url, null, responseHandler);
    }

    /**
     * post请求
     * 
     * @param url
     * @param params
     * @param responseHandler
     */
    public static void post(String url, RequestParams params,
            AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    /**
     * post请求
     * 
     * @param url
     * @param responseHandler
     */
    public static void post(String url, AsyncHttpResponseHandler responseHandler) {
        client.post(url, null, responseHandler);
    }
    
    
    /**
     * post请求直接传json字符串
     * 
     * @param url
     * @param responseHandler
     */
    public static void post(Context context,  String url, String json,AsyncHttpResponseHandler responseHandler) {
    	
        ByteArrayEntity entity;
		try {
			entity = new ByteArrayEntity(json.getBytes("UTF-8"));
			client.post(context,url, entity, "application/json", responseHandler);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        
    }


}
