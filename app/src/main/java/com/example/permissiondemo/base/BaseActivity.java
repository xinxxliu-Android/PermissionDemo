package com.example.permissiondemo.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {
    public BaseActivity context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //7.0只用NoTitle无法去掉标题栏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        context = this;
        initView();
        initData();
    }

    /**
     * 获取布局Id
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 查找布局控件
     *
     */

    public abstract void initView();

    /**
     * 获取数据
     *
     * @return
     */

    public abstract void initData();

}
