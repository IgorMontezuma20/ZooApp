package com.example.zooapp.di;

import com.example.zooapp.model.AnimalApi;
import com.example.zooapp.model.AnimalApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final String BASE_URL = "https://us-central1-apis-4674e.cloudfunctions.net";

    @Provides
    public AnimalApi provideAnimalApi() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(AnimalApi.class);
    }

    @Provides
    public AnimalApiService provideAnimalApiService() {
        return new AnimalApiService();
    }
}
