package com.android.thesmartdataloader.data.repository;

import com.android.thesmartdataloader.core.loggers.DebugLogger;
import com.android.thesmartdataloader.domain.models.Person;
import com.android.thesmartdataloader.domain.repository.IPersonRepository;

import java.util.Collections;
import java.util.List;

public class JsonPersonRepositoryImpl implements IPersonRepository {
    private final DebugLogger debugLogger = new DebugLogger();

    @Override
    public void add(Person value) {
        debugLogger.log("Add person by JsonPersonRepositoryImpl");
    }

    @Override
    public void clearAll() {
        debugLogger.log("Clear persons by JsonPersonRepositoryImpl");
    }

    @Override
    public List<Person> getData() {
        debugLogger.log("Get persons by JsonPersonRepositoryImpl");
        return Collections.emptyList();
    }
}
