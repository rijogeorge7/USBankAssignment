package com.rijo.usbankassignment.ui.converter.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rijo.usbankassignment.R;
import com.rijo.usbankassignment.dataRepository.converter.domain.CurrencyRates;
import java.util.ArrayList;

/**
 * Created by rijogeorge on 8/24/17.
 */

public class ConverterRecyclerAdapter extends RecyclerView.Adapter<ConverterRecyclerAdapter.ViewHolder>{

    //private Exchange exchange;
    private ArrayList<CurrencyRates> rateList;
    private double amount;

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ArrayList<CurrencyRates> getRateList() {
        return rateList;
    }

    public void setRateList(ArrayList<CurrencyRates> rateList) {
        this.rateList = rateList;
    }

    public ConverterRecyclerAdapter(double amount, ArrayList<CurrencyRates> rateList) {
        this.amount=amount;
        this.rateList=rateList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.converter_rates_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.currencyTextView.setText(rateList.get(position).getName());
        double currentRate=rateList.get(position).getRate() * amount;
        holder.rateTextView.setText(String.valueOf(currentRate));
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyTextView, rateTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            currencyTextView=(TextView) itemView.findViewById(R.id.currencyTextView);
            rateTextView=(TextView) itemView.findViewById(R.id.rateTextView);
        }
    }
}
