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

import com.edu.bjfu.cs2015.ibasketball.UI.SearchView;
import com.edu.bjfu.cs2015.ibasketball.adapter.ListGamesAdapter;
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
import JSONPO.Gameinfo;
import JSONPO.Newsinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class FragmentGames extends Fragment implements SearchView.SearchViewListener {
    private RecyclerView mRecyclerView;     //赛事列表
    private RecyclerView.LayoutManager mLayoutManager;
    private ListGamesAdapter mGameInfoAdapter;      //赛事列表Adapter
    private List<Gameinfo> mGameInfoList;  //所有赛事数据
    private String infoMessage;
    // 传入参数
    List<Gameinfo> gameInfoList = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_games, container, false);


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
                try {
                    listAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return view;
    }


    public void listAll() throws InterruptedException {

        infoMessage = "";

        Action listAllAction = new ListAllAction("game");
        //获取到当前contenxt
        listAllAction.setContext(getContext());

        HttpConnection.execute(listAllAction, new HashMap(), new ServerCallback() {

            @Override
            public void onSuccess(JsonObject reponse) {
                List<Gameinfo> newsInfoList = new ArrayList<>();

                if (reponse != null) {
                    Log.e("LogJson2", reponse + "");

                    JsonToInstance<List<Gameinfo>> jsonToInstance = new JsonToInstance();

                    Type typeForParam = new TypeToken<List<Gameinfo>>() {
                    }.getType();

                    //传入去掉头部的json String 进行解析
                    gameInfoList = jsonToInstance.ToInstance(reponse.get("gameinfoList").toString(), typeForParam);

                }

                if (gameInfoList != null) {
                    mGameInfoAdapter = new ListGamesAdapter(gameInfoList, getActivity());
                    mRecyclerView.setAdapter(mGameInfoAdapter);
                    mGameInfoAdapter.setOnRecyclerViewItemClickListener(new ListGamesAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        // 有错！！!
                        public void onItemClick(View view, Newsinfo newsinfo) {
                            int newsId = newsinfo.getNewsId();
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            i.putExtra("newsId", newsId);
                            startActivity(i);
                        }
                    });

//            return gameInfoList;
                }

            }
        });


    }


    // 搜索功能用
    @Override
    public void onRefreshAutoComplete(String text) {

    }

    @Override
    public void onSearch(String text) {

    }

    @Override
    public void onListItemClick(String text) {

    }
}