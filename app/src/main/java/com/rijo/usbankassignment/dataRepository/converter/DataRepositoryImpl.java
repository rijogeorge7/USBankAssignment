package com.rijo.usbankassignment.dataRepository.converter;

import com.rijo.usbankassignment.dataRepository.converter.domain.CurrencyRates;
import com.rijo.usbankassignment.ui.converter.ConverterActivity;
import com.rijo.usbankassignment.util.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rijogeorge on 8/24/17.
 */

public class DataRepositoryImpl implements DataRepository {
    @Override
    public ArrayList<CurrencyRates> getExchangeRate(ConverterActivity.currency baseCurrency) {
        String exchangeJsonStr= Utilities.downloadJsonFromUrl(Constants.EXCHANGE_RATE_API+baseCurrency);
        if(exchangeJsonStr!=null){
            ArrayList<CurrencyRates> currencyRates=parseCurrencyJson(exchangeJsonStr);
            return currencyRates;
        }
        else{
            return null;
        }
    }

    private ArrayList<CurrencyRates> parseCurrencyJson(String exchangeJsonStr) {
        String[] curencies={"USD", "AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "GBP", "HKD", "HRK", "HUF", "IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "ZAR", "EUR"};
        ArrayList<CurrencyRates> ratesList=new ArrayList();
        try {
            JSONObject jsonObj=new JSONObject(exchangeJsonStr);
            JSONObject rates=jsonObj.getJSONObject("rates");
            for(String currency:curencies){
                try {
                    double rate = rates.getDouble(currency);
                    CurrencyRates cr = new CurrencyRates();
                    cr.setName(currency);
                    cr.setRate(rate);
                    ratesList.add(cr);
                } catch (JSONException e){
                    continue;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ratesList;
    }

}
