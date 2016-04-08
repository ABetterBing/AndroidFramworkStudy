package net.betterbing.androidframworkstudy.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.betterbing.androidframworkstudy.R;

/**
 * Created by aibb on 16/4/8.
 */
public class ViewPagerFragment extends Fragment {

    private static String mName;
    private Context mContext;

    public ViewPagerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.activity_viewpager_item, null);
        TextView textView = (TextView) contentView.findViewById(R.id.viewpager_item_tv);
        textView.setText(getArguments().getString("name"));
        return contentView;
    }
}
