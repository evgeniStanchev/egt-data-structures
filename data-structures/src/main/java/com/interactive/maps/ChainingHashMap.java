package com.interactive.maps;

import java.util.List;
import java.util.Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ChainingHashMap<K, V> implements Map<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int ZERO = 0;
    private List<Entry<K, V>>[] map;
    private float loadFactor;
    private int threshold;
    private int size;

    public final static class Entry<K, V> {

	private final K key;
	private final V value;

	private Entry(final K key, final V value) {
	    this.key = key;
	    this.value = value;
	}

	public K getKey() {
	    return this.key;
	}

	public V getValue() {
	    return this.value;
	}

	@Override
	public String toString() {
	    return String.format("[%s, %s]", key, value);
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    if (!Objects.equals(key, null)) {
		result = prime * result + key.hashCode();
	    }
	    if (!Objects.equals(value, null)) {
		result = prime * result + ((value == null) ? 0 : value.hashCode());
	    }
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    Entry<?, ?> other = (Entry<?, ?>) obj;
	    if (key == null) {
		if (other.key != null)
		    return false;
	    } else if (!key.equals(other.key))
		return false;
	    if (value == null) {
		if (other.value != null)
		    return false;
	    } else if (!value.equals(other.value))
		return false;
	    return true;
	}
    }

    public ChainingHashMap() {
	this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    private ChainingHashMap(final int capacity, final float loadFactor) {
	this.map = new ArrayList[capacity];
	this.loadFactor = loadFactor;
	this.threshold = (int) (this.map.length * this.loadFactor);
    }

    @Override
    public void clear() {
	Arrays.fill(this.map, null);
	this.size = ZERO;
    }

    @Override
    public V get(final K key) {
	final List<Entry<K, V>> chain = findChain(key, false);
	if (chain != null) {
	    for (Entry<K, V> dictionaryEntry : chain) {
		if (Objects.equals(dictionaryEntry.key, key)) {
		    return dictionaryEntry.getValue();
		}
	    }
	}
	return null;
    }

    private List<Entry<K, V>> findChain(final K key, final boolean createIfMissing) {
	int index = ZERO;
	if (key != null) {
	    index = key.hashCode();
	}
	if (index < ZERO) {
	    index *= -1;
	}

	index %= this.map.length;
	if (map[index] == null && createIfMissing) {
	    map[index] = new ArrayList<Entry<K, V>>();
	}
	return map[index];
    }

    @Override
    public V put(final K key, final V value) {
	if (size + 1 >= threshold) {
	    expand();
	}

	final List<Entry<K, V>> chain = findChain(key, true);
	for (int i = ZERO; i < chain.size(); i++) {
	    final Entry<K, V> entry = chain.get(i);
	    if (Objects.equals(entry.key, key)) {
		Entry<K, V> newEntry = new Entry<K, V>(key, value);
		chain.set(i, newEntry);
		return entry.getValue();
	    }
	}
	chain.add(new Entry<K, V>(key, value));
	size++;
	return null;
    }

    private void increaseThreshold(final int newCapacity) {
	this.threshold = (int) (newCapacity * this.loadFactor);
    }

    private int getNewCapacity() {
	return this.map.length * 4;
    }

    @SuppressWarnings("unchecked")
    private void expand() {
	int newCapacity = getNewCapacity();
	final List<Entry<K, V>>[] oldTable = this.map;
	this.map = new ArrayList[newCapacity];
	increaseThreshold(newCapacity);

	for (List<Entry<K, V>> oldChain : oldTable) {
	    if (oldChain != null) {
		for (Entry<K, V> entry : oldChain) {
		    final List<Entry<K, V>> chain = findChain(entry.getKey(), true);
		    chain.add(entry);
		}
	    }
	}
    }

    @Override
    public V remove(final K key) {
	if (containsKey(key)) {
	    final List<Entry<K, V>> chain = findChain(key, false);
	    if (chain != null) {
		for (int i = ZERO; i < chain.size(); i++) {
		    final Entry<K, V> entry = chain.get(i);
		    if (Objects.equals(entry.key, key)) {
			chain.remove(i);
			size--;
			return entry.getValue();
		    }
		}
	    }
	}
	return null;
    }

    @Override
    public boolean containsKey(final K key) {
	return containsTheKey(key);
    }

    private boolean containsTheKey(final K key) {
	final List<Entry<K, V>> chain = findChain(key, false);
	if (chain != null) {
	    for (int i = ZERO; i < chain.size(); i++) {
		final Entry<K, V> entry = chain.get(i);
		if (Objects.equals(entry.key, key)) {
		    return true;
		}
	    }
	}
	return false;
    }

    @Override
    public boolean containsValue(final V value) {
	for (List<Entry<K, V>> listOfEntries : map) {
	    if (listOfEntries != null) {
		for (Entry<K, V> entry : listOfEntries) {
		    if (Objects.equals(entry.getValue(), value)) {
			return true;
		    }
		}
	    }
	}
	return false;
    }

    @Override
    public int size() {
	return size;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + size();

	for (int i = 0; i < this.size(); i++) {
	    if (!Objects.equals(map[i], null)) {
		List<Entry<K, V>> list = map[i];
		if (!Objects.equals(list, null)) {
		    for (Entry<K, V> entry : list) {
			if (!Objects.equals(entry.key, null))
			    result = prime * result + entry.key.hashCode();
			if (!Objects.equals(entry.value, null))
			    result = prime * result + entry.value.hashCode();
		    }
		}
	    }
	}
	return result;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Map)) {
	    return false;
	}
	Map<?, ?> other = (ChainingHashMap<?, ?>) obj;
	if (size != other.size()) {
	    return false;
	}
	// TODO Iterator
	// Iterator<?> it = other.iterator();
	// while (it.hasNext()) {
	// if (!Objects.equals(it.next(), itr.next())) {
	// return false;
	// }
	// }
	return true;
    }

    @Override
    public Iterator<com.interactive.maps.ProbingHashMap.Entry<?, ?>> iterator() {
	// TODO Auto-generated method stub
	return null;
    }
}