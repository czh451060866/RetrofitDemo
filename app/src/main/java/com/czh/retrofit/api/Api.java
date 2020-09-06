package com.czh.retrofit.api;

import com.czh.retrofit.domain.CommentItem;
import com.czh.retrofit.domain.GetWithParamResult;
import com.czh.retrofit.domain.LoginResult;
import com.czh.retrofit.domain.PostFileResult;
import com.czh.retrofit.domain.PostWithParamResult;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Api {
    @GET("/get/text")
    Call<ResponseBody> getJson();

    @GET("/get/param")
    Call<GetWithParamResult> getWithParam(@Query("keyword") String keyword, @Query("page") int page, @Query("order") String order);

    @GET("/get/param")
    Call<GetWithParamResult> getWithParamMap(@QueryMap Map<String, Object> params);

    @POST("/post/string")
    Call<PostWithParamResult> postWithParam(@Query("string") String contents);

    @POST
    Call<PostWithParamResult> postWithUrl(@Url String url);

    @POST("/post/comment")
    Call<PostWithParamResult> postWithBody(@Body CommentItem commentItem);

    @Multipart
    @POST("/file/upload")
    Call<PostFileResult> postFile(@Part MultipartBody.Part part);

    @Headers({"token:sdfjkljadfiosahfjkadsk", "client:android", "version:1.0"})
    @Multipart
    @POST("/file/upload")
    Call<PostFileResult> postFiles(@Part List<MultipartBody.Part> part);

    @Multipart
    @POST("/file/params/upload")
    Call<PostFileResult> postFileWithParams(@Part MultipartBody.Part part, @PartMap Map<String, String> params);

    @FormUrlEncoded
    @POST("/login")
    Call<LoginResult> doLogin(@Field("userName") String userName, @Field("password") String password);

    @FormUrlEncoded
    @POST("/login")
    Call<LoginResult> doLoginWithPrams(@FieldMap Map<String, String> params);

    @GET
    Call<ResponseBody> downloadFile(@Url String url);
}
