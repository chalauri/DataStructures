package splay;

import java.lang.reflect.Array;

/**
 * 
 * @author Giga
 *
 *         04.10.2015
 */
public class Node<T extends Comparable<T>> {

	private T key;
	
	@SuppressWarnings("unchecked")
	private Node<T> child[] =(Node<T>[])Array.newInstance(Node.class, 2); ;
	
	public Node(T key) {
		this.key = key;
		child[0] = null;
		child[1] = null;
	}
	
	public Node(T key,Node<T> left, Node<T> right) {
		this.key = key;
		child[0] = left;
		child[1] = right;
	}
	
	public Node(T key,Node<T> left) {
		this.key = key;
		child[0] = left;
		child[1] = null;
	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public Node<T>[] getChild() {
		return child;
	}

	public void setChild(Node<T>[] child) {
		this.child = child;
	}

}
