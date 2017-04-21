package com.anandroid.iailapp.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.anandroid.iailapp.R;
import com.anandroid.iailapp.adapter.ListDisplayAdapter;
import com.anandroid.iailapp.adapter.ListMod;
import com.anandroid.iailapp.app.AppController;
import com.anandroid.iailapp.utills.Const;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Admin on 3/13/2017.
 */

public class HomeScreen extends Fragment {
    private String TAG = HomeScreen.class.getSimpleName();
    private ListDisplayAdapter listDisplayAdapter;

    @BindView(R.id.homescr_recyc_display)
    RecyclerView homescr_recyc_display;
    @BindView(R.id.edt_search)
    EditText edt_searchJ;
    @BindView(R.id.home_about_img)
    ImageView home_about_imgJ;
    ArrayList<ListMod> arrayList;

    // These tags will be used to cancel the requests
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
/*
    private TextView resultTextView;
    private QRCodeReaderView qrCodeReaderView;
    private CheckBox flashlightCheckBox;
    private CheckBox enableDecodingCheckBox;
    private PointsOverlayView pointsOverlayView;
    private Set<String> setDatas;
    private ArrayList<String> listDatas;
    private int[] arrayNum;
    private InputMethodManager imm;
    private ViewGroup viewContainer;
    private boolean isAlreadyRunning = false;
*/

    /* private ListDisplayAdapter ListDisplayAdapter;
     @BindView(R.id.homescr_recyc_display)
     RecyclerView homescr_recyc_display;
     @BindView(R.id.total_txt)
     TextView total_txtJ;
     @BindView(R.id.confirm_img)
     ImageView confirm_imgJ;
     @BindView(R.id.progres_bar_homepage)
     ProgressBar progres_bar_homepage;
     private QrGene task;
 */
    public final static int QRcodeWidth = 500;
    Bitmap bitmap;

    public HomeScreen() {
        super();
        setRetainInstance(true);
    }

    private static final String CURRENT_TAG = HomeScreen.class.getSimpleName();
    private Unbinder unbinder;
    public static final int INDEX = 0;
    private Bundle data;
    private int hideIndex = -1;
    protected AppCompatActivity mBaseAct;
    protected Context mBaseCon;


    public static HomeScreen newInstance(Bundle tag) {
        HomeScreen fragment = new HomeScreen();
        Bundle args = tag;
        fragment.setArguments(args);
        return fragment;
    }

    public void onAttach(Context context) {
        mBaseCon = context;
        if ((mBaseCon instanceof AppCompatActivity)) {
            mBaseAct = (AppCompatActivity) context;
        }
        super.onAttach(context);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
        } else {
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //Hide the soft keyboard
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.home_screen_frag, container, false);
        unbinder = ButterKnife.bind(this, viewRoot);

        return viewRoot;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

                 /* Set Text Watcher listener */
        edt_searchJ.addTextChangedListener(textSearch);
        //888
        // Async Task
        new DownloadWebPageTask().execute();

        // Offile line Json Parse From Assert File
        // offlineJson();

        // Online json parse
        // makeJsonObjReq();

