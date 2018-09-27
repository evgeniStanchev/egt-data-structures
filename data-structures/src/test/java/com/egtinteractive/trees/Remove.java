package com.egtinteractive.trees;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.trees.BinaryTree;
import com.interactive.trees.Tree;

public class Remove extends SetupTests {

    @Test(dataProvider = "trees")
    public void removedElement_soTheSizeDecreaseWithOne(final Tree<Integer> tree) {
	final int size = 10;
	for (int index = 0; index < size; index++) {
	    tree.add(index);
	}
	tree.remove(3);
	final int expectedSize = size - 1;
	final int actualSize = tree.size();
	assertEquals(actualSize, expectedSize);
    }

    @Test(dataProvider = "trees")
    public void removedElementShouldntExists(final BinaryTree<Integer> tree) {
	final int size = 10;
	for (int index = 0; index < size; index++) {
	    tree.add(index);
	}
	final int value = 3;
	tree.remove(value);

	final boolean actual = tree.exists(value);
	final boolean expected = false;
	assertEquals(actual, expected);
    }

    @Test(dataProvider = "trees", expectedExceptions = NullPointerException.class)
    public void removeNull(final Tree<Integer> tree) throws NullPointerException {
	tree.remove(null);
    }
}
