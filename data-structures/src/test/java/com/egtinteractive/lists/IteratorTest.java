package com.egtinteractive.lists;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.lists.List;
import com.egtinteractive.resources.SetupTests;

public class IteratorTest extends SetupTests {

    @Test(dataProvider = "lists")
    public void shouldNotDoAnythingOnAnEmptyList(List<Integer> list) {
	int result = ZERO;
	for (int i = 0; i < list.size(); i++) {
	    result += i;
	}
	assertEquals(result, ZERO);
    }

    @Test(dataProvider = "lists")
    public void stringRemovedWithIterator(final List<String> list) {
	list.add("a");
	list.add(null);
	list.add("b");
	list.add("c");
	assertTrue(list.remove("c"));
	final Iterator<String> it = list.iterator();
	while (it.hasNext()) {
	    it.next();
	}
    }

    @Test(dataProvider = "lists")
    public void iteratorHasNextTest(final List<Integer> list) {
	final int size = RANDOM_POSITIVE_INT;
	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	final Iterator<Integer> iterator = list.iterator();
	final boolean expected = true;
	final boolean actual = iterator.hasNext();
	assertEquals(actual, expected);
    }

    @Test(dataProvider = "lists")
    public void iteratorRemoveThenListSizeDecrease(final List<Integer> list) {
	final int size = RANDOM_POSITIVE_INT;
	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	final Iterator<Integer> iterator = list.iterator();
	iterator.next();
	iterator.remove();
	final int expected = size - 1;
	final int actual = list.size();
	assertEquals(actual, expected);
    }

}
