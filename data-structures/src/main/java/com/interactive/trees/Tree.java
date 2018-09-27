package com.interactive.trees;

import java.util.Iterator;

public interface Tree<T extends Comparable<T>> {

    /**
     * Adds the specified element to the tree;
     * 
     * @param element
     *            - the specified element;
     */
    void add(T element);

    /**
     * Removes the specified element from this tree if it is present;
     * 
     * @param element
     *            - the specified element;
     * @return true if the operation removed an element or false if it doesn't;
     */
    boolean remove(T element);

    /**
     * Returns the greatest element in this set strictly less than the given
     * element, or null if there is no such element;
     * 
     * @param e
     *            - the element;
     * @return - the greatest element in this set strictly less than the given
     *         or null;
     */
    T lower(T e);

    /**
     * Returns the least element in this set strictly greater than the given
     * element, or null if there is no such element;
     * 
     * @param e
     *            - the element;
     * @return - the least element in this set strictly greater than the given
     *         or null;
     */
    T higher(T e);

    /**
     * Retrieves and removes the first (lowest) element or returns null if this
     * tree is empty;
     * 
     * @return the lowest element or null if this tree is empty
     */
    T pollFirst();

    /**
     * Retrieves and removes the last (highest) element or returns null if this
     * tree is empty;
     * 
     * @return the highest element or null if this tree is empty
     */
    T pollLast();

    /**
     * Returns the number of the elements in this tree;
     * 
     * @return the number of the elements in this tree
     */
    int size();

    /**
     * Removes all of the elements from this tree;
     */
    void clear();

    /**
     * Returns an iterator over the elements;
     * 
     * @return an iterator over the elements;
     */
    Iterator<T> iterator();

    /**
     * Indicates whether some other object is "equal to" this one;
     * 
     * @param otherObject
     * @return true if the two objects are equal or false if they are not
     */
    boolean equals(Object otherObject);

    /**
     * 
     * @return a hash code value for the object
     */
    int hashCode();
}
