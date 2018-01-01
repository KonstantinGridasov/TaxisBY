package com.transport.taxi.bus.taxis.main;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;

import java.util.ArrayList;

/**
 * Created by GHome on 01.01.2018.
 */

public class AdapterMainRecycler extends RecyclerView.Adapter<AdapterMainRecycler.Holder> {
    private MainPresenter presenter;
    private ArrayList<TaxisDomain> itemsTaxis;

    public AdapterMainRecycler(ArrayList<TaxisDomain> itemsTaxis) {
        this.itemsTaxis = itemsTaxis;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_main, parent, false);
        Log.e("ClassWork6Adapter", "onCreateViewHolder");
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.textViewID.setText(itemsTaxis.get(position).getId());
        holder.textViewName.setText(itemsTaxis.get(position).getName());

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

        }
    }

}
