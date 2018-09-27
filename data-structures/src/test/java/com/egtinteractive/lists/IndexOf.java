package com.egtinteractive.lists;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.lists.List;
import com.egtinteractive.resources.SetupTests;

public class IndexOf extends SetupTests {

    @Test(dataProvider = "lists")
    public void shouldReturnTheIndexOfTheFirstOccurence(final List<Integer> list) {
	final int size = 50;
	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	list.set(1, 10);
	list.set(10, 10);
	final int actual = list.indexOf(10);
	final int expected = 1;
	assertEquals(actual, expected);
    }

    @Test(dataProvider = "lists")
    public void indexOfHasNoOccurence(final List<Integer> list) {
	final int size = RANDOM_POSITIVE_INT;
	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt(10, 100));
	}
	int nonExistingRandomNum = RANDOM_INT;
	while ((nonExistingRandomNum >= 10) && (nonExistingRandomNum < 100)) {
	    nonExistingRandomNum = ThreadLocalRandom.current().nextInt();
	}

	final int actual = list.indexOf(nonExistingRandomNum);
	final int expected = -1;
	assertEquals(actual, expected);
    }

    @Test(dataProvider = "lists")
    public void indexOfNull(final List<Integer> list) {
	final int size = 50;
	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt(10, 100));
	}
	list.add(null);
	final int actual = list.indexOf(null);
	final int expected = size;
	assertEquals(actual, expected);
    }
}
