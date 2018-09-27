package com.egtinteractive.trees;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.trees.BinaryTree;
import com.interactive.trees.Tree;

public class HigherThan extends SetupTests {

    @Test(dataProvider = "trees")
    public void theTreeIsEmpty_shouldReturnNull(final Tree<Integer> tree) {
	final Object expected = null;
	final Object actual = tree.lower(RANDOM_INT);
	assertEquals(actual, expected);
    }

    @Test
    public void theHigherElementIsInTheTree_ReturnTheRightElement() {
	final BinaryTree<Integer> tree = new BinaryTree<>();
	for (int index = 0; index < RANDOM_POSITIVE_INT; index++) {
	    tree.add(index);
	}
	final int randomValue = 0;
	if (RANDOM_POSITIVE_INT != 0) {
	    ThreadLocalRandom.current().nextInt(RANDOM_POSITIVE_INT);
	}
	if (randomValue == RANDOM_POSITIVE_INT) {
	    Object actual = tree.higher(randomValue);
	    Object expected = null;
	    assertEquals(actual, expected);
	} else {
	    Integer actual = tree.higher(randomValue);
	    Integer expected = null;
	    if (tree.exists(randomValue + 1)) {
		expected = randomValue + 1;
	    }
	    assertEquals(actual, expected);
	}
    }

    @Test(dataProvider = "trees")
    public void theTreeIsEmptyCallNull_shouldReturnNull(final Tree<Integer> tree) {

	final Object expected = null;
	final Object actual = tree.higher(3);

	assertEquals(actual, expected);
    }

    @Test(dataProvider = "trees", expectedExceptions = NullPointerException.class)
    public void treeIsNotEmptyCallNull_expectedException(final Tree<Integer> tree) throws NullPointerException {
	tree.add(RANDOM_INT);
	tree.higher(null);
    }

}
