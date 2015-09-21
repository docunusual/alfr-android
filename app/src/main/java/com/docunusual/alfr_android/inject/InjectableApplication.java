package com.docunusual.alfr_android.inject;

import android.app.Application;

import com.docunusual.alfr_android.DaggerModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public class InjectableApplication extends Application {

    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();
        graph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                (Object) new DaggerModule(this)
        );
    }

    public void inject(Object object) {
        graph.inject(object);
    }
}