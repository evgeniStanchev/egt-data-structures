package com.egtinteractive.lists;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.lists.List;
import com.egtinteractive.resources.SetupTests;

public class AddByElement extends SetupTests {

    @Test(dataProvider = "lists")
    public void addIntegerByValue_SizeIncreaseAndAddedValueShouldBeLast(final List<Integer> list) {

	for (int index = ZERO; index < RANDOM_POSITIVE_INT + 1; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}

	final int expectedSize = list.size() + 1;
	final int expectedLastElement = RANDOM_INT;

	list.add(expectedLastElement);

	final int actualSize = list.size();
	final int actualLastElement = list.get(list.size() - 1);

	assertEquals(actualSize, expectedSize);
	assertEquals(actualLastElement, expectedLastElement);
    }

    @Test(dataProvider = "lists")
    public void addStringbyValue_SizeShouldIncreaseAndAddedStringShouldBeLast(final List<String> list) {

	for (int index = ZERO; index < RANDOM_INT_BIGGER_THAN_10; index++) {
	    list.add("index" + index);
	}

	final int expectedSize = list.size() + 1;
	final String expectedLastElement = "index" + RANDOM_INT;

	list.add(expectedLastElement);

	final int actualSize = list.size();
	final String actualLastElement = list.get(list.size() - 1);

	assertEquals(actualSize, expectedSize);
	assertEquals(actualLastElement, expectedLastElement);
    }

    @Test(dataProvider = "lists")
    public void addNullValue_TheListShouldContainsNull(final List<Integer> list) {
	list.add(RANDOM_INT);
	list.add(null);
	list.add(RANDOM_INT);
	assertTrue(list.contains(null));
    }

    @Test(dataProvider = "lists")
    public void addNullString_TheListShouldContainsNull(final List<String> list) {
	list.add("" + RANDOM_INT);
	list.add(null);
	list.add("" + RANDOM_INT);
	assertTrue(list.contains(null));
    }

    @Test(dataProvider = "lists")
    public void addFirstValueToBeNull_TheListShouldContainsNull(final List<Integer> list) {
	list.add(null);
	assertTrue(list.contains(null));
    }

    @Test(dataProvider = "lists")
    public void addFirstStringToBeNull_TheListShouldContainsNull(final List<String> list) {
	list.add(null);
	assertTrue(list.contains(null));
    }

    @Test(dataProvider = "lists")
    public void addLastValueToBeNull_TheListShouldContainsNull(final List<Integer> list) {
	list.add(RANDOM_INT);
	list.add(null);
	assertTrue(list.contains(null));
    }

    @Test(dataProvider = "lists")
    public void addLastStringToBeNull_TheListShouldContainsNull(final List<String> list) {
	list.add("" + RANDOM_INT);
	list.add(null);
	assertTrue(list.contains(null));
    }
}
