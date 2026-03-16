package com.android.thesmartdataloader.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.thesmartdataloader.core.annotation.QualifierCore;
import com.android.thesmartdataloader.domain.callback.OnDataLoadedListener;
import com.android.thesmartdataloader.domain.models.Person;
import com.android.thesmartdataloader.domain.usecase.ClearPersonUseCase;
import com.android.thesmartdataloader.domain.usecase.GetPersonDataUseCase;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PersonViewModel extends ViewModel {
    private final GetPersonDataUseCase getPersonDataUseCase;
    private final ClearPersonUseCase clearPersonUseCase;
    private final ExecutorService executorService;

    private final MutableLiveData<List<Person>> _personList = new MutableLiveData<>();
    public LiveData<List<Person>> personList = _personList;


    public LiveData<List<Person>> getPersonList() {
        return personList;
    }

    @Inject
    public PersonViewModel(GetPersonDataUseCase getPersonDataUseCase, ClearPersonUseCase clearPersonUseCase,@QualifierCore.LightweightTasks ExecutorService executorService) {
        this.getPersonDataUseCase = getPersonDataUseCase;
        this.clearPersonUseCase = clearPersonUseCase;
        this.executorService = executorService;
    }

    public void clearAll(){
        executorService.execute(clearPersonUseCase::execute);
    }


    public void loadPersonData() {
        executorService.execute(() -> {
            try {
                getPersonDataUseCase.executeV2((OnDataLoadedListener<Person>) _personList::postValue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    };
}
