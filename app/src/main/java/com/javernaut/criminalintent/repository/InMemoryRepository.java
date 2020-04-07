package com.javernaut.criminalintent.repository;

import com.javernaut.criminalintent.model.Crime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

class InMemoryRepository implements Repository {

    private final List<Crime> crimeList = new ArrayList<>();

    InMemoryRepository() {
    }

    @Override
    public List<Crime> getAllCrimes() {
        return crimeList;
    }

    @Override
    public Crime getCrimeById(UUID uuid) {
        for (Crime crime : crimeList) {
            if (crime.getId().equals(uuid)) {
                return crime;
            }
        }
        return null;
    }

    @Override
    public void generateRandomCrime() {
        Random random = new Random();
        Crime crime = new Crime();
        crime.setTitle("Crime #" + random.nextInt());
        crime.setSolved(random.nextBoolean());
        crimeList.add(crime);
    }

    @Override
    public void delete(Crime crime) {
        crimeList.remove(crime);
    }
}
