package net.betterbing.androidframworkstudy.base;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


import net.betterbing.androidframworkstudy.R;

import butterknife.Bind;


public abstract class BaseHeadActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.head_tv_title)
    TextView mTitleText;

    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if (getHeadTitle() != 0) {
            mToolbar.setTitle("");
            mTitleText.setText(getHeadTitle());
        }
        mToolbar.setNavigationIcon(R.mipmap.icon_return);
        View.OnClickListener navigationClick = getNavigationClick();
        if (navigationClick == null) {
            navigationClick = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            };
        }
        mToolbar.setNavigationOnClickListener(navigationClick);
        onCreateView(savedInstanceState, mToolbar);
    }

    public View.OnClickListener getNavigationClick() {
        return null;
    }

    @StringRes
    public abstract int getHeadTitle();


    public abstract void onCreateView(Bundle savedInstanceState, Toolbar toolBar);

    public void setTitle(String title) {
        mTitleText.setText(title);
    }


}
