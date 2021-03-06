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
 * Created by Akasha on 9/14/17.
 */

public class NewItemDialogFragment extends DialogFragment {

    @BindView(R.id.task_entry)
    EditText newItemEntry;

    public static String newTaskEntry = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_task_dialog, null);
        ButterKnife.bind(this, v);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Add a New Item")
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })

                // negative button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        newItemDialogListener.onDialogNegativeClick(NewItemDialogFragment.this);
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
                newTaskEntry = newItemEntry.getText().toString();
                if (newTaskEntry.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter a task", Toast.LENGTH_SHORT).show();
                } else if (!newTaskEntry.isEmpty()){
                    newItemDialogListener.onDialogPositiveClick(NewItemDialogFragment.this);
                    dialog.dismiss();
                }

            }
        });
    }


    public interface NewItemDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    NewItemDialogListener newItemDialogListener;


    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            newItemDialogListener = (NewItemDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


}
