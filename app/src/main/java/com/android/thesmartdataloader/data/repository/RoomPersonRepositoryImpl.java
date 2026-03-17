package com.android.thesmartdataloader.data.repository;

import com.android.thesmartdataloader.data.datasource.local.dao.PersonDao;
import com.android.thesmartdataloader.domain.models.Person;
import com.android.thesmartdataloader.domain.repository.IPersonRepository;

import java.util.List;

import javax.inject.Inject;

public class RoomPersonRepositoryImpl implements IPersonRepository {
    private final PersonDao personDao;

    @Inject
    public RoomPersonRepositoryImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void add(Person value) {
        personDao.insert(value);
    }

    @Override
    public void addAll(List<Person> values) {
        personDao.insertAll(values);
    }

    @Override
    public void clearAll() {
        personDao.deleteAll();
    }

    @Override
    public List<Person> getData() {
        return personDao.getAllPeople();
    }


}
