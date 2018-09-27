package com.egtinteractive.maps;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.maps.Map;

public class Size extends SetupTests {

    @Test(dataProvider = "maps")
    public void getSize(final Map<String, Integer> map) {
	int randomSize = ThreadLocalRandom.current().nextInt(100);

	for (int index = ZERO; index < randomSize; index++) {
	    map.put("index" + index, index);
	}

	final int expectedSize = randomSize;
	final int actualSize = map.size();
	assertEquals(actualSize, expectedSize);
    }

    @Test(dataProvider = "maps")
    public void sizeShouldBeNull(final Map<String, Integer> map) {
	for (int i = 0; i < 10; i++) {
	    map.put(i + "", i);
	}

	for (int i = 0; i < 10; i++) {
	    map.remove(i + "");
	}
	assertEquals(map.size(), 0);
    }

    @Test(dataProvider = "maps")
    public void addNullValuesSizeIncrease(final Map<String, Integer> map) {
	final int randomSize = ThreadLocalRandom.current().nextInt(10);
	for (int index = 0; index < randomSize; index++) {
	    map.put("index" + index, index);
	}
	map.put(null, null);
	final int actual = randomSize + 1;
	final int expected = map.size();
	assertEquals(actual, expected);
    }
}
