package net.betterbing.androidframworkstudy.net;


/**
 * Created by WHY on 2015/5/12 16:46
 * 代表请求结果的基类
 * Copyright (c) 2015年 Beijing Yunshan Information Technology Co., Ltd. All rights reserved.
 */

public class BaseResult<T> {
    private int ret;
    private Error error;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "ret=" + ret +
                ", error=" + error +
                ", data=" + data +
                '}';
    }
}
