package com.egtinteractive.lists;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.lists.List;
import com.egtinteractive.resources.SetupTests;

public class SetTests extends SetupTests {

    @Test(dataProvider = "lists")
    public void overwriteAnElement(final List<Integer> list) {
	final int size = RANDOM_POSITIVE_INT;
	for (int index = ZERO; index < RANDOM_POSITIVE_INT; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	final int randomIndex = ThreadLocalRandom.current().nextInt(size);
	final int expectedElement = RANDOM_INT;
	list.set(randomIndex, expectedElement);
	final int actualElement = list.get(randomIndex);
	assertEquals(actualElement, expectedElement);
    }

    @Test(dataProvider = "lists")
    public void setNull(final List<Integer> list) {
	final int size = 50;
	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	final int randomPlace = RANDOM_POSITIVE_INT;
	list.set(randomPlace, null);
	assertNull(list.get(randomPlace));
    }
}
