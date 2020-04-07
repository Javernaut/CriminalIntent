package com.javernaut.criminalintent.repository;

import com.javernaut.criminalintent.model.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    private final List<Crime> crimeList = new ArrayList<>();

    public List<Crime> getAllCrimes() {
        return crimeList;
    }

    public Crime getCrimeById(UUID uuid) {
        for (Crime crime : crimeList) {
            if (crime.getId().equals(uuid)) {
                return crime;
            }
        }
        return null;
    }

    public void generateRandomCrime() {
        Random random = new Random();
        Crime crime = new Crime();
        crime.setTitle("Crime #" + random.nextInt());
        crime.setSolved(random.nextBoolean());
        crimeList.add(crime);
    }

    public void delete(Crime crime) {
        crimeList.remove(crime);
    }
}
