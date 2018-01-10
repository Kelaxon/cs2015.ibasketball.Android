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

import com.edu.bjfu.cs2015.ibasketball.adapter.ListNewsinfoAdapter;
import com.edu.bjfu.cs2015.ibasketball.tool.HttpConnection;

import java.util.List;

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

        // 获取数据
        mNewsInfoList = listAll();
        mNewsInfoAdapter = new ListNewsinfoAdapter(mNewsInfoList,getActivity() );


        //设置Adapter
        mRecyclerView.setAdapter(mNewsInfoAdapter);

        return view;
    }

    public List<Newsinfo> listAll() throws InterruptedException {

        // 传入参数
        List<Newsinfo> newsInfoList = null;
        infoMessage = "";


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                // TODO Post Response操作获取所有新闻
                Action listAllAction = new listAllAction("news");
                HttpConnection.execute(listAllAction);
                String reponse = HttpConnection.getResponse();
                infoMessage = HttpConnection.get ??;
                newsInfoList = ???
            }
        });

        t1.start();
        t1.join();

        // 传出参数
        if(mNewsInfoList==null){
            Toast.makeText(getActivity(), infoMessage, Toast.LENGTH_SHORT).show();
            return null;
        }else{
            return newsInfoList;
        }
    }

}


