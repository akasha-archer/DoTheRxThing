package com.akashaarcher.android.dotherxthing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.akashaarcher.android.dotherxthing.model.ListItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Akasha on 9/14/17.
 */

public class ListItemAdapter extends RecyclerView.Adapter {

    private List<ListItem> items = Collections.emptyList();
    Context context;

    public ListItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ListItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListItemViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItemViewHolder viewHolder = (ListItemViewHolder) holder;
        ListItem item = items.get(position);
        viewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
