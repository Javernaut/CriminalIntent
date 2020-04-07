package com.javernaut.criminalintent.repository;

import android.content.Context;

public class RepositoryProvider {

    private static Repository INSTANCE;

    public static Repository getInstance(Context context) {
        if (INSTANCE == null) {
//            INSTANCE = new InMemoryRepository();
            INSTANCE = new SharedPreferencesRepository(context);
        }
        return INSTANCE;
    }

    private RepositoryProvider() {
    }

}
