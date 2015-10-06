package com.docunusual.alfr_android.authentication;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.docunusual.alfr_android.db.AlfrContract;

public class AccountCreator {

    private static final String ACCOUNT_TYPE = "com.docunusual.alfr";
    public static final String ACCOUNT = "myaccount2";
    private static final String PREF_SETUP_COMPLETE = "setup_complete";

    /**
     * Create a new dummy account for the sync adapter
     *
     * @param context The application context
     */
    public static Account CreateSyncAccount(Context context) {

        boolean newAccount = false;
        boolean setupComplete = PreferenceManager
                .getDefaultSharedPreferences(context).getBoolean(PREF_SETUP_COMPLETE, false);

        // Create the account type and default account
        Account account = new Account(ACCOUNT, ACCOUNT_TYPE);
        // Get an instance of the Android account manager
        AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
        if (accountManager.addAccountExplicitly(account, null, null)) {
            Log.i("ALFR", "Create new account");
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
            ContentResolver.setIsSyncable(account, AlfrContract.AUTHORITY, 1);
            ContentResolver.setSyncAutomatically(account, AlfrContract.AUTHORITY, true);
            //ContentResolver.addPeriodicSync(account, AlfrContract.AUTHORITY, new Bundle(), 5);
            newAccount = true;
        }

        // Schedule an initial sync if we detect problems with either our account or our local
        // data has been deleted. (Note that it's possible to clear app data WITHOUT affecting
        // the account list, so wee need to check both.)
        if (newAccount || !setupComplete) {
            TriggerRefresh(account);
            PreferenceManager.getDefaultSharedPreferences(context).edit()
                    .putBoolean(PREF_SETUP_COMPLETE, true).commit();
        }

        return account;
    }

    /**
     * Helper method to trigger an immediate sync ("refresh").
     * <p/>
     * <p>This should only be used when we need to preempt the normal sync schedule. Typically, this
     * means the user has pressed the "refresh" button.
     * <p/>
     * Note that SYNC_EXTRAS_MANUAL will cause an immediate sync, without any optimization to
     * preserve battery life. If you know new data is available (perhaps via a GCM notification),
     * but the user is not actively waiting for that data, you should omit this flag; this will give
     * the OS additional freedom in scheduling your sync request.
     *
     * @param account
     */
    public static void TriggerRefresh(Account account) {
        Bundle b = new Bundle();
        // Disable sync backoff and ignore sync preferences. In other words...perform sync NOW!
        b.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        b.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        ContentResolver.requestSync(
                account, // Sync account
                AlfrContract.AUTHORITY,                 // Content authority
                b);                                             // Extras
    }
}
