package com.android.thesmartdataloader.domain.usecase;

import com.android.thesmartdataloader.core.annotation.QualifierCore;
import com.android.thesmartdataloader.domain.models.Person;
import com.android.thesmartdataloader.domain.repository.IPersonRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class InsertPersonDataUseCase {
    private final IPersonRepository personRepository;

    @Inject
    public InsertPersonDataUseCase(@QualifierCore.RoomRepo IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void execute() throws InterruptedException {
        int dataLength = 1111;
        List<Person> buffer = new ArrayList<>();
        for(int i=1; i<=dataLength; i++) {
            Thread.sleep(10);
            buffer.add(new Person("Name " + i, 30, "Gender " + i, i%5==0));
            if(i%100==0 || i==dataLength) {
                personRepository.addAll(new ArrayList<>(buffer));
                buffer.clear();
            }
        }
    }
}
