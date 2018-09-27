package com.interactive.maps;

import java.util.Iterator;

import com.interactive.maps.ProbingHashMap.Entry;

public interface Map<K, V> {

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key;
     * 
     * @param key
     *            the specified key;
     * @return - null if this map contains no mapping for the specified key or
     *         the value to with the specified key is mapped;
     */

    V get(final K key);

    /**
     * Associates the specified value with the specified key in this map;
     * 
     * @param key
     *            - the specified key;
     * @param value
     *            - the specified value;
     * @return the value of the element with the specified key before
     *         overwriting or null if there is no such element;
     */
    V put(final K key, final V value);

    /**
     * Removes the mapping for a key from this map if it is present;
     * 
     * @param key
     *            - the specified key;
     * @return the value of the element with the specified key or null if there
     *         is no such element;
     */
    V remove(final K key);

    /**
     * Returns true if this map contains a mapping for the specified key;
     * 
     * @param key;
     * @return true if this map contains a mapping for the specified key;
     */
    boolean containsKey(final K key);

    /**
     * Returns true if this map maps one or more keys to the specified value;
     * 
     * @param value
     *            - the specified value
     * @return true if this map maps one or more keys to the specified value;
     */
    boolean containsValue(final V value);

    /**
     * Returns the number of key-value mappings in this map;
     * 
     * @return the number of key-value mapping in this map;
     */
    int size();

    /**
     * Removes all of the mappings from this map;
     */
    void clear();

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

    Iterator<Entry<?, ?>> iterator();

}
