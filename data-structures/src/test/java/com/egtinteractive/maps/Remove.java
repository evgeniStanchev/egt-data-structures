package com.egtinteractive.maps;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.maps.Map;

public class Remove extends SetupTests {

    @Test(dataProvider = "maps")
    public void removedElementShoudntExist(final Map<String, Integer> map) {
	for (int index = ZERO; index < 50; index++) {
	    map.put("index" + index, ThreadLocalRandom.current().nextInt(100));
	}

	final String randomKey = "index" + ThreadLocalRandom.current().nextInt(50);
	map.remove(randomKey);

	final boolean expected = false;
	final boolean actual = map.containsKey(randomKey);
	assertEquals(actual, expected);
    }

    @Test(dataProvider = "maps")
    public void removedReturnsOldElement(final Map<String, Integer> map) {
	for (int index = ZERO; index < 50; index++) {
	    map.put("index" + index, ThreadLocalRandom.current().nextInt(100));
	}

	final String randomKey = "index" + ThreadLocalRandom.current().nextInt(50);

	final Integer expected = map.get(randomKey);
	final Integer actual = map.remove(randomKey);

	assertEquals(actual, expected);
    }

    @Test(dataProvider = "maps")
    public void removeNull(final Map<String, Integer> map) {
	for (int index = ZERO; index < 50; index++) {
	    map.put("index" + index, ThreadLocalRandom.current().nextInt(100));
	}
	final int expected = ThreadLocalRandom.current().nextInt();
	map.put(null, expected);
	final int actual = map.remove(null);

	assertEquals(expected, actual);
    }
}
