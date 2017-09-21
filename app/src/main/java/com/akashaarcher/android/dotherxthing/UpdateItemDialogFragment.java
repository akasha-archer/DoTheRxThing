package com.akashaarcher.android.dotherxthing;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akashaarcher on 9/20/17.
 */

public class UpdateItemDialogFragment extends DialogFragment {

    @BindView(R.id.delete_icon)
    ImageView deleteTaskIv;

    @BindView(R.id.delete_task_heading)
    TextView deleteTaskTv;

    @BindView(R.id.edit_task)
    EditText editTask;

    public static String updateTaskEntry = "";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_update_task_dialog, null);
        ButterKnife.bind(this, v);

        deleteTaskIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Edit/Delete Task")
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })

                // negative button
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        updateDialogListener.onUpdateDialogNegativeClick(UpdateItemDialogFragment.this);
                    }
                })
                .create();
    }


    @Override
    public void onResume() {
        super.onResume();

        final AlertDialog dialog = (AlertDialog)getDialog();

        Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View onClick) {
                updateTaskEntry = editTask.getText().toString();
                if (updateTaskEntry.isEmpty()) {
                    Toast.makeText(getActivity(), "Please edit your task", Toast.LENGTH_SHORT).show();
                } else if (!updateTaskEntry.isEmpty()){
                    updateDialogListener.onUpdateDialogPositiveClick(UpdateItemDialogFragment.this);
                    dialog.dismiss();
                }

            }
        });
    }


    public interface UpdateItemDialogListener {
        public void onUpdateDialogPositiveClick(DialogFragment dialog);
        public void onUpdateDialogNegativeClick(DialogFragment dialog);
    }

    UpdateItemDialogListener updateDialogListener;


    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            updateDialogListener = (UpdateItemDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }




}
