package com.edu.bjfu.cs2015.ibasketball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.edu.bjfu.cs2015.ibasketball.adapter.ListMessageinfoAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import JDBC.Beans.Newsinfo;
import JDBC.Beans.Usermessagenews;

public class DetailActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private ListMessageinfoAdapter mMessageAdapter;
    private List<Usermessagenews> mMessageList = new ArrayList<>();

    // layout的各种View
    TextView mNewsTitleView;
    TextView mNewsTimeView;
    TextView mNewsContentView;
    TextView mAddMessageButton;


    // News的具体信息
    private int mNewsID;
    private Newsinfo mNews;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        // 获取数据
        initData();

        // view 处理页面
        mNewsTitleView = (TextView) findViewById(R.id.tv_news_title);
        mNewsTimeView = (TextView) findViewById(R.id.tv_news_time);
        mNewsContentView = (TextView) findViewById(R.id.tv_news_content);
        mAddMessageButton = (TextView) findViewById(R.id.tv_add_message);

        // 赋值
        mNewsTitleView.setText(mNews.getNewsTitle());
        mNewsTimeView.setText(new SimpleDateFormat(Newsinfo.timeFormat).format(mNews.getNewsTime()));
        mNewsContentView.setText(mNews.getNewsContent());
        mAddMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 增加留言界面
            }
        });

        // 展现留言页面
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mMessageAdapter = new ListMessageinfoAdapter(mMessageList, this);
        mRecyclerView.setAdapter(mMessageAdapter);

    }


    private void initData(){
        mMessageList.clear();
        Intent i = getIntent();
        mNewsID = i.getIntExtra("newsId", -1); // 如果出错会得到-1值

        // TODO 实现getById()
        //mNews = Newsinfo.getById(mNewsID);
        // TODO 实现getAllbyNewsId()
//        if(newsID!=-1)
//            mMessageList = Usermessagenews.getAllbyNewsId(newsID);
        // TODO 可选：实现mMessageList orderByLastTime()

    }


}
