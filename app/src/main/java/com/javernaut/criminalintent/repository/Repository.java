package com.javernaut.criminalintent.repository;

import com.javernaut.criminalintent.model.Crime;

import java.util.List;
import java.util.UUID;

public interface Repository {
    List<Crime> getAllCrimes();

    Crime getCrimeById(UUID uuid);

    void generateRandomCrime();

    void delete(Crime crime);
}
