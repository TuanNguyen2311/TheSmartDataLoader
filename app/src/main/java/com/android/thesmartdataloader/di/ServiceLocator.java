package com.android.thesmartdataloader.di;

import com.android.thesmartdataloader.data.repository.JsonPersonRepositoryImpl;
import com.android.thesmartdataloader.data.repository.PersonRepositoryImpl;
import com.android.thesmartdataloader.domain.repository.IPersonRepository;
import com.android.thesmartdataloader.domain.usecase.ClearPersonUseCase;
import com.android.thesmartdataloader.domain.usecase.GetPersonDataUseCase;
import com.android.thesmartdataloader.presentation.presenter.PersonPresenter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceLocator {
    private static final boolean USE_JSON = true;
    public static PersonPresenter providePersonPresenter() {
        IPersonRepository iPersonRepository = new PersonRepositoryImpl();
        if(USE_JSON) iPersonRepository = new JsonPersonRepositoryImpl();
        GetPersonDataUseCase getPersonDataUseCase = new GetPersonDataUseCase(iPersonRepository);
        ClearPersonUseCase clearPersonUseCase = new ClearPersonUseCase(iPersonRepository);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        return new PersonPresenter(getPersonDataUseCase, clearPersonUseCase, executorService);
    }

}
