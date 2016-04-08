package net.betterbing.androidframworkstudy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import net.betterbing.androidframworkstudy.pojo.TabPagePOJO;

import java.util.List;

/**
 * Created by aibb on 16/4/8.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter implements AdvancedPagerSlidingTabStrip.IconTabProvider {
    List<TabPagePOJO> mFragmentList;

    public ViewPagerAdapter(FragmentManager fm, List<TabPagePOJO> framgentList) {
        super(fm);
        this.mFragmentList = framgentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position).fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Integer getPageIcon(int position) {
        return mFragmentList.get(position).pageIcon;
    }

    @Override
    public Integer getPageSelectIcon(int position) {
        return mFragmentList.get(position).pageSelectedIcon;
    }

    @Override
    public String getPageIconText(int position) {
        return mFragmentList.get(position).titleName;
    }
}
