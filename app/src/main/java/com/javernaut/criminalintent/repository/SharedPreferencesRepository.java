package com.javernaut.criminalintent.repository;

import android.content.Context;

import com.javernaut.criminalintent.model.Crime;

import java.util.List;
import java.util.UUID;

class SharedPreferencesRepository implements Repository {

    SharedPreferencesRepository(Context context) {

    }

    @Override
    public List<Crime> getAllCrimes() {
        return null;
    }

    @Override
    public Crime getCrimeById(UUID uuid) {
        return null;
    }

    @Override
    public void generateRandomCrime() {

    }

    @Override
    public void delete(Crime crime) {

    }

}
