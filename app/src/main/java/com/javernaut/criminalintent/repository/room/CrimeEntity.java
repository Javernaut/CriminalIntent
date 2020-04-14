package com.javernaut.criminalintent.repository.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CrimeEntity {

    @PrimaryKey
    @NonNull
    public String id;
    public String title;
    public long date;
    public boolean solved;

}
