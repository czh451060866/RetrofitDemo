package com.czh.retrofit.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.czh.retrofit.api.Api;
import com.czh.retrofit.domain.CommentItem;
import com.czh.retrofit.domain.GetWithParamResult;
import com.czh.retrofit.domain.LoginResult;
import com.czh.retrofit.domain.PostFileResult;
import com.czh.retrofit.domain.PostWithParamResult;
import com.czh.retrofit.utils.RetrofitManager;
import com.yl.retrofitdemo.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;

public class RequestActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private static final String TAG = "RequestActivity";
    private Api mApi;
    private Retrofit retrofit;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        retrofit = RetrofitManager.getInstance();
        mApi = retrofit.create(Api.class);
        int permissionResult =  checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permissionResult != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE){

        }
    }

    public void getWithParams(View view){
        Call<GetWithParamResult> task = mApi.getWithParam("我是搜索关键字..", 10, "1");
        task.enqueue(new Callback<GetWithParamResult>() {
            @Override
            public void onResponse(Call<GetWithParamResult> call, Response<GetWithParamResult> response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse----->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<GetWithParamResult> call, Throwable t) {
                Log.d(TAG, "onFailure----->" + t.toString());
            }
        });
    }

    public void getWithParamsMap(View view){
        Map<String, Object> param = new HashMap<>();
        param.put("keyword", "我是搜索关键字..");
        param.put("page", 10);
        param.put("order", "0");
        Call<GetWithParamResult> task = mApi.getWithParamMap(param);
        task.enqueue(new Callback<GetWithParamResult>() {
            @Override
            public void onResponse(Call<GetWithParamResult> call, Response<GetWithParamResult> response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG, "onResponse----->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<GetWithParamResult> call, Throwable t) {
                Log.d(TAG, "onFailure----->" + t.toString());
            }
        });
    }

    public void postWithParams(View view){
        Call<PostWithParamResult> task = mApi.postWithParam("这是我的内容...");
        task.enqueue(new Callback<PostWithParamResult>() {
            @Override
            public void onResponse(Call<PostWithParamResult> call, Response<PostWithParamResult> response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG, "onResponse----->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostWithParamResult> call, Throwable t) {
                Log.d(TAG, "onFailure----->" + t.toString());
            }
        });
    }

    public void postWithUrl(View view){
        String url = "/post/string?string=内容测试内容";
        Call<PostWithParamResult> task = mApi.postWithUrl(url);
        task.enqueue(new Callback<PostWithParamResult>() {
            @Override
            public void onResponse(Call<PostWithParamResult> call, Response<PostWithParamResult> response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG, "onResponse----->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostWithParamResult> call, Throwable t) {
                Log.d(TAG, "onFailure----->" + t.toString());
            }
        });
    }

    public void postWithBody(View view){
        CommentItem commentItem = new CommentItem("23131","你的文章太好了");
        Call<PostWithParamResult> task = mApi.postWithBody(commentItem);
        task.enqueue(new Callback<PostWithParamResult>() {
            @Override
            public void onResponse(Call<PostWithParamResult> call, Response<PostWithParamResult> response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG, "onResponse----->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostWithParamResult> call, Throwable t) {
                Log.d(TAG, "onFailure----->" + t.toString());
            }
        });
    }

    public void postFile(View view){
        MultipartBody.Part part = createPartByTypeAndKey("\"/storage/emulated/0/Download/1.jpeg\"", "file");
        Call<PostFileResult> task = mApi.postFile(part);
        task.enqueue(new Callback<PostFileResult>() {
            @Override
            public void onResponse(Call<PostFileResult> call, Response<PostFileResult> response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG, "onResponse----->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostFileResult> call, Throwable t) {
                Log.d(TAG, "onFailure----->" + t.toString());
            }
        });
    }

    public void postFiles(View view){
        List<MultipartBody.Part> parts = new ArrayList<>();
        MultipartBody.Part partOne = createPartByTypeAndKey("/storage/emulated/0/Download/1.jpeg", "file");
        parts.add(partOne);
        MultipartBody.Part partTwo = createPartByTypeAndKey("/storage/emulated/0/Download/1.jpeg", "file");
        parts.add(partTwo);
        MultipartBody.Part partThree = createPartByTypeAndKey("/storage/emulated/0/Download/1.jpeg", "file");
        parts.add(partThree);
        MultipartBody.Part partFour = createPartByTypeAndKey("/storage/emulated/0/Download/1.jpeg", "file");
        parts.add(partFour);
        Call<PostFileResult> task = mApi.postFiles(parts);
        task.enqueue(new Callback<PostFileResult>() {
            @Override
            public void onResponse(Call<PostFileResult> call, Response<PostFileResult> response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG, "onResponse----->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostFileResult> call, Throwable t) {
                Log.d(TAG, "onFailure----->" + t.toString());
            }
        });
    }

    private MultipartBody.Part createPartByTypeAndKey(String path,String key){
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), requestBody);
        return part;
    }

    public void postFileWithParams(View view){
        MultipartBody.Part part = createPartByTypeAndKey("\"/storage/emulated/0/Download/1.jpeg\"", "file");
        Map<String, String> params = new HashMap<>();
        params.put("description", "这是一张长方形的轮播图，安卓开发路线");
        params.put("isFres", "true");
        Call<PostFileResult> task = mApi.postFileWithParams(part, params);
        task.enqueue(new Callback<PostFileResult>() {
            @Override
            public void onResponse(Call<PostFileResult> call, Response<PostFileResult> response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG, "onResponse----->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostFileResult> call, Throwable t) {
                Log.d(TAG, "onFailure----->" + t.toString());
            }
        });
    }

    public void doLogin(View view){
        Call<LoginResult> task = mApi.doLogin("黄大锤", "123123");
        task.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG, "onResponse----->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.d(TAG, "onFailure----->" + t.toString());
            }
        });
    }

    public void doLoginWithParams(View view){
        Map<String, String> params = new HashMap<>();
        params.put("userName", "黄大锤");
        params.put("passWord", "123123");
        Call<LoginResult> task = mApi.doLoginWithPrams(params);
        task.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG, "onResponse----->" + response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.d(TAG, "onFailure----->" + t.toString());
            }
        });
    }

    public void downloadFile(View view){
        String url = "/download/8";
        Call<ResponseBody> task = mApi.downloadFile(url);
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int code = response.code();
                if(response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG, "onResponse----->" + response.body());
                    //知道文件名称--header里
                    Headers header = response.headers();
                    String fileNameHeader = header.get("Content-disposition");
                    String fileName = "未命名.png";
                    if(fileNameHeader != null){
                        fileName = fileNameHeader.replace("attachment; filename=", "");
                        Log.d(TAG, "fileName--->" + fileName);
                    }
//                    for(int i = 0; i < header.size(); i++){
//                        String key = header.name(i);
//                        String value = header.value(i);
//                        Log.d(TAG, key + "---->" + value);
//                    }
                    //写文件，但是这里非ui线程，不能写
                    writeStream2Disk(response, fileName);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure----->" + t.toString());
            }
        });
    }

    private void writeStream2Disk(final Response<ResponseBody> response, final String fileName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = response.body().byteStream();
                File baseOutputFile = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File outputFile = new File(baseOutputFile, fileName);
                Log.d(TAG, outputFile.getAbsolutePath());
                try{
                    FileOutputStream fos = new FileOutputStream(outputFile);
                    byte[] buffer = new byte[1024];
                    int len;
                    while((len = inputStream.read(buffer)) != -1){
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
