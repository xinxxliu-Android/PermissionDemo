package com.example.permissiondemo.data;



import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceApi {
    @GET("/banner/json")
    Call<BannerBean<BannerInfo>> getBanner();
}
