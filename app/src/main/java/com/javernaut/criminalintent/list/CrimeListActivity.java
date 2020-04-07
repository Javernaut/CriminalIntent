package com.javernaut.criminalintent.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.javernaut.criminalintent.R;
import com.javernaut.criminalintent.model.Crime;

import java.util.ArrayList;
import java.util.List;

public class CrimeListActivity extends AppCompatActivity {

    // Model
    public static final List<Crime> CRIMES_LIST = generateDemoCrimes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_list);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragmentContainer) == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, new CrimeListFragment())
                    .commit();
        }
    }

    private static List<Crime> generateDemoCrimes() {
        List<Crime> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); // Для каждого второго объекта
            result.add(crime);
        }
        return result;
    }
}
