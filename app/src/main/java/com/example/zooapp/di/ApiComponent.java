package com.example.zooapp.di;

import com.example.zooapp.model.AnimalApiService;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(AnimalApiService service);
}
