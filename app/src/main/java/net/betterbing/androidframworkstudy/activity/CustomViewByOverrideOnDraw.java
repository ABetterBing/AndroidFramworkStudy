package net.betterbing.androidframworkstudy.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.base.BaseHeadActivity;

/**
 * Created by aibb on 16/4/11.
 */
public class CustomViewByOverrideOnDraw extends BaseHeadActivity {
    @Override
    public int getHeadTitle() {
        return 0;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState, Toolbar toolBar) {
        setTitle("自定义view-onDraw()");
    }

    @Override
    public int getLayout() {
        return R.layout.activity_customview_override_ondraw;
    }

    @Override
    public String getTag() {
        return this.getClass().getSimpleName().toString();
    }
}
