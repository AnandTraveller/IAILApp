package com.anandroid.iailapp.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.anandroid.iailapp.R;


/**
 * Created by Charles on 3/7/2017.
 */

public class AboutDialogFragment extends DialogFragment {

    private TextView txt_cancel_dialo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_dialog, container, false);
        // getDialog().setTitle("Simple Dialog");
        getDialog().setCancelable(true);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        txt_cancel_dialo = (TextView) rootView.findViewById(R.id.txt_cancel_dialo);

     /*   myCurretnLocTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment mFrag = getActivity().getSupportFragmentManager().findFragmentById(R.id.main_login_container);
                if (mFrag instanceof FilterFragment) {
                    ((FilterFragment) mFrag).selectedMyLocation();
                    ((FilterFragment) mFrag).txtApplyFLTJ.setVisibility(View.VISIBLE);
                }
                getDialog().dismiss();
            }
        });*/


        txt_cancel_dialo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return rootView;
    }

}
