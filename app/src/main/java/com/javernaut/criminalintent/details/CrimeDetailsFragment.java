package com.javernaut.criminalintent.details;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.javernaut.criminalintent.R;
import com.javernaut.criminalintent.model.Crime;
import com.javernaut.criminalintent.repository.Repository;
import com.javernaut.criminalintent.repository.RepositoryProvider;

import java.util.UUID;

public class CrimeDetailsFragment extends Fragment {

    private static final String KEY_ID = "key_id";

    // Model
    private UUID crimeId;
    private Crime crime;

    private Repository repository;

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

        crimeId = (UUID) arguments.getSerializable(KEY_ID);

        repository = RepositoryProvider.getInstance(getContext());

        crime = repository.getCrimeById(crimeId);
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

        titleView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                crime.setTitle(s.toString());
                saveCrime();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        solvedView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crime.setSolved(isChecked);
                saveCrime();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        repository.addListener(repositoryListener);
    }

    @Override
    public void onPause() {
        repository.removeListener(repositoryListener);
        super.onPause();
    }

    private void saveCrime() {
        repository.update(crime);
    }

    private final Repository.Listener repositoryListener = new Repository.Listener() {
        @Override
        public void onDataChanged() {
            if (repository.getCrimeById(crimeId) == null) {
                getParentFragmentManager().beginTransaction()
                        .remove(CrimeDetailsFragment.this)
                        .commit();
            }
        }
    };

    public static CrimeDetailsFragment makeInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_ID, id);

        CrimeDetailsFragment fragment = new CrimeDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
