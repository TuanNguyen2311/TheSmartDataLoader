package com.android.thesmartdataloader.presentation.presenter;

import android.os.Handler;
import android.os.Looper;

import com.android.thesmartdataloader.core.annotation.QualifierCore;
import com.android.thesmartdataloader.domain.repository.IRepository;
import com.android.thesmartdataloader.domain.callback.OnDataLoadedListener;
import com.android.thesmartdataloader.domain.models.Person;
import com.android.thesmartdataloader.domain.usecase.ClearPersonUseCase;
import com.android.thesmartdataloader.domain.usecase.GetPersonDataUseCase;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class PersonPresenter {

    private final ExecutorService executorService;
    private final GetPersonDataUseCase getPersonDataUseCase;
    private final ClearPersonUseCase clearPersonUseCase;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());


    @Inject
    public PersonPresenter(GetPersonDataUseCase getPersonDataUseCase, ClearPersonUseCase clearPersonUseCase,@QualifierCore.LightweightTasks ExecutorService executorService) {
        this.getPersonDataUseCase = getPersonDataUseCase;
        this.clearPersonUseCase = clearPersonUseCase;
        this.executorService = executorService;
    }


    public void clearAll(){
        executorService.execute(clearPersonUseCase::execute);
    }

    public void getData(WeakReference<OnDataLoadedListener<Person>> listener) {
        executorService.execute(() -> {
            try {
                getPersonDataUseCase.execute(new WeakReference<>((OnDataLoadedListener<Person>) result -> {
                    if(listener.get()!=null) mainHandler.post(() -> listener.get().onLoaded(result));
                }));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    };

    public void shutdown() {
        executorService.shutdownNow();
    }
}
