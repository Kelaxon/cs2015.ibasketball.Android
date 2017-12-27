package com.edu.bjfu.cs2015.ibasketball;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.itheima.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ChrisYoung on 2017/12/26.
 */

public class FragmentGames extends Fragment implements SearchView.SearchViewListener {

    private RecyclerView mRecyclerView;     //搜索结果列表
    private SearchView mSearchView;         //搜索view
    private AdapterHintList mTipsHintAdapter;      //推荐Adapter
    private ArrayAdapter<String> mAutoCompleteAdapter;  //自动补全Adapter
    private AdapterClassList mResultAdapter;            //搜索结果Adapter
    private List<AVObject> mCourseList;                 //搜索结果数据
    private List<AVObject> mHintData;                   //输入推荐数据
    private List<String> mAutoCompleteData;             //自动补全数据

    private static int DEFAULT_HINT_COUNT = 5;          //输入提示框显示数据项默认个数
    private static int mHintCount = DEFAULT_HINT_COUNT;

    private List<AVObject> l = new ArrayList<>();
    private List<String> autocompleteText = new ArrayList<>();




    //设置提示框显示数据项的个数
    public static void setHintCount(int hintCount){
        FragmentSousuo.mHintCount = hintCount;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sousuo, container ,false);
        initData();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_list_search);
        mRecyclerView.setHasFixedSize(true);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        mSearchView = (SearchView) view.findViewById(R.id.searview_sousuo);
        //设置监听
        mSearchView.setSearchViewListener(this);
        //设置adapter
        mSearchView.setmTipsHintAdapter(mTipsHintAdapter);
        mSearchView.setmAutoCompleteAdapter(mAutoCompleteAdapter);
        //设置搜索结果recyclerview点击监听
        mResultAdapter.setOnRecyclerViewItemClickListener(new AdapterClassList.OnRecyclerViewItemClickListener(){
            @Override
            public void onItemClick(View view, AVObject avObject) {
                String id = avObject.getObjectId();
                Intent i = new Intent(getActivity(), ActivityDetail.class);
                i.putExtra("ParkID", id);
                startActivity(i);
            }
        });

