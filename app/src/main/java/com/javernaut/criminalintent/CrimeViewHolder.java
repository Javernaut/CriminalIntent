package com.javernaut.criminalintent;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class CrimeViewHolder extends RecyclerView.ViewHolder {

    private final TextView titleView;
    private final TextView dateView;
    private final CheckBox solvedView;

    public CrimeViewHolder(@NonNull View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.title);
        dateView = itemView.findViewById(R.id.date);
        solvedView = itemView.findViewById(R.id.solved);
    }

    public void bindTo(Crime crime) {
        titleView.setText(crime.getTitle());
        dateView.setText(crime.getDate().toString());
        solvedView.setChecked(crime.isSolved());
    }
}
