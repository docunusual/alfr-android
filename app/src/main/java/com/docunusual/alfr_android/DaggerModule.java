package com.docunusual.alfr_android;

import android.content.Context;

import com.docunusual.alfr_android.inject.Application;

import dagger.Module;
import dagger.Provides;

@Module(injects = {MainActivity.class})
public class DaggerModule {

    private final Context appContext;

    public DaggerModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Application
    public Context provideContext() {
        return appContext;
    }

}
