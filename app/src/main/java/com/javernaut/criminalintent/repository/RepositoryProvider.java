package com.javernaut.criminalintent.repository;

public class RepositoryProvider {

    private static Repository INSTANCE;

    public static Repository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }
        return INSTANCE;
    }

    private RepositoryProvider() {
    }

}
