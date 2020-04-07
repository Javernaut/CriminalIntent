package com.javernaut.criminalintent.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.javernaut.criminalintent.R;
import com.javernaut.criminalintent.model.Crime;

import java.util.List;

public class CrimeListAdapter extends RecyclerView.Adapter<CrimeViewHolder> {

    private final List<Crime> crimesList;
    private final ItemEventsListener listener;

    public CrimeListAdapter(List<Crime> crimesList, ItemEventsListener listener) {
        this.crimesList = crimesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CrimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_crime, parent, false);
        return new CrimeViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeViewHolder holder, int position) {
        holder.bindTo(crimesList.get(position));
    }

    @Override
    public int getItemCount() {
        return crimesList.size();
    }

    interface ItemEventsListener {
        void onItemClick(Crime crime);
    }
}