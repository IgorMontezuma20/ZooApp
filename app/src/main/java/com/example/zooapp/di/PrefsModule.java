package com.example.zooapp.di;

import static com.example.zooapp.di.TypeOfContext.CONTEXT_ACTIVITY;
import static com.example.zooapp.di.TypeOfContext.CONTEXT_APP;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zooapp.util.SharedPreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PrefsModule {

    @Provides
    @Singleton
    @TypeOfContext(CONTEXT_APP)
    public SharedPreferencesHelper providesSharedPreferences(Application app){
        return new SharedPreferencesHelper(app);
    }

    @Provides
    @Singleton
    @TypeOfContext(CONTEXT_ACTIVITY)
    public SharedPreferencesHelper providesActivitySharedPreferences(AppCompatActivity activity){
        return new SharedPreferencesHelper(activity);
    }
}
