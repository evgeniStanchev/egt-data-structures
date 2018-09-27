package com.egtinteractive.lists;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.egtinteractive.lists.List;
import com.egtinteractive.resources.SetupTests;

public class Equals extends SetupTests {

    private static final int ZERO = 0;

    /**
     * All objects must be equal to itself
     * 
     * @param list
     */
    @Test(dataProvider = "list")
    public void reflexivity(final List<Integer> list) {
	for (int index = ZERO; index < RANDOM_POSITIVE_INT; index++) {
	    list.add(index);
	}
	assertTrue(list.equals(list));
    }

    /**
     * If object 'A' is equal to object 'B', then object 'B' must be equal to
     * object 'A'
     * 
     * @param list
     */
    @Test(dataProvider = "twoLists")
    public void simetry(final List<Integer> A, final List<Integer> B) {

	for (int index = ZERO; index < RANDOM_POSITIVE_INT; index++) {
	    A.add(index);
	    B.add(index);
	}
	assertTrue((A.equals(B)) && (B.equals(A)));
    }

    @Test(dataProvider = "threeLists")
    /**
     * If A is equal to B and B is equal to C, then A must be equal to C
     * 
     * @param list
     */
    public void transitivity(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
	for (int index = ZERO; index < RANDOM_POSITIVE_INT; index++) {
	    A.add(index);
	    B.add(index);
	    C.add(index);
	}
	if (A.equals(B) && B.equals(C)) {
	    assertTrue(A.equals(C));
	}
    }

    @Test(dataProvider = "twoLists")
    /**
     * If two objects are equal, they must remain equal for all time, unless one
     * of them is changed
     * 
     * @param list
     */
    public void consistency(final List<Integer> A, final List<Integer> B) {
	for (int index = ZERO; index < RANDOM_POSITIVE_INT; index++) {
	    A.add(index);
	    B.add(index);
	}
	assertTrue(A.equals(B));
	B.add(null);
	assertFalse(A.equals(B));
    }

    @Test(dataProvider = "twoLists")
    public void changeOneOfTheLists(final List<String> A, final List<String> B) {
	for (int i = 0; i < 10; i++) {
	    A.add(i + "");
	    B.add(i + "");
	}
	assertTrue(A.equals(B));
	A.set(2, "asdf");
	assertFalse(A.equals(B));
    }

    @Test(dataProvider = "twoLists")
    /**
     * All objects must be unequal to null
     * 
     * @param list
     */
    public void nonality(final List<Integer> A, List<Integer> B) {
	B = null;
	assertFalse(A.equals(B));
    }

    @Test(dataProvider = "twoLists")
    public void twoListsAreEmpty_shouldReturnTrue(final List<Integer> A, final List<String> B) {
	assertTrue(A.equals(B));
    }
}
