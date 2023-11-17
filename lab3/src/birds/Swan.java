package birds;

public class Swan extends Bird {
    public void glide(){
        System.out.println("I'm graceful");
    }

    public static void main(String[] args) {
        Bird duck = new Duck();
        Swan s = new Swan();
        Bird b = s;
        // Fixed by casting: RHS type = Bird, LHS type = Duck, RHS is superclass of b
        Duck d = (Duck) b;
        // Fixed by casting RHS type = Bird, LHS type = Duck, RHS is superclass of b
        Duck d1 = (Duck) duck;

        // prediction per requested in lab instructions: object type is Duck, this should print "quack quack" (though this will never be reached, see below)
        d1.quack();
        // prediction per requested in lab instructions: this line could never be reached: d is cast from b, which has type Swan and a ClassCastException
        d.quack();
    }
}
