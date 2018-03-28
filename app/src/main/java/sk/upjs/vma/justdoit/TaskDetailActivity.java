package sk.upjs.vma.justdoit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.Serializable;

public class TaskDetailActivity extends AppCompatActivity {

    private TaskDao taskDao = TaskDao.INSTANCE;
    private Task task;

    private EditText editTextTaskName;
    private CheckBox checkBoxTaskCompleted;

    private boolean ignoreSaveOnFinish;

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
        // v pripade ze sa odstranuje uloha a nema byt ulozena
        if (ignoreSaveOnFinish) {
            ignoreSaveOnFinish = false;
            return;
        }

        task.setName(editTextTaskName.getText().toString());
        task.setDone(checkBoxTaskCompleted.isChecked());
        taskDao.saveOrUpdate(task);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_detail_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_item_delete) {
            taskDao.delete(task);
            // priznak aby sa uloha neukladala po navrate z detail aktivity
            ignoreSaveOnFinish = true;
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
