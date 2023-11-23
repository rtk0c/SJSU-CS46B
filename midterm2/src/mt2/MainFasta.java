package mt2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

class InvalidFastaException extends Exception {
	public InvalidFastaException(String string) {
		// xxx fill in missing codes
	}
}

class FastaRec {
	private String defline;
	private String sequence;

	public FastaRec(String d, String s) throws InvalidFastaException {
		// xxx fill in missing codes
	}

	public String getDefline() {
		return defline;
	}

	public String getSequence() {
		return sequence;
	}

	@Override
	public String toString() {
		return defline + "\n" + sequence;
	}

	@Override
	public boolean equals(Object o) {
		// xxx fill in missing codes
		// xxx return true if both have the same defline and sequence
		return false;
	}

	@Override
	public int hashCode() {
		// xxx fill in missing codes
		return 0;
	}
}

public class MainFasta {

	public HashSet<FastaRec> findLongFastaRecords(File f) {
		// xxx fill in missing codes
		HashSet<FastaRec> result = new HashSet<>();
		return result;
	}

	public static void test(String fn) {
		System.out.println("Reading " + fn + " ...\n");
		File f = new File(fn);
		MainFasta mf = new MainFasta();
		HashSet<FastaRec> ans = mf.findLongFastaRecords(f);
		if (ans != null) {
			for (FastaRec x : ans) {
				System.out.println(x);
			}
		}
		System.out.println("---------------------------\n\n");
	}

	public static void main(String[] args) {
		test("data/fa_good.txt");
		test("data/fa_bad.txt");
		test("data/fa_ok.txt");
	}

}
