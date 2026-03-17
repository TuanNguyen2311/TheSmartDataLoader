package com.android.thesmartdataloader.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.thesmartdataloader.core.annotation.QualifierCore;
import com.android.thesmartdataloader.domain.callback.OnDataLoadedListener;
import com.android.thesmartdataloader.domain.models.Person;
import com.android.thesmartdataloader.domain.usecase.ClearPersonUseCase;
import com.android.thesmartdataloader.domain.usecase.GetPersonDataUseCase;
import com.android.thesmartdataloader.domain.usecase.InsertPersonDataUseCase;
import com.android.thesmartdataloader.presentation.Resource;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PersonViewModel extends ViewModel {
    private final GetPersonDataUseCase getPersonDataUseCase;
    private final ClearPersonUseCase clearPersonUseCase;
    private final InsertPersonDataUseCase insertPersonDataUseCase;
    private final ExecutorService executorService;

    private final MutableLiveData<Resource<List<Person>>> _personList = new MutableLiveData<>();
    public LiveData<Resource<List<Person>>> personList = _personList;


    public LiveData<Resource<List<Person>>> getPersonList() {
        return personList;
    }

    @Inject
    public PersonViewModel(InsertPersonDataUseCase insertPersonDataUseCase, GetPersonDataUseCase getPersonDataUseCase, ClearPersonUseCase clearPersonUseCase,@QualifierCore.LightweightTasks ExecutorService executorService) {
        this.insertPersonDataUseCase  = insertPersonDataUseCase;
        this.getPersonDataUseCase = getPersonDataUseCase;
        this.clearPersonUseCase = clearPersonUseCase;
        this.executorService = executorService;
    }

    public void clearAll(){
        executorService.execute(clearPersonUseCase::execute);
    }

    public void addPersonDataForTesting(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    insertPersonDataUseCase.execute();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void loadPersonData() {
        if(_personList.getValue() != null && _personList.getValue().status == Resource.Status.LOADING) return;

        _personList.postValue(Resource.loading());

        executorService.execute(() -> {
            try {
                getPersonDataUseCase.executeV2(new OnDataLoadedListener<Person>() {
                    @Override
                    public void onLoaded(List<Person> result) {
                        _personList.postValue(Resource.success(result));
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    };
}
