package com.egtinteractive.trees;

import static org.testng.Assert.assertEquals;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.Test;

import com.egtinteractive.resources.SetupTests;
import com.interactive.trees.Tree;

public class IteratorTest extends SetupTests {

    @Test(dataProvider = "trees", expectedExceptions = IllegalStateException.class)
    public void iteratorRemoveWithoutNextMethod(Tree<Integer> tree) throws IllegalStateException {
	for (int currentIndex = 0; currentIndex < 10; currentIndex++) {
	    tree.add(ThreadLocalRandom.current().nextInt());
	}

	final Iterator<Integer> iterator = tree.iterator();
	iterator.remove();
    }

    @Test(dataProvider = "trees", expectedExceptions = ConcurrentModificationException.class)
    public void callNextMethodAfterTheTreeIsChangedByAdding(final Tree<Integer> tree)
	    throws ConcurrentModificationException {
	tree.add(1);
	tree.add(2);
	tree.add(3);
	final Iterator<Integer> it = tree.iterator();
	it.next();
	tree.add(4);
	it.next();
    }

    @Test(dataProvider = "trees", expectedExceptions = ConcurrentModificationException.class)
    public void callNextMethodAfterTheTreeIsChangedByDeleting(final Tree<Integer> tree)
	    throws ConcurrentModificationException {
	tree.add(1);
	tree.add(2);
	tree.add(3);
	final Iterator<Integer> it = tree.iterator();
	it.next();
	tree.remove(3);
	it.next();
    }

    @Test(dataProvider = "trees", expectedExceptions = ConcurrentModificationException.class)
    public void callRemoveMethodAfterTheTreeIsChangedByDeleting(final Tree<Integer> tree)
	    throws ConcurrentModificationException {
	tree.add(1);
	tree.add(2);
	tree.add(3);
	final Iterator<Integer> it = tree.iterator();
	it.next();
	tree.remove(3);
	it.remove();
    }

    @Test(dataProvider = "trees", expectedExceptions = ConcurrentModificationException.class)
    public void callRemoveMethodAfterTheTreeIsChangedByAdding(final Tree<Integer> tree)
	    throws ConcurrentModificationException {
	tree.add(1);
	tree.add(2);
	tree.add(3);
	final Iterator<Integer> it = tree.iterator();
	it.next();
	tree.add(4);
	it.remove();
    }

    @Test(dataProvider = "trees")
    public void callRemoveMethodAfterTheTreeIsChangedByAddingAnExistingNumber(final Tree<Integer> tree) {
	tree.add(1);
	tree.add(2);
	tree.add(3);
	final Iterator<Integer> it = tree.iterator();
	it.next();
	tree.add(3);
	it.remove();
    }

    @Test(dataProvider = "trees")
    public void callRemoveMethodAfterTheTreeIsChangedByDeletingAnonexistingNumber(final Tree<Integer> tree) {
	tree.add(1);
	tree.add(2);
	tree.add(3);
	final Iterator<Integer> it = tree.iterator();
	it.next();
	tree.remove(4);
	it.remove();
    }

    @Test(dataProvider = "trees")
    public void callNextMethodAfterTheTreeIsChangedByDeletingAnonexistingNumber(final Tree<Integer> tree) {
	tree.add(1);
	tree.add(2);
	tree.add(3);
	final Iterator<Integer> it = tree.iterator();
	it.next();
	tree.remove(4);
	it.next();
    }

    @Test(dataProvider = "trees")
    public void callNextMethodAfterTheTreeIsChangedByAddingAnExistingNumber(final Tree<Integer> tree) {
	tree.add(1);
	tree.add(2);
	tree.add(3);
	final Iterator<Integer> it = tree.iterator();
	it.next();
	tree.add(3);
	it.next();
    }

    @Test(dataProvider = "trees", expectedExceptions = IllegalStateException.class)
    public void iteratorRemoveAfterRemoving(final Tree<Integer> tree) throws IllegalStateException {
	for (int currentIndex = 0; currentIndex < 10; currentIndex++) {
	    tree.add(ThreadLocalRandom.current().nextInt());
	}

	final Iterator<Integer> it = tree.iterator();
	it.next();
	it.remove();
	it.remove();
    }

    @Test(dataProvider = "trees")
    public void iteratorHasNextTest(final Tree<Integer> tree) {
	for (int currentIndex = 0; currentIndex < 10; currentIndex++) {
	    tree.add(ThreadLocalRandom.current().nextInt());
	}

	final Iterator<Integer> iterator = tree.iterator();

	final boolean expected = true;
	final boolean actual = iterator.hasNext();

	assertEquals(actual, expected);
    }

    @Test(dataProvider = "trees")
    public void iteratorRemoveThenTreeSizeDecrease(final Tree<Integer> tree) {

	int size = 10;
	for (int currentIndex = 0; currentIndex < size; currentIndex++) {
	    tree.add(ThreadLocalRandom.current().nextInt());
	}

	final Iterator<Integer> iterator = tree.iterator();

	iterator.next();
	iterator.remove();
	final int expected = size - 1;
	final int actual = tree.size();
	assertEquals(actual, expected);
    }

}
