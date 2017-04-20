package com.anandroid.iailapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Anand Wekancode on 1/6/2017.
 */

public class Splash extends AppCompatActivity {

    //ImageView bg_logo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // user is not logged in redirect him to Login Activity
        Intent i = new Intent(Splash.this, MainAct.class);

        // Closing all the Activities from stack
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        startActivity(i);

    }
}
