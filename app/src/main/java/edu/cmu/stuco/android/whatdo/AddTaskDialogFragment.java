package edu.cmu.stuco.android.whatdo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * AddTaskDialogFragment is a popup that prompts the user to enter a task.
 */
public class AddTaskDialogFragment extends DialogFragment {
    private DialogListener dialogListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        dialogListener = (DialogListener) activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        /* Inflate and set the layout for the dialog
        *  For the curious: We pass null to the inflater when it asks for its
        *  root view because we're passing this view to a dialog. The dialog
        *  will be its root so we don't need to use anything for that parameter
        */
        View view = inflater.inflate(R.layout.dialog_add_task, null);
        final EditText taskEditText = (EditText) view.findViewById(R.id.task_EditText);
        builder.setView(view)
                .setMessage(R.string.add_task)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogListener.onPositiveClick(taskEditText.getText().toString());
                    }
                });

        return builder.create();
    }

    /**
     * Interface used to let a listener know what task the user wants to add.
     */
    public interface DialogListener {
        void onPositiveClick(String taskName);
    }
}