//        //获取Include里的
//        View hr1 = view.findViewById(R.id.item_horizontal_recommand);
//        cvOne1 = (CardView) hr1.findViewById(R.id.card_view1);
//        cvOne1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mafengwo.cn/poi/3474.html"));
//                startActivity(browserIntent);
//            }
//        });
//
//        View hr2 = view.findViewById(R.id.item_horizontal_recommand2);
//        cvOne2 = (CardView) hr2.findViewById(R.id.card_view3);
//        cvOne2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mafengwo.cn/poi/77946.html"));
//                startActivity(browserIntent);
//            }
//        });

        return view;
    }


    //初始化数据
    private void initData(){
        //初始化推荐输入数据
        getmHintData();
        //初始化自动补全数据
//        getmAutoCompleteData(null)
        getRecommandData();
        //初始化搜索结果数据
        getmCourseList(null);
    }

    public void getRecommandData() {

        AVQuery<AVObject> avQuery = new AVQuery<>("recommond");
        avQuery.limit(7); // 每次只取7个
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    // 上面的推荐数据
                    Collections.shuffle(list); //打乱顺序

                    //上方推荐
                    final AVObject av1 = list.get(0);
                    View hr1 = getView().findViewById(R.id.item_horizontal_recommand);
                    //设置图片
                    Picasso.with(getContext()).load(av1.getAVFile("image") == null ? "NO PICTURE" : av1.getAVFile("image").getUrl()).into((RoundedImageView) hr1.findViewById(R.id.pic1));
                    //设置公园名字
                    ((TextView) hr1.findViewById(R.id.title1)).setText(av1.getString("parkname"));
                    //设置公园介绍
                    ((TextView) hr1.findViewById(R.id.discription1)).setText(av1.getString("description"));
                    //设置点击事件
                    (hr1.findViewById(R.id.card_view1)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(av1.getString("url")));
                            startActivity(browserIntent);
                        }
                    });

                    final AVObject av2 = list.get(1);
                    //设置图片
                    Picasso.with(getContext()).load(av2.getAVFile("image") == null ? "NO PICTURE" : av2.getAVFile("image").getUrl()).into((RoundedImageView) hr1.findViewById(R.id.pic2));
                    //设置公园名字
                    ((TextView) hr1.findViewById(R.id.title2)).setText(av2.getString("parkname"));
                    //设置公园介绍
                    ((TextView) hr1.findViewById(R.id.discription2)).setText(av2.getString("description"));
                    //设置点击事件
                    (hr1.findViewById(R.id.card_view2)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(av2.getString("url")));
                            startActivity(browserIntent);
                        }
                    });

                    final AVObject av3 = list.get(2);
                    //设置图片
                    Picasso.with(getContext()).load(av3.getAVFile("image") == null ? "NO PICTURE" : av3.getAVFile("image").getUrl()).into((RoundedImageView) hr1.findViewById(R.id.pic3));
                    //设置公园名字
                    ((TextView) hr1.findViewById(R.id.title3)).setText(av3.getString("parkname"));
                    //设置公园介绍
                    ((TextView) hr1.findViewById(R.id.discription3)).setText(av3.getString("description"));
                    //设置点击事件
                    (hr1.findViewById(R.id.card_view3)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(av3.getString("url")));
                            startActivity(browserIntent);
                        }
                    });

                    final AVObject av4 = list.get(3);
                    //设置图片
                    Picasso.with(getContext()).load(av4.getAVFile("image") == null ? "NO PICTURE" : av4.getAVFile("image").getUrl()).into((RoundedImageView) hr1.findViewById(R.id.pic4));
                    //设置公园名字
                    ((TextView) hr1.findViewById(R.id.title4)).setText(av4.getString("parkname"));
                    //设置公园介绍
                    ((TextView) hr1.findViewById(R.id.discription4)).setText(av4.getString("description"));
                    //设置点击事件
                    (hr1.findViewById(R.id.card_view4)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(av4.getString("url")));
                            startActivity(browserIntent);
                        }
                    });

                    //下方推荐
                    final AVObject av5 = list.get(4);
                    View hr2 = getView().findViewById(R.id.item_horizontal_recommand2);
                    //设置图片
                    Picasso.with(getContext()).load(av5.getAVFile("image") == null ? "NO PICTURE" : av5.getAVFile("image").getUrl()).into((RoundedImageView) hr2.findViewById(R.id.pic1));
                    //设置公园名字
                    ((TextView) hr2.findViewById(R.id.name1)).setText(av5.getString("parkname"));
                    //设置公园一句话
                    ((TextView) hr2.findViewById(R.id.title1)).setText(av5.getString("title"));

                    //设置公园介绍
                    ((TextView) hr2.findViewById(R.id.description1)).setText(av5.getString("description"));
                    //设置点击事件
                    (hr2.findViewById(R.id.card_view1)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(av5.getString("url")));
                            startActivity(browserIntent);
                        }
                    });

                    final AVObject av6 = list.get(5);
                    //设置图片
                    Picasso.with(getContext()).load(av6.getAVFile("image") == null ? "NO PICTURE" : av6.getAVFile("image").getUrl()).into((RoundedImageView) hr2.findViewById(R.id.pic2));
                    //设置公园名字
                    ((TextView) hr2.findViewById(R.id.name2)).setText(av6.getString("parkname"));
                    //设置公园一句话
                    ((TextView) hr2.findViewById(R.id.title2)).setText(av6.getString("title"));
                    //设置公园介绍
                    ((TextView) hr2.findViewById(R.id.description2)).setText(av6.getString("description"));
                    //设置点击事件
                    (hr2.findViewById(R.id.card_view2)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(av6.getString("url")));
                            startActivity(browserIntent);
                        }
                    });

                    final AVObject av7 = list.get(6);
                    //设置图片
                    Picasso.with(getContext()).load(av7.getAVFile("image") == null ? "NO PICTURE" : av7.getAVFile("image").getUrl()).into((RoundedImageView) hr2.findViewById(R.id.pic3));
                    //设置公园名字
                    ((TextView) hr2.findViewById(R.id.name3)).setText(av7.getString("parkname"));
                    //设置公园一句话
                    ((TextView) hr2.findViewById(R.id.title3)).setText(av7.getString("title"));
                    //设置公园介绍
                    ((TextView) hr2.findViewById(R.id.description3)).setText(av7.getString("description"));
                    //设置点击事件
                    (hr2.findViewById(R.id.card_view3)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(av7.getString("url")));
                            startActivity(browserIntent);
                        }
                    });


                } else {
                    Log.e("@@@", e.toString());
                }
            }
        });

    }


    private void getmHintData(){
        mHintData = new ArrayList<>();
        AVQuery<AVObject> avQuery = new AVQuery<>("Information");
        avQuery.orderByDescending("updatedAt");
        avQuery.limit(mHintCount);      //限定查询个数
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    mHintData.addAll(list);
                    mTipsHintAdapter.notifyDataSetChanged();
                } else {
                    e.printStackTrace();
                }
            }
        });

        mTipsHintAdapter = new AdapterHintList(getContext(), R.layout.item_list_searchhint, mHintData);
    }

