package com.android.thesmartdataloader.data.repository;

import com.android.thesmartdataloader.domain.repository.IPersonRepository;
import com.android.thesmartdataloader.domain.models.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonRepositoryImpl implements IPersonRepository {
    private final List<Person> persons = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void add(Person value) {
        persons.add(value);
    }
    @Override
    public void clearAll() {
        persons.clear();
    }
    @Override
    public List<Person> getData() {
        return new ArrayList<>(persons);
    }
}
