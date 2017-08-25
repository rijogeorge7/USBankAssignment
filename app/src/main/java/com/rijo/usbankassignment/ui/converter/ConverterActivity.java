package com.rijo.usbankassignment.ui.converter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.rijo.usbankassignment.R;
import com.rijo.usbankassignment.dataRepository.converter.DataRepositoryImpl;
import com.rijo.usbankassignment.dataRepository.converter.domain.CurrencyRates;

import com.rijo.usbankassignment.ui.CountriesAutoComplete.CountriesActivity;
import com.rijo.usbankassignment.ui.converter.adapters.ConverterRecyclerAdapter;
import com.rijo.usbankassignment.util.Utilities;

import java.util.ArrayList;

public class ConverterActivity extends Activity implements ConverterActivityContract.View{
    
    public enum currency {
       USD, AUD, BGN, BRL, CAD, CHF, CNY, CZK, DKK, GBP, HKD, HRK, HUF, IDR, ILS, INR, JPY, KRW, MXN, MYR, NOK, NZD, PHP, PLN, RON, RUB, SEK, SGD, THB, TRY, ZAR, EUR
    }

    private currency baseCurrency=currency.USD;
    private double amount=1;
    //private Exchange exchange;
    private ArrayList<CurrencyRates> rateList;
    private RecyclerView ratesRecyclerView;
    private ConverterRecyclerAdapter ratesAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataRepositoryImpl dataRepositoryImpl=new DataRepositoryImpl();
        final ConverterActivityPresenter presenter=new ConverterActivityPresenter(this, dataRepositoryImpl);
        setContentView(R.layout.activity_converter);
        EditText currencyEditText=(EditText) findViewById(R.id.currencyEditText);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        currencyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    amount = Double.parseDouble(editable.toString());
                    ratesAdapter.setAmount(amount);
                    ratesAdapter.notifyDataSetChanged();
                } catch (Exception e) {

                }

            }
        });
        final Spinner currencySpinner = (Spinner) findViewById(R.id.currencySpinner);
        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String selected=(String)adapterView.getItemAtPosition(pos);
                if(selected.equals(baseCurrency)){
                    //do nothing
                } else {
                    baseCurrency=currency.valueOf(selected);
                    if(Utilities.IsInternetAvailable(getApplicationContext())) {
                        progressBar.setVisibility(View.VISIBLE);
                        presenter.getExchangeRates(baseCurrency);
                    }
                    else {
                        showNoNetworkDialog();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(adapter);
        ratesRecyclerView = (RecyclerView) findViewById(R.id.convertionView);
        GridLayoutManager mLayoutManager=new GridLayoutManager(this,3);
        ratesRecyclerView.setHasFixedSize(true);
        ratesRecyclerView.setLayoutManager(mLayoutManager);

        if(Utilities.IsInternetAvailable(getApplicationContext()))
            presenter.getExchangeRates(baseCurrency);
        else
            showNoNetworkDialog();

    }

    private void showNoNetworkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ConverterActivity.this);

        builder.setMessage(R.string.internet_dialog_message)
                .setTitle(R.string.internet_dialog_title);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
    }

    @Override
    public void setExchangeRates(ArrayList<CurrencyRates> rateList) {
        progressBar.setVisibility(View.GONE);
        this.rateList=rateList;
        if(ratesRecyclerView.getAdapter()==null) {
            ratesAdapter = new ConverterRecyclerAdapter(amount, rateList);
            ratesRecyclerView.setAdapter(ratesAdapter);
        }
        else {
            ratesAdapter.setRateList(rateList);
            ratesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void exchangeRateNotAvailable() {
        progressBar.setVisibility(View.GONE);
        AlertDialog.Builder builder = new AlertDialog.Builder(ConverterActivity.this);

        builder.setMessage(R.string.rateNotAvailable_dialog_message)
                .setTitle(R.string.rateNotAvailable_dialog_title);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
    }
}
