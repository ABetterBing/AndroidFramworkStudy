package net.betterbing.androidframworkstudy.activity;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.adapter.DividerItemDecoration;
import net.betterbing.androidframworkstudy.adapter.RecyclerViewAdapter;
import net.betterbing.androidframworkstudy.base.BaseHeadActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by aibb on 16/4/8.
 */
public class RecyclerViewActivity extends BaseHeadActivity {
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    @Override
    public int getHeadTitle() {
        return 0;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState, Toolbar toolBar) {
        setTitle("RecyclerView");
        List<String> dataSet = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            dataSet.add("item" + i);
        }
        //创建默认的线性LayoutManager,还有GridLayoutManager网格布局和StaggeredGridLayoutManager瀑布流
        mLayoutManager = new LinearLayoutManager(this);
        //设置方向是横向的
        //mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //删除，添加Item时的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置divider
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),R.drawable.list_divider));
        //设置点击时事件
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getApplicationContext(),dataSet);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_recyclerview;
    }

    @Override
    public String getTag() {
        return this.getClass().getSimpleName().toString();
    }
}
