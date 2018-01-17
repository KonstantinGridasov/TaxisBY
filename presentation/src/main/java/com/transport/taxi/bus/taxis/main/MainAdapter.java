package com.transport.taxi.bus.taxis.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;
import com.transport.taxi.bus.taxis.halt.HaltActivity;
import com.transport.taxi.bus.taxis.info.InfoActivity;

import java.util.ArrayList;
import java.util.List;

//import com.transport.taxi.bus.taxis.halt.HaltActivity;

/**
 * Created by GHome on 01.01.2018.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Holder> {

    public static final String KEY_ID = "com.transport.taxi.bus.taxis.main.id";
    public static final String KEY_NAME = "com.transport.taxi.bus.taxis.main.name";


    private List<TaxisDomain> itemsTaxis = new ArrayList<>();

    public MainAdapter() {
        Log.e("MainAdapter", "MainAdapter");

    }

    public void setItemsTaxis(List<TaxisDomain> itemsTaxis) {
        Log.e("MainAdapter", "setItemsTaxis");
        this.itemsTaxis = itemsTaxis;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("MainAdapter", "onCreateViewHolder");
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        Log.e("MainAdapter", "onBindViewHolder");
        holder.textViewID.setText(itemsTaxis.get(position).getId());
        holder.textViewName.setText(itemsTaxis.get(position).getDirectName());
        holder.textWeek.setText(itemsTaxis.get(position).getInWeek());
    }

    @Override
    public int getItemCount() {
        return itemsTaxis == null ? 0 : itemsTaxis.size();
    }


    public static class Holder extends RecyclerView.ViewHolder {

        private TextView textViewID;
        private TextView textViewName;
        private TextView textWeek;

        public Holder(View itemView) {
            super(itemView);
            textWeek = (TextView) itemView.findViewById(R.id.textWeek);
            textViewID = (TextView) itemView.findViewById(R.id.textID);
            textViewName = (TextView) itemView.findViewById(R.id.textName);

            textViewName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), HaltActivity.class);
                    intent.putExtra(KEY_ID, textViewID.getText());
                    intent.putExtra(KEY_NAME, textViewName.getText());
                    v.getContext().startActivity(intent);
                }
            });
            textViewID.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), InfoActivity.class);
                    intent.putExtra(KEY_ID, textViewID.getText());
                    v.getContext().startActivity(intent);
                }
            });

        }

    }

}
