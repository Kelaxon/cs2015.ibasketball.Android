package com.edu.bjfu.cs2015.ibasketball;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.edu.bjfu.cs2015.ibasketball.adapter.ListNewsinfoAdapter;
import com.edu.bjfu.cs2015.ibasketball.tool.HttpConnection;
import com.edu.bjfu.cs2015.ibasketball.tool.JsonToInstance;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import JSONPO.Newsinfo;
import JSONPO.Userinfo;
import JSONPO.UserinfoMessage;
import JSONPO.Usermessagenew;

import INTERFACE.Action;
import Action.ListAllAction;

/**
 * Created by ChrisYoung on 2017/12/27.
 */

public class FragmentNews extends Fragment {
    private RecyclerView mRecyclerView;     //新闻列表
    private RecyclerView.LayoutManager mLayoutManager;
    private ListNewsinfoAdapter mNewsInfoAdapter;      //新闻列表Adapter
    private List<Newsinfo> mNewsInfoList;  //所有新闻数据
    private String infoMessage;
    // 传入参数
    List<Newsinfo> newsInfoList = null;

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

        return view;
    }



        public List<Newsinfo> listAll () throws InterruptedException {

//            // 传入参数
//             List<Newsinfo> newsInfoList = null;
            infoMessage = "";


            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO Post Response操作获取所有新闻 modify
                    Action listAllAction = new ListAllAction("news");
                    //获取到当前contenxt
                    listAllAction.setContext(getContext());
                    //请你指定http请求参数
                    Map mapInfo=new HashMap();
                    mapInfo.put("key","value");
                    //申请http
                    HttpConnection.execute(listAllAction,mapInfo);
                    //获取响应 json文件
                    String reponse = HttpConnection.getResponse();

//                    infoMessage = HttpConnection.get ??;

                    JsonToInstance<List<Newsinfo>> jsonToInstance = new JsonToInstance();
                    //get类型
                    Type typeForParam=new TypeToken<List<Newsinfo>>(){}.getType();
                    //这个变量的作用域要修改一下
                    newsInfoList =jsonToInstance.ToInstance(reponse,typeForParam);
                }
            });

            t1.start();
            t1.join();

            // 传出参数
            if (newsInfoList.get(0) == null) {
                Toast.makeText(getActivity(), infoMessage, Toast.LENGTH_SHORT).show();
                return null;
            } else {
                return newsInfoList;
            }
        }

    }


