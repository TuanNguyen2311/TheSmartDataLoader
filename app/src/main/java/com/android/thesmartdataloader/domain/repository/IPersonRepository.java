package com.android.thesmartdataloader.domain.repository;

import com.android.thesmartdataloader.domain.models.Person;

import java.util.List;

public interface IPersonRepository extends IRepository<Person>{
    void addAll(List<Person> values);
}
