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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GHome on 01.01.2018.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Holder> {

    public static final String KEY_ID = "com.transport.taxi.bus.taxis.main.id";
    public static final String KEY_NAME = "com.transport.taxi.bus.taxis.main.name";

    private MainPresenter presenter;
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
        holder.textViewName.setText(itemsTaxis.get(position).getName());

//        holder.textViewName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("onCLickItemAdapter", "s=" + itemsTaxis.get(position).getId());
//
//                Intent intent = new Intent(holder.textViewID.getContext(), HaltActivity.class);
//                intent.putExtra(KEY_ID, itemsTaxis.get(position).getId());
//                intent.putExtra(KEY_NAME, itemsTaxis.get(position).getName());
//
//                holder.textViewName.getContext().startActivity(intent);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return itemsTaxis == null ? 0 : itemsTaxis.size();
    }


    public static class Holder extends RecyclerView.ViewHolder {

        private TextView textViewID;
        private TextView textViewName;

        public Holder(View itemView) {
            super(itemView);
            textViewID = (TextView) itemView.findViewById(R.id.textID);
            textViewName = (TextView) itemView.findViewById(R.id.textName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), HaltActivity.class);
                    intent.putExtra(KEY_ID, textViewID.getText());
                    intent.putExtra(KEY_NAME, textViewName.getText());
                    v.getContext().startActivity(intent);
                }
            });

        }

    }

}
