package com.egtinteractive.maps;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.maps.Map;

public class Put extends SetupTests {

    @Test(dataProvider = "maps")
    public void newKey(final Map<String, Integer> map) {

	for (int index = ZERO; index < 50; index++) {
	    map.put("index" + index, ThreadLocalRandom.current().nextInt(100));
	}

	final String randomString = "Abs0lU7ey-R@nd0m-S7r1nG";
	final int randomValue = ThreadLocalRandom.current().nextInt(100);

	final Object actualReturn = map.put(randomString, randomValue);
	final Object expectedReturn = null;

	int actualValue = map.get(randomString);
	int expectedValue = randomValue;

	assertEquals(actualReturn, expectedReturn);
	assertEquals(actualValue, expectedValue);
    }

    @Test(dataProvider = "maps")
    public void usedKey(final Map<String, Integer> map) {

	final int randomSize = ThreadLocalRandom.current().nextInt(10, 100);

	final int randomIndex = ThreadLocalRandom.current().nextInt(randomSize);

	int randomValue = ThreadLocalRandom.current().nextInt(100);
	String randomKey = "";

	for (int index = ZERO; index < randomSize; index++) {
	    randomValue = ThreadLocalRandom.current().nextInt(100);
	    map.put("index" + index, randomValue);
	    if (index == randomIndex) {
		randomKey = "index" + index;
	    }
	}

	final Object expected = map.get(randomKey);
	final Object actual = map.put(randomKey, randomValue);

	assertEquals(actual, expected);

	final int actualValue = map.get(randomKey);
	final int expectedValue = randomValue;

	assertEquals(actualValue, expectedValue);

    }

    @Test(dataProvider = "maps")
    public void putNullvalue_(final Map<String, Integer> map) {
	map.put("Evgeni", null);

	final Object actual = map.get("Evgeni");
	final Object expected = null;
	assertEquals(actual, expected);
    }

    @Test(dataProvider = "maps")
    public void putNullKey_(final Map<String, Integer> map) {
	map.put(null, null);
	final Object actual = map.get(null);
	final Object expected = null;
	assertEquals(actual, expected);
    }
}
