package com.example.cardgame.cardgame;


import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.example.cardgame.cardgame.helper.DateUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class DataUtilTest {

    @Test
    public void testGetDiffInDays() {
        // number need to be changed according to current date,
        // the tests below are based on 12/03/2015
        assertEquals(-1, DateUtil.getDiffInDays("Dec", "2"));
        assertEquals(0, DateUtil.getDiffInDays("Dec", "3"));
        assertEquals(1, DateUtil.getDiffInDays("Dec", "4"));
        assertEquals(2, DateUtil.getDiffInDays("Dec", "5"));
    }

}
