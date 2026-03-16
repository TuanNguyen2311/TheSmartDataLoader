package com.android.thesmartdataloader.domain.usecase;

import com.android.thesmartdataloader.core.annotation.QualifierCore;
import com.android.thesmartdataloader.domain.repository.IPersonRepository;

import javax.inject.Inject;

public class ClearPersonUseCase {
    private final IPersonRepository personRepository;
    @Inject
    public ClearPersonUseCase(@QualifierCore.MemoryRepo IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void execute() {
        personRepository.clearAll();
    }
}
