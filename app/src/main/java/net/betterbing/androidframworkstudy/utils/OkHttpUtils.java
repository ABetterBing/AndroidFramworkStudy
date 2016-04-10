package net.betterbing.androidframworkstudy.utils;

import android.text.TextUtils;
import android.util.Log;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by aibb on 15/11/7.
 * 与请求有关的工具类
 */
public class OkHttpUtils {

    private static final String TAG = "OkHttpUtils";

    private static OkHttpClient mInstance;

    private static final long DEFAULT_CONNECT_TIME_OUT = 15;

    private OkHttpUtils() {
        mInstance = new OkHttpClient();
        mInstance.setConnectTimeout(DEFAULT_CONNECT_TIME_OUT, TimeUnit.SECONDS);
    }

    private static OkHttpClient getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpClient();
                    //TODO 这里在不需要stetho的时候需要去掉
                    mInstance.networkInterceptors().add(new StethoInterceptor());
                }
            }
        }
        return mInstance;
    }

    public static final ResultCallback<Response> DEFAULT_RESULT_CALLBACK = new ResultCallback<Response>() {
        @Override
        public void onError(Request request, Exception e) {
        }

        @Override
        public void onResponse(Response response) {
        }
    };

    public static void get(String url, final ResultCallback callback) {
        get(url, null, null, null, callback);
    }

    public static void get(String url, Map<String, String> params, final ResultCallback callback) {
        get(url, params, null, null, callback);
    }

    public static void get(String url, Map<String, String> params, String tag, final ResultCallback callback) {
        get(url, params, null, tag, callback);
    }

    public static void get(String url, Map<String, String> params, Map<String, String> headers, String tag, final ResultCallback callback) {
        Request request = buildGetRequest(url, tag, headers, params);
        if (callback != null) {
            callback.onBefore(request);
        } else {
            DEFAULT_RESULT_CALLBACK.onBefore(request);
        }

        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, request.method().toUpperCase() + request.urlString());
        }
        getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (callback != null) {
                    callback.onError(request, e);
                    callback.onAfter();
                } else {
                    DEFAULT_RESULT_CALLBACK.onError(request, e);
                    DEFAULT_RESULT_CALLBACK.onAfter();
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (callback != null) {
                    callback.onResponse(response);
                    callback.onAfter();
                } else {
                    DEFAULT_RESULT_CALLBACK.onResponse(response);
                    DEFAULT_RESULT_CALLBACK.onAfter();
                }
            }
        });
    }

    public static void post(String url, final ResultCallback callback) {
        post(url, null, null, null, callback);
    }

    public static void post(String url, Map<String, String> params, final ResultCallback callback) {
        post(url, params, null, null, callback);
    }

    public static void post(String url, Map<String, String> params, String tag, final ResultCallback callback) {
        post(url, params, null, tag, callback);
    }

    public static void post(String url, Map<String, String> params, Map<String, String> headers, String tag, final ResultCallback callback) {
        Request request = buildPostRequest(url, tag, headers, params);
        if (callback != null) {
            callback.onBefore(request);
        } else {
            DEFAULT_RESULT_CALLBACK.onBefore(request);
        }

        getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (callback != null) {
                    callback.onError(request, e);
                    callback.onAfter();
                } else {
                    DEFAULT_RESULT_CALLBACK.onError(request, e);
                    DEFAULT_RESULT_CALLBACK.onAfter();
                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (callback != null) {
                    callback.onResponse(response);
                    callback.onAfter();
                } else {
                    DEFAULT_RESULT_CALLBACK.onResponse(response);
                    DEFAULT_RESULT_CALLBACK.onAfter();
                }
            }
        });
    }

    public static void cancelRequest(String tag) {
        getInstance().cancel(tag);
    }


    private static Request buildGetRequest(String url, String tag, Map<String, String> headers, Map<String, String> params) {
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url can not be empty!");
        }
        url = appendGetParams(url, params);
        if (TextUtils.isEmpty(tag)) {
            tag = url;
        }
        return buildRequestHeader(headers).url(url).tag(tag).get().build();
    }

    private static Request buildPostRequest(String url, String tag, Map<String, String> headers, Map<String, String> params) {
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url can not be empty!");
        }
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        appendPostParams(formEncodingBuilder, params);
        if (TextUtils.isEmpty(tag)) {
            tag = url;
        }
        return buildRequestHeader(headers).url(url).tag(tag).post(formEncodingBuilder.build()).build();
    }

    private static Request.Builder buildRequestHeader(Map<String, String> headers) {
        Request.Builder builder = new Request.Builder();
        if (headers != null && !headers.isEmpty()) {
            appendHeaders(builder, headers);
        }
        return builder;
    }

    private static void appendHeaders(Request.Builder builder, Map<String, String> headers) {
        if (builder == null) {
            throw new IllegalArgumentException("builder can not be empty!");
        }

        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

    private static String appendGetParams(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url + "?");
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }

        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private static void appendPostParams(FormEncodingBuilder builder, Map<String, String> params) {
        if (builder == null) {
            return;
        }
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }
    }
}