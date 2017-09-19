package com.akashaarcher.android.dotherxthing;

import android.graphics.Picture;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akashaarcher.android.dotherxthing.model.ListItem;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.akashaarcher.android.dotherxthing.R.id.due_date;

/**
 * Created by Akasha on 9/14/17.
 */

public class ListItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.task_name)
    TextView taskNameTv;

    @BindView(due_date)
    TextView dueDateTv;


    public ListItemViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.task_entry_viewholder, parent, false);
    }

    public void bind(ListItem listItem) {
        taskNameTv.setText(listItem.getTaskEntry());
    }

}
