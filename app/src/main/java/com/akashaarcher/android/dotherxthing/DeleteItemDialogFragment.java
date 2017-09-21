package com.akashaarcher.android.dotherxthing;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Akasha on 9/21/17.
 */

public class DeleteItemDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_delete_task_dialog, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                //.setTitle("")
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteItemDialogListener.onDeleteDialogPositiveClick(DeleteItemDialogFragment.this);
                    }
                })

                // negative button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteItemDialogListener.onDeleteDialogNegativeClick(DeleteItemDialogFragment.this);
                    }
                })
                .create();
    }

    public interface DeleteItemDialogListener {
        public void onDeleteDialogPositiveClick(DialogFragment dialog);
        public void onDeleteDialogNegativeClick(DialogFragment dialog);
    }

    DeleteItemDialogListener deleteItemDialogListener;


    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            deleteItemDialogListener = (DeleteItemDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }


}


