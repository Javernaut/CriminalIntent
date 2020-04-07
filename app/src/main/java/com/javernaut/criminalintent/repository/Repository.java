package com.javernaut.criminalintent.repository;

import com.javernaut.criminalintent.model.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Repository {

    private static Repository INSTANCE;

    public static Repository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }
        return INSTANCE;
    }

    private Repository() {
    }

    // Model
    private final List<Crime> crimeList = generateDemoCrimes();

    public List<Crime> getAllCrimes() {
        return new ArrayList<>(crimeList);
    }

    public Crime getCrimeById(UUID uuid) {
        for (Crime crime : crimeList) {
            if (crime.getId().equals(uuid)) {
                return crime;
            }
        }
        return null;
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
