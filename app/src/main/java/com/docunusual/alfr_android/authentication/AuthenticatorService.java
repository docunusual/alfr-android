package com.docunusual.alfr_android.authentication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * A bound Service that instantiates the authenticator
 * when started.
 */
public class AuthenticatorService extends Service {

    // Instance field that stores the authenticator object
    private Authenticator mAuthenticator;
    private boolean isCreated = false;

    @Override
    public void onCreate() {
        // Create a new authenticator object
        Log.i("ALFR", "AuthenticatorService.onCreate");
        mAuthenticator = new Authenticator(this);
        isCreated = true;
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("ALFR", "AuthenticatorService.onBind");
        return mAuthenticator.getIBinder();
    }

    public boolean isCreated() {
        return isCreated;
    }
}