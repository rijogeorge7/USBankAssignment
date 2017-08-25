package com.rijo.usbankassignment.ui.converter;

import com.rijo.usbankassignment.dataRepository.converter.DataRepository;
import com.rijo.usbankassignment.dataRepository.converter.domain.CurrencyRates;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by rijogeorge on 8/24/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ConverterActivityPresenter.class)
public class ConverterActivityPresenterTest {

    @Mock
    ConverterActivityContract.View viewMock;
    @Mock
    DataRepository repositoryMock;

    ConverterActivityPresenter presenter;
    @Before
    public void setUp() throws Exception {

        presenter=new ConverterActivityPresenter(viewMock,repositoryMock);
    }

    @Test
    public void getExchangeRatesTest() throws Exception {
        //given
        ConverterActivity.currency baseCurrency= ConverterActivity.currency.USD;
        ArrayList<CurrencyRates> listRate=new ArrayList<CurrencyRates>();
        CurrencyRates cr=new CurrencyRates();
        cr.setName("AUD");
        cr.setRate(10);
        listRate.add(cr);
        when(repositoryMock.getExchangeRate(baseCurrency)).thenReturn(listRate);

        //when
        presenter.getExchangeRates(baseCurrency);

        //then
        Mockito.verify(viewMock).setExchangeRates(listRate);

    }

    @After
    public void tearDown() throws Exception {
        presenter=null;
    }
}