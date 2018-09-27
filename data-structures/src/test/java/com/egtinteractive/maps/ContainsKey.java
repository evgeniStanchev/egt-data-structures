package com.egtinteractive.maps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.maps.Map;

public class ContainsKey extends SetupTests {

    @Test(dataProvider = "maps")
    public void doesContainsTheKey(final Map<String, Integer> map) {
	for (int index = ZERO; index < 50; index++) {
	    map.put("index" + index, ThreadLocalRandom.current().nextInt(100));
	}
	for (int index = ZERO; index < 50; index++) {
	    final boolean actual = map.containsKey("index" + index);
	    final boolean expected = true;
	    assertEquals(actual, expected);
	}
    }

    @Test(dataProvider = "maps")
    public void doesNotContainsTheKey(final Map<String, Integer> map) {
	for (int index = ZERO; index < 50; index++) {
	    map.put("index " + index, ThreadLocalRandom.current().nextInt(100));
	}
	final boolean actual = map.containsKey("dfkjasdsahfewuf");
	final boolean expected = false;

	assertEquals(actual, expected);
    }

    @Test(dataProvider = "maps")
    public void putNull(final Map<String, Integer> map) {
	map.put(null, 2);
	assertTrue(map.containsKey(null));
    }
}
