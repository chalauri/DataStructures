package splay;

import utils.Utils;

/**
 * 
 * @author G. Chalauri
 *
 *         04.10.2015
 */
public class SplayMethods {

	/**
	 * Rotate
	 * 
	 * @param h
	 * @param direction
	 * @return STNode<T>
	 */
	public static <T extends Comparable<T>> STNode<T> rotate(STNode<T> h,
			int direction) {
		int oppositeDir = Math.abs(direction - 1);

		if (h == null || h.getChild()[oppositeDir] == null) {
			return h;
		}

		STNode<T> tmp = h.getChild()[oppositeDir];
		h.getChild()[oppositeDir] = tmp.getChild()[direction];
		tmp.getChild()[direction] = h;

		return tmp;
	}

	/**
	 * Splay Insert
	 * 
	 * @param h
	 * @param key
	 * @return STNode<T>
	 */
	public static <T extends Comparable<T>> STNode<T> splayInsert(STNode<T> h,
			T key) {

		if (h == null) {

			return new STNode<T>(key);
		}

		if (key.compareTo(h.getKey()) == -1) {

			if (h.getChild()[Utils.LEFT] == null) {

				return new STNode<T>(key, null, h);
			}

			if (key.compareTo(h.getChild()[Utils.LEFT].getKey()) == -1) {
				h.getChild()[Utils.LEFT].getChild()[Utils.LEFT] = splayInsert(
						h.getChild()[Utils.LEFT].getChild()[Utils.LEFT], key);
				h = rotate(h, Utils.RIGHT);
			} else {
				h.getChild()[Utils.LEFT].getChild()[Utils.RIGHT] = splayInsert(
						h.getChild()[Utils.LEFT].getChild()[Utils.RIGHT], key);
				h = rotate(h.getChild()[Utils.LEFT], Utils.LEFT);
			}
			return rotate(h, Utils.RIGHT);
		}

		if (h.getChild()[Utils.RIGHT] == null) {

			return new STNode<T>(key, h);
		}

		if (key.compareTo(h.getChild()[Utils.RIGHT].getKey()) == 1) {
			h.getChild()[Utils.RIGHT].getChild()[Utils.RIGHT] = splayInsert(
					h.getChild()[Utils.RIGHT].getChild()[Utils.RIGHT], key);
			h = rotate(h, Utils.LEFT);
		} else {
			h.getChild()[Utils.RIGHT].getChild()[Utils.LEFT] = splayInsert(
					h.getChild()[Utils.RIGHT].getChild()[Utils.LEFT], key);
			h = rotate(h.getChild()[Utils.RIGHT], Utils.RIGHT);
		}

		return rotate(h, Utils.LEFT);
	}
}
