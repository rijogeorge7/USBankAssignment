package com.rijo.usbankassignment.ui.CountriesAutoComplete;

import com.rijo.usbankassignment.dataRepository.CountriesAutoComplete.DataRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by rijogeorge on 8/23/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(CountriesActivityPresenter.class)
public class CountriesActivityPresenterTest {

    @Mock
    CountriesActivityContract.View viewMock;
    @Mock
    DataRepository repositoryMock;

    CountriesActivityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter=new CountriesActivityPresenter(viewMock,repositoryMock);
    }

    @Test
    public void initialiseAutoCompleteTest() throws Exception {
        //given
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("USA");
        when(repositoryMock.getCountriesList()).thenReturn(arrayList);
        //when
        presenter.initialiseAutoComplete();
        //then
        Mockito.verify(viewMock).setAdapterToTextView(arrayList);
    }

    @After
    public void tearDown() throws Exception {
        presenter=null;
    }
}