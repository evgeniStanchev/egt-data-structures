package com.egtinteractive.lists;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.lists.List;
import com.egtinteractive.resources.SetupTests;

public class Contains extends SetupTests {

    @Test(dataProvider = "lists")
    public void containsTheValue_ShouldReturnTrue(final List<Integer> list) {

	for (int index = ZERO; index < 50; index++) {
	    list.add(index);

	}
	final int existing = ThreadLocalRandom.current().nextInt(50);
	assertTrue(list.contains(existing));

    }

    @Test(dataProvider = "lists")
    public void doesntContainsTheValue_ShouldReturnFalse(final List<Integer> list) {
	for (int index = ZERO; index < 50; index++) {
	    list.add(index);

	}
	final int nonexisting = ThreadLocalRandom.current().nextInt(50, 1000);
	assertFalse(list.contains(nonexisting));
    }

    @Test(dataProvider = "lists")
    public void containsNullValue_ShouldReturnTrue(final List<Integer> list) {
	list.add(null);
	assertTrue(list.contains(null));
    }

    @Test(dataProvider = "lists")
    public void doesntContainsNullValue_ShouldReturnFalse(final List<Integer> list) {
	for (int index = 0; index < 20; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	assertFalse(list.contains(null));
    }

    @Test(dataProvider = "lists")
    public void containsTheString_ShouldReturnTrue(final List<String> list) {

	for (int index = ZERO; index < 50; index++) {
	    list.add("index" + index);

	}
	final String existing = "index" + ThreadLocalRandom.current().nextInt(50);
	assertTrue(list.contains(existing));

    }

    @Test(dataProvider = "lists")
    public void doesntContainsTheString_ShouldReturnFalse(final List<String> list) {
	for (int index = ZERO; index < 50; index++) {
	    list.add("index" + index);
	}
	final String nonexisting = "index" + ThreadLocalRandom.current().nextInt(50, 1000);
	assertFalse(list.contains(nonexisting));
    }

    @Test(dataProvider = "lists")
    public void containsNullString_ShouldReturnTrue(final List<String> list) {
	list.add(null);
	assertTrue(list.contains(null));
    }

    @Test(dataProvider = "lists")
    public void doesntContainsNullString_ShouldReturnFalse(final List<String> list) {
	for (int index = 0; index < 20; index++) {
	    list.add("index" + ThreadLocalRandom.current().nextInt());
	}
	assertFalse(list.contains(null));
    }

}
