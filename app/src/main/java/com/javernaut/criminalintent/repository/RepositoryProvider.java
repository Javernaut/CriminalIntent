package com.javernaut.criminalintent.repository;

import android.content.Context;

import com.javernaut.criminalintent.repository.room.RoomRepository;

public class RepositoryProvider {

    private static Repository INSTANCE;

    public static Repository getInstance(Context context) {
        if (INSTANCE == null) {
//            INSTANCE = new InMemoryRepository();
//            INSTANCE = new SharedPreferencesRepository(context);
            INSTANCE = new RoomRepository(context);
        }
        return INSTANCE;
    }

    private RepositoryProvider() {
    }

}
