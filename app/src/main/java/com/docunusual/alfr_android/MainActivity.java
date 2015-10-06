package com.docunusual.alfr_android;

import android.accounts.Account;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.docunusual.alfr_android.authentication.AccountCreator;
import com.docunusual.alfr_android.db.AlfrContract;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText msgEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Account account = AccountCreator.CreateSyncAccount(this);
        getContentResolver().registerContentObserver(AlfrContract.Events.CONTENT_URI, true, new ContentObserver(null) {
            @Override
            public void onChange(boolean selfChange) {
                onChange(selfChange, null);
            }

            @Override
            public void onChange(boolean selfChange, Uri uri) {
                Log.i("ALFR", "ContentObserver.CHANGE");
                AccountCreator.TriggerRefresh(account);
            }
        });

        setContentView(R.layout.activity_main);
        msgEdit = (EditText) findViewById(R.id.msg_edit);
        findViewById(R.id.send_btn).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_btn:
                final String textMessage = msgEdit.getText().toString();
                msgEdit.setText(null);
                final ContentValues values = new ContentValues(2);
                values.put(AlfrContract.Events.COLUMN_INLINE, textMessage);
                values.put(AlfrContract.Events.COLUMN_BY, "Ich");
                getContentResolver().insert(AlfrContract.Events.CONTENT_URI, values);
                break;
        }
    }
}
