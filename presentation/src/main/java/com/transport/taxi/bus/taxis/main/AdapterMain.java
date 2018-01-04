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

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.Holder> {
    public static final String KEY_ID = "com.transport.taxi.bus.taxis.main.id";
    public static final String KEY_NAME = "com.transport.taxi.bus.taxis.main.name";

    private MainPresenter presenter;
    private List<TaxisDomain> itemsTaxis = new ArrayList<>();

    public AdapterMain() {
        Log.e("AdapterMain", "AdapterMain");

    }

    public void setItemsTaxis(List<TaxisDomain> itemsTaxis) {
        Log.e("AdapterMain", "setItemsTaxis");
        this.itemsTaxis = itemsTaxis;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("AdapterMain", "onCreateViewHolder");
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        Log.e("AdapterMain", "onBindViewHolder");
        holder.textViewID.setText(itemsTaxis.get(position).getId());
        holder.textViewID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onCLickItemAdapter", "s=" + itemsTaxis.get(position).getId());

                Intent intent = new Intent(holder.textViewID.getContext(), HaltActivity.class);
                intent.putExtra(KEY_ID, itemsTaxis.get(position).getId());
                intent.putExtra(KEY_NAME, itemsTaxis.get(position).getName());

                holder.textViewID.getContext().startActivity(intent);
            }
        });
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
