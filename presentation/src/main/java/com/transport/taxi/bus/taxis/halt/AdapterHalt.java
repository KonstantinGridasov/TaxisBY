package com.transport.taxi.bus.taxis.halt;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by GHome on 02.01.2018.
 */

public class AdapterHalt extends RecyclerView.Adapter<AdapterHalt.HolderHalt> {

    @Override
    public HolderHalt onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(HolderHalt holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class HolderHalt extends RecyclerView.ViewHolder {
        public HolderHalt(View itemView) {
            super(itemView);
        }
    }
}
