package sk.upjs.vma.justdoit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.Serializable;

public class TaskDetailActivity extends AppCompatActivity {

    private TaskDao taskDao = TaskDao.INSTANCE;

    private EditText editTextTaskName;
    private CheckBox checkBoxTaskCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        //long taskId = getIntent().getLongExtra(TaskListActivity.TASK_ID_EXTRA, -1);
        Long taskId = (Long) getIntent().getSerializableExtra(
                TaskListActivity.TASK_ID_EXTRA);

        Log.d("DETAIL", "intent includes " + taskId);

        editTextTaskName = findViewById(R.id.editTextTaskName);
        checkBoxTaskCompleted = findViewById(R.id.checkboxTaskCompleted);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTask();
    }

    private void saveTask() {
        Task task = new Task();
        task.setName(editTextTaskName.getText().toString());
        task.setDone(checkBoxTaskCompleted.isChecked());
        taskDao.saveOrUpdate(task);
    }
}
