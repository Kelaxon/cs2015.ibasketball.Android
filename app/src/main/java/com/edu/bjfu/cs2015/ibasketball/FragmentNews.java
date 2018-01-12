package com.edu.bjfu.cs2015.ibasketball;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.edu.bjfu.cs2015.ibasketball.adapter.ListNewsinfoAdapter;
import com.edu.bjfu.cs2015.ibasketball.tool.HttpConnection;
import com.edu.bjfu.cs2015.ibasketball.tool.JsonToInstance;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Action.Action;
import Action.ListAllAction;
import Action.ServerCallback;
import JSONPO.Newsinfo;

/**
 * Created by ChrisYoung on 2017/12/27.
 */

public class FragmentNews extends Fragment {
    private RecyclerView mRecyclerView;     //新闻列表
    private RecyclerView.LayoutManager mLayoutManager;
    private ListNewsinfoAdapter mNewsInfoAdapter;      //新闻列表Adapter
    private List<Newsinfo> mNewsInfoList;  //所有新闻数据
    private String infoMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        //RecyclerView的初始化
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL); //单列瀑布流

        //设置LayoutMananger
        mRecyclerView.setLayoutManager(mLayoutManager);
        //设置item的动画，可以不设置
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取数据
                mNewsInfoList = listAll();

                mNewsInfoAdapter = new ListNewsinfoAdapter(mNewsInfoList, getActivity());


                //设置Adapter
                mRecyclerView.setAdapter(mNewsInfoAdapter);
                mNewsInfoAdapter.setOnRecyclerViewItemClickListener(new ListNewsinfoAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(View view, Newsinfo newsinfo) {
                        int newsId = newsinfo.getNewsId();
                        Intent i = new Intent(getActivity(), DetailNewsActivity.class);
                        i.putExtra("newsId", newsId);
                        startActivity(i);
                    }
                });
            }
        });



        return view;
    }

    List<Newsinfo> newsInfoList = new ArrayList<>();

    //why  ?
    //how to debug?!
    public List<Newsinfo> listAll() {

        infoMessage = "";

        // TODO Post Response操作获取所有新闻 modify by molinli
        Action listAllAction = new ListAllAction("news");
        //获取到当前contenxt
        listAllAction.setContext(getContext());
        //请你指定http请求参数
        //申请http
        HttpConnection.execute(listAllAction, null, new ServerCallback() {

            @Override
            public void onSuccess(JSONObject reponse) {

                //处理response
                if (reponse != null) {
                    Log.e("LogJson2", reponse + "");

                    JsonToInstance<List<Newsinfo>> jsonToInstance = new JsonToInstance();
                    //get类型
                    Type typeForParam = new TypeToken<List<Newsinfo>>() {}.getType();
                    //传入去掉头部的json String 进行解析
                    try {
                        newsInfoList = jsonToInstance.ToInstance(reponse.getString("userinfo").toString(), typeForParam);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (newsInfoList != null) {
                    Log.e("LogJson2", reponse + ""+"newsInfoList is null!");

                } else {
                    // 无错情况
                    Log.e("newsInfoList", newsInfoList.toString()+"");
                }
            }

        });

        // getNews不需要参数

        //获取响应 json文件
//        String reponse = HttpConnection.getResponse();
//
//        JsonToInstance<List<Newsinfo>> jsonToInstance = new JsonToInstance();
//        //get类型
//        Type typeForParam = new TypeToken<List<Newsinfo>>() {
//        }.getType();

        //这个变量的作用域要修改一下
//        newsInfoList.set(0, (Newsinfo) jsonToInstance.ToInstance(reponse, typeForParam));

        // 传出参数
        if (newsInfoList.get(0) == null) {
            Toast.makeText(getActivity(), infoMessage, Toast.LENGTH_SHORT).show();
            return null;
        } else {
            return newsInfoList;
        }



    }

}


