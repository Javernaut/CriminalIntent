package com.javernaut.criminalintent.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.javernaut.criminalintent.R;
import com.javernaut.criminalintent.details.CrimeDetailsFragment;
import com.javernaut.criminalintent.model.Crime;

public class CrimeListFragment extends Fragment {

    // View
    private RecyclerView recyclerView;

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
        recyclerView.setAdapter(new CrimeListAdapter(CrimeListActivity.CRIMES_LIST, itemEventsListener));
    }

    private final CrimeListAdapter.ItemEventsListener itemEventsListener = new CrimeListAdapter.ItemEventsListener() {
        @Override
        public void onItemClick(Crime crime) {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, CrimeDetailsFragment.makeInstance(crime.getId()))
                    .addToBackStack(null)
                    .commit();
        }
    };
}
