package com.egtinteractive.maps;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.maps.Map;

public class GetByKey extends SetupTests {

    @Test(dataProvider = "maps")
    public void GetValueByKey(final Map<String, Integer> map) {
	String randomString = "";
	for (int index = ZERO; index < 50; index++) {
	    map.put("i" + index, index);
	}

	randomString = "dfas;k";
	Object expected = null;

	if (!map.containsKey(randomString) && map.get(randomString) != null) {
	    expected = map.get(randomString);
	}

	final Object actual = map.get(randomString);
	assertEquals(actual, expected);
    }

    @Test(dataProvider = "maps")
    public void GetByNullKey(final Map<String, Integer> map) {
	for (int index = ZERO; index < 100; index++) {
	    map.put("i" + index, index);
	}
	map.put(null, null);
	final Object expected = null;
	assertEquals(null, expected);
    }

}
