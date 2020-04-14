package com.javernaut.criminalintent.repository.room;

import android.content.Context;

import androidx.room.Room;

import com.javernaut.criminalintent.model.Crime;
import com.javernaut.criminalintent.repository.InMemoryRepository;
import com.javernaut.criminalintent.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomRepository implements Repository {

    private CrimesDao crimesDao;

    public RoomRepository(Context context) {
        crimesDao = Room.databaseBuilder(
                context,
                CrimeDatabase.class,
                "crime-database.sqlite"
        )
                .allowMainThreadQueries()
                .build()
                .getCrimeDao();
    }

    @Override
    public List<Crime> getAllCrimes() {
        List<CrimeEntity> crimeEntities = crimesDao.getAll();

        List<Crime> resultList = new ArrayList<>();

        for (CrimeEntity crimeEntity : crimeEntities) {
            resultList.add(Converter.convert(crimeEntity));
        }

        return resultList;
    }

    @Override
    public Crime getCrimeById(UUID uuid) {
        CrimeEntity crimeEntity = crimesDao.getById(uuid.toString());
        return Converter.convert(crimeEntity);
    }

    @Override
    public void generateRandomCrime() {
        Crime crime = InMemoryRepository.getRandomCrime();
        crimesDao.add(Converter.convert(crime));
    }

    @Override
    public void delete(Crime crime) {
        crimesDao.delete(Converter.convert(crime));
    }

    @Override
    public void update(Crime crime) {
        crimesDao.update(Converter.convert(crime));
    }
}
