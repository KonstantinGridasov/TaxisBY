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

import javax.inject.Inject;


/**
 * Created by GHome on 19.12.2017.
 */
//Класс для чтения Json файла

public class ReaderJSON {
    @Inject
    Context context;

    public ReaderJSON(Context context) {
        this.context = context;
    }

    List<TaxisData> readJsonStream(Context context) throws IOException {

        JsonReader reader = new JsonReader(new InputStreamReader((context
                .getAssets()
                .open("taxis.json")), "UTF-8"));
        List<TaxisData> list = readTaxisArray(reader);
        reader.close();
        return list;
    }

    private List<TaxisData> readTaxisArray(JsonReader reader) throws IOException {
        List<TaxisData> taxisData = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            taxisData.add(readTaxis(reader));
        }
        reader.endArray();
        return taxisData;
    }

    private TaxisData readTaxis(JsonReader reader) throws IOException {
        TaxisData taxisData = new TaxisData();
        reader.beginObject();
        while (reader.hasNext()) {
            String taxis = reader.nextName();
            if (taxis.equals("id")) {
                taxisData.setId(reader.nextString());
            } else if (taxis.equals("inWeek")) {
                taxisData.setInWeek(reader.nextString());
            } else if (taxis.equals("workingTime")) {
                taxisData.setWorkingTime(reader.nextString());
            } else if (taxis.equals("interval")) {
                taxisData.setInterval(reader.nextString());
            } else if (taxis.equals("directName")) {
                taxisData.setDirectName(reader.nextString());
            } else if (taxis.equals("reverseName")) {
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
        return taxisData;
    }

    private List<Halt> readHaltArray(JsonReader reader) throws IOException {
        List<Halt> halts = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            halts.add(readHalt(reader));
        }
        reader.endArray();
        return halts;
    }

    private Halt readHalt(JsonReader reader) throws IOException {
        Halt halt = new Halt();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("haltName")) {
                halt.setHaltName(reader.nextString());
            } else if (name.equals("haltId")) {
                halt.setId(reader.nextString());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return halt;
    }


}
