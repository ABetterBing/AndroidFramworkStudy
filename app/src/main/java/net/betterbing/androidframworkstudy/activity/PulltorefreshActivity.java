package net.betterbing.androidframworkstudy.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.adapter.PulltoRefreshAdapter;
import net.betterbing.androidframworkstudy.base.BaseHeadActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by aibb on 16/4/8.
 */
public class PulltorefreshActivity extends BaseHeadActivity {
    @Bind(R.id.pulltorefresh_listview)
    PullToRefreshListView mListView;

    @Override
    public int getHeadTitle() {
        return 0;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState, Toolbar toolBar) {
        setTitle("PulltoRefresh");
        loadData();
    }

    void loadData() {
        List<String> strs = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strs.add(i + "");
        }
        PulltoRefreshAdapter adapter = new PulltoRefreshAdapter(getApplicationContext(), strs);
        mListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mListView.setAdapter(adapter);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Handler(getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mListView.onRefreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_pulltorefresh;
    }

    @Override
    public String getTag() {
        return this.getClass().getSimpleName().toString();
    }
}
