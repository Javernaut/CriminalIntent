package com.javernaut.criminalintent.repository.room;

import com.javernaut.criminalintent.model.Crime;

import java.util.Date;
import java.util.UUID;

public class Converter {

    static CrimeEntity convert(Crime crime) {
        CrimeEntity crimeEntity = new CrimeEntity();

        crimeEntity.id = crime.getId().toString();
        crimeEntity.title = crime.getTitle();
        crimeEntity.date = crime.getDate().getTime();
        crimeEntity.solved = crime.isSolved();

        return crimeEntity;
    }

    static Crime convert(CrimeEntity crime) {
        Crime result = new Crime();

        result.setId(UUID.fromString(crime.id));
        result.setTitle(crime.title);
        result.setDate(new Date(crime.date));
        result.setSolved(crime.solved);

        return result;
    }
}
