package com.transport.taxi.bus.taxis.resultsID;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transport.taxi.bus.taxis.R;
import com.transport.taxi.bus.taxis.domain.base.TaxisDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GHome on 01.01.2018.
 */

public class AdapterResult extends RecyclerView.Adapter<AdapterResult.HolderResult> {
    private List<TaxisDomain> itemsTaxis = new ArrayList<>();

    public AdapterResult() {
        Log.e("AdapterResult", "AdapterMain");

    }

    public void setItemsTaxis(List<TaxisDomain> itemsTaxis) {
//        Integer n = itemsID.size();
//        Log.e("setItemsTaxis", n.toString());
        this.itemsTaxis = itemsTaxis;
        notifyDataSetChanged();
    }

    @Override
    public HolderResult onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("AdapterResult", "onCreateViewHolder");
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_result, parent, false);
        return new HolderResult(root);
    }

    @Override
    public void onBindViewHolder(HolderResult holder, int position) {
        Log.e("AdapterResult", "onBindViewHolder");
        if (itemsTaxis.get(0).getName().equals("null")) {
            holder.resultID.setVisibility(View.GONE);
            holder.resultName.setVisibility(View.GONE);
            holder.resultText.setVisibility(View.VISIBLE);
        }
        else {
            holder.resultText.setVisibility(View.GONE);
            holder.resultID.setText(itemsTaxis.get(position).getId());
            holder.resultName.setText(itemsTaxis.get(position).getName());
            holder.resultID.setVisibility(View.VISIBLE);
            holder.resultName.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return itemsTaxis == null ? 0 : itemsTaxis.size();
    }

    public static class HolderResult extends RecyclerView.ViewHolder {
        private TextView resultID;
        private TextView resultName;
        private TextView resultText;


        public HolderResult(View itemView) {
            super(itemView);
            resultID = (TextView) itemView.findViewById(R.id.resultID);
            resultName = (TextView) itemView.findViewById(R.id.resultName);
            resultText = (TextView) itemView.findViewById(R.id.resultTEXT);
        }
    }

}
