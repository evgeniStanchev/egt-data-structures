package com.egtinteractive.maps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.maps.Map;

public class HashCode extends SetupTests {

    @Test(dataProvider = "twoMaps")
    public void twoObjectsAreEqual(final Map<String, Integer> A, final Map<String, Integer> B) {

	for (int index = ZERO; index < 50; index++) {
	    A.put("Index" + index, index);
	    B.put("Index" + index, index);
	}

	assertTrue(A.equals(B));
	assertTrue(A.hashCode() == B.hashCode());
    }

    @Test(dataProvider = "map")
    public void invokingHashCodeMoreThanOnce(final Map<String, Integer> map) {
	for (int index = ZERO; index < 50; index++) {
	    map.put("Index" + index, index);
	}
	assertTrue(map.hashCode() == map.hashCode());
    }

    @Test(dataProvider = "twoMaps")
    public void mapsWithEqualKeysButNullValues(Map<String, Integer> A, Map<String, Integer> B) {
	int size = ThreadLocalRandom.current().nextInt(30);
	for (int i = 1; i < size; i++) {
	    A.put(String.valueOf(i), null);
	    B.put(String.valueOf(i), null);
	}
	assertEquals(A.hashCode(), B.hashCode());
    }
}
