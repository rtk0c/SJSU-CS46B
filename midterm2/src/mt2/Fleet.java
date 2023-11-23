package mt2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

class Ship implements Comparable<Ship> {
	long price;
	String identity;
	double length;

	public Ship(long price, String identity, double length) {
		this.price = price;
		this.identity = identity;
		this.length = length;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identity, length, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		Ship other = (Ship) obj;
		return Objects.equals(identity, other.identity) && length == other.length && price == other.price;
	}

	@Override
	public int compareTo(Ship o) {
		int v;
		if ((v = Long.compare(this.price, o.price)) != 0)
			return v;
		if ((v = this.identity.compareTo(o.identity)) != 0)
			return v;
		return Double.compare(this.length, o.length);
	}

	@Override
	public String toString() {
		String s = price + identity + length;
		return s;
	}
}

public class Fleet extends TreeSet<Ship> {

	public ArrayList<Ship> toSortedList() {
		var res = new ArrayList<Ship>(this.size());
		for (var elm : this)
			res.add(elm);
		return res;
	}

	public static void test(Fleet f) {
		String A = "A";
		String B = "B";
		String C = "C";
		String D = "D";
		String E = "E";
		long[] prices = { 1, 2, };
		String[] names = { B, A };
		double[] length = { 7, 8, };

		for (String id : names) {
			for (long p : prices) {
				for (double len : length) {
					Ship x = new Ship(p, id, len);
					f.add(x);
				}
			}
		}
		System.out.println(f);
	}

	public static void main(String[] args) {
		Fleet f = new Fleet();
		for (int i = 0; i < 5; ++i) {
			test(f);
		}
	}
}
