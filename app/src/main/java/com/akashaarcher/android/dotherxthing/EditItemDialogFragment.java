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
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akashaarcher on 9/20/17.
 */

public class EditItemDialogFragment extends DialogFragment {

    @BindView(R.id.edit_task)
    EditText editTask;

    public static String updateTaskEntry = "";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_edit_task_dialog, null);
        ButterKnife.bind(this, v);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Edit Task")
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton("update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })

                // negative button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editItemDialogListener.onEditItemDialogNegativeClick(EditItemDialogFragment.this);
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
                    editItemDialogListener.onEditItemDialogPositiveClick(EditItemDialogFragment.this);
                    dialog.dismiss();
                }

            }
        });
    }


    public interface EditItemDialogListener {
        public void onEditItemDialogPositiveClick(DialogFragment dialog);
        public void onEditItemDialogNegativeClick(DialogFragment dialog);
    }

    EditItemDialogListener editItemDialogListener;


    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            editItemDialogListener = (EditItemDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }




}
