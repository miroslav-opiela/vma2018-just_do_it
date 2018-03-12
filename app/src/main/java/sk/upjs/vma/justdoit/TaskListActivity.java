package sk.upjs.vma.justdoit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    private TaskDao taskDao = TaskDao.INSTANCE;

    public static final String TASK_ID_EXTRA = "taskId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        ListView listView = findViewById(R.id.listViewTasks);

        List<Task> list = taskDao.list();

        ListAdapter adapter = new ArrayAdapter<Task>(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int position, long id) {
                Log.d(TaskListActivity.class.getName(), "on item click " + position);

                Intent intent = new Intent(TaskListActivity.this,
                        TaskDetailActivity.class);

                Task task = taskDao.list().get(position);

                intent.putExtra(TASK_ID_EXTRA, task.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_list_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.addNewTask) {
            Log.d("Hello", "vytvorenie novej aktivity");

            /*Uri uri = Uri.parse("https://www.upjs.sk/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);*/

            Intent intent = new Intent(this, TaskDetailActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
