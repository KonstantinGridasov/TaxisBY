package com.transport.taxi.bus.taxis.halt;

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
 * Created by GHome on 02.01.2018.
 */

public class AdapterHalt extends RecyclerView.Adapter<AdapterHalt.HolderHalt> {
    private List<String> halt = new ArrayList<>();


    public AdapterHalt() {
    }

    public void setItemsTaxisHalt(List<String> halt) {
        this.halt = halt;
        notifyDataSetChanged();
    }

    @Override
    public HolderHalt onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("AdapterHalt", "onCreateViewHolder");
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_halt, parent, false);
        return new HolderHalt(root);
    }

    @Override
    public void onBindViewHolder(HolderHalt holder, int position) {
        holder.textView.setText(halt.get(position));

    }


    @Override
    public int getItemCount() {
        return halt == null ? 0 : halt.size();
    }

    public class HolderHalt extends RecyclerView.ViewHolder {
        private TextView textView;

        public HolderHalt(View itemView) {

            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.haltTaxis);
        }
    }
}
