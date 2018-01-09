package com.edu.bjfu.cs2015.ibasketball;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.edu.bjfu.cs2015.ibasketball.R;
import com.edu.bjfu.cs2015.ibasketball.tool.MySingleton;

import org.json.JSONObject;


/**
 * Created by 莫林立 on 2018/1/9.
 *
 */

public class GoodsCategoryFragment extends Fragment {

    private TextView textView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentGoodscategoryView = inflater.inflate(R.layout.fragment_goodscategory, null);

        textView = (TextView) fragmentGoodscategoryView.findViewById(R.id.textview);

        String requsetUrl = "http://apis.baidu.com/apistore/weatherservice/citylist";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(requsetUrl, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                textView.setText("The json data is :" + jsonObject.toString());

            }
        },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(getActivity(), "获取JSON数据失败", Toast.LENGTH_SHORT).show();
                textView.setText("Json request faileed, The volleyError is :" + volleyError);

            }
        });

        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);

        return fragmentGoodscategoryView;
    }


}
