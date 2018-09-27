package com.egtinteractive.trees;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.trees.BinaryTree;
import com.interactive.trees.Tree;

public class PollFirst extends SetupTests {

    @Test
    public void emptyTree_shouldReturnNull() {

	final Object expected = null;

	final BinaryTree<Integer> tree = new BinaryTree<Integer>();

	final Object actual = tree.pollFirst();

	assertEquals(actual, expected);
    }

    @Test(dataProvider = "trees")
    public void treeIsNotEmpty_itShouldDeleteTheLowest(final Tree<Integer> tree) {

	int randomInt = ThreadLocalRandom.current().nextInt();
	int lowest = randomInt;
	tree.add(lowest);
	final int randomSize = RANDOM_INT_BIGGER_THAN_10;

	for (int index = 0; index < randomSize; index++) {
	    randomInt = ThreadLocalRandom.current().nextInt();
	    tree.add(randomInt);
	    if (randomInt < lowest) {
		lowest = randomInt;
	    }
	}
	final int actual = tree.pollFirst();
	final int expected = lowest;

	assertEquals(actual, expected);
    }

    @Test(dataProvider = "trees")
    public void removedElementShouldNotBeInTheTree(final BinaryTree<Integer> tree) {

	int randomInt = ThreadLocalRandom.current().nextInt();

	final int randomSize = RANDOM_INT_BIGGER_THAN_10;

	for (int index = 0; index < randomSize; index++) {
	    randomInt = ThreadLocalRandom.current().nextInt();
	    tree.add(randomInt);
	}

	int element = tree.pollFirst();
	final boolean actual = tree.exists(element);
	final boolean expected = false;

	assertEquals(actual, expected);
    }
}
