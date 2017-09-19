package com.akashaarcher.android.dotherxthing;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.akashaarcher.android.dotherxthing.model.ListItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NewItemDialogFragment.NewItemDialogListener {

    @BindView(R.id.add_task_fab)
    FloatingActionButton addTaskFab;

    @BindView(R.id.rvItem)
    RecyclerView listRv;

    private List<ListItem> items;
    private ListItemAdapter adapter;
    private static final String DIALOG_TITLE = "WRITE SOMETHING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

//        listRv.setLayoutManager(new LinearLayoutManager(this));
//        listRv.setAdapter(adapter);
//        Log.i("Main Activity", "passed adapter");
//     //   adapter.setData(items);
        addTaskFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewItemDialog();
            }
        });

        setupRecyclerView();
    }

    void openNewItemDialog() {
        FragmentManager manager = getSupportFragmentManager();
        DialogFragment newFragment = new NewItemDialogFragment();
        newFragment.show(manager, DIALOG_TITLE);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
        Log.i("FragmentAlertDialog", "Positive click!");
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
        Log.i("FragmentAlertDialog", "Negative click!");
        Toast.makeText(getApplicationContext(), "Transaction Cancelled", Toast.LENGTH_SHORT).show();
    }


    private void setupRecyclerView ()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listRv.setLayoutManager(layoutManager);

        listRv.setHasFixedSize(true);

        adapter = new ListItemAdapter(this);
        listRv.setAdapter( adapter );
    }

}
