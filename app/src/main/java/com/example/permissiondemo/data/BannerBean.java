package com.example.permissiondemo.data;


import java.util.List;

public class BannerBean<B> {


    private List<BannerInfo> data;
    private int errorCode;
    private String errorMsg;



    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<BannerInfo> getData() {
        return data;
    }

    public void setData(List<BannerInfo> data) {
        this.data = data;
    }
}
