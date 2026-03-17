package com.android.thesmartdataloader.di;

import android.content.Context;

import androidx.room.Room;

import com.android.thesmartdataloader.core.annotation.QualifierCore;
import com.android.thesmartdataloader.data.datasource.AppDatabase;
import com.android.thesmartdataloader.data.datasource.local.dao.PersonDao;
import com.android.thesmartdataloader.data.repository.JsonPersonRepositoryImpl;
import com.android.thesmartdataloader.data.repository.PersonRepositoryImpl;
import com.android.thesmartdataloader.data.repository.RoomPersonRepositoryImpl;
import com.android.thesmartdataloader.domain.repository.IPersonRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataModule {
    @Provides
    @QualifierCore.BackgroundTasks
    public ExecutorService provideBackgroundExecutorService() {
        return Executors.newFixedThreadPool(8);
    }

    @Provides
    @QualifierCore.LightweightTasks
    public ExecutorService provideLightweightExecutorService() {
        return Executors.newFixedThreadPool(4);
    }

    @Provides
    @Singleton
    @QualifierCore.MemoryRepo
    public IPersonRepository provideMemoryPersonRepository() {
        return new PersonRepositoryImpl();
    }

    @Provides
    @Singleton
    @QualifierCore.JsonRepo
    public IPersonRepository provideJsonPersonRepository() {
        return new JsonPersonRepositoryImpl();
    }

    @Provides
    @Singleton
    @QualifierCore.RoomRepo
    public IPersonRepository provideRoomPersonRepository(PersonDao personDao) {
        return new RoomPersonRepositoryImpl(personDao);
    }

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "smart_data.loader.db").build();
    }

    @Provides
    public PersonDao providePersonDao(AppDatabase appDatabase) {
        return appDatabase.personDao();
    }

}
