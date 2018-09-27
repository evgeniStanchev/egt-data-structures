package com.egtinteractive.lists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class LinkedList<T> implements List<T> {

    private final static int ZERO = 0;
    private static boolean listIsChanged = false;

    private class Node {
	T element;
	Node next, prev;

	Node(final T element, final Node prevNode) {
	    this.element = element;
	    prevNode.next = this;
	    this.prev = prevNode;
	}

	Node(final T element) {
	    this.element = element;
	}
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
    }

    private final void checkForExceptions(final int index) {
	if (index > size || index < 0) {
	    throw new IndexOutOfBoundsException("Invalid index: " + index + ", Size:" + size);
	}
    }

    public void checkForExceptionsWithEquals(int index) {
	if (index >= size || index < 0) {
	    throw new IndexOutOfBoundsException("Invalid index: " + index + ", Size:" + size);
	}
    }

    @Override
    public T get(final int index) {
	checkForExceptionsWithEquals(index);
	Node currentNode = this.head;
	for (int i = ZERO; i < index; i++, currentNode = currentNode.next) {
	}
	return currentNode.element;
    }

    @Override
    public void add(final T element) {
	if (head == null) {
	    head = new Node(element);
	    tail = head;
	} else {
	    final Node newNode = new Node(element, tail);
	    tail = newNode;
	}
	size++;
	listIsChanged = true;
    }

    @Override
    public void add(final int index, final T element) {
	checkForExceptions(index);
	if (head == null) {
	    head = new Node(element);
	    tail = head;
	    size++;
	    return;
	}
	if (index == size) {
	    Node newNode = new Node(element);
	    newNode.prev = tail;
	    tail.next = newNode;
	    tail = newNode;
	    size++;
	    return;
	}
	size++;
	final Node newNode = new Node(null);
	newNode.prev = tail;
	tail.next = newNode;
	tail = newNode;
	Node currentNode = this.tail;
	for (int i = this.size(); i > index; i--) {
	    if (i > 1) {
		currentNode.element = currentNode.prev.element;
		currentNode = currentNode.prev;
	    }
	}
	this.set(index, element);
    }

    @Override
    public void set(final int index, final T element) {
	checkForExceptionsWithEquals(index);
	Node currentNode = head;
	for (int currentIndex = 0; currentIndex < this.size(); currentIndex++, currentNode = currentNode.next) {
	    if (Objects.equals(currentIndex, index)) {
		currentNode.element = element;
	    }
	}
    }

    @Override
    public boolean remove(final T element) {
	final int index = indexOf(element);
	if (index != -1) {
	    remove(index);
	    return true;
	}
	return false;
    }

    @Override
    public boolean remove(final int index) {
	checkForExceptionsWithEquals(index);
	listIsChanged = true;
	Node currentNode = head;
	Node prevNode = null;
	for (int currentIndex = ZERO; currentIndex < index; currentIndex++, prevNode = currentNode, currentNode = currentNode.next) {
	}
	if (size != ZERO) {
	    size--;
	}
	if (size == ZERO) {
	    head = null;
	    tail = null;
	} else if (prevNode == null) {
	    head = currentNode.next;
	} else if (currentNode == this.tail) {
	    tail = prevNode;
	} else {
	    prevNode.next = currentNode.next;
	}
	return true;
    }

    @Override
    public boolean contains(final T o) {
	int index = indexOf(o);
	if (index != -1) {
	    return true;
	} else {
	    return false;
	}
    }

    @Override
    public int indexOf(final T element) {
	Node currentNode = head;
	for (int index = 0; index < this.size(); index++, currentNode = currentNode.next) {
	    if (Objects.equals(currentNode.element, element)) {
		return index;
	    }
	}
	return -1;
    }

    @Override
    public int size() {
	return size;
    }

    @Override
    public void clear() {
	this.head = null;
	this.tail = null;
	size = ZERO;
    }

    @Override
    public Iterator<T> iterator() {
	listIsChanged = false;
	/**
	 * @param size
	 *            - the size of the list when the iterator is created
	 * @param currentNode
	 *            - the current element
	 * @param youCanRemove
	 *            - return boolean if it is possible to use remove()
	 * @next T - the current index increase and the iterator points the next
	 *       element of the list
	 * @hasNext - return false if there is no more next elements
	 * @remove - remove the element at index @param index
	 */
	Iterator<T> it = new Iterator<T>() {
	    private Node currentNode;
	    private boolean youCanRemove = false;

	    @Override
	    public boolean hasNext() {
		if (head == null) {
		    return false;
		}
		if (currentNode == null)
		    return true;
		return !(currentNode.equals(tail));
	    }

	    @Override
	    public T next() {
		if (listIsChanged) {
		    throw new ConcurrentModificationException(
			    "\nThe iterator is unusable after you add/remove element! You should create a new one!");
		}
		if (hasNext()) {
		    youCanRemove = true;
		    if (currentNode == null) {
			currentNode = head;
		    } else {
			currentNode = currentNode.next;
		    }
		    return currentNode.element;
		} else {
		    throw new NoSuchElementException("There is no more next elements!");
		}
	    }

	    public void remove() {
		if (listIsChanged) {
		    throw new ConcurrentModificationException(
			    "\nThe iterator is unusable after you add/remove element! You should create a new one!");
		}
		if (!youCanRemove) {
		    throw new IllegalStateException("You need to use next() method first!");
		}
		if (currentNode.equals(head)) {
		    head = currentNode.next;
		} else if (currentNode.equals(tail)) {
		    tail = currentNode.prev;
		} else {
		    currentNode.prev.next = currentNode.next;
		}
		youCanRemove = false;
		size--;
	    }
	};
	return it;
    }

    @Override
    public final int hashCode() {
	final int prime = 31;
	int result = 1;

	for (int index = 0; index < size; index++) {
	    if (!Objects.equals(this.get(index), null))
		result = prime * result + this.get(index).hashCode();
	}
	result = prime * result + size;
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

	final List<?> other = (LinkedList<?>) obj;

	if (this.size() != other.size()) {
	    return false;
	}

	Iterator<?> itr = other.iterator();

	Iterator<?> itr2 = this.iterator();

	for (int index = 0; index < this.size(); index++) {
	    if (!(Objects.equals(itr.next(), itr2.next()))) {
		return false;
	    }
	}
	return true;
    }
}
