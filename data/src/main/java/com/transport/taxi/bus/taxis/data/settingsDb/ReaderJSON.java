package com.transport.taxi.bus.taxis.data.settingsDb;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import com.transport.taxi.bus.taxis.data.base.Halt;
import com.transport.taxi.bus.taxis.data.base.TaxisData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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
    private URL myUrl;
    private JsonReader readerUrl;
    private List<TaxisData> list;

    public ReaderJSON(Context context) {
        this.context = context;
    }

    List<TaxisData> readerFromUrl() throws IOException { //Сделать через Observable  (RXAndroid)
        myUrl = new URL("https://api.backendless.com/B744EA4C-80EA-94BC-FFB2-8B4EA665D800/3E1D86FC-60F8-8072-FFEB-5D9AEB099600/files/taxis_ubdate.json");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    readerUrl = new JsonReader(new BufferedReader(new InputStreamReader(myUrl.openStream())));
                    list = readTaxisArray(readerUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            thread.start();
            thread.join(); // Используется для приостановки основного потока , пока не завершиться дополнительный
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
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
            } else if (taxis.equals("ubdate")) {
                taxisData.setUbdate(reader.nextString());
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
