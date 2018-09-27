package com.egtinteractive.lists;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private static final int ZERO = 0;

    private T[] arr;
    private int size;
    private static final int INITIAL_CAPACITY = 20;
    private static boolean listIsChanged;

    @SuppressWarnings("unchecked")
    public ArrayList() {
	arr = (T[]) new Object[INITIAL_CAPACITY];
    }

    private final void checkForExceptions(int index) {
	if (index >= size || index < ZERO) {
	    throw new IndexOutOfBoundsException("Invalid index: " + index + ", Size:" + size);
	}
    }

    @Override
    public T get(final int index) {
	checkForExceptions(index);
	return arr[index];
    }

    @Override
    public void add(final T element) {
	add(size, element);
    }

    @Override
    public void add(final int index, final T element) {
	if (size + 1 == arr.length) {
	    ensureCapacity();
	}
	if (index == size) {
	    arr[size++] = element;
	    listIsChanged = true;
	    return;
	}
	checkForExceptions(index);
	size++;
	listIsChanged = true;
	for (int i = this.size() - 1; i >= index; i--) {
	    if (i != ZERO) {
		arr[i] = arr[i - 1];
	    }
	}
	arr[index] = element;
    }

    private void ensureCapacity() {
	final int newIncreasedCapacity = arr.length * 2;
	this.arr = Arrays.copyOf(arr, newIncreasedCapacity);
    }

    @Override
    public void set(int index, final T element) {
	checkForExceptions(index);
	arr[index] = element;
    }

    @Override
    public boolean remove(final T element) {
	int index = indexOff(element);
	if (index != -1) {
	    System.arraycopy(arr, index + 1, arr, index, size - index);
	    size--;
	    listIsChanged = true;
	    return true;
	}
	return false;
    }

    @Override
    public boolean remove(final int index) {
	checkForExceptions(index);
	System.arraycopy(arr, index + 1, arr, index, size - index + 1);
	size--;
	listIsChanged = true;
	return true;
    }

    @Override
    public boolean contains(final T o) {
	int index = indexOff(o);
	if (index != -1) {
	    return true;
	}
	return false;
    }

    private int indexOff(final T element) {
	for (int index = ZERO; index < size; index++) {
	    if (Objects.equals(arr[index], element)) {
		return index;
	    }
	}
	return -1;
    }

    @Override
    public int indexOf(final T element) {
	return indexOff(element);
    }

    @Override
    public int size() {
	return this.size;
    }

    @Override
    public void clear() {
	size = ZERO;
    }

    @Override
    public Iterator<T> iterator() {
	listIsChanged = false;
	/**
	 * @param listIsChanged
	 *            - if we add or remove an element after we create the
	 *            iterator and we call next() after that we will have an
	 *            exception
	 * @param size
	 *            - the size of the list when the iterator is created
	 * @param index
	 *            - the current index of the iterator
	 * @param youCanRemove
	 *            - return boolean if it is possible to use remove()
	 * @next T - the current index increase and the iterator points the next
	 *       element of the list
	 * @hasNext - return false if there is no more next elements
	 * @remove - remove the element at index @param index
	 */
	final Iterator<T> it = new Iterator<T>() {
	    private int index = -1;
	    private boolean youCanRemove = false;

	    @Override
	    public boolean hasNext() {
		return index < size - 1;
	    }

	    @Override
	    public T next() {
		if (listIsChanged) {
		    throw new ConcurrentModificationException(
			    "\nThe iterator is unusable after you add/remove element!");
		}
		if (hasNext()) {
		    index++;
		    youCanRemove = true;
		    return arr[index];
		} else {
		    throw new NoSuchElementException("There is no more elements!");
		}
	    }

	    public void remove() {
		if (!youCanRemove) {
		    throw new IllegalStateException("You need to use next() method first!");
		}
		if (listIsChanged) {
		    throw new ConcurrentModificationException(
			    "\nThe iterator is unusable after you add/remove element!");
		}
		System.arraycopy(arr, index + 1, arr, index, size - index + 1);
		size--;
		index--;
		youCanRemove = false;
	    }
	};
	return it;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	for (int index = 0; index < this.size; index++) {
	    if (!Objects.equals(this.get(index), null))
		result = prime * result + this.get(index).hashCode();
	}
	result = prime * result + Integer.hashCode(this.size());
	return result;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}

	if (!(obj instanceof List)) {
	    return false;
	}

	final List<?> other = (ArrayList<?>) obj;

	if (this.size() != other.size()) {
	    return false;
	}

	Iterator<?> thisIt = this.iterator();
	Iterator<?> objIt = other.iterator();

	for (int index = 0; index < this.size(); index++) {
	    if (!Objects.equals(thisIt.next(), objIt.next())) {
		return false;
	    }
	}
	return true;
    }

}