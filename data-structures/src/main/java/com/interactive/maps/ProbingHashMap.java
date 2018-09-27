package com.interactive.maps;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class ProbingHashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int ZERO = 0;

    private boolean mapIsChanged;

    private Entry<K, V>[] map;
    private float loadFactor;
    private int threshold;
    public int size;

    public static class Entry<K, V> {

	private final K key;
	private V value;

	public Entry(final K key, final V value) {
	    this.key = key;
	    this.value = value;
	}

	@Override
	public String toString() {
	    return String.format("[%s, %s]", key, value);
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((key == null) ? 0 : key.hashCode());
	    result = prime * result + ((value == null) ? 0 : value.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (!(obj instanceof Entry))
		return false;
	    Entry<?, ?> other = (Entry<?, ?>) obj;
	    if (!Objects.equals(key, other.key)) {
		return false;
	    }
	    if (!Objects.equals(value, other.value)) {
		return false;
	    }
	    return true;
	}
    }

    public ProbingHashMap() {
	this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    private ProbingHashMap(final int capacity, final float loadFactor) {
	this.loadFactor = loadFactor;
	this.threshold = (int) (capacity * this.loadFactor);
	this.map = new Entry[this.threshold];
    }

    @Override
    public void clear() {
	Arrays.fill(this.map, null);
	size = ZERO;
    }

    @Override
    public V get(final K key) {
	if (size == ZERO) {
	    return null;
	}
	int hash = ZERO;
	if (key != null) {
	    hash = key.hashCode();
	    hash %= this.threshold;
	    if (hash < 0) {
		hash *= -1;
	    }
	}

	for (int index = hash; index < map.length; index++) {
	    if (map[index] != null) {
		if (Objects.equals(map[index].key, key)) {
		    return map[index].value;
		}
	    }
	}
	return null;
    }

    @Override
    public V put(final K key, final V value) {

	final Entry<K, V> newEntry = new Entry<K, V>(key, value);
	int hash = ZERO;
	if (key != null) {
	    hash = newEntry.key.hashCode();
	    hash %= this.threshold;
	}
	if (hash < 0) {
	    hash *= -1;
	}
	while (size < hash) {
	    size++;
	}

	for (int currentIndex = hash; currentIndex <= map.length; currentIndex++) {
	    if (currentIndex + 1 == map.length) {
		expand();
	    }
	    if (this.map[currentIndex] == null) {
		this.map[currentIndex] = newEntry;
		size++;
		mapIsChanged = true;
		return null;
	    } else {
		if (Objects.equals(this.map[currentIndex].key, newEntry.key)) {
		    final V oldValue = get(this.map[currentIndex].key);
		    this.map[currentIndex] = newEntry;
		    mapIsChanged = true;
		    return oldValue;
		}
	    }
	}
	return null;
    }

    @Override
    public V remove(final K key) {
	if (containsKey(key)) {
	    for (int index = ZERO; index < map.length; index++) {
		if (map[index] != null) {
		    if (Objects.equals(map[index].key, key)) {
			V value = map[index].value;
			for (int i = index; i < map.length - 1; i++) {
			    map[i] = map[i + 1];
			}
			size--;
			mapIsChanged = true;
			return value;
		    }
		}
	    }
	}
	return null;
    }

    @SuppressWarnings("unchecked")
    private void expand() {
	final int newCapacity = this.size * 2;
	final Entry<K, V>[] oldMap = this.map;
	this.map = new Entry[newCapacity];
	increaseThreshold(newCapacity);
	for (int index = ZERO; index < oldMap.length; index++) {
	    if (oldMap[index] != null) {
		this.put(oldMap[index].key, oldMap[index].value);
	    }
	}
    }

    private void increaseThreshold(final int newCapacity) {
	this.threshold = (int) (newCapacity * this.loadFactor);
    }

    @Override
    public boolean containsKey(final K key) {
	for (int index = ZERO; index < map.length; index++) {
	    if (map[index] != null) {
		if (Objects.equals(map[index].key, key)) {
		    return true;
		}
	    }
	}
	return false;
    }

    @Override
    public boolean containsValue(final V value) {
	for (int index = ZERO; index < map.length; index++) {
	    if (map[index] != null) {
		if (Objects.equals(map[index].value, value)) {
		    return true;
		}
	    }
	}
	return false;
    }

    @Override
    public int size() {
	int newSize = map.length;
	for (int index = 0; index < map.length; index++) {
	    if (map[index] == null) {
		newSize--;
	    }
	}

	return newSize;
    }

    public Iterator<Entry<?, ?>> iterator() {
	mapIsChanged = false;
	/**
	 * @param mapIsChanged
	 *            - if we add or remove an element after we create the
	 *            iterator and we call next() after that we will have an
	 *            exception
	 * @param size
	 *            - the size of the map when the iterator is created
	 * @param index
	 *            - the current index of the iterator
	 * @param youCanRemove
	 *            - return boolean if it is possible to use remove()
	 * @next T - the current index increase and the iterator points the next
	 *       element of the map
	 * @hasNext - return false if there is no more next elements
	 * @remove - remove the element at index @param index
	 */
	final Iterator<Entry<?, ?>> it = new Iterator<Entry<?, ?>>() {

	    private int index = -1;
	    private boolean youCanRemove = false;
	    private int currentElement = 0;

	    @Override
	    public boolean hasNext() {
		return (index < size - 1) && (currentElement < size());
	    }

	    @Override
	    public Entry<K, V> next() {
		if (mapIsChanged) {
		    throw new ConcurrentModificationException(
			    "\nThe iterator is unusable after you add/remove an element!");
		}
		if (hasNext()) {
		    index++;
		    youCanRemove = true;
		    if ((Entry<K, V>) map[index] != null) {
			currentElement++;
			return (Entry<K, V>) map[index];

		    } else {
			return next();
		    }
		} else {
		    throw new NoSuchElementException("There is no more elements!");
		}
	    }

	    public void remove() {
		if (!youCanRemove) {
		    throw new IllegalStateException("You need to use next() method first!");
		}
		if (mapIsChanged) {
		    throw new ConcurrentModificationException(
			    "\nThe iterator is unusable after you add/remove element!");
		}
		System.arraycopy(map, index + 1, map, index, size - index + 1);
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
	for (int i = 0; i < this.size(); i++) {
	    if (!Objects.equals(map[i], null)) {
		Entry<K, V> entry = map[i];
		result = prime * result + entry.hashCode();
	    }
	}
	result = prime * result + size();
	return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;

	if (!(obj instanceof Map)) {
	    return false;
	}

	Map<?, ?> other = (ProbingHashMap<?, ?>) obj;

	if (this.size() != other.size()) {
	    return false;
	}

	final Iterator<?> it1 = ((ProbingHashMap<?, ?>) other).iterator();

	while (it1.hasNext()) {
	    final Entry<?, ?> entry = (Entry<?, ?>) it1.next();

	    if (!(this.containsKey((K) entry.key)) || !(this.containsValue((V) entry.value))) {
		return false;
	    }
	}
	return true;
    }
}