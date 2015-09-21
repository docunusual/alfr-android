package com.docunusual.alfr_android.inject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class InjectableActionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((InjectableApplication) getApplication()).inject(this);
    }

}
