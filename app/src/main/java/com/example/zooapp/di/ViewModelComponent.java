package com.example.zooapp.di;

import com.example.zooapp.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ViewModelComponent {
    void inject(ListViewModel viewModel);
}
