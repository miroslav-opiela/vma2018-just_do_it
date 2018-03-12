package sk.upjs.vma.justdoit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        //long taskId = getIntent().getLongExtra(TaskListActivity.TASK_ID_EXTRA, -1);
        Long taskId = (Long) getIntent().getSerializableExtra(
                TaskListActivity.TASK_ID_EXTRA);

        Log.d("DETAIL", "intent includes " + taskId);

    }
}
