package com.rijo.usbankassignment.ui.CountriesAutoComplete;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.rijo.usbankassignment.R;
import com.rijo.usbankassignment.dataRepository.CountriesAutoComplete.DataRepositoryImpl;
import com.rijo.usbankassignment.util.Utilities;

import java.util.List;

/**
 * Created by rijogeorge on 8/23/17.
 */

public class CountriesActivity extends Activity implements CountriesActivityContract.View{

    private AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        DataRepositoryImpl repositoryImpl=new DataRepositoryImpl();
        CountriesActivityPresenter presenter=new CountriesActivityPresenter(this,repositoryImpl);
        if(Utilities.IsInternetAvailable(getApplicationContext()))
            presenter.initialiseAutoComplete();
        else
            showNoNetworkDialog();
    }

    private void showNoNetworkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CountriesActivity.this);

        builder.setMessage(R.string.internet_dialog_message)
                .setTitle(R.string.internet_dialog_title);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        builder.show();
    }

    @Override
    public void setAdapterToTextView(List countries) {
        ArrayAdapter<List> adapter = new ArrayAdapter<List>(this,
                android.R.layout.simple_dropdown_item_1line, countries);
        autoCompleteTextView.setAdapter(adapter);
    }
}
