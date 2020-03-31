package com.javernaut.criminalintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrimeListActivity extends AppCompatActivity {

    // Model
    private List<Crime> crimesList = generateDemoCrimes();

    // View
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CrimeListAdapter(crimesList));
    }

    private static List<Crime> generateDemoCrimes() {
        List<Crime> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); // Для каждого второго объекта
            result.add(crime);
        }
        return result;
    }
}
