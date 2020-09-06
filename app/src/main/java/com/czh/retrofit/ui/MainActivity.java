package com.czh.retrofit.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.czh.retrofit.adapter.JsonResultListAdapter;
import com.czh.retrofit.api.Api;
import com.czh.retrofit.domain.JsonResult;
import com.google.gson.Gson;
import com.yl.retrofitdemo.R;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 主页
 * Created by yangle on 2017/6/19.
 * <p>
 * Website：http://www.yangle.tech
 * GitHub：https://github.com/alidili
 * CSDN：http://blog.csdn.net/kong_gu_you_lan
 * JianShu：http://www.jianshu.com/u/34ece31cd6eb
 */

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    RecyclerView mRecyclerView;
    JsonResultListAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_czh);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.result_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = 5;
                outRect.top = 5;
            }
        });
        adapter = new JsonResultListAdapter();
        mRecyclerView.setAdapter(adapter);
    }

    public void baseRequest(View view){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.4.140.63:9102")
                .build();

        Api api = retrofit.create(Api.class);
        Call<ResponseBody> task = api.getJson();
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse----->" + response.code());
                if(response.code() == HttpURLConnection.HTTP_OK){
                    try {
//                        Log.d(TAG, "json----->" + response.body().string());
                        String result = response.body().string();
                        Gson gson = new Gson();
                        JsonResult jsonResult = gson.fromJson(result, JsonResult.class);
                        updateList(jsonResult);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure...");
            }
        });
    }

    private void updateList(JsonResult jsonResult) {
        adapter.setData(jsonResult);
    }

}
