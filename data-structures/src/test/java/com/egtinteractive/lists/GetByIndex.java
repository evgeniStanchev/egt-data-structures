package com.egtinteractive.lists;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.lists.List;
import com.egtinteractive.resources.SetupTests;

public class GetByIndex extends SetupTests {

    @Test(dataProvider = "lists")
    public void getElementByCorrectIndex(final List<Integer> list) {
	final int size = RANDOM_POSITIVE_INT;
	final int randomIndex = ThreadLocalRandom.current().nextInt(size - 1);

	int nextValue = ZERO;
	int expected = ZERO;

	for (int index = ZERO; index < size; index++) {
	    nextValue = ThreadLocalRandom.current().nextInt();
	    list.add(nextValue);
	    if (index == randomIndex) {
		expected = nextValue;
	    }
	}
	final int actual = list.get(randomIndex);
	assertEquals(actual, expected);
    }

    @Test(dataProvider = "lists")
    public void getNullByCorrectIndex(final List<Integer> list) {
	for (int index = ZERO; index < 50; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	list.add(null);
	assertNull(list.get(50));
    }
}
