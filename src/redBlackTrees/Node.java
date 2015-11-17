package redBlackTrees;

import java.lang.reflect.Array;

/**
 * 
 * @author G. Chalauri
 *
 * @param <T>
 * 
 *            17.11.2015
 */
public class Node<T extends Comparable<T>>  {

	private T key;
	Node<T> parent;
	@SuppressWarnings("unchecked")
	private Node<T> child[] = (Node<T>[]) Array.newInstance(Node.class, 2);
	private Character color;

	public Node() {
		this.key = null;
	}

	public Node(T key) {
		this.key = key;
	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public Node<T>[] getChild() {
		return child;
	}

	public void setChild(Node<T>[] child) {
		this.child = child;
	}

	public Character getColor() {
		return color;
	}

	public void setColor(Character color) {
		this.color = color;
	}

}
