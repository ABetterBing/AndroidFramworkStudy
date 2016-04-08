package net.betterbing.androidframworkstudy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import net.betterbing.androidframworkstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aibb on 16/4/8.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SimpleItemViewHolder> {
    private List<String> items;
    private Context mContext;

    public RecyclerViewAdapter(Context context, @NonNull List<String> dateItems) {
        this.items = (dateItems != null ? dateItems : new ArrayList<String>());
        mContext = context;
    }

    @Override
    public SimpleItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new SimpleItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SimpleItemViewHolder viewHolder, final int position) {
        viewHolder.textView.setText(items.get(position));
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"item"+position+" is onclick",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (this.items != null) ? this.items.size() : 0;
    }

    protected class SimpleItemViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;

        public SimpleItemViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.recyclerview_text);
        }

    }
}
