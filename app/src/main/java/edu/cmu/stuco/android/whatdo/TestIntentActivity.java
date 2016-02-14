package edu.cmu.stuco.android.whatdo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TestIntentActivity extends AppCompatActivity {
    public static final String TASK_KEY = "task";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intent);

        TextView textView = (TextView) findViewById(R.id.task_TextView);
        Intent intent = getIntent();
        String task = intent.getStringExtra(TASK_KEY);
        textView.setText(task);

    }

}