//    private void getmAutoCompleteData(String text){
//        if (mAutoCompleteData == null){
//            mAutoCompleteData = new ArrayList<>(mHintCount);
//        } else {
//            mAutoCompleteData.clear();
//            AVQuery<AVObject> avQuery = new AVQuery<>("Course");
//            avQuery.whereContains("CourseName", text.trim());
//            avQuery.orderByDescending("createdAt");
//            avQuery.limit(mHintCount);
//            avQuery.findInBackground(new FindCallback<AVObject>() {
//                @Override
//                public void done(List<AVObject> list, AVException e) {
//                    if (e == null) {
//                        l.addAll(list);
//                        mAutoCompleteAdapter.notifyDataSetChanged();
//                    } else {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            mAutoCompleteData.add()
//        }
//    }

    //获取搜索结果和adapter
    private void getmCourseList(String text){
            if (mCourseList == null) {
                mCourseList = new ArrayList<>();
            }else {
                mCourseList.clear();
                AVQuery<AVObject> avQuery = new AVQuery<>("Information");
                avQuery.whereContains("pname", text.trim());
                avQuery.orderByDescending("createdAt");
                avQuery.findInBackground(new FindCallback<AVObject>() {
                    @Override
                    public void done(List<AVObject> list, AVException e) {
                        if (e == null) {
                            mCourseList.addAll(list);
                            mResultAdapter.notifyDataSetChanged();
                        } else {
                            e.printStackTrace();
                        }
                    }
                });
//////////////////////////
//                AVQuery<AVObject> avQuery1 = new AVQuery<>("flowerType");
//                avQuery1.whereContains("pname", text.trim());
//                avQuery1.orderByDescending("createdAt");
//                avQuery1.findInBackground(new FindCallback<AVObject>() {
//                    @Override
//                    public void done(List<AVObject> list, AVException e) {
//                        if (e == null) {
//                            mCourseList.addAll(list);
//                            mResultAdapter.notifyDataSetChanged();
//                        } else {
//                            e.printStackTrace();
//                        }
//                    }
//                });
                /////////////////////////////
            }
        if (mResultAdapter == null ){
            mResultAdapter = new AdapterClassList(mCourseList, getContext());
        } else{
            mResultAdapter.notifyDataSetChanged();
        }
    }

    //搜索

    @Override
    public void onRefreshAutoComplete(String text) {

    }

    @Override
    public void onSearch(String text) {
        getmCourseList(text);
        mRecyclerView.setVisibility(View.VISIBLE);
        if (mRecyclerView.getAdapter() == null) {
            mRecyclerView.setAdapter(mResultAdapter);
        } else {
            mResultAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onListItemClick(String text) {

    }
}
