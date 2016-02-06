package edu.cmu.stuco.android.whatdo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ToDoActivity extends AppCompatActivity implements AddTaskDialogFragment.DialogListener {
    private ArrayList<String> tasks = new ArrayList<>();
    private TaskListAdapter taskListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        FloatingActionButton addItemFab = (FloatingActionButton) findViewById(R.id.add_item_fab);
        addItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new AddTaskDialogFragment();
                fragment.show(getSupportFragmentManager(), "AddTask");
            }
        });

        ListView listView = (ListView) findViewById(R.id.list);
        taskListAdapter = new TaskListAdapter(this, tasks);
        listView.setAdapter(taskListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tasks.remove(position);
                // Let the adapter know the underlying data has changed so the ListView can be
                // re-drawn appropriately
                taskListAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onPositiveClick(String taskName) {
        tasks.add(taskName);
        // Let the adapter know the underlying data has changed so the ListView can be
        // re-drawn appropriately
        taskListAdapter.notifyDataSetChanged();
    }
}
