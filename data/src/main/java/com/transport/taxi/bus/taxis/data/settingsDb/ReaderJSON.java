package com.transport.taxi.bus.taxis.data.settingsDb;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.Halt;
import com.transport.taxi.bus.taxis.data.base.TaxisData;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by GHome on 19.12.2017.
 */

public class ReaderJSON {

    Context context;

    public ReaderJSON(Context context) {
        this.context = context;
    }


    public List<TaxisData> readJsonStream(Context context) throws IOException {
        List<TaxisData> list = new ArrayList<>();

        JsonReader reader = new JsonReader(new InputStreamReader((context
                .getAssets()
                .open("taxis.json")), "UTF-8"));
        try {

            list = readTaxisArray(reader);
        } finally {
            reader.close();

        }

        return list;
    }

    public List<TaxisData> readTaxisArray(JsonReader reader) throws IOException {
        List<TaxisData> taxisData = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            taxisData.add(readTaxis(reader));
        }
        reader.endArray();
        return taxisData;
    }

    public TaxisData readTaxis(JsonReader reader) throws IOException {
        TaxisData taxisData = new TaxisData();
        reader.beginObject();
        while (reader.hasNext()) {
            String taxis = reader.nextName();
            if (taxis.equals("id")) {
                taxisData.setId(reader.nextString());
                Log.e("readTaxis", taxisData.getId());

            } else if (taxis.equals("inWeek")) {
                taxisData.setInWeek(reader.nextString());
            } else if (taxis.equals("workingTime")) {
                taxisData.setWorkingTime(reader.nextString());
            } else if (taxis.equals("interval")) {
                taxisData.setInterval(reader.nextString());
            }  else if (taxis.equals("directName")) {
                taxisData.setDirectName(reader.nextString());
            }  else if (taxis.equals("reverseName")) {
                taxisData.setReverseName(reader.nextString());
            } else if (taxis.equals("directHalt")) {
                taxisData.setDirectHalt(readHaltArray(reader));
            } else if (taxis.equals("reverseHalt")) {
                taxisData.setReverseHalt(readHaltArray(reader));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
//        String nach = taxisData.getDirectHalt().get(0).getHaltName();
//        String kon = taxisData.getDirectHalt().get(taxisData.getDirectHalt().size() - 1).getHaltName();
//        taxisData.setDirectName(nach + "-" + kon);
//        taxisData.setReverseName(kon + "-" + nach);


        return taxisData;
    }

    public List<Halt> readHaltArray(JsonReader reader) throws IOException {
        List<Halt> halts = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            halts.add(readHalt(reader));
        }
        reader.endArray();
        return halts;
    }

    public Halt readHalt(JsonReader reader) throws IOException {
        Halt halt = new Halt();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("haltId")) {
                halt.setHaltId(reader.nextString());
            } else if (name.equals("haltName")) {
                halt.setHaltName(reader.nextString());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return halt;
    }


}
