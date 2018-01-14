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

import com.edu.bjfu.cs2015.ibasketball.adapter.ListNewsinfoAdapter;
import com.edu.bjfu.cs2015.ibasketball.tool.HttpConnection;
import com.edu.bjfu.cs2015.ibasketball.tool.JsonToInstance;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
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
                listAll();

            }
        }).start();


        return view;
    }


    public void listAll() {

        infoMessage = "";

        // TODO Post Response操作获取所有新闻 modify by molinli
        Action listAllAction = new ListAllAction("news");
        //获取到当前contenxt
        listAllAction.setContext(getContext());
        //请你指定http请求参数
        //申请http
        HttpConnection.execute(listAllAction, new HashMap(), new ServerCallback() {
            @Override
            public void onSuccess(JsonObject reponse) {
                List<Newsinfo> newsInfoList = new ArrayList<>();
                //处理response
                if (reponse != null) {
                    Log.e("LogJson2", reponse + "");

                    JsonToInstance<List<Newsinfo>> jsonToInstance = new JsonToInstance();
                    //get类型
                    Type typeForParam = new TypeToken<List<Newsinfo>>() {
                    }.getType();
                    //传入去掉头部的json String 进行解析
                    newsInfoList = jsonToInstance.ToInstance(reponse.get("newsInfoList").toString(), typeForParam);

                }

                if (newsInfoList != null) {
                    mNewsInfoAdapter = new ListNewsinfoAdapter(newsInfoList, getActivity());
                    //设置Adapter
                    mRecyclerView.setAdapter(mNewsInfoAdapter);
                    mNewsInfoAdapter.setOnRecyclerViewItemClickListener(new ListNewsinfoAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, Newsinfo newsinfo) {
//                            int newsId = newsinfo.getNewsId();
                            Intent i = new Intent(getActivity(), DetailNewsActivity.class);
                            i.putExtra("newsinfo", newsinfo);
                            startActivity(i);
                        }
                    });
                }
            }

        });

    }

}
