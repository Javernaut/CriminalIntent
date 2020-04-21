package com.javernaut.criminalintent.list;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.javernaut.criminalintent.model.Crime;
import com.javernaut.criminalintent.repository.Repository;
import com.javernaut.criminalintent.repository.RepositoryProvider;

import java.util.UUID;

public class DeleteConfirmationDialogFragment extends DialogFragment {

    private static final String KEY_ID = "key_id";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteCurrentCrime();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .create();
    }

    private void deleteCurrentCrime() {
        Repository repository = RepositoryProvider.getInstance(getContext());
        UUID crimeId = (UUID) getArguments().getSerializable(KEY_ID);
        Crime crimeToDelete = repository.getCrimeById(crimeId);
        repository.delete(crimeToDelete);
    }

    public static DialogFragment makeInstance(Crime crime) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_ID, crime.getId());

        DeleteConfirmationDialogFragment fragment = new DeleteConfirmationDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

}
