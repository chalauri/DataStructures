package splay;

import java.lang.reflect.Array;

/**
 * 
 * @author G. Chalauri
 *
 *         04.10.2015
 */
public class STNode<T extends Comparable<T>> {

	private T key;
	
	@SuppressWarnings("unchecked")
	private STNode<T> child[] =(STNode<T>[])Array.newInstance(STNode.class, 2); ;
	
	public STNode(T key) {
		this.key = key;
		child[0] = null;
		child[1] = null;
	}
	
	public STNode(T key,STNode<T> left, STNode<T> right) {
		this.key = key;
		child[0] = left;
		child[1] = right;
	}
	
	public STNode(T key,STNode<T> left) {
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

	public STNode<T>[] getChild() {
		return child;
	}

	public void setChild(STNode<T>[] child) {
		this.child = child;
	}

}
