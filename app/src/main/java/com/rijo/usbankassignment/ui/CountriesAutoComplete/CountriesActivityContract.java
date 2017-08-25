package com.rijo.usbankassignment.ui.CountriesAutoComplete;

import java.util.List;

/**
 * Created by rijogeorge on 8/23/17.
 */

public interface CountriesActivityContract {

    interface View {
        void setAdapterToTextView(List countries);
    }

    interface UserActionListener {
        void initialiseAutoComplete();
    }
}
