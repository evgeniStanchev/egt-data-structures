package com.egtinteractive.maps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.maps.Map;

public class ContainsValue extends SetupTests {

    @Test(dataProvider = "maps")
    public void doesContainsTheKey(final Map<String, Integer> map) {

	for (int index = ZERO; index < 100; index++) {
	    map.put("Index" + index, index);
	}

	final boolean actual = map.containsValue(10);
	final boolean expected = true;
	assertEquals(actual, expected);
    }

    public static int generateDifferentNumber(ArrayList<int[]> values, int i) {
	final int value = i;
	if (!(values.contains(value))) {
	    return value;
	}
	return generateDifferentNumber(values, ++i);
    }

    @Test(dataProvider = "maps")
    public void containsShouldReturnTrueIfContains(final Map<String, Integer> map) {

	final int randomSize = ThreadLocalRandom.current().nextInt(100);
	for (int i = ZERO; i < randomSize; i++) {
	    map.put(i + "", i);
	}

	for (int i = ZERO; i < randomSize; i++) {
	    assertTrue(map.containsValue(i));
	}
    }

    @Test(dataProvider = "maps")
    public void containsNull(final Map<String, Integer> map) {

	map.put(UUID.randomUUID().toString(), null);
	assertTrue(map.containsValue(null));

    }

    @Test(dataProvider = "maps")
    public void theValuesExists_containsShouldReturnTrueForEachOne(Map<Character, Integer> map) {
	for (int i = 1; i < 30; i++) {
	    map.put((char) i, i);
	}
	for (int i = 1; i < 30; i++) {
	    assertTrue(map.containsValue(i));
	}
    }

    @Test(dataProvider = "maps")
    public void addingStrings(Map<String, String> map) {

	for (int i = 0; i < 10; i++) {
	    map.put(i + "", new String(i + ""));
	}

	for (int i = 0; i < 10; i++) {
	    assertTrue(map.containsValue(1 + ""));
	}
    }
}
