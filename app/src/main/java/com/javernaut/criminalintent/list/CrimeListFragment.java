package com.javernaut.criminalintent.list;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.javernaut.criminalintent.R;
import com.javernaut.criminalintent.details.CrimeDetailsFragment;
import com.javernaut.criminalintent.model.Crime;
import com.javernaut.criminalintent.repository.Repository;
import com.javernaut.criminalintent.repository.RepositoryProvider;

public class CrimeListFragment extends Fragment {

    // View
    private RecyclerView recyclerView;
    private CrimeListAdapter crimeListAdapter;

    private Repository repository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        repository = RepositoryProvider.getInstance(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crime_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        crimeListAdapter = new CrimeListAdapter(
                RepositoryProvider.getInstance(getContext()).getAllCrimes(), itemEventsListener
        );
        recyclerView.setAdapter(crimeListAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.crime_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.createNew) {
            repository.generateRandomCrime();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
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

    private void showCrime(Crime crime) {
        boolean twoPanelMode = getResources().getBoolean(R.bool.two_panel_mode);
        int containerId = twoPanelMode ? R.id.fragmentContainerDetails : R.id.fragmentContainer;

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction()
                .replace(containerId, CrimeDetailsFragment.makeInstance(crime.getId()));

        if (!twoPanelMode) {
            transaction = transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    private void showDeleteDialog(Crime crime) {
        DeleteConfirmationDialogFragment.makeInstance(crime)
                .show(getParentFragmentManager(), null);
    }

    private final Repository.Listener repositoryListener = new Repository.Listener() {
        @Override
        public void onDataChanged() {
            crimeListAdapter.setNewCrimes(repository.getAllCrimes());
        }
    };

    private final CrimeListAdapter.ItemEventsListener itemEventsListener = new CrimeListAdapter.ItemEventsListener() {
        @Override
        public void onItemClick(Crime crime) {
            showCrime(crime);
        }

        @Override
        public void onLongItemClick(Crime crime) {
            showDeleteDialog(crime);
        }
    };
}
