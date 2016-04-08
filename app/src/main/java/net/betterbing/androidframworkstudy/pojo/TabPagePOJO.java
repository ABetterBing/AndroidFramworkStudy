package net.betterbing.androidframworkstudy.pojo;


import android.support.v4.app.Fragment;

/**
 * Created by aibb on 16/4/5.
 */
public class TabPagePOJO {
    public Fragment fragment;
    public String titleName;
    public int pageIcon;
    public int pageSelectedIcon;

    public TabPagePOJO(Fragment fragment, String titleName, int pageIcon, int pageSelectedIcon) {
        this.fragment = fragment;
        this.titleName = titleName;
        this.pageIcon = pageIcon;
        this.pageSelectedIcon = pageSelectedIcon;
    }
}
