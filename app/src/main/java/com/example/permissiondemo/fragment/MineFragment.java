package com.example.permissiondemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.permissiondemo.R;
import com.example.permissiondemo.custom.TitleBar;

public class MineFragment extends Fragment {
    private TitleBar mTitleBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTitleBar = view.findViewById(R.id.mine_titlebar);
        mTitleBar.setTitle("我的");
        mTitleBar.setOnBackClickListener(new TitleBar.OnBackClickListener() {
            @Override
            public void onBackClick() {

            }
        });

    }
}
