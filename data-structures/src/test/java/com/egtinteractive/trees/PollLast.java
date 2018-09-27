package com.egtinteractive.trees;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.trees.BinaryTree;
import com.interactive.trees.Tree;

public class PollLast extends SetupTests {

    @Test(dataProvider = "trees")
    public void emptyTree(final Tree<Integer> tree) {
	final Object expected = null;
	final Object actual = tree.pollLast();
	assertEquals(actual, expected);
    }

    @Test(dataProvider = "trees")
    public void treeIsNotEmpty_itShouldDeleteTheLowest(final Tree<Integer> tree) {
	int randomInt = RANDOM_INT;
	int biggest = randomInt;
	tree.add(biggest);
	int randomSize = RANDOM_INT_BIGGER_THAN_10;

	for (int index = 0; index < randomSize; index++) {
	    randomInt = ThreadLocalRandom.current().nextInt();
	    tree.add(randomInt);
	    if (randomInt > biggest) {
		biggest = randomInt;
	    }
	}
	final int actual = tree.pollLast();
	final int expected = biggest;
	assertEquals(actual, expected);
    }

    @Test(dataProvider = "trees")
    public void removedElementShouldNotBeInTheTree(final BinaryTree<Integer> tree) {
	int randomInt = RANDOM_INT;
	final int randomSize = ThreadLocalRandom.current().nextInt(10, 100);

	for (int index = 0; index < randomSize; index++) {
	    randomInt = ThreadLocalRandom.current().nextInt();
	    tree.add(randomInt);
	}
	final int element = tree.pollLast();
	final boolean actual = tree.exists(element);
	final boolean expected = false;
	assertEquals(actual, expected);
    }
}
