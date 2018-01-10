package com.edu.bjfu.cs2015.ibasketball;

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

import com.edu.bjfu.cs2015.ibasketball.UI.SearchView;
import com.edu.bjfu.cs2015.ibasketball.adapter.ListGamesAdapter;
import com.edu.bjfu.cs2015.ibasketball.tool.HttpConnection;

import java.util.List;

import JSONPO.Gameinfo;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class FragmentGames extends Fragment implements SearchView.SearchViewListener {
    private RecyclerView mRecyclerView;     //赛事列表
    private RecyclerView.LayoutManager mLayoutManager;
    private ListGamesAdapter mGameInfoAdapter;      //赛事列表Adapter
    private List<Gameinfo> mGameInfoList;  //所有赛事数据
    private String infoMessage;


    //设置提示框显示数据项的个数
//    public static void setHintCount(int hintCount){
//        FragmentSousuo.mHintCount = hintCount;
//    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_games, container, false);

        //初始化数据
        mGameInfoList = null;


        //RecyclerView的初始化
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL); //单列瀑布流

        //设置LayoutMananger
        mRecyclerView.setLayoutManager(mLayoutManager);
        //设置item的动画，可以不设置
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // TODO Post Response操作获取所有赛事
        mGameInfoList = listAll();
        mGameInfoAdapter = new ListGamesAdapter(mGameInfoList, getActivity());


        //设置Adapter
        mRecyclerView.setAdapter(mGameInfoAdapter);

        return view;
    }

    public List<Gameinfo> listAll() throws InterruptedException {

        // 传入参数
        List<Gameinfo> gameInfoList = null;
        infoMessage = "";

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Post Response操作获取所有新闻
                Action listAllAction = new listAllAction("game");
                HttpConnection.execute(listAllAction);
                String reponse = HttpConnection.getResponse();
                infoMessage = HttpConnection.get ??;
                gameInfoList = ???
            }
        });
        t1.start();
        t1.join();

        if(gameInfoList==null){
            Toast.makeText(getActivity(), infoMessage, Toast.LENGTH_SHORT).show();
            return null;
        }else{
            return gameInfoList;
        }
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
