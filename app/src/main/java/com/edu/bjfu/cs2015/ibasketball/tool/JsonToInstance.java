package com.edu.bjfu.cs2015.ibasketball.tool;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by 莫林立 on 2018/1/6.
 *      JsonToInstance泛型类
 */
public class JsonToInstance<T> {

    //泛型方法 Json文件 把Type当作参数传入
    public T JsonToInstance(String JsonString, Type type){

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        T t = gson.fromJson(JsonString, type);

        return t;
    }

}
