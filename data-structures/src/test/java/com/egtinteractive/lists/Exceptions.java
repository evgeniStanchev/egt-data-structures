package com.egtinteractive.lists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;

public class Exceptions extends SetupTests {

    @Test(dataProvider = "lists", expectedExceptions = IndexOutOfBoundsException.class)
    public void addValueByNegativeIndex_ExpectedException(final List<Integer> list) throws IndexOutOfBoundsException {
	list.add(RANDOM_NEGAVITE_INT, RANDOM_INT);
    }

    @Test(dataProvider = "lists", expectedExceptions = IndexOutOfBoundsException.class)
    public void addValueByIndexOutOfBounds_ExpectedException(final List<Integer> list)
	    throws IndexOutOfBoundsException {
	final int size = RANDOM_POSITIVE_INT;
	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	list.add(size + 1, RANDOM_INT);
    }

    @Test(dataProvider = "lists", expectedExceptions = IndexOutOfBoundsException.class)
    public void callAnElementAfterMethodClear(final List<Integer> list) throws IndexOutOfBoundsException {
	final int size = RANDOM_POSITIVE_INT;
	for (int index = ZERO; index < size; index++) {
	    list.add(RANDOM_POSITIVE_INT++);
	}
	list.clear();
	list.get(RANDOM_POSITIVE_INT);
    }

    @Test(dataProvider = "lists", expectedExceptions = IndexOutOfBoundsException.class)
    public void getValueByNegativeIndex(final List<Integer> list) throws IndexOutOfBoundsException {
	final int negativeNum = RANDOM_NEGAVITE_INT;
	list.get(negativeNum);
    }

    @Test(dataProvider = "lists", expectedExceptions = IndexOutOfBoundsException.class)
    public void getValueByIndexOutOfBounds(final List<Integer> list) throws IndexOutOfBoundsException {
	final int size = RANDOM_POSITIVE_INT;
	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	list.get(size);
    }

    @Test(dataProvider = "lists", expectedExceptions = NoSuchElementException.class)
    public void iteratorDoesNotHasNext(final List<Integer> list) throws NoSuchElementException {
	final int size = RANDOM_POSITIVE_INT;
	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	final Iterator<Integer> iterator = list.iterator();

	for (int index = ZERO; index <= size; index++) {
	    iterator.next();
	}
    }

    @Test(dataProvider = "lists", expectedExceptions = IllegalStateException.class)
    public void iteratorRemoveWithoutNextMethod(final List<Integer> list) throws IllegalStateException {
	final int size = RANDOM_POSITIVE_INT;
	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	final Iterator<Integer> iterator = list.iterator();
	iterator.remove();
    }

    @Test(dataProvider = "lists", expectedExceptions = IllegalStateException.class)
    public void iteratorRemoveAfterRemoving(final List<Integer> list) throws IllegalStateException {
	final int size = RANDOM_POSITIVE_INT;
	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	final Iterator<Integer> it = list.iterator();
	it.next();
	it.next();
	it.next();
	it.remove();
	it.remove();
    }

    @Test(dataProvider = "lists", expectedExceptions = ConcurrentModificationException.class)
    public void callNextMethodAfterTheListIsChangedByAdding(final List<Integer> list)
	    throws ConcurrentModificationException {
	list.add(1);
	list.add(2);
	list.add(3);
	final Iterator<Integer> it = list.iterator();
	it.next();
	list.add(10);
	it.next();
    }

    @Test(dataProvider = "lists", expectedExceptions = ConcurrentModificationException.class)
    public void callNextMethodAfterTheListIsChangedByDeleting(final List<Integer> list)
	    throws ConcurrentModificationException {
	list.add(1);
	list.add(2);
	list.add(3);
	final Iterator<Integer> it = list.iterator();
	it.next();
	list.remove(2);
	it.next();
    }

    @Test(dataProvider = "lists", expectedExceptions = ConcurrentModificationException.class)
    public void callRemoveMethodAfterTheListIsChangedByDeleting(final List<Integer> list)
	    throws ConcurrentModificationException {
	list.add(1);
	list.add(2);
	list.add(3);
	final Iterator<Integer> it = list.iterator();
	it.next();
	list.remove(2);
	it.remove();
    }

    @Test(dataProvider = "lists", expectedExceptions = ConcurrentModificationException.class)
    public void callRemoveMethodAfterTheListIsChangedByAdding(final List<Integer> list)
	    throws ConcurrentModificationException {
	list.add(1);
	list.add(2);
	list.add(3);
	final Iterator<Integer> it = list.iterator();
	it.next();
	list.add(4);
	it.remove();
    }

    @Test(dataProvider = "lists", expectedExceptions = IndexOutOfBoundsException.class)
    public void removeAtIndexOutOfBounds(final List<Integer> list) throws IndexOutOfBoundsException {
	final int size = RANDOM_POSITIVE_INT;
	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	list.remove(size);
    }

    @Test(dataProvider = "lists", expectedExceptions = IndexOutOfBoundsException.class)
    public void removeAtNegativeIndex(final List<Integer> list) throws IndexOutOfBoundsException {
	list.remove(RANDOM_NEGAVITE_INT);
    }

    @Test(dataProvider = "lists", expectedExceptions = IndexOutOfBoundsException.class)
    public void overwriteByIndexOutOfBounds(final List<Integer> list) throws IndexOutOfBoundsException {

	final int size = RANDOM_POSITIVE_INT;

	for (int index = ZERO; index < size; index++) {
	    list.add(ThreadLocalRandom.current().nextInt());
	}
	list.set(size, RANDOM_INT);
    }

    @Test(dataProvider = "lists", expectedExceptions = IndexOutOfBoundsException.class)
    public void overwriteByNegativeIndex(final List<Integer> list) throws IndexOutOfBoundsException {
	list.set(RANDOM_NEGAVITE_INT, RANDOM_INT);
    }
}
