package net.betterbing.androidframworkstudy.net;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author：aibb
 * datetime：16/5/12 11:24
 * email：aibingbing@meicai.cn
 */
public abstract class DataCallback<T> implements Callback<BaseResult<T>> {

    int REQUEST_CODE_SUCCESS = 1;
    int REQUEST_CODE_FAILED = -1;

    public static final String ERROR_MSG_SERVICE_ERROR = "服务器错误";
    public static final String ERROR_MSG_NETWORK_ERROR = "网络错误";
    public static final String ERROR_MSG_DEFAULT = "获取数据失败";
    public static final String ERROR_MSG_TIME_OUT = "请求超时，请稍后重试";


    public abstract void success(T t, Response response);

    public abstract void failed(String message, String url);

    @Override
    public void onResponse(Call<BaseResult<T>> call, Response<BaseResult<T>> response) {
        if (response != null) {
            if (!response.isSuccessful()) {
                failed(ERROR_MSG_SERVICE_ERROR, call.request().url().toString());
            } else if (response.body().getRet() == REQUEST_CODE_SUCCESS) {
                success(response.body().getData(), response);
            } else {
                failed(response.body().getError().getMsg(), call.request().url().toString());
            }
        } else {
            failed(ERROR_MSG_DEFAULT, call.request().url().toString());
        }
    }

    @Override
    public void onFailure(Call<BaseResult<T>> call, Throwable t) {
        String msg = ERROR_MSG_NETWORK_ERROR;
        failed(msg, call.request().url().toString());
    }
}
