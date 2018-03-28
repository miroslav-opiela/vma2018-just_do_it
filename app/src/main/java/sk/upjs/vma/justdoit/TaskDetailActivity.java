package sk.upjs.vma.justdoit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.Serializable;

public class TaskDetailActivity extends AppCompatActivity {

    private TaskDao taskDao = TaskDao.INSTANCE;
    private Task task;

    private EditText editTextTaskName;
    private CheckBox checkBoxTaskCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        editTextTaskName = findViewById(R.id.editTextTaskName);
        checkBoxTaskCompleted = findViewById(R.id.checkboxTaskCompleted);

        Long taskId = (Long) getIntent().getSerializableExtra(
                TaskListActivity.TASK_ID_EXTRA);

        Log.d("DETAIL", "intent includes " + taskId);

        if (taskId == null) {
            // pripad ak vytvaram novu ulohu
            task = new Task();
        } else {
            // pripad ak editujem existujucu ulohu
            task = taskDao.getTask(taskId);
        }

        editTextTaskName.setText(task.getName());
        checkBoxTaskCompleted.setChecked(task.isDone());
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTask();
    }

    private void saveTask() {
        task.setName(editTextTaskName.getText().toString());
        task.setDone(checkBoxTaskCompleted.isChecked());
        taskDao.saveOrUpdate(task);
    }
}
