package com.transport.taxi.bus.taxis.maps;

import android.support.v7.widget.RecyclerView;
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

public class MapsAdapter extends RecyclerView.Adapter<MapsAdapter.HolderHalt> {
    private MapsView mapsView;
    private List<String> name = new ArrayList<>();

    MapsAdapter(MapsView view) {
        mapsView = view;
    }

    void setItemsTaxisHalt(List<String> name) {
        this.name = name;
        notifyDataSetChanged();
    }

    @Override
    public HolderHalt onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_maps, parent, false);
        return new HolderHalt(root);
    }

    @Override
    public void onBindViewHolder(HolderHalt holder, int position) {
        holder.textView.setText(name.get(position));

    }


    @Override
    public int getItemCount() {
        return name == null ? 0 : name.size();
    }

    class HolderHalt extends RecyclerView.ViewHolder {
        private TextView textView;

        HolderHalt(View itemView) {

            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.name_item_maps);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MapsPresenter mapsPresenter = new MapsPresenter(mapsView);
                    mapsPresenter.getTaxis(textView.getText().toString());
//                    textView.setBackgroundColor(textView.getResources().getColor(R.color.Grey));


                }
            });

        }
    }
}
