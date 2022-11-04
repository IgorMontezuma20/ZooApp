package com.example.zooapp.viewmodel;

import static com.example.zooapp.di.TypeOfContext.CONTEXT_APP;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zooapp.di.AppModule;
import com.example.zooapp.di.DaggerViewModelComponent;
import com.example.zooapp.di.TypeOfContext;
import com.example.zooapp.model.AnimalApiService;
import com.example.zooapp.model.AnimalModel;
import com.example.zooapp.model.ApiKeyModel;
import com.example.zooapp.util.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends AndroidViewModel {

    @Inject
    AnimalApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<List<AnimalModel>> animals = new MutableLiveData<>();
    public MutableLiveData<Boolean> loadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    @Inject
            @TypeOfContext(CONTEXT_APP)
    SharedPreferencesHelper prefs;

    private Boolean invalidApiKey = false;

    public ListViewModel(Application application) {
        super(application);
        DaggerViewModelComponent.builder()
                .appModule(new AppModule(getApplication()))
                .build()
                .inject(this);
    }

    public void refresh() {
        loading.setValue(true);
        invalidApiKey = false;
        String key = prefs.getApiKey();
        if (key == null || key.equals("")) {
            getKey();
        } else {
            getAnimals(key);
        }
    }

    public void hardRefresh(){
        loading.setValue(true);
        getKey();
    }

    private void getKey() {
        disposable.add(
                apiService.getApiKey()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ApiKeyModel>() {
                            @Override
                            public void onSuccess(ApiKeyModel key) {
                                if (key.key.isEmpty()) {
                                    loadError.setValue(true);
                                    loading.setValue(false);
                                } else {
                                    prefs.saveApiKey(key.key);
                                    getAnimals(key.key);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                loading.setValue(false);
                                loadError.setValue(true);
                            }
                        })
        );
    }

    private void getAnimals(String key) {
        disposable.add(
                apiService.getAnimals(key)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<AnimalModel>>() {

                            @Override
                            public void onSuccess(List<AnimalModel> animalModels) {
                                loadError.setValue(false);
                                animals.setValue(animalModels);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (!invalidApiKey) {
                                    invalidApiKey = true;
                                    getKey();
                                } else {
                                    e.printStackTrace();
                                    loading.setValue(false);
                                    loadError.setValue(true);
                                }

                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
