package com.dxtdkwt.zzh.appframework.ipctest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.dxtdkwt.zzh.appframework.R;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ProviderActivity extends AppCompatActivity {

    private static final String TAG = "ProviderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        Uri bookUri = Uri.parse("content://com.dxtdkwt.zzh.appframework.ipctest/book");

        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "程序设计的艺术 ");

        getContentResolver().insert(bookUri, values);

        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{
                "_id", "name"
        }, null, null, null);

        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.id = bookCursor.getInt(0);
            book.name = bookCursor.getString(1);
            Log.d(TAG, "query book:" + book.toString());
        }

        bookCursor.close();

        Uri userUri = Uri.parse("content://com.dxtdkwt.zzh.appframework.ipctest/user");
        Cursor userCursor = getContentResolver().query(userUri, new String[]{
                "_id", "name", "sex"
        }, null, null, null);
        while (userCursor.moveToNext()) {
            User user = new User();
            user.userId = userCursor.getInt(0);
            user.userName = userCursor.getString(1);
            user.isMale = userCursor.getInt(2) == 1;
            Log.d(TAG, "query user:" + user.toString());
        }
        userCursor.close();
    }
}