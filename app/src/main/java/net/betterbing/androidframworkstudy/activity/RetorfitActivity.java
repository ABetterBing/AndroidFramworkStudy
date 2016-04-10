package net.betterbing.androidframworkstudy.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.base.BaseHeadActivity;
import net.betterbing.androidframworkstudy.net.GitHubService;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aibb on 16/4/9.
 */
public class RetorfitActivity extends BaseHeadActivity {

    public static final String API_URL = "https://api.github.com";

    @Bind(R.id.tv_result)
    TextView mResult;

    @Override
    public int getHeadTitle() {
        return 0;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState, Toolbar toolBar) {
        setTitle("使用retrofit发起请求");
    }

    @Override
    public int getLayout() {
        return R.layout.activity_retrofit;
    }

    @Override
    public String getTag() {
        return this.getClass().getSimpleName().toString();
    }

    @OnClick(R.id.button_build_request)
    void onButtonClick() {
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        GitHubService.GitHub github = retrofit.create(GitHubService.GitHub.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<GitHubService.Contributor>> call = github.contributors("square", "retrofit");

        // Fetch and print a list of the contributors to the library.

        call.enqueue(new Callback<List<GitHubService.Contributor>>() {
            @Override
            public void onResponse(Call<List<GitHubService.Contributor>> call, Response<List<GitHubService.Contributor>> response) {
                StringBuffer stringBuffer = new StringBuffer();
                for (GitHubService.Contributor contributor : response.body()) {
                    System.out.println(contributor.login + " (" + contributor.contributions + ")");
                    stringBuffer.append(contributor.login + " (" + contributor.contributions + ")" + "\n");
                }
                mResult.setText(stringBuffer.toString());
            }

            @Override
            public void onFailure(Call<List<GitHubService.Contributor>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_LONG).show();
            }
        });

    }
}
