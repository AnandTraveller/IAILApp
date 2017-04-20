package com.anandroid.iailapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.anandroid.iailapp.BrowserActivity;
import com.anandroid.iailapp.MainAct;
import com.anandroid.iailapp.R;
import com.anandroid.iailapp.fragment.HomeScreen;

import java.util.ArrayList;

public class ListDisplayAdapter extends RecyclerView.Adapter<ListDisplayAdapter.MyViewHolder> {
    private static final String CURRENT_TAG = ListDisplayAdapter.class.getSimpleName();

    private ArrayList<ListMod> listData;
    private Fragment fragment;
    private Activity act;

    public interface DataPass {
        void dataArray(ArrayList<String> arrayList);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView row_author_txt, row_title_txt, row_year_txt;
        private CardView card_main_row;


        public MyViewHolder(View view) {
            super(view);
            row_author_txt = (TextView) view.findViewById(R.id.row_author_txt);
            row_title_txt = (TextView) view.findViewById(R.id.row_title_txt);
            row_year_txt = (TextView) view.findViewById(R.id.row_year_txt);
            card_main_row = (CardView) view.findViewById(R.id.card_main_row);

        }
    }


    public ListDisplayAdapter( Fragment fragment, ArrayList<ListMod> listData) {
        this.fragment = fragment;
        this.listData = listData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_display_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.row_author_txt.setText(listData.get(position).getAuthour());
        holder.row_title_txt.setText(listData.get(position).getTitle());
        // holder.txt.setText(listData.get(position).getUrl());
        holder.row_year_txt.setText(listData.get(position).getYear());
        holder.card_main_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listData.remove(position);
                Log.i("Position == ", "" + position);
                Log.i("Clicked Url ==", "" + listData.get(position).getUrl());

                ((MainAct) fragment.getActivity()).inAppBrouser(listData.get(position).getUrl().trim());

         /*       //  notifyItemRemoved(position);
                notifyDataSetChanged();
                ((HomeScreen) fragment).dataFrag(listData);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void clear() {
        if (null != listData) {
            listData.clear();
            notifyDataSetChanged();
        }
    }

    public void updateList(ArrayList<ListMod> list) {
        listData = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


}
