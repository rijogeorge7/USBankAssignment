package com.rijo.usbankassignment.ui.converter;

import com.rijo.usbankassignment.dataRepository.converter.DataRepository;
import com.rijo.usbankassignment.dataRepository.converter.DataRepositoryImpl;
import com.rijo.usbankassignment.dataRepository.converter.domain.CurrencyRates;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by rijogeorge on 8/24/17.
 */

public class ConverterActivityPresenter implements ConverterActivityContract.UserActionListener{

    ConverterActivityContract.View view;
    DataRepository repository;

    public ConverterActivityPresenter(ConverterActivityContract.View view, DataRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getExchangeRates(final ConverterActivity.currency baseCurrency) {
        ArrayList<CurrencyRates> rateList=null;
        ExecutorService executor= Executors.newSingleThreadExecutor();
        Future<ArrayList<CurrencyRates>> exchangeFuture=executor.submit(new Callable<ArrayList<CurrencyRates>>() {
            @Override
            public ArrayList<CurrencyRates> call() throws Exception {
                return repository.getExchangeRate(baseCurrency);
            }
        });
        try {
            rateList=exchangeFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(rateList.size()>0) {
            view.setExchangeRates(rateList);
        } else {
            view.exchangeRateNotAvailable();
        }

    }
}
