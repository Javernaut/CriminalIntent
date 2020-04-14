package com.javernaut.criminalintent.repository.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CrimesDao {

    @Query("SELECT * FROM CrimeEntity")
    List<CrimeEntity> getAll();

    @Query("SELECT * FROM CrimeEntity WHERE id == :id")
    CrimeEntity getById(String id);

    @Insert
    void add(CrimeEntity entity);

    @Delete
    void delete(CrimeEntity entity);

}
