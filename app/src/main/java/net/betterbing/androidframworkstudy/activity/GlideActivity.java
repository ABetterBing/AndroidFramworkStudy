package net.betterbing.androidframworkstudy.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.base.BaseHeadActivity;

/**
 * Created by aibb on 16/4/8.
 */
public class GlideActivity extends BaseHeadActivity {
    @Override
    public int getHeadTitle() {
        return 0;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState, Toolbar toolBar) {
        setTitle("Glide加载图片");
    }

    @Override
    public int getLayout() {
        return R.layout.activity_glide;
    }

    @Override
    public String getTag() {
        return this.getClass().getSimpleName().toString();
    }
}
