package com.egtinteractive.trees;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.trees.BinaryTree;
import com.interactive.trees.Tree;

public class Add extends SetupTests {

    @Test(dataProvider = "trees")
    public void addFirstElement(final Tree<Integer> tree) throws Throwable {

	tree.add(RANDOM_INT);

	final Object root = BinaryTree.getInstanceField(tree, "root");

	Object expected = RANDOM_INT;
	expected = expected.toString();
	final String actual = root.toString();
	assertEquals(actual, expected);
    }

    @Test(dataProvider = "trees", expectedExceptions = NullPointerException.class)
    public void addNullValue(final Tree<Integer> tree) throws NullPointerException {
	tree.add(null);
    }

    @Test(dataProvider = "trees")
    public void addALotOfElements(final Tree<Integer> tree) {
	int randomInt;
	for (int index = 0; index < RANDOM_INT_BIGGER_THAN_10; index++) {
	    randomInt = ThreadLocalRandom.current().nextInt();
	    tree.add(randomInt);
	}
    }
}
