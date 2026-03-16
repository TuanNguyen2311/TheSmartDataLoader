package com.android.thesmartdataloader.domain.usecase;

import com.android.thesmartdataloader.core.annotation.QualifierCore;
import com.android.thesmartdataloader.domain.callback.OnDataLoadedListener;
import com.android.thesmartdataloader.domain.models.Person;
import com.android.thesmartdataloader.domain.repository.IPersonRepository;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

public class GetPersonDataUseCase {
    private final IPersonRepository personRepository;

    @Inject
    public GetPersonDataUseCase(@QualifierCore.MemoryRepo IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void execute(WeakReference<OnDataLoadedListener<Person>> listener) throws InterruptedException {
        int dataLength = 1111;
        for(int i=1; i<=dataLength; i++) {
            Thread.sleep(10);
            personRepository.add(new Person("Name " + i, 30, "Gender " + i, i%5==0));
            if(i%100==0 || i==dataLength) {
                if(listener.get()!=null) {
                    listener.get().onLoaded(personRepository.getData());
                }
            }
        }
    }

    public void executeV2(OnDataLoadedListener<Person> listener) throws InterruptedException {
        int dataLength = 1111;
        for(int i=1; i<=dataLength; i++) {
            Thread.sleep(10);
            personRepository.add(new Person("Name " + i, 30, "Gender " + i, i%5==0));
            if(i%100==0 || i==dataLength) {
                if(listener!=null) {
                    listener.onLoaded(personRepository.getData());
                }
            }
        }
    }
}
