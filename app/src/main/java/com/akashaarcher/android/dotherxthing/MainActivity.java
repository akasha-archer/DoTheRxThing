package com.akashaarcher.android.dotherxthing;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.akashaarcher.android.dotherxthing.database.TaskSQLiteOpenHelper;
import com.akashaarcher.android.dotherxthing.model.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nl.qbusict.cupboard.QueryResultIterable;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity implements NewItemDialogFragment.NewItemDialogListener, TaskAdapter.Listener, EditItemDialogFragment.EditItemDialogListener, DeleteItemDialogFragment.DeleteItemDialogListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.add_task_fab)
    FloatingActionButton addTaskFab;

    @BindView(R.id.rvItem)
    RecyclerView listRv;

    private TaskAdapter adapter;
    private static final String DIALOG_TITLE = "WRITE SOMETHING";
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get an instance of the DatabaseHelper
        TaskSQLiteOpenHelper dbHelper = TaskSQLiteOpenHelper.getInstance(this);
        db = dbHelper.getWritableDatabase();

        ButterKnife.bind(this);

        addTaskFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewItemDialog();
            }
        });

        adapter = new TaskAdapter(selectAllTasks(), this);

        listRv.setLayoutManager(new LinearLayoutManager(this));
        listRv.setAdapter(adapter);
        Log.i("Main Activity", "passed adapter");
    }

    // DIALOG OPEN METHODS

    void openNewItemDialog() {
        FragmentManager manager = getSupportFragmentManager();
        DialogFragment newFragment = new NewItemDialogFragment();
        newFragment.show(manager, DIALOG_TITLE);
    }

    void openUpdateItemDialog() {
        FragmentManager manager = getSupportFragmentManager();
        DialogFragment newFragment = new EditItemDialogFragment();
        newFragment.show(manager, DIALOG_TITLE);
    }

    void openDeleteItemDialog() {
        FragmentManager manager = getSupportFragmentManager();
        DialogFragment newFragment = new DeleteItemDialogFragment();
        newFragment.show(manager, DIALOG_TITLE);
    }

    // ADD NEW ITEM DIALOG
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
        Log.i("FragmentAlertDialog", "Positive click!");
        addTask(new Task(NewItemDialogFragment.newTaskEntry));
        Toast.makeText(getApplicationContext(), NewItemDialogFragment.newTaskEntry + " has been added to the List", Toast.LENGTH_SHORT).show();

        refreshTaskList();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        Log.i("FragmentAlertDialog", "Negative click!");
        Toast.makeText(getApplicationContext(), "Transaction Cancelled", Toast.LENGTH_SHORT).show();
    }

    // EDIT ITEM DIALOG
    @Override
    public void onEditItemDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
        Log.i("UpdateAlertDialog", "Positive click!");
        Toast.makeText(getApplicationContext(), "Task Updated", Toast.LENGTH_SHORT).show();
        updateTask();
        refreshTaskList();
    }

    @Override
    public void onEditItemDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        Log.i("UpdateAlertDialog", "Negative click!");
        Toast.makeText(getApplicationContext(), "Item edit Cancelled", Toast.LENGTH_SHORT).show();
//        deleteTask(task);
//        refreshTaskList();
    }


    // DELETE ITEM DIALOG
    @Override
    public void onDeleteDialogPositiveClick(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "Item deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "delete item cancelled", Toast.LENGTH_SHORT).show();
    }


    private void addTask(Task task) {
        cupboard().withDatabase(db).put(task);
    }

    private void deleteTask(Task task) {
        String currTask = task.getTaskEntry();
        cupboard().withDatabase(db).delete(task);
    }

    private void updateTask() {
        String currEntry = NewItemDialogFragment.newTaskEntry;
        Task task = new Task(EditItemDialogFragment.updateTaskEntry);
        ContentValues values = new ContentValues(1);
        values.put("taskEntry", String.valueOf(task));
        cupboard().withDatabase(db).update(Task.class, values, "taskEntry = ?", currEntry);

    }

    private void refreshTaskList() {
        adapter.setData(selectAllTasks());
    }

    private List<Task> selectAllTasks() {
        List<Task> tasks = new ArrayList<>();

        try {
            // Iterate cats
            QueryResultIterable<Task> itr = cupboard().withDatabase(db).query(Task.class).query();
            for (Task task : itr) {
                tasks.add(task);
            }
            itr.close();
        } catch (Exception e) {
            Log.e(TAG, "selectAllTasks: ", e);
        }

        return tasks;
    }

    // VIEWHOLDER METHODS
    @Override
    public void onItemLongClicked(Task task) {
        Toast.makeText(this, "You longClicked me!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteBtnClicked(Task task) {
        openDeleteItemDialog();
    }

    @Override
    public void onEditBtnClicked(Task task) {
        openUpdateItemDialog();
    }


}
