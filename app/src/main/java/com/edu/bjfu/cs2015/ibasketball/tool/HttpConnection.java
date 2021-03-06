package com.edu.bjfu.cs2015.ibasketball.tool;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;

import Action.ServerCallback;
import Action.Action;
/**
 * Created by 莫林立 on 2018/1/6.
 */

public class HttpConnection {

    private static Map map = null;        //3.请求参数
    private static String url = null;     //1.url地址
    private static String response=null;        //响应
    private static Context context = null;        //2.组件内容

    public static Map getMap() {
        return map;
    }

    public static String getUrl() {
        return url;
    }

    public static JsonObject getResponse() {

        if(response!=null){
         return    new JsonParser().parse(response).getAsJsonObject();
        }else {
            return null;
        }
    }

    public static Context getContext() {
        return context;
    }

    public static void setMap(Map map) {
        HttpConnection.map = map;
    }

    public static void setUrl(String url) {
        HttpConnection.url = url;
    }

    public static void setResponse(String response) {
        HttpConnection.response = response;
    }

    public static void setContext(Context context) {
        HttpConnection.context = context;
    }

    //volleyPost请求 设置
    public static void volleyPost() {

    }

    //获取到url 和 context 提供参数
    public static void execute(Action action, Map mapInfo, final ServerCallback callback) {

        mapInfo.put("UA", "1");
        context = action.getContext();
        map = mapInfo;
        Log.e("url", action.getUrl());


        /**
         * 第一个参数指定了请求方式，第二个参数指定了url，第三个参数指定了正确访问的返回结果，第四个参数是访问失败后的业务逻辑；
         *
         */
        StringRequest request = new StringRequest(Request.Method.POST, action.getUrl(), new Response.Listener<String>() {

            @Override
            public void onResponse(String result) {

                Log.e("tagJson", result+"");

                JsonObject returnData = new JsonParser().parse(result).getAsJsonObject();

                callback.onSuccess(returnData);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError == null)
                    Log.e("tag3", "空");
                else {
                    volleyError.printStackTrace();
                    Log.e("tag3", volleyError.getMessage());
                }
                setResponse("未能请求到数据");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {//在这里封装了需要发送的参数；
                Map<String, String> mapinfo = getMap();
                return mapinfo;
            }
        };
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        Log.e("tagContext",context==null?true+"":false+"");
        Volley.newRequestQueue(context).add(request);


    }

}