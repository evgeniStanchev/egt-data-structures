package com.egtinteractive.trees;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.trees.Tree;

public class LowerThan extends SetupTests {

    @Test(dataProvider = "trees")
    public void emptyTree(final Tree<Integer> tree) {
	final Object expected = null;

	final Object actual = tree.lower(ThreadLocalRandom.current().nextInt());

	assertEquals(actual, expected);
    }

    @Test(dataProvider = "trees")
    public void theLowerElementIsInTheTree_ReturnTheRightElement(final Tree<Integer> tree) {

	for (int index = 1; index < 50; index++) {
	    tree.add(index);
	}
	final int randomValue = RANDOM_INT_LESS_THAN_50;
	if (randomValue == 0) {
	    final Object actual = tree.lower(randomValue);
	    final Object expected = null;
	    assertEquals(actual, expected);
	} else {
	    final int actual = tree.lower(randomValue);
	    final int expected = randomValue - 1;
	    assertEquals(actual, expected);
	}
    }

    @Test(dataProvider = "trees")
    public void theTreeIsEmptyCallNull_shouldReturnNull(final Tree<Integer> tree) {

	final Object expected = null;
	final Object actual = tree.lower(null);

	assertEquals(actual, expected);
    }

    @Test(dataProvider = "trees", expectedExceptions = NullPointerException.class)
    public void treeIsNotEmptyCallNull_expectedException(final Tree<Integer> tree) throws NullPointerException {
	tree.add(RANDOM_INT);
	tree.lower(null);
    }

}
