package com.example.zooapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zooapp.model.AnimalModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<AnimalModel>> animals = new MutableLiveData<>();
    public MutableLiveData<Boolean> loadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public void refresh(){
        AnimalModel animal1 = new AnimalModel("leão");
        AnimalModel animal2 = new AnimalModel("gato");
        AnimalModel animal3 = new AnimalModel("cachorro");

        List<AnimalModel> animalList = new ArrayList<>();
        animalList.add(animal1);
        animalList.add(animal2);
        animalList.add(animal3);

        animals.setValue(animalList);
        loadError.setValue(false);
        loading.setValue(false);
    }
}
