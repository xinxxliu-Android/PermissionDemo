package com.example.permissiondemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private AppCompatButton mBtnPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_permission:
                    checkPermiss();
                    break;
            }
        }
    };

    private void checkPermiss() {

        PermissionX.init(this)
                .permissions(Manifest.permission.CALL_PHONE)
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                        if (allGranted){
                            Toast.makeText(MainActivity.this, "权限申请成功!!!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "权限申请失败。", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initView() {
        mBtnPermission = findViewById(R.id.btn_permission);
        mBtnPermission.setOnClickListener(mOnClickListener);
    }
}