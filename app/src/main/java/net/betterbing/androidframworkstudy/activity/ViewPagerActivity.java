package net.betterbing.androidframworkstudy.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.adapter.ViewPagerAdapter;
import net.betterbing.androidframworkstudy.base.BaseHeadActivity;
import net.betterbing.androidframworkstudy.fragment.ViewPagerFragment;
import net.betterbing.androidframworkstudy.pojo.TabPagePOJO;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by aibb on 16/4/8.
 */
public class ViewPagerActivity extends BaseHeadActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.tabs)
    AdvancedPagerSlidingTabStrip mTabHost;

    @Bind(R.id.center_viewpager)
    ViewPager mViewPager;

    @Override
    public int getHeadTitle() {
        return 0;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState, Toolbar toolBar) {
        setTitle("ViewPager");
        toolBar.setVisibility(View.GONE);
        mViewPager.addOnPageChangeListener(this);
        List<TabPagePOJO> listFramgent = new ArrayList<>();
        ViewPagerFragment mallFragment = new ViewPagerFragment();
        Bundle mallBundle = new Bundle();
        mallBundle.putString("name", "商城");
        mallFragment.setArguments(mallBundle);

        ViewPagerFragment settingFragment = new ViewPagerFragment();
        Bundle settingBundle = new Bundle();
        settingBundle.putString("name", "设置");
        settingFragment.setArguments(settingBundle);
        listFramgent.add(new TabPagePOJO(mallFragment, "商城", R.mipmap.tab_shop_default, R.mipmap.tab_shop_choosed));
        listFramgent.add(new TabPagePOJO(settingFragment, "设置", R.mipmap.tab_user_default, R.mipmap.tab_user_choosed));
        mViewPager.setOffscreenPageLimit(listFramgent.size());
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), listFramgent));
        mTabHost.setViewPager(mViewPager);
        mTabHost.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_viewpager;
    }

    @Override
    public String getTag() {
        return this.getClass().getSimpleName().toString();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
