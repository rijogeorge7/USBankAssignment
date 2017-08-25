package com.rijo.usbankassignment.ui.CountriesAutoComplete;


import com.rijo.usbankassignment.dataRepository.CountriesAutoComplete.DataRepository;
import com.rijo.usbankassignment.dataRepository.CountriesAutoComplete.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Created by rijogeorge on 8/23/17.
 */

public class CountriesActivityPresenter implements CountriesActivityContract.UserActionListener{

    CountriesActivityContract.View view;
    DataRepository repository;

    public CountriesActivityPresenter(CountriesActivityContract.View view, DataRepository repository) {
        this.view = view;
        this.repository=repository;
    }

    @Override
    public void initialiseAutoComplete() {
        List countries=null;
        ExecutorService executor= Executors.newSingleThreadExecutor();
        Future<ArrayList> countriesFuture=executor.submit(new Callable<ArrayList>() {
            @Override
            public ArrayList call() throws Exception {
                return repository.getCountriesList();
            }
        });
        try {
            countries=countriesFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(countries.size()>0)
            view.setAdapterToTextView(countries);
    }


}
