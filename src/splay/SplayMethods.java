package splay;

/**
 * 
 * @author Giga
 *
 *         04.10.2015
 */
public class SplayMethods {

	public static <T extends Comparable<T>> STNode<T> rotate(STNode<T> h, int direction) {
		int oppositeDir = Math.abs(direction - 1);

		if (h == null || h.getChild()[oppositeDir] == null) {
			return h;
		}

		STNode<T> tmp = h.getChild()[oppositeDir];
		h.getChild()[oppositeDir] = tmp.getChild()[direction];
		tmp.getChild()[direction] = h;

		return tmp;
	}

	public static <T extends Comparable<T>> STNode<T> splayInsert(STNode<T> h, T key) {

		if (h == null) {

			return new STNode<T>(key);
		}

		if (key.compareTo(h.getKey()) == -1) {

			if (h.getChild()[0] == null) {

				return new STNode<T>(key, null, h);
			}

			if (key.compareTo(h.getChild()[0].getKey()) == -1) {
				h.getChild()[0].getChild()[0] = splayInsert(
						h.getChild()[0].getChild()[0], key);
				h = rotate(h, 1);
			} else {
				h.getChild()[0].getChild()[1] = splayInsert(
						h.getChild()[0].getChild()[1], key);
				h = rotate(h.getChild()[0], 0);
			}
			return rotate(h, 1);
		}

		if (h.getChild()[1] == null) {

			return new STNode<T>(key, h);
		}

		if (key.compareTo(h.getChild()[1].getKey()) == 1) {
			h.getChild()[1].getChild()[1] = splayInsert(
					h.getChild()[1].getChild()[1], key);
			h = rotate(h, 0);
		} else {
			h.getChild()[1].getChild()[0] = splayInsert(
					h.getChild()[1].getChild()[0], key);
			h = rotate(h.getChild()[1], 1);
		}

		return rotate(h, 0);
	}
}
