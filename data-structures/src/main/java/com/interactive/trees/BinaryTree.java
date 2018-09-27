package com.interactive.trees;

import java.lang.reflect.Field;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.egtinteractive.lists.List;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    public static class Node<T> {

	final T value;

	private Node<T> leftChild;
	private Node<T> rightChild;
	private Node<T> parent;

	public Node(final T value) {
	    this.value = value;
	}

	public String toString() {
	    return this.value.toString();
	}

	@Override
	public int hashCode() {
	    return this.value.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
	    @SuppressWarnings("unchecked")
	    final Node<T> other = (Node<T>) obj;
	    return this.compareTo(other) == 0;
	}

	public int compareTo(Node<T> other) {
	    if ((int) this.value > (int) other.value) {
		return 1;
	    } else if ((int) this.value < (int) other.value) {
		return -1;
	    } else {
		return 0;
	    }
	}
    }

    private static boolean treeIsChanged;
    private int size;

    private Node<T> root;

    public BinaryTree() {
    }

    @Override
    public boolean remove(final T element) {
	Node<T> focusNode = root;
	Node<T> parent = root;
	Node<T> newNode = new Node<T>(element);

	boolean isItALeftChild = true;

	while (focusNode.value != newNode.value) {
	    parent = focusNode;
	    if (focusNode.compareTo(newNode) == 1) {
		isItALeftChild = true;
		focusNode = focusNode.leftChild;
	    } else {
		isItALeftChild = false;
		focusNode = focusNode.rightChild;
	    }
	    if (focusNode == null)
		return false;
	}

	if (focusNode.leftChild == null && focusNode.rightChild == null) {
	    if (focusNode == root) {
		root = null;
	    } else if (isItALeftChild) {
		parent.leftChild = null;
	    } else {
		parent.rightChild = null;
	    }
	}

	else if (focusNode.rightChild == null) {
	    if (focusNode == root) {
		root = focusNode.leftChild;
	    } else if (isItALeftChild) {
		parent.leftChild = focusNode.leftChild;
	    } else {
		parent.rightChild = focusNode.leftChild;
	    }
	}

	else if (focusNode.leftChild == null) {
	    if (focusNode == root) {
		root = focusNode.rightChild;
	    } else if (isItALeftChild) {
		parent.leftChild = focusNode.rightChild;
	    } else {
		parent.rightChild = focusNode.rightChild;
	    }
	} else {
	    final Node<T> replacement = getReplacementNode(focusNode);
	    if (focusNode == root) {
		root = replacement;
	    } else if (isItALeftChild) {
		parent.leftChild = replacement;
	    } else {
		parent.rightChild = replacement;
	    }
	    replacement.leftChild = focusNode.leftChild;
	}
	treeIsChanged = true;
	this.size--;
	return true;
    }

    public Node<T> getReplacementNode(final Node<T> replacedNode) {

	Node<T> replacementParent = replacedNode;
	Node<T> replacement = replacedNode;
	Node<T> focusNode = replacedNode.rightChild;

	while (focusNode != null) {
	    replacementParent = replacement;
	    replacement = focusNode;
	    focusNode = focusNode.leftChild;
	}

	if (replacement != replacedNode.rightChild) {
	    replacementParent.leftChild = replacement.rightChild;
	    replacement.rightChild = replacedNode.rightChild;
	}
	return replacement;
    }

    @Override
    public T pollFirst() {
	if (this.root == null) {
	    return null;
	}
	Node<T> focusNode = this.root;
	while (focusNode.leftChild != null) {
	    focusNode = focusNode.leftChild;
	}
	this.remove(focusNode.value);
	return focusNode.value;
    }

    @Override
    public T pollLast() {
	if (this.root == null) {
	    return null;
	}
	Node<T> focusNode = this.root;
	while (focusNode.rightChild != null) {
	    focusNode = focusNode.rightChild;
	}
	this.remove(focusNode.value);
	return focusNode.value;
    }

    @Override
    public int size() {
	return this.size;
    }

    @Override
    public void clear() {
	this.size = 0;

    }

    public void inOrderTraverseTree(final Node<Integer> focusNode, final List<Integer> list) {
	if (focusNode != null) {
	    inOrderTraverseTree(focusNode.leftChild, list);
	    list.add(focusNode.value);
	    inOrderTraverseTree(focusNode.rightChild, list);

	}
    }

    public static Object getInstanceField(final Tree<Integer> newTree, final String string) throws Throwable {
	final Field field = newTree.getClass().getDeclaredField(string);
	return field.get(newTree);
    }

    public T getLowestElement(final BinaryTree<T> tree) {
	Node<T> focusNode = new Node<T>(tree.root.value);
	while (focusNode.leftChild != null) {
	    focusNode = focusNode.leftChild;
	}
	return focusNode.value;
    }

    public T getBiggestElement(final BinaryTree<T> tree) {
	Node<T> focusNode = new Node<T>(tree.root.value);
	while (focusNode.rightChild != null) {
	    focusNode = focusNode.rightChild;
	}
	return focusNode.value;
    }

    public boolean exists(final T element) {
	if (root == null) {
	    return false;
	}
	final Node<T> newNode = new Node<T>(element);
	Node<T> focusNode = this.root;

	if (focusNode.leftChild == null && focusNode.rightChild == null) {
	    return false;
	}

	while (!(focusNode.leftChild == null && focusNode.rightChild == null)) {
	    if (focusNode != null)
		if (newNode.value.compareTo(focusNode.value) == 0) {
		    return true;
		}

	    if (newNode.value.compareTo(focusNode.value) < 0) {
		focusNode = focusNode.leftChild;
		if (focusNode == null) {
		    return false;
		}
		if (newNode.value.compareTo(focusNode.value) == 0) {
		    return true;
		}
	    }
	    if (focusNode != null)
		if (newNode.value.compareTo(focusNode.value) > 0) {
		    focusNode = focusNode.rightChild;
		    if (focusNode == null) {
			return false;
		    }
		    if (newNode.value.compareTo(focusNode.value) == 0) {
			return true;
		    }
		}
	}
	return false;
    }

    @Override
    public void add(final T element) {

	if (element == null) {
	    throw new NullPointerException();
	}
	if (exists(element)) {
	    return;
	}
	final Node<T> newNode = new Node<T>(element);
	if (root == null) {
	    root = newNode;
	    root.parent = null;
	} else {
	    Node<T> focusNode = root;
	    Node<T> parent;
	    while (true) {
		parent = focusNode;
		int compare = focusNode.compareTo(newNode);
		if (compare > 0) {
		    focusNode = focusNode.leftChild;
		    if (focusNode == null) {
			parent.leftChild = newNode;
			parent.leftChild.parent = parent;
			break;
		    }
		} else if (compare < 0) {
		    focusNode = focusNode.rightChild;
		    if (focusNode == null) {
			parent.rightChild = newNode;
			parent.rightChild.parent = parent;
			break;
		    }
		}
	    }
	}
	treeIsChanged = true;
	size++;
    }

    @Override
    public T lower(final T e) {
	Node<T> lowerElement = root;
	Node<T> focusNode = root;
	Node<T> target = new Node<T>(e);
	if (this.size == 0) {
	    return null;
	} else if (this.size == 1) {
	    if (target.compareTo(focusNode) >= 0) {
		return null;
	    } else {
		return target.value;
	    }
	} else {
	    int index = this.size;
	    while (!(focusNode.rightChild == null && focusNode.leftChild == null) || (index < 0)) {
		index--;
		int compare = focusNode.compareTo(target);
		if (compare >= 0) {
		    if (focusNode.leftChild != null) {
			focusNode = focusNode.leftChild;
			compare = focusNode.compareTo(target);
		    }
		} else {
		    if (focusNode.rightChild != null) {
			if (focusNode.rightChild.compareTo(target) >= 0) {
			    if (focusNode.rightChild.leftChild != null) {
				focusNode = focusNode.rightChild.leftChild;
			    } else {
				return focusNode.value;
			    }
			} else {
			    focusNode = focusNode.rightChild;
			}
		    } else {
			return focusNode.value;
		    }
		}
		if (lowerElement.compareTo(target) >= 0) {
		    lowerElement = focusNode;
		}
		if (target.compareTo(focusNode) > 0 && focusNode.compareTo(lowerElement) > 0) {
		    lowerElement = focusNode;
		}
	    }
	}
	return lowerElement.value;
    }

    @Override
    public T higher(final T e) {
	Node<T> higherElement = root;
	Node<T> focusNode = root;
	Node<T> target = new Node<T>(e);
	if (this.size == 0) {
	    return null;
	} else if (this.size == 1) {
	    if (target.compareTo(focusNode) >= 0) {
		return null;
	    } else {
		return target.value;
	    }
	} else {
	    int index = this.size;
	    while ((index > 0) && !(focusNode.leftChild == null && focusNode.rightChild == null)) {
		index--;
		int compare = focusNode.compareTo(target);
		if (compare <= 0) {
		    if (focusNode.rightChild != null) {
			focusNode = focusNode.rightChild;
		    } else {
			return null;
		    }
		} else {
		    if (focusNode.leftChild != null) {
			if (focusNode.leftChild.compareTo(target) > 0) {
			    focusNode = focusNode.leftChild;
			} else if (focusNode.leftChild.rightChild != null) {
			    if (focusNode.leftChild.rightChild.compareTo(target) > 0) {
				focusNode = focusNode.leftChild.rightChild;
			    }
			}
		    }
		}
		if (higherElement.compareTo(target) <= 0) {
		    higherElement = focusNode;
		} else {
		    if (higherElement.compareTo(focusNode) > 0) {

			higherElement = focusNode;
		    }
		}
	    }
	}
	if (Objects.equals(higherElement.value, e)) {
	    return null;
	}
	return higherElement.value;
    }

    private class TreeIterator implements Iterator<T> {

	private Node<T> next;
	private boolean youCanRemove;
	private Node<T> last = null;

	public TreeIterator(final Node<T> root) {
	    next = root;
	    if (next == null) {
		return;
	    }
	    while (next.leftChild != null) {
		next = next.leftChild;
	    }
	}

	public void remove() {
	    if (treeIsChanged) {
		throw new ConcurrentModificationException("\nThe iterator is unusable after you add/remove element!");
	    }
	    if (!youCanRemove) {
		throw new IllegalStateException("You must call next() method first!");
	    }
	    youCanRemove = false;
	    size--;
	    next = last;

	}

	public boolean hasNext() {
	    return next != null;
	}

	public final T next() {
	    if (treeIsChanged) {
		throw new ConcurrentModificationException("\nThe iterator is unusable after you add/remove element!");
	    }
	    if (!hasNext()) {
		throw new NoSuchElementException("There is no more elements!");
	    }
	    youCanRemove = true;
	    Node<T> r = next;
	    T value = r.value;

	    if (next.rightChild != null) {
		last = next;
		next = next.rightChild;
		while (next.leftChild != null) {
		    next = next.leftChild;
		}
		return value;
	    }

	    while (true) {
		if (next.parent == null) {
		    next = null;
		    return value;
		}
		if (next.parent.leftChild == next) {
		    next = next.parent;
		    return value;
		}
		next = next.parent;
	    }
	}

    }

    @Override
    public final Iterator<T> iterator() {
	treeIsChanged = false;
	return new TreeIterator(this.root);
    }
}
