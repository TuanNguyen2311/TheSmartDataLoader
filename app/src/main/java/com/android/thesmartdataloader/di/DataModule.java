package com.android.thesmartdataloader.di;

import com.android.thesmartdataloader.core.annotation.QualifierCore;
import com.android.thesmartdataloader.data.repository.JsonPersonRepositoryImpl;
import com.android.thesmartdataloader.data.repository.PersonRepositoryImpl;
import com.android.thesmartdataloader.domain.repository.IPersonRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
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


}
