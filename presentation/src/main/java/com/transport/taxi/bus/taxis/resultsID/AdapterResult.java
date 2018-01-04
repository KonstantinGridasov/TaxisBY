package com.transport.taxi.bus.taxis.resultsID;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transport.taxi.bus.taxis.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GHome on 01.01.2018.
 */

public class AdapterResult extends RecyclerView.Adapter<AdapterResult.HolderResult> {
    private List<String> itemsID = new ArrayList<>();

    public AdapterResult() {
        Log.e("AdapterResult", "AdapterMain");

    }

    public void setItemsTaxis(List<String> itemsID) {
        Integer n = itemsID.size();
        Log.e("setItemsTaxis", n.toString());
        this.itemsID = itemsID;
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
        if (itemsID.get(position).equals("null")) {
            holder.resultText.setVisibility(View.VISIBLE);
            holder.resultText.setText("Не найденно!");
            holder.resultID.setVisibility(View.GONE);
        } else {
            holder.resultID.setVisibility(View.VISIBLE);
            holder.resultID.setText(itemsID.get(position));
            holder.resultText.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemsID == null ? 0 : itemsID.size();
    }

    public static class HolderResult extends RecyclerView.ViewHolder {
        private TextView resultID;
        private TextView resultText;

        public HolderResult(View itemView) {
            super(itemView);
            resultID = (TextView) itemView.findViewById(R.id.resultID);
            resultText = (TextView) itemView.findViewById(R.id.resultText);

        }
    }

}
