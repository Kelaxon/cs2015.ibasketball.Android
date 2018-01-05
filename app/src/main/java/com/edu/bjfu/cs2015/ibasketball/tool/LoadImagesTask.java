package com.edu.bjfu.cs2015.ibasketball.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 装载网络图片工具
 *
 *      使用方法：
 *         在主进程控件里调用 new LoadImagesTask(imageView).execute(Url_str);
 * Created by 莫林立 on 2018/1/4.
 */

public class LoadImagesTask extends AsyncTask<String, Void, Bitmap>
 {
     private ImageView imageView;

     public LoadImagesTask(ImageView imageView)
     { this.imageView = imageView; }

     @Override
     protected Bitmap doInBackground(String... params) {
         URL imageUrl=null;
         Bitmap bitmap=null;
         InputStream inputStream=null;
         try{
             imageUrl=new URL(params[0]);
             HttpURLConnection conn= (HttpURLConnection) imageUrl.openConnection();
             conn.setDoInput(true);
             conn.connect();
             inputStream=conn.getInputStream();
             bitmap= BitmapFactory.decodeStream(inputStream);
             inputStream.close();

         }catch (Exception e){

         }
         return bitmap;
     }

     @Override
     protected void  onPostExecute(Bitmap bitmap) {
         imageView.setImageBitmap(bitmap);
     }
 }
