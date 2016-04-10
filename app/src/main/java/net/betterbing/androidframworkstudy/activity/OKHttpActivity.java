package net.betterbing.androidframworkstudy.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.base.BaseHeadActivity;
import net.betterbing.androidframworkstudy.net.GitHubService;
import net.betterbing.androidframworkstudy.utils.OkHttpUtils;
import net.betterbing.androidframworkstudy.utils.ResultCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by aibb on 16/4/9.
 */
public class OKHttpActivity extends BaseHeadActivity {

    public static final String API_URL = "https://api.github.com";
    StringBuffer stringBuffer = new StringBuffer();

    @Bind(R.id.tv_result)
    TextView mResult;

    @Override
    public int getHeadTitle() {
        return 0;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState, Toolbar toolBar) {
        setTitle("使用OKHttp发起请求");
    }

    @Override
    public int getLayout() {
        return R.layout.activity_okhttp;
    }

    @Override
    public String getTag() {
        return this.getClass().getSimpleName().toString();
    }

    @OnClick(R.id.button_build_request)
    void onButtonClick() {
        String url = "https://api.github.com/repos/square/retrofit/contributors";
        OkHttpUtils.get(url, new ResultCallback<Response>() {
            @Override
            public void onError(Request request, Exception e) {
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Response response) {
                String responseStr = response.body().toString();
                if (response.isSuccessful()) {
                    List<GitHubService.Contributor> contributors = JSON.parseArray(responseStr, GitHubService.Contributor.class);
                    for (GitHubService.Contributor contributor : contributors) {
                        System.out.println(contributor.login + " (" + contributor.contributions + ")");
                        stringBuffer.append(contributor.login + " (" + contributor.contributions + ")" + "\n");
                    }
                    new Handler(getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            mResult.setText(stringBuffer.toString());
                        }
                    });

                }
            }

        });

    }
}