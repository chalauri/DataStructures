package redBlackTrees;

import utils.Utils;

/**
 * 
 * @author G. Chalauri
 *
 * @param <T>
 * 
 *            17.11.2015
 */
public class RBTree<T extends Comparable<T>> {

	private Integer size;
	Node<T> nill;
	Node<T> root;

	/**
	 * Constructor
	 */
	public RBTree() {
		nill = new Node<>();
		nill.setColor(Utils.BLACK);
		root = nill;
		size = 0;
	}

	/**
	 * Search node in tree
	 * 
	 * @param k
	 * @return Node<T>
	 */
	public Node<T> search(T k) {

		Node<T> x = root;
		while (!x.equals(nill) && !k.equals(x.getKey())) {
			if (k.compareTo(x.getKey()) == -1) {
				x = x.getChild()[Utils.LEFT];
			} else {
				x = x.getChild()[Utils.LEFT];
			}
		}

		return x;
	}

	/**
	 * Get node with minimum key
	 * 
	 * @param x
	 * @return Node<T>
	 */
	public Node<T> treeMinimum(Node<T> x) {

		while (!x.getChild()[Utils.LEFT].equals(nill)) {
			x = x.getChild()[Utils.LEFT];
		}

		return x;
	}

	/**
	 * Rotate
	 * 
	 * @param direction
	 * @param x
	 */
	public void rotate(Integer direction, Node<T> x) {

		int oppositeDir = Math.abs(direction - 1);
		Node<T> y = x.getChild()[oppositeDir];
		x.getChild()[oppositeDir] = y.getChild()[direction];
		if (!y.getChild()[direction].equals(nill)) {
			y.getChild()[direction].setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent().equals(nill)) {
			root = y;
		} else {
			if (x.equals(x.getParent().getChild()[direction])) {
				x.getParent().getChild()[direction] = y;
			} else {
				x.getParent().getChild()[oppositeDir] = y;
			}
		}
		y.getChild()[direction] = x;
		x.setParent(y);
	}

	/**
	 * Rotate left
	 * 
	 * @param x
	 */
	public void rotateLeft(Node<T> x) {
		Node<T> y = x.getChild()[Utils.RIGHT];
		x.getChild()[Utils.RIGHT] = y.getChild()[Utils.LEFT];
		if (!y.getChild()[Utils.LEFT].equals(nill)) {
			y.getChild()[Utils.LEFT].setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent().equals(nill)) {
			root = y;
		} else {
			if (x.equals(x.getParent().getChild()[Utils.LEFT])) {
				x.getParent().getChild()[Utils.LEFT] = y;
			} else {
				x.getParent().getChild()[Utils.RIGHT] = y;
			}
		}
		y.getChild()[Utils.LEFT] = x;

		x.setParent(y);
	}

	/**
	 * Rotate right
	 * 
	 * @param x
	 */
	public void rotateRight(Node<T> x) {
		Node<T> y = x.getChild()[Utils.LEFT];
		x.getChild()[Utils.LEFT] = y.getChild()[Utils.RIGHT];
		if (!y.getChild()[Utils.RIGHT].equals(nill)) {
			y.getChild()[Utils.RIGHT].setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent().equals(nill)) {
			root = y;
		} else {
			if (x.equals(x.getParent().getChild()[Utils.LEFT])) {
				x.getParent().getChild()[Utils.LEFT] = y;
			} else {
				x.getParent().getChild()[Utils.RIGHT] = y;
			}
		}
		y.getChild()[Utils.RIGHT] = x;

		x.setParent(y);
	}

	/**
	 * Insert fix-up
	 * 
	 * @param z
	 */
	public void insertFixup(Node<T> z) {

		while (z.getParent().getColor().equals(Utils.RED)) {
			if (z.getParent().equals(
					z.getParent().getParent().getChild()[Utils.LEFT])) {
				Node<T> y = z.getParent().getParent().getChild()[Utils.RIGHT];
				if (y.getColor().equals(Utils.RED)) {
					z.getParent().setColor(Utils.BLACK);
					y.setColor(Utils.BLACK);
					z.getParent().getParent().setColor(Utils.RED);
					z = z.getParent().getParent();
					continue;
				}

				if (z.equals(z.getParent().getChild()[Utils.RIGHT])) {
					z = z.getParent();
					rotateLeft(z);
				}

				z.getParent().setColor(Utils.BLACK);
				z.getParent().getParent().setColor(Utils.RED);
				rotateRight(z.getParent().getParent());
			} else {
				Node<T> y = z.getParent().getParent().getChild()[Utils.LEFT];
				if (y.getColor().equals(Utils.RED)) {
					z.getParent().setColor(Utils.BLACK);
					y.setColor(Utils.BLACK);
					z.getParent().getParent().setColor(Utils.RED);
					z = z.getParent().getParent();
					continue;
				}

				if (z.equals(z.getParent().getChild()[Utils.LEFT])) {
					z = z.getParent();
					rotateRight(z);
				}

				z.getParent().setColor(Utils.BLACK);
				z.getParent().getParent().setColor(Utils.RED);
				rotateLeft(z.getParent().getParent());
			}
		}

		root.setColor(Utils.BLACK);
	}

	/**
	 * Insert node
	 * 
	 * @param z
	 */
	public void insert(Node<T> z) {
		insertBST(z);
		insertFixup(z);
	}

