package com.egtinteractive.lists;

import java.util.Iterator;

public interface List<T> extends Iterable<T> {

    /**
     * 
     * @param index
     *            - a specified position;
     * @return - the element at the specified position;
     */
    T get(final int index);

    /**
     * Appends the specified element to the end of this list;
     * 
     * @param element
     *            - the specified element;
     */
    void add(final T element);

    /**
     * Inserts the specified element at the specified position;
     * 
     * @param index
     *            - the specified position;
     * @param element
     *            - the specified element;
     */
    void add(final int index,final T element);

    /**
     * Overwrites the element at the specified position;
     * 
     * @param index
     *            - the specified position;
     * @param element
     *            - the new element at the specified position;
     */
    void set(final int index, final T element);

    /**
     * Removes the first occurrence of the specified element;
     * 
     * @param element
     *            - the specified element for removing;
     * @return true if there is an element or false if there is not;
     */
    boolean remove(final T element);

    /**
     * Removes the element at the specified position;
     * 
     * @param index
     *            - the specified position of the element;
     * @return true if there is an element or false if there is not;
     */
    boolean remove(final int index);

    /**
     * Returns true if this list contains the specified element;
     * 
     * @param o
     *            - the specified element;
     * @return true if this list contains the specified element or false if it
     *         doesn't;
     */
    boolean contains(final T o);

    /**
     * Returns the index of the first occurrence of the specified element;
     * 
     * @param element
     *            - the specified element;
     * @return the index of the occurrence of the specified element or -1;
     */
    int indexOf(final T element);

    /**
     * Returns the number of elements in this list;
     * 
     * @return
     */
    int size();

    /**
     * Removes all elements;
     */
    void clear();

    /**
     * Returns an iterator over the elements;
     */
    Iterator<T> iterator();
    
    /**
     * Indicates whether some other object is "equal to" this one;
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
