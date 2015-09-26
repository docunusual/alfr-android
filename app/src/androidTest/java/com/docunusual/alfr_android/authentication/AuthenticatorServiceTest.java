package com.docunusual.alfr_android.authentication;

import android.content.Intent;
import android.test.ServiceTestCase;

import junit.framework.Assert;

public class AuthenticatorServiceTest extends ServiceTestCase<AuthenticatorService> {
    /**
     * Constructor
     *
     * @param serviceClass The type of the service under test.
     */
    public AuthenticatorServiceTest() {
        super(AuthenticatorService.class);
    }

    public void testCreate() throws Exception {
        startService(new Intent(getContext(), AuthenticatorService.class));
        Assert.assertTrue(getService().isCreated());
    }
}
