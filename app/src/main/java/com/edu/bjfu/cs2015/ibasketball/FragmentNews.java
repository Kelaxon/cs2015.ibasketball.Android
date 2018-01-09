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

import com.edu.bjfu.cs2015.ibasketball.adapter.ListNewsinfoAdapter;

import java.util.List;
import JSONPO.Newsinfo;
import mapper.mapperImpl.NewsinfoMapperImpl;
/**
 * Created by ChrisYoung on 2017/12/27.
 */

public class FragmentNews extends Fragment {
    private RecyclerView mRecyclerView;     //新闻列表
    private RecyclerView.LayoutManager mLayoutManager;
    private ListNewsinfoAdapter mResultAdapter;      //新闻列表Adapter
    private List<Newsinfo> mNewsInfoList;  //所有新闻数据


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        //初始化数据
        mNewsInfoList = null;


        //RecyclerView的初始化
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL); //单列瀑布流

        //设置LayoutMananger
        mRecyclerView.setLayoutManager(mLayoutManager);
        //设置item的动画，可以不设置
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        // TODO Post Response操作获取所有新闻
        /*------------------------modify by 莫林立-----------------------*/
        NewsinfoMapperImpl newsinfoMapperImpl=new NewsinfoMapperImpl();

         mResultAdapter = new ListNewsinfoAdapter(newsinfoMapperImpl.findAll(),getActivity() );  //在Fragment中，this要替换成getActivitiy
        /*------------------------modify by 莫林立 end-----------------------*/
        //设置Adapter
        mRecyclerView.setAdapter(mResultAdapter);

        return view;
    }

}




