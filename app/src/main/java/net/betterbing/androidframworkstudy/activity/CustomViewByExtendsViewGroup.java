package net.betterbing.androidframworkstudy.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.base.BaseHeadActivity;
import net.betterbing.androidframworkstudy.utils.DensityUtil;
import net.betterbing.androidframworkstudy.view.HorizontalScrollViewEx;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by aibb on 16/4/11.
 */
public class CustomViewByExtendsViewGroup extends BaseHeadActivity {

    @Bind(R.id.container)
    HorizontalScrollViewEx mListContainer;

    @Override
    public int getHeadTitle() {
        return 0;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState, Toolbar toolBar) {
        setTitle("自定义ViewPager-继承ViewGroup");
        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        final int screenWidth = DensityUtil.getScreenMetrics(this).widthPixels;
        final int screenHeight = DensityUtil.getScreenMetrics(this).heightPixels;
        for (int i = 0; i < 4; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.activity_customview_extends_viewgroup_content, mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = (TextView) layout.findViewById(R.id.title);
            textView.setText("page " + (i + 1));
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mListContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_customview_extends_viewgroup_content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(CustomViewByExtendsViewGroup.this, "click item",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_customview_extends_viewgroup;
    }

    @Override
    public String getTag() {
        return this.getClass().getSimpleName().toString();
    }
}
