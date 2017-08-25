package com.rijo.usbankassignment.ui.converter;

import com.rijo.usbankassignment.dataRepository.converter.domain.CurrencyRates;

import java.util.ArrayList;

/**
 * Created by rijogeorge on 8/24/17.
 */

public interface ConverterActivityContract {

    interface View{
        void setExchangeRates(ArrayList<CurrencyRates> rateList);
        void exchangeRateNotAvailable();
    }

    interface UserActionListener{
        void getExchangeRates(ConverterActivity.currency baseCurrency);
    }
}
