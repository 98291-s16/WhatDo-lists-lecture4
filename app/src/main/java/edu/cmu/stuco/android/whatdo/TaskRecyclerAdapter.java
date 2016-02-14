package edu.cmu.stuco.android.whatdo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by AndrewOrobator on 2/11/16.
 */
public class TaskRecyclerAdapter extends RecyclerView.Adapter<TaskRecyclerAdapter.ViewHolder> {
    private final Context context;
    private ArrayList<String> tasks;

    public TaskRecyclerAdapter(Context context, ArrayList<String> tasks) {
        this.context = context;
        this.tasks = tasks;
    }


    @Override
    public TaskRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskRecyclerAdapter.ViewHolder holder, int position) {
        String task = tasks.get(position);
        holder.taskTextView.setText(task);
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskTextView;
        int position;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tasks.remove(position);
                    notifyItemRemoved(position);
                }
            });

            taskTextView = (TextView) itemView.findViewById(R.id.task_TextView);
        }
    }
}
