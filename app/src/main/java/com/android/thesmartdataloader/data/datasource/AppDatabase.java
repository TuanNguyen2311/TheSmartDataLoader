package com.android.thesmartdataloader.data.datasource;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android.thesmartdataloader.data.datasource.local.dao.PersonDao;
import com.android.thesmartdataloader.domain.models.Person;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}
