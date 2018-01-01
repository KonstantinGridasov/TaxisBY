package com.transport.taxi.bus.taxis.data.settingsDb;

import android.content.Context;

import com.transport.taxi.bus.taxis.data.base.TaxisData;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by GHome on 19.12.2017.
 */

public class ReaderJSON {

    Context context;
    private List<TaxisData> list = new ArrayList<>();

    public ReaderJSON(Context context) {
        this.context = context;
    }

    public List<TaxisData> getTaxisList() {
        String jsonString = null;
        JSONArray jsonArrayTaxis = null;
        try {
            jsonString = readFile("taxis.json", context);
            jsonArrayTaxis = new JSONArray(jsonString);
            for (int i = 0; i < jsonArrayTaxis.length(); i++) {
                TaxisData taxisData = new TaxisData();
                taxisData.setId(jsonArrayTaxis.getJSONObject(i).getString("id"));
                taxisData.setName(jsonArrayTaxis.getJSONObject(i).getString("name"));
                taxisData.setDirect_direction(jsonArrayTaxis.getJSONObject(i).getString("direct direction"));
                taxisData.setReverse_direction(jsonArrayTaxis.getJSONObject(i).getString("reverse direction"));
                list.add(taxisData);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();

        }
        Integer k = list.size();
        return list;
    }

    private String readFile(String fileName, Context cxt) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                cxt.getAssets().open(fileName), "UTF-8"));
        String content = "";
        String line;
        while ((line = reader.readLine()) != null) {
            content += line;
        }
        reader.close();
        return content;
    }
}
