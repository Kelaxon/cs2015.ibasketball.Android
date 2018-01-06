package com.edu.bjfu.cs2015.ibasketball.tool;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.edu.bjfu.cs2015.ibasketball.R;
import com.edu.bjfu.cs2015.ibasketball.TestActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by 莫林立 on 2018/1/6.
 * Json 读取本地文件
 */

public class JsonToString {

    /*
  读取文件函数 需要当前activity的resource
   */
    @NonNull
    public  static  String JsonToString(Resources resources) {
        String jsonStr="";
        //从resource的raw中读取文件数据
//        Resources resources=TestActivity.this.getResources();
        try{
            InputStream is=resources.openRawResource(R.raw.useractionapp);

            InputStreamReader isr=new InputStreamReader(is,"UTF-8");

            BufferedReader br=new BufferedReader(isr);

            String str="";

            StringBuffer sb=new StringBuffer();
            while ((str=br.readLine())!=null){
                sb.append(str);
                sb.append('\n');
            }
            jsonStr=sb.toString();
            br.close();
            isr.close();
            is.close();
        }catch (Exception e){

            e.printStackTrace();
        }
        return jsonStr;
    }
}
