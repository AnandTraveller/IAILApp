package com.anandroid.iailapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;

import com.anandroid.iailapp.fragment.HomeScreen;
import com.anandroid.iailapp.utills.Constants;
import com.anandroid.iailapp.utills.FragmentKey;

import butterknife.ButterKnife;

public class MainAct extends AppCompatActivity {

    private static final String CURRENT_TAG = MainAct.class.getSimpleName();
    private String FROM_TAG;
    public String cusIdd = "";
    private Bundle data = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            initFragment();
        }


    }

    private void initFragment() {
        if (null == data)
            data = new Bundle();

        data.putInt(FragmentKey.INDEX, 0);
        data.putString(FROM_TAG, Constants.FROM_TAG);
        getSupportFragmentManager()
                .beginTransaction()
                // .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                .add(R.id.main_login_container, HomeScreen.newInstance(data), "LoginFrag")
                .addToBackStack("LoginFrag")
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {

        Fragment f = getSupportFragmentManager().findFragmentByTag("HomeScreen");
        Fragment summaryfrag = getSupportFragmentManager().findFragmentByTag("SummaryFrag");
        if (f instanceof HomeScreen) {//the fragment on which you want to handle your back press
            Log.i("BACK PRESSED", "BACK PRESSED");
        } else {
            //super.onBackPressed();
        }
    }

    public void uiBackPressed() {
        if (!popFragment()) {
            //finish();
            finish();
        }
    }

    public boolean popFragment() {
        boolean isPop = false;
        Fragment currentFragment = getSupportFragmentManager()
                .findFragmentById(R.id.main_login_container);
        //  LOGE(TAG, "POP Back Current Fragment " + currentFragment);
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                //onUpdateHomeActivity("CreateLeagueDialogFrag", true);
                // LOGI(TAG, "All Fragment Finished!");
                return false;
            }
            isPop = true;
        }
        return isPop;
    }

    public void inAppBrouser(String url) {

        Intent intent = new Intent(MainAct.this, BrowserActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    public void addHomeScreenFragment() {

        HomeScreen homeSc = HomeScreen.newInstance(data);
        homeSc.setEnterTransition(new Slide(Gravity.RIGHT));
        homeSc.setExitTransition(new Slide(Gravity.LEFT));
        data.putInt(FragmentKey.INDEX, 1);
        data.putString(FROM_TAG, Constants.FROM_TAG);
        getSupportFragmentManager()
                .beginTransaction()
                //   .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(R.id.main_login_container, homeSc, "HomeScreen")
                .addToBackStack("HomeScreen")
                .commitAllowingStateLoss();
    }

    public String jsonStr() {
        String str = "";


        return str;
    }

}