	/**
	 * Insert node in BST
	 * 
	 * @param z
	 */
	public void insertBST(Node<T> z) {
		size++;
		Node<T> x = root;
		Node<T> y = nill;

		while (!nill.equals(x)) {
			y = x;
			if (z.getKey().compareTo(x.getKey()) == -1) {
				x = x.getChild()[Utils.LEFT];
			} else {
				x = x.getChild()[Utils.RIGHT];
			}
		}

		z.setParent(y);
		if (y.equals(nill)) {
			root = z;
		} else {
			if (z.getKey().compareTo(y.getKey()) == -1) {
				y.getChild()[Utils.LEFT] = z;
			} else {
				y.getChild()[Utils.RIGHT] = z;
			}
		}

		z.getChild()[Utils.LEFT] = nill;
		z.getChild()[Utils.RIGHT] = nill;
		z.setColor(Utils.RED);
	}

	/**
	 * Delete fix-up
	 * 
	 * @param x
	 */
	public void deleteFixup(Node<T> x) {
		Node<T> w;
		while (!x.equals(root) && x.getColor().equals(Utils.BLACK)) {
			if (x.equals(x.getParent().getChild()[Utils.LEFT])) {
				w = x.getParent().getChild()[Utils.RIGHT];
				if (w.getColor().equals(Utils.RED)) {
					w.setColor(Utils.BLACK);
					x.getParent().setColor(Utils.BLACK);
					rotate(Utils.LEFT, x.getParent());
					w = x.getParent().getChild()[Utils.RIGHT];
				}

				if (w.getChild()[Utils.LEFT].getColor().equals(Utils.BLACK)
						&& w.getChild()[Utils.RIGHT].getColor().equals(
								Utils.BLACK)) {
					w.setColor(Utils.RED);
					x = x.getParent();
				} else {
					if (w.getChild()[Utils.RIGHT].getColor()
							.equals(Utils.BLACK)) {
						w.getChild()[Utils.LEFT].setColor(Utils.BLACK);
						w.setColor(Utils.RED);
						rotate(Utils.RIGHT, w);
						w = x.getParent().getChild()[Utils.RIGHT];
					}

					x.getParent().setColor(Utils.BLACK);
					w.getChild()[Utils.RIGHT].setColor(Utils.BLACK);
					rotate(Utils.LEFT, x.getParent());
					x = root;
				}
			} else {
				w = x.getParent().getChild()[Utils.LEFT];
				if (w.getColor().equals(Utils.RED)) {
					w.setColor(Utils.BLACK);
					x.getParent().setColor(Utils.RED);
					rotate(Utils.RIGHT, x.getParent());
					w = x.getParent().getChild()[Utils.RIGHT];
				}

				if (w.getChild()[Utils.LEFT].getColor().equals(Utils.BLACK)
						&& w.getChild()[Utils.RIGHT].getColor().equals(
								Utils.BLACK)) {
					w.setColor(Utils.RED);
					x = x.getParent();
				} else {
					if (w.getChild()[Utils.LEFT].getColor().equals(Utils.BLACK)) {
						w.getChild()[Utils.RIGHT].setColor(Utils.BLACK);
						w.setColor(Utils.RED);
						rotate(Utils.LEFT, w);
						w = x.getParent().getChild()[Utils.LEFT];
					}

					w.setColor(x.getParent().getColor());
					x.getParent().setColor(Utils.BLACK);
					w.getChild()[Utils.LEFT].setColor(Utils.BLACK);
					rotate(Utils.RIGHT, x.getParent());
					x = root;
				}
			}
		}

		x.setColor(Utils.BLACK);
	}

	/**
	 * Delete node from tree
	 * 
	 * @param z
	 */
	public void delete(Node<T> z) {
		Node<T> x;
		if (z.getChild()[Utils.LEFT].equals(nill)) {
			x = z.getChild()[Utils.RIGHT];
			transplant(z, x);
			if (z.getColor().equals(Utils.BLACK)) {
				deleteFixup(x);
			}

			return;
		}

		if (z.getChild()[Utils.RIGHT].equals(nill)) {
			x = z.getChild()[Utils.LEFT];
			transplant(z, x);
			if (z.getColor().equals(Utils.BLACK)) {
				deleteFixup(x);
			}

			return;
		}

		Node<T> y = treeMinimum(z.getChild()[Utils.RIGHT]);
		Character yOriginalColor = y.getColor();
		x = y.getChild()[Utils.RIGHT];
		if (y.getParent().equals(z)) {
			x.setParent(y);
		} else {
			transplant(y, x);
			y.getChild()[Utils.RIGHT] = z.getChild()[Utils.RIGHT];
			y.getChild()[Utils.RIGHT].setParent(y);
		}

		transplant(z, y);
		y.getChild()[Utils.LEFT] = z.getChild()[Utils.LEFT];
		y.getChild()[Utils.LEFT].setParent(y);
		y.setColor(z.getColor());

		if (yOriginalColor.equals(Utils.BLACK)) {
			deleteFixup(x);
		}
	}

	/**
	 * Transplant
	 * 
	 * @param u
	 * @param v
	 */
	public void transplant(Node<T> u, Node<T> v) {

		if (u.getParent().equals(nill)) {
			root = v;

			return;
		}

		if (u.equals(u.getParent().getChild()[Utils.LEFT])) {
			u.getParent().getChild()[Utils.LEFT] = v;
		} else {
			u.getParent().getChild()[Utils.RIGHT] = v;
		}

		v.setParent(u.getParent());
	}

	/**
	 * In-order walk
	 * 
	 * @param x
	 */
	void inorderWalk(Node<T> x) {
		if (!x.equals(nill)) {
			inorderWalk(x.getChild()[Utils.LEFT]);
			System.out.println(x.getKey() + "\t");
			inorderWalk(x.getChild()[Utils.RIGHT]);
		}
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Node<T> getNill() {
		return nill;
	}

	public void setNill(Node<T> nill) {
		this.nill = nill;
	}

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}

}
