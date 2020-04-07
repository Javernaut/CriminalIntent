package com.javernaut.criminalintent.details;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.javernaut.criminalintent.R;
import com.javernaut.criminalintent.list.CrimeListActivity;
import com.javernaut.criminalintent.model.Crime;
import com.javernaut.criminalintent.repository.Repository;

import java.util.UUID;

public class CrimeDetailsFragment extends Fragment {

    private static final String KEY_ID = "key_id";

    // Model
    private Crime crime;

    // View
    private EditText titleView;
    private Button dateView;
    private CheckBox solvedView;

    public CrimeDetailsFragment() {
        super(R.layout.fragment_crime_details);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        UUID id = (UUID) arguments.getSerializable(KEY_ID);
        this.crime = Repository.getInstance().getCrimeById(id);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleView = view.findViewById(R.id.crime_title);
        dateView = view.findViewById(R.id.crime_date);
        solvedView = view.findViewById(R.id.crime_solved);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleView.setText(crime.getTitle());
        dateView.setText(crime.getDate().toString());
        solvedView.setChecked(crime.isSolved());
    }

    public static CrimeDetailsFragment makeInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_ID, id);

        CrimeDetailsFragment fragment = new CrimeDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }
}