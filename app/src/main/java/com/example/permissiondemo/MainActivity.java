package com.example.permissiondemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.permissiondemo.fragment.HomeFragment;
import com.example.permissiondemo.fragment.MineFragment;
import com.example.permissiondemo.fragment.MumicFragment;
import com.example.permissiondemo.fragment.VideoFragment;
import com.example.permissiondemo.util.ThreadUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final int EXIT_AFFIRM_INTERVAL = 2000;
    private BottomNavigationViewEx mBottomNavigationViewEx;
    private static final int DEFAULT_SELECTED_MENU_ITEM_POSITION = 0;
    private Fragment mCurrentShownFragment;
    private int mCurrentMenuItemPosition;
    private SparseArray<Fragment> mMenuItemPagerMapping = new SparseArray<>();
    private boolean mExitflag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= flagTranslucentStatus | flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
        initView();
        initListeners();

    }

    private void initListeners() {
        mBottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switchFragment(menuItem.getItemId());
                return true;
            }
        });
    }

    private void initView() {
        mBottomNavigationViewEx = findViewById(R.id.bottom_navigation);
        mBottomNavigationViewEx.enableAnimation(false);
        mBottomNavigationViewEx.enableShiftingMode(false);
        mBottomNavigationViewEx.enableItemShiftingMode(false);
        mBottomNavigationViewEx.setItemIconTintList(null);
        mBottomNavigationViewEx.setTypeface(null, Typeface.BOLD);
        mBottomNavigationViewEx.setCurrentItem(DEFAULT_SELECTED_MENU_ITEM_POSITION);
//        如果不调用此方法 那么首次第一个fragment不显示
        switchFragment(mBottomNavigationViewEx.getMenu().getItem(DEFAULT_SELECTED_MENU_ITEM_POSITION).getItemId());

    }

    private void switchFragment(int menuItemId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment targetFragment;
        int targetMenuItemPosition = mBottomNavigationViewEx.getMenuItemPosition(mBottomNavigationViewEx.getMenu().findItem(menuItemId));
        if (mMenuItemPagerMapping.indexOfKey(menuItemId) < 0) {
            targetFragment = createFragmentBasedOnMenuItemId(menuItemId);
            transaction.add(R.id.content_page_container, targetFragment);
        } else {
            targetFragment = mMenuItemPagerMapping.get(menuItemId);
        }
        if (mCurrentShownFragment == targetFragment) {
            return;
        }
        if (mCurrentShownFragment != null) {
            if (mCurrentMenuItemPosition < targetMenuItemPosition) {
                transaction.setCustomAnimations(R.anim.page_slide_right_in, R.anim.page_slide_left_out, 0, 0);
            } else {
                transaction.setCustomAnimations(R.anim.page_slide_left_in, R.anim.page_slide_right_out, 0, 0);
            }
            mCurrentShownFragment.setUserVisibleHint(false);
            transaction.hide(mCurrentShownFragment);
        }
        targetFragment.setUserVisibleHint(true);
        transaction.show(targetFragment);
        mCurrentMenuItemPosition = targetMenuItemPosition;
        mCurrentShownFragment = targetFragment;
        mMenuItemPagerMapping.put(menuItemId, targetFragment);
        transaction.commit();

    }

    private Fragment createFragmentBasedOnMenuItemId(int menuItemId) {
        Fragment fragment = null;
        switch (menuItemId) {
            case R.id.home:
                fragment = new HomeFragment();
                break;
            case R.id.video:
                fragment = new VideoFragment();
                break;
            case R.id.mumic:
                fragment = new MumicFragment();
                break;
            case R.id.mine:
                fragment = new MineFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public void onBackPressed() {
        if (mExitflag) {
            finish();
        } else {
            Toast.makeText(this, "再按一次退出本App", Toast.LENGTH_SHORT).show();
            mExitflag = true;
            ThreadUtil.mainHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mExitflag = false;
                }
            }, EXIT_AFFIRM_INTERVAL);
        }
    }
}