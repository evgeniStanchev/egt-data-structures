package com.egtinteractive.lists;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.lists.ArrayList;
import com.egtinteractive.lists.List;
import com.egtinteractive.resources.SetupTests;

public class AddByIndexAndElement extends SetupTests {

    @Test(dataProvider = "lists")
    public void addAtSpecificIndexShouldAddCorrectlyWithStrings(List<Integer> list) {
	final List<Integer> arrayList = new ArrayList<>();
	final int size = RANDOM_POSITIVE_INT;

	list.add(ZERO, RANDOM_INT);
	arrayList.add(ZERO, RANDOM_INT);

	for (int i = 1; i < size; i++) {
	    final int num = ThreadLocalRandom.current().nextInt(300);
	    final int index = ThreadLocalRandom.current().nextInt(i);
	    list.add(index, num);
	    arrayList.add(index, num);
	}

	for (int i = ZERO; i < size; i++) {
	    assertEquals(list.get(i), arrayList.get(i));
	}
    }

    @Test(dataProvider = "lists")
    public void addNullValue_TheListShouldContainsNull(final List<Integer> list) {
	list.add(1);
	list.add(1, null);
	list.add(2);
	assertTrue(list.contains(2));
    }

    @Test(dataProvider = "lists")
    public void addNullString_TheListShouldContainsNull(final List<String> list) {
	list.add("" + 1);
	list.add(1, null);
	list.add("" + 2);
	assertTrue(list.contains(null));
    }

    @Test(dataProvider = "lists")
    public void addNullHead_(final List<Integer> list) {
	for (int index = 0; index < 10; index++) {
	    list.add(index);
	}
	list.add(0, null);

	final Integer actual = list.get(0);
	assertNull(actual);
    }

    @Test(dataProvider = "lists")
    public void addNullHeadListOfStrings_(final List<String> list) {
	for (int index = 0; index < 10; index++) {
	    list.add("index" + index);
	}
	list.add(0, null);

	final String actual = list.get(0);
	assertNull(actual);
    }

    @Test(dataProvider = "lists")
    public void addNullValueAtTailPosition(final List<Integer> list) {
	final int size = 10;
	for (int index = 0; index < size; index++) {
	    list.add(index);
	}
	list.add(size, null);

	final Integer actual = list.get(size);

	assertNull(actual);
    }

    @Test(dataProvider = "lists")
    public void addNullStringAtTailPosition(final List<String> list) {

	for (int index = 0; index < RANDOM_INT_BIGGER_THAN_10; index++) {
	    list.add("index" + index);
	}
	list.add(RANDOM_INT_BIGGER_THAN_10, null);

	final String actual = list.get(RANDOM_INT_BIGGER_THAN_10);

	assertNull(actual);
    }

    @Test(dataProvider = "lists")
    public void addByIndexAndValue(final List<Integer> list) {

	final int size = RANDOM_POSITIVE_INT;
	for (int index = ZERO; index < size; index++) {
	    list.add(RANDOM_INT);
	}

	final int expectedSize = size + 1;
	final int randomValue = RANDOM_INT;
	final int randomIndex = ThreadLocalRandom.current().nextInt(size);

	list.add(randomIndex, randomValue);

	final int actualElement = list.get(randomIndex);
	final int expectedElement = randomValue;
	assertEquals(list.size(), expectedSize);
	assertEquals(actualElement, expectedElement);
    }

    @Test(dataProvider = "lists")
    public void addByIndexAndString(final List<String> list) {

	final int size = RANDOM_POSITIVE_INT;

	for (int index = ZERO; index < size; index++) {
	    list.add("index" + ThreadLocalRandom.current().nextInt());
	}

	final int expectedSize = size + 1;

	final String randomValue = "index" + RANDOM_INT;
	final int randomIndex = ThreadLocalRandom.current().nextInt(size);

	list.add(randomIndex, randomValue);

	final String actualElement = list.get(randomIndex);
	final String expectedElement = randomValue;
	assertEquals(list.size(), expectedSize);
	assertEquals(actualElement, expectedElement);
    }

}
