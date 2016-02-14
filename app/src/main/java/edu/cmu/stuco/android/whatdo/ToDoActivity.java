package edu.cmu.stuco.android.whatdo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ToDoActivity extends AppCompatActivity implements AddTaskDialogFragment.DialogListener {
    private ArrayList<String> tasks = new ArrayList<>();
    private TaskListAdapter taskListAdapter;
    private RecyclerView recyclerView;
    private ListView listView;
    private boolean showRecyclerView = false;

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

        listView = (ListView) findViewById(R.id.list);
        taskListAdapter = new TaskListAdapter(this, tasks);
        listView.setAdapter(taskListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Passing data to an activity
                Intent intent = new Intent(ToDoActivity.this, TestIntentActivity.class);
                String task = tasks.get(position);
                intent.putExtra(TestIntentActivity.TASK_KEY, task);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskRecyclerAdapter(this, tasks));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.what_do_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showRecyclerView = !showRecyclerView;

        if (showRecyclerView) {
            recyclerView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        } else {
            recyclerView.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        }

        return true;
    }

    @Override
    public void onPositiveClick(String taskName) {
        tasks.add(taskName);
        // Let the adapter know the underlying data has changed so the ListView can be
        // re-drawn appropriately
        taskListAdapter.notifyDataSetChanged();
    }
}
