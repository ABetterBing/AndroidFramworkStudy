package net.betterbing.androidframworkstudy.net;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author：aibb
 * datetime：16/5/12 11:24
 * email：aibingbing@meicai.cn
 */
public class RetrofitUtils {
    /**
     * Retrofit
     */
    private volatile static Retrofit mInstance;

    /**
     * Interceptor
     */
    private static MyOKHttpInterceptor myOKHttpInterceptor = new MyOKHttpInterceptor();

    /**
     * OKHttpClient
     */
    private static OkHttpClient mClient = new OkHttpClient.Builder()
            .addInterceptor(myOKHttpInterceptor)
            .build();

    /**
     * header map
     */
    private static HashMap<String, String> mHeaderMap = new HashMap<>();
    /**
     * 服务器url
     */
    private static String mBaseServiceUrl;

    /**
     * 初始化Retrofit
     *
     * @param baseServiceUrl 服务器Url
     */
    public static void initRetrofit(String baseServiceUrl) {
        mBaseServiceUrl = baseServiceUrl;
    }

    public static void addHeader(Map<String, String> headerMap) {
        mHeaderMap.putAll(headerMap);
        mInstance = null;
    }

    public static void removeHeader(String name) {
        mHeaderMap.remove(name);
        mInstance = null;
    }

    public static void addHeader(String name, String value) {
        mHeaderMap.put(name, value);
        mInstance = null;
    }

    public static Retrofit getRetrofit() {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(mBaseServiceUrl);
                    builder.client(mClient);
                    builder.addConverterFactory(GsonConverterFactory.create());
                    mInstance = builder.build();
                }
            }
        }
        return mInstance;
    }

    public static <T> T create(Class<T> clazz) {
        return getRetrofit().create(clazz);
    }

    private static Request.Builder appendHeaders(Request.Builder builder, Map<String, String> headers) {
        if (builder == null) {
            throw new IllegalArgumentException("builder can not be empty!");
        }

        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty())
            return builder;

        for (String key : headers.keySet()) {
            headerBuilder.add(key, headers.get(key));
        }
        return builder.headers(headerBuilder.build());
    }

    private static class MyOKHttpInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = appendHeaders(request.newBuilder(), mHeaderMap);
            RequestBody body = new RequestBody() {
                @Override
                public MediaType contentType() {
                    return MediaType.parse("application/json");
                }

                @Override
                public void writeTo(BufferedSink sink) throws IOException {
//                    try {
//                        //添加sign验证
//                        JSONObject jsonObject = new JSONObject("{}");
//                        RestUtil.getInstance().tokenize(jsonObject);
//                        String wrapData = jsonObject.toString();
//                        LogUtils.w("meicai_request_pruchase>>>>>" + wrapData);
//                        sink.write(wrapData.getBytes());
//                    } catch (JSONException jsonException) {
//                        LogUtils.e(jsonException.getMessage());
//                    } catch (MCException e) {
//                        e.printStackTrace();
//                    }
                }
            };
            builder.method(request.method(), body);
            request = builder.build();
            return chain.proceed(request);
        }
    }

    public static HashMap<String, String> getHeaderMap() {
        return mHeaderMap;
    }
}
