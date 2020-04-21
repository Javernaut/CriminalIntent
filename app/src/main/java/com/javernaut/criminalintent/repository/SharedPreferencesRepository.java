package com.javernaut.criminalintent.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.javernaut.criminalintent.model.Crime;

import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

class SharedPreferencesRepository extends BaseRepository {

    private static final String KEY_CRIMES = "key_crimes";

    private final SharedPreferences preferences;


    SharedPreferencesRepository(Context context) {
        preferences = context.getSharedPreferences("repository.prefs", Context.MODE_PRIVATE);
    }

    @Override
    public List<Crime> getAllCrimes() {
        String encodedCrimes = preferences.getString(KEY_CRIMES, "[]");

        Gson gson = new Gson();

        Type listType = new TypeToken<List<Crime>>() {
        }.getType();
        List<Crime> result = gson.fromJson(encodedCrimes, listType);

        return result;
    }

    @Override
    public Crime getCrimeById(UUID uuid) {
        List<Crime> allCrimes = getAllCrimes();
        for (Crime crime : allCrimes) {
            if (crime.getId().equals(uuid)) {
                return crime;
            }
        }
        return null;
    }

    @Override
    public void generateRandomCrime() {
        Crime randomCrime = InMemoryRepository.getRandomCrime();

        List<Crime> allCrimes = getAllCrimes();
        allCrimes.add(randomCrime);

        Gson gson = new Gson();
        preferences.edit()
                .putString(KEY_CRIMES, gson.toJson(allCrimes))
                .apply();

        notifyListeners();
    }

    @Override
    public void delete(Crime crime) {
        List<Crime> allCrimes = getAllCrimes();

        for (Crime savedCrime : allCrimes) {
            if (savedCrime.getId().equals(crime.getId())) {
                allCrimes.remove(savedCrime);
                break;
            }
        }

        Gson gson = new Gson();
        preferences.edit()
                .putString(KEY_CRIMES, gson.toJson(allCrimes))
                .apply();

        notifyListeners();
    }

    @Override
    public void update(Crime crime) {
        // TODO implement me

        notifyListeners();
    }

}
