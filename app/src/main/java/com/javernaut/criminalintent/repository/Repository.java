package com.javernaut.criminalintent.repository;

import com.javernaut.criminalintent.model.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Repository {

    private final List<Crime> crimeList = new ArrayList<>();

    Repository() {
    }

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
