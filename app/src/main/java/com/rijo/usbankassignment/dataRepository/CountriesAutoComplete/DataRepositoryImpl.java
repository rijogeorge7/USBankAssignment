package com.rijo.usbankassignment.dataRepository.CountriesAutoComplete;

import com.rijo.usbankassignment.util.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rijogeorge on 8/23/17.
 */

public class DataRepositoryImpl implements DataRepository {
    @Override
    public ArrayList getCountriesList() {
        String stringUrl=Constants.countriesAPI;
        String countryJson= Utilities.downloadJsonFromUrl(Constants.countriesAPI);
        ArrayList countriesList=getCountriesFromJson(countryJson);
        return countriesList;
    }

    private ArrayList getCountriesFromJson(String countryJson) {
        ArrayList<String> countriesList=new ArrayList();
        try {
            JSONArray countries=new JSONArray(countryJson);
            for(int i=0; i<countries.length(); i++) {
                JSONObject c=countries.getJSONObject(i);
                String countryName=c.getString("name");
                countriesList.add(countryName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return countriesList;
    }

}
