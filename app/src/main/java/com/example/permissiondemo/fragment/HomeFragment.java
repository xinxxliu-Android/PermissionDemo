package com.example.permissiondemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.permissiondemo.R;
import com.example.permissiondemo.custom.TitleBar;
import com.example.permissiondemo.data.BannerBean;
import com.example.permissiondemo.data.BannerInfo;
import com.example.permissiondemo.data.ServiceApi;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private TitleBar mTitleBar;
    private Banner mBanner;
    BannerBean<BannerInfo> mBody;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiceApi serviceApi = retrofit.create(ServiceApi.class);
        Call<BannerBean<BannerInfo>> banner = serviceApi.getBanner();
        banner.enqueue(new Callback<BannerBean<BannerInfo>>() {
            @Override
            public void onResponse(Call<BannerBean<BannerInfo>> call, Response<BannerBean<BannerInfo>> response) {
                Log.e("TAG", "请求成功" );
                mBody= response.body();
                String imagePath = mBody.getData().get(0).getImagePath();
                Log.e("TAG","===================>"+imagePath);
            }

            @Override
            public void onFailure(Call<BannerBean<BannerInfo>> call, Throwable t) {
                Log.e("TAG", "请求失败" + t.getMessage());
            }
        });
    }

    private void initView(View view) {
        mBanner = view.findViewById(R.id.banner);


    }

}
