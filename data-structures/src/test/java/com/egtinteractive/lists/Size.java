package com.egtinteractive.lists;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.lists.List;
import com.egtinteractive.resources.SetupTests;

public class Size extends SetupTests {

    @Test(dataProvider = "lists")
    public void sizeMethodReturnsCorrectValue(final List<Integer> list) {
	final int size = RANDOM_POSITIVE_INT;

	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}

	final int actual = size;
	final int expected = list.size();
	assertEquals(actual, expected);
    }

    @Test(dataProvider = "lists")
    public void sizeIncreaseAfterAddingNull(final List<Integer> list) {
	int size = RANDOM_POSITIVE_INT;

	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	list.add(null);
	size++;
	final int actual = size;
	final int expected = list.size();
	assertEquals(actual, expected);
    }

}
