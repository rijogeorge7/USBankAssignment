package com.rijo.usbankassignment.util;

import com.rijo.usbankassignment.Mock.MockedStrings;
import com.rijo.usbankassignment.dataRepository.CountriesAutoComplete.Constants;


import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by rijogeorge on 8/24/17.
 */
public class UtilitiesTest {
    @Test
    public void downloadProductsFromUrlTest() throws Exception {
        //when
        String downloadStr=Utilities.downloadJsonFromUrl(Constants.countriesAPI);
        //then
        assertTrue(downloadStr.length()>0);
    }


    @Test
    public void covertInputStreamToStringTest() throws Exception {
        String string="Rijo George";
        InputStream stream=new ByteArrayInputStream(string.getBytes());
        //using reflection to access private method
        Class c=Class.forName("com.rijo.usbankassignment.util.Utilities");
        Method method = c.getDeclaredMethod("covertInputStreamToString", InputStream.class);
        method.setAccessible(true);
        String result=(String)method.invoke(c,stream);
        assertEquals(string,result);
    }
}