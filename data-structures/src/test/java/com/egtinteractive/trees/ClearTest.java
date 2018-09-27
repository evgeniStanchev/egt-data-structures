package com.egtinteractive.trees;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.trees.Tree;

public class ClearTest extends SetupTests {

    @Test(dataProvider = "trees")
    public void sizeMustBeZero(final Tree<Integer> tree) {
	for (int currentIndex = 0; currentIndex < RANDOM_POSITIVE_INT; currentIndex++) {
	    tree.add(ThreadLocalRandom.current().nextInt());
	}
	tree.clear();
	final int expectedSize = ZERO;
	final int actualSize = tree.size();
	assertEquals(actualSize, expectedSize);
    }
}
