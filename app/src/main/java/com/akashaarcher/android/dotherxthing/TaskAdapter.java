package com.akashaarcher.android.dotherxthing;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akashaarcher.android.dotherxthing.model.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.akashaarcher.android.dotherxthing.R.id.due_date;

/**
 * Created by Akasha on 9/14/17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Listener listener;
    private List<Task> items;

    public TaskAdapter(List<Task> items, Listener listener) {
        this.items = items;
        this.listener = listener;
    }


    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_entry_viewholder, parent, false);
        TaskViewHolder vh = new TaskViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = items.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<Task> items) {
        this.items = items;
        notifyDataSetChanged();
    }


    public class TaskViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.task_name)
        TextView taskNameTv;

        @BindView(due_date)
        TextView dueDateTv;

        @BindView(R.id.delete_icon)
        ImageView deleteTaskIv;

        @BindView(R.id.edit_icon)
        ImageView editTaskIv;
        
        LinearLayout taskItemLayout;


        public TaskViewHolder(View itemView) {
            super(itemView);
            taskItemLayout = (LinearLayout) itemView.findViewById(R.id.task_item_layout);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Task task) {
            final Task item = task;
            taskNameTv.setText(item.getTaskEntry());

            deleteTaskIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDeleteBtnClicked(item);
                }
            });

            editTaskIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onEditBtnClicked(item);
                }
            });

            taskItemLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemLongClicked(item);
                    return true;
                }

            });
        }

    }

    interface Listener {
        void onItemLongClicked(Task task);
        void onDeleteBtnClicked(Task task);
        void onEditBtnClicked(Task task);
    }

}
