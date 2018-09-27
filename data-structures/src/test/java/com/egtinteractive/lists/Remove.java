package com.egtinteractive.lists;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.lists.List;
import com.egtinteractive.resources.SetupTests;

public class Remove extends SetupTests {

    @Test(dataProvider = "lists")
    public void removeValueSizeDecrement(final List<Integer> list) {
	final int size = RANDOM_POSITIVE_INT + 2;

	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}

	final int sizeBeforeRemoving = list.size();

	list.remove(new Integer(list.get(1)));

	list.remove(ThreadLocalRandom.current().nextInt(size));

	final int sizeAfterRemoving = list.size();
	assertEquals(sizeAfterRemoving, sizeBeforeRemoving - 2);
    }

    @Test(dataProvider = "lists")
    public void removeOnlyTheFirstOccurence(final List<Integer> list) {
	final int size = RANDOM_POSITIVE_INT + 2;

	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}

	final int elementThatShouldBeRemoved = ThreadLocalRandom.current().nextInt(65563);
	int differentElement = ZERO;
	while (differentElement == elementThatShouldBeRemoved) {
	    differentElement = ThreadLocalRandom.current().nextInt();
	}

	final int elementThatShouldNotBeRemoved = elementThatShouldBeRemoved;

	list.set(1, elementThatShouldBeRemoved);
	list.set(2, differentElement);
	list.set(9, differentElement);
	list.set(10, elementThatShouldNotBeRemoved);
	list.remove(new Integer(elementThatShouldBeRemoved));
	final int actual1 = list.get(1);
	final int actual9 = list.get(9);

	assertEquals(actual1, differentElement);
	assertEquals(actual9, elementThatShouldNotBeRemoved);
    }

    @Test(dataProvider = "lists")
    public void removeAllElementsOneByOne(final List<Integer> list) {
	for (int i = 0; i < 10; i++) {
	    list.add(i);
	}
	for (int i = list.size() - 1; i >= 0; i--) {
	    list.remove(i);
	}
	assertEquals(list.size(), 0);
	for (int i = 0; i < 10; i++) {
	    list.add(i);
	}
	final Iterator<Integer> it = list.iterator();
	while (it.hasNext()) {
	    it.next();
	    it.remove();
	}
	assertEquals(list.size(), 0);
    }

    @Test(dataProvider = "lists")
    public void removeAll_shouldReturnZeroSize(List<Integer> list) {

	for (int i = ZERO; i < list.size(); i++) {
	    list.add(i);
	}
	for (int i = ZERO; i < list.size(); i++) {
	    list.remove(ZERO);
	}
	assertEquals(list.size(), ZERO);

	int counter = ZERO;
	for (int i : list) {
	    counter += i;
	}
	assertEquals(counter, ZERO);
    }

    @Test(dataProvider = "lists")
    public void removeAllElementsByElements_shouldReturnZeroSize(List<String> list) {
	int size = RANDOM_POSITIVE_INT;
	String[] arr = new String[size];

	for (int i = ZERO; i < size; i++) {
	    final String str = String.valueOf(ThreadLocalRandom.current().nextInt(10, 100));
	    arr[i] = str;
	    list.add(str);
	}
	for (int i = ZERO; i < size; i++) {

	    list.remove(arr[i]);
	}
	assertEquals(list.size(), ZERO);

	String counter = "";
	for (String str : list) {
	    counter += str;
	}
	assertEquals(counter, "");
    }

    @Test(dataProvider = "lists")
    public void removeAtIndex(final List<Integer> list) {
	final int size = RANDOM_INT_BIGGER_THAN_10;
	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}

	while (list.get(3).equals(list.get(4))) {
	    list.set(4, ThreadLocalRandom.current().nextInt());
	}

	final int elementThatShouldReplace = list.get(4);
	list.remove(3);
	final int actual = list.get(3);

	assertEquals(actual, elementThatShouldReplace);
    }

    @Test(dataProvider = "lists")
    public void removeNullValue(final List<Integer> list) {
	final int size = 50;
	list.add(null);
	for (int index = 1; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	assertTrue(list.remove(null));
    }

}
