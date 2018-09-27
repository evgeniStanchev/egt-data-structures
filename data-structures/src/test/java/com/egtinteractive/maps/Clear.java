package com.egtinteractive.maps;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.maps.Map;

public class Clear extends SetupTests {

    @Test(dataProvider = "maps")
    public void sizeShouldBeZERO(final Map<String, Integer> map) {
	for (int index = ZERO; index < 50; index++) {
	    map.put("index" + index, index);
	}
	map.clear();
	final int expectedSize = ZERO;
	final int actualSize = map.size();
	assertEquals(actualSize, expectedSize);
    }
}