        home_about_imgJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new AboutDialogFragment();
                newFragment.show(getFragmentManager(), "dialog");


            }
        });


    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onPause() {
        super.onPause();

    }


    /* @OnClick(R.id.chat_back_img)
    public void onUiBackPressed(View view) {

       *//* getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        if(getActivity().getSupportFragmentManager().getBackStackEntryCount()==0){
        }*//*

        ((MainAC) getActivity()).uiBackPressed();
    }*/

    @Override

    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @Override
    public void onDetach() {
        unbinder = null;
        mBaseAct = null;
        mBaseCon = null;
        super.onDetach();
    }

    private void makeJsonObjReq() {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Const.URL_JSON_OBJECT, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        String addedValue = "";
                        try {
                            JSONArray jArray = response.getJSONArray("Sheet1");
                            //JSONArray jArray = (JSONArray) response.get("Sheet1");
                            Log.d("JsonArray DATAS", jArray.toString());
                            arrayList = new ArrayList<ListMod>();

                            for (int i = 0; i < jArray.length(); i++) {

                                JSONObject jobj = jArray.getJSONObject(i);

                                // Passing Values to Model
                                ListMod listMod = new ListMod();
                                listMod.setTitle(jobj.get("title").toString());
                                listMod.setUrl(jobj.get("url").toString());
                                listMod.setAuthour(jobj.get("author").toString());
                                listMod.setYear(jobj.get("year").toString());

                                addedValue += listMod.getTitle() + " " + listMod.getYear() + "\n";
                                Log.d("Inside  DATAS", "" + addedValue);
                                Log.d("Inside JsonArray DATAS", "" + jArray.length());

                                arrayList.add(listMod);
                            }


                            listDisplayAdapter = new ListDisplayAdapter(HomeScreen.this, arrayList);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            homescr_recyc_display.setLayoutManager(layoutManager);
                            homescr_recyc_display.setItemAnimator(new DefaultItemAnimator());
                            homescr_recyc_display.smoothScrollToPosition(0);
                            homescr_recyc_display.setHasFixedSize(true);
                            homescr_recyc_display.setAdapter(listDisplayAdapter);
                            /*try {
                                data = getArguments();
                                if (null != data) {
                                    hideIndex = data.getInt(FragmentKey.HIDE_INDEX, -1);
                                    LOGI(TAG, "Hide Index is : " + hideIndex);
                                }

                            } catch (Exception e) {
                            }
*/

                            //    msgResponse.setText(addedValue);
                            // msgResponse.setText(response.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
              /*  params.put("name", "Androidhive");
                params.put("email", "abc@androidhive.info");
                params.put("pass", "password123");*/
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }


    private final TextWatcher textSearch = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //  textView.setVisibility(View.VISIBLE);
        }

        public void afterTextChanged(Editable s) {
            String input = s.toString();
            if (s.length() > 0) {
                if (arrayList != null) {
                    Log.i("Inside 1 ", "Entered --- " + input);

                    ArrayList<ListMod> tempSearch = new ArrayList<>();
                    for (ListMod list : arrayList) {

                        String temp = list.getTitle() + " | " + list.getYear() + " | " + list.getAuthour();
                        Log.i("Inside 2 ", "Entered --- " + input + "  ---  " + temp);
                        Log.i("Inside 2 ", "Entered --- " + input + "  ---2-  " + list.getTitle());

                        if (temp.toLowerCase().contains(input.toLowerCase())) {
                            Log.i("Inside Search Loop ", "" + list.getTitle() + "   --   " + list.getYear());
                            tempSearch.add(list);
                        }

                    }
                    // Adding Searched Results
                    listDisplayAdapter.updateList(tempSearch);
                    homescr_recyc_display.setAdapter(listDisplayAdapter);

                }
            } else {
                listDisplayAdapter.updateList(arrayList);
                homescr_recyc_display.setAdapter(listDisplayAdapter);

            }
        }
    };

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("list_data_offline.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void offlineJson() {
        String addedValue = "";

        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());

            JSONArray jArray = jsonObject.getJSONArray("Sheet1");
            //JSONArray jArray = (JSONArray) response.get("Sheet1");
            Log.d("JsonArray DATAS", jArray.toString());
            arrayList = new ArrayList<ListMod>();

            for (int i = 0; i < jArray.length(); i++) {

                JSONObject jobj = jArray.getJSONObject(i);

                // Passing Values to Model
                ListMod listMod = new ListMod();
                listMod.setTitle(jobj.get("title").toString());
                listMod.setUrl(jobj.get("url").toString());
                listMod.setAuthour(jobj.get("author").toString());
                listMod.setYear(jobj.get("year").toString());

                addedValue += listMod.getTitle() + " " + listMod.getYear() + "\n";
                Log.d("Inside  DATAS", "" + addedValue);
                Log.d("Inside JsonArray DATAS", "" + jArray.length());

                arrayList.add(listMod);
            }


            listDisplayAdapter = new ListDisplayAdapter(HomeScreen.this, arrayList);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            homescr_recyc_display.setLayoutManager(layoutManager);
            homescr_recyc_display.setItemAnimator(new DefaultItemAnimator());
            homescr_recyc_display.smoothScrollToPosition(0);
            homescr_recyc_display.setHasFixedSize(true);
            homescr_recyc_display.setAdapter(listDisplayAdapter);
                            /*try {
                                data = getArguments();
                                if (null != data) {
                                    hideIndex = data.getInt(FragmentKey.HIDE_INDEX, -1);
                                    LOGI(TAG, "Hide Index is : " + hideIndex);
                                }

                            } catch (Exception e) {
                            }
*/

            //    msgResponse.setText(addedValue);
            // msgResponse.setText(response.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String addedValue = "";

            try {
                JSONObject jsonObject = new JSONObject(loadJSONFromAsset());

                JSONArray jArray = jsonObject.getJSONArray("Sheet1");
                //JSONArray jArray = (JSONArray) response.get("Sheet1");
                Log.d("JsonArray DATAS", jArray.toString());

                for (int i = 0; i < jArray.length(); i++) {

                    JSONObject jobj = jArray.getJSONObject(i);

                    // Passing Values to Model
                    ListMod listMod = new ListMod();
                    listMod.setTitle(jobj.get("title").toString());
                    listMod.setUrl(jobj.get("url").toString());
                    listMod.setAuthour(jobj.get("author").toString());
                    listMod.setYear(jobj.get("year").toString());

                    addedValue += listMod.getTitle() + " " + listMod.getYear() + "\n";
                    Log.d("Inside  DATAS", "" + addedValue);
                    Log.d("Inside JsonArray DATAS", "" + jArray.length());

                    arrayList.add(listMod);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            arrayList = new ArrayList<ListMod>();

        }


        @Override
        protected void onPostExecute(String tt) {

            listDisplayAdapter = new ListDisplayAdapter(HomeScreen.this, arrayList);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            homescr_recyc_display.setLayoutManager(layoutManager);
            homescr_recyc_display.setItemAnimator(new DefaultItemAnimator());
            homescr_recyc_display.smoothScrollToPosition(0);
            homescr_recyc_display.setHasFixedSize(true);
            homescr_recyc_display.setAdapter(listDisplayAdapter);
        }
    }

}
