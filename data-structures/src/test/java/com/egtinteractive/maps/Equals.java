package com.egtinteractive.maps;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.maps.Map;

public class Equals extends SetupTests {

    /**
     * All objects must be equal to itself
     * 
     * @param list
     */
    @Test(dataProvider = "map")
    public void reflexivity(final Map<String, Integer> map) {
	for (int index = ZERO; index < ThreadLocalRandom.current().nextInt(50); index++) {
	    map.put("Index" + index, index);
	}
	assertTrue(map.equals(map));
    }

    /**
     * If object 'A' is equal to object 'B', then object 'B' must be equal to
     * object 'A'
     * 
     * @param list
     */
    @Test(dataProvider = "twoMaps")
    public void simetry(final Map<String, Integer> A, final Map<String, Integer> B) {
	for (int index = ZERO; index < ThreadLocalRandom.current().nextInt(50); index++) {
	    A.put("Index" + index, index);
	    B.put("Index" + index, index);
	}
	assertTrue((A.equals(B)) && (B.equals(A)));
    }

    @Test(dataProvider = "threeMaps")
    /**
     * If A is equal to B and B is equal to C, then A must be equal to C
     * 
     * @param list
     */
    public void transitivity(final Map<String, Integer> A, final Map<String, Integer> B, final Map<String, Integer> C) {
	for (int index = ZERO; index < ThreadLocalRandom.current().nextInt(50); index++) {
	    A.put("Index" + index, index);
	    B.put("Index" + index, index);
	    C.put("Index" + index, index);
	}
	if (A.equals(B) && B.equals(C)) {
	    assertTrue(A.equals(C));
	} else
	    assertFalse(A.equals(C));
    }

    @Test(dataProvider = "twoMaps")
    /**
     * If two objects are equal, they must remain equal for all time, unless one
     * of them is changed
     * 
     * @param list
     */
    public void consistency(final Map<String, Integer> A, final Map<String, Integer> B) {
	for (int index = ZERO; index < ThreadLocalRandom.current().nextInt(50); index++) {
	    A.put("Index" + index, index);
	    B.put("Index" + index, index);
	}
	assertTrue(A.equals(B));
	B.put(null, null);
	assertFalse(A.equals(B));
    }

    @Test(dataProvider = "twoMaps")
    /**
     * All objects must be unequal to null
     * 
     * @param list
     */
    public void nonality(final Map<String, Integer> A, Map<String, Integer> B) {
	B = null;
	assertFalse(A.equals(B));
    }

    @Test(dataProvider = "twoMaps")
    public void mapsHasTheSameContent_ShouldReturnTrue(Map<String, Integer> A, Map<String, Integer> B) {
	for (int i = 1; i < 100; i++) {
	    A.put(String.valueOf(i), i);
	    B.put(String.valueOf(i), i);
	}

	final boolean result1 = A.equals(B);
	final boolean result2 = B.equals(A);
	assertTrue(result1);
	assertTrue(result2);

    }

    @Test(dataProvider = "twoMaps")
    public void mapsHasNullValuesAndEqualKeys_shouldReturnTrue(Map<String, Integer> A, Map<String, Integer> B) {
	for (int i = 1; i < 100; i++) {

	    A.put(String.valueOf(i), null);
	    B.put(String.valueOf(i), null);
	}

	final boolean result1 = A.equals(B);
	final boolean result2 = B.equals(A);
	assertTrue(result1);
	assertTrue(result2);
    }
}