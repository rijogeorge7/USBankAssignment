package com.rijo.usbankassignment.dataRepository.converter;

import com.rijo.usbankassignment.dataRepository.converter.domain.CurrencyRates;
import com.rijo.usbankassignment.ui.converter.ConverterActivity;

import java.util.ArrayList;

/**
 * Created by rijogeorge on 8/24/17.
 */

public interface DataRepository {
    ArrayList<CurrencyRates> getExchangeRate(ConverterActivity.currency baseCurrency);
}
