package com.android.thesmartdataloader.data.datasource.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.android.thesmartdataloader.domain.models.Person;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Person person);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Person> persons);

    @Query("SELECT * FROM person")
    List<Person> getAllPeople();

    @Query("DELETE FROM person")
    void deleteAll();
}
