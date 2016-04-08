package net.betterbing.androidframworkstudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.utils.ViewHolder;

import java.util.List;

/**
 * Created by aibb on 16/4/8.
 */
public class PulltoRefreshAdapter extends ArrayAdapter<String> {
    List<String> mList;

    public PulltoRefreshAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
        mList = objects;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getPosition(String item) {
        return mList.indexOf(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_pulltorefresh_item, null);
        }
        TextView textView = ViewHolder.get(convertView,R.id.pulltorefresh_item_tv);
        textView.setText(mList.get(position));
        return convertView;
    }
}
