package com.javernaut.criminalintent.list;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.javernaut.criminalintent.R;
import com.javernaut.criminalintent.model.Crime;

class CrimeViewHolder extends RecyclerView.ViewHolder {

    private final TextView titleView;
    private final TextView dateView;
    private final CheckBox solvedView;

    private final CrimeListAdapter.ItemEventsListener listener;

    private Crime currentCrime;

    public CrimeViewHolder(@NonNull View itemView, CrimeListAdapter.ItemEventsListener listener) {
        super(itemView);

        this.listener = listener;

        titleView = itemView.findViewById(R.id.title);
        dateView = itemView.findViewById(R.id.date);
        solvedView = itemView.findViewById(R.id.solved);

        itemView.setOnClickListener(itemClickListener);
        itemView.setOnLongClickListener(itemLongClickListener);
    }

    private final View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.onItemClick(currentCrime);
        }
    };

    private final View.OnLongClickListener itemLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            listener.onLongItemClick(currentCrime);
            return true;
        }
    };

    public void bindTo(Crime crime) {
        this.currentCrime = crime;

        titleView.setText(crime.getTitle());
        dateView.setText(crime.getDate().toString());
        solvedView.setChecked(crime.isSolved());
    }
}
