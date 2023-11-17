package tiktoks;

public class TikToker implements Comparable<TikToker> {

    private int numFollowers;
    private String creator;

    public TikToker(String creator, int numFollowers) {
        this.creator = creator;
        this.numFollowers = numFollowers;

    }

    @Override
    public boolean equals(Object o) {
        TikToker that = (TikToker) o;
        return this.compareTo(that) == 0;
    }

    @Override
    public int hashCode() {
        return creator.hashCode() + numFollowers;
    }

    /*
     * Firt comapres by number of followers
     * Then by the creator name
     *
     */
    public int compareTo(TikToker that) {
		if (this.numFollowers < that.numFollowers)
			return -1;
		if (this.numFollowers > that.numFollowers)
			return 1;
		return this.creator.compareTo(that.creator);
    }

    @Override
    public String toString() {
        return "(" + creator + "; " + numFollowers + ")";
    }

	public static void main(String[] args) {
		System.out.println("N.B. res < 0 => less than, res > 0 => greater than, res == 0 => equal");
		{
			var t1 = new TikToker("Alice", 1000);
			var t2 = new TikToker("Bob", 900);
			System.out.printf("Case: more followers: %d\n", t1.compareTo(t2));
		}
		{
			var t1 = new TikToker("Alice", 1000);
			var t2 = new TikToker("Bob", 1500);
			System.out.printf("Case: less followers: %d\n", t1.compareTo(t2));
		}
		{
			var t1 = new TikToker("Alice", 1000);
			var t2 = new TikToker("Bob", 1000);
			System.out.printf("Case: same followers, creator before: %d\n", t1.compareTo(t2));
		}
		{
			var t1 = new TikToker("David", 1000);
			var t2 = new TikToker("Bob", 1000);
			System.out.printf("Case: same followers, creator after: %d\n", t1.compareTo(t2));
		}
		{
			var t1 = new TikToker("Bob", 1000);
			var t2 = new TikToker("Bob", 1000);
			System.out.printf("Case: same followers, creator same: %d\n", t1.compareTo(t2));
		}
	}
}


