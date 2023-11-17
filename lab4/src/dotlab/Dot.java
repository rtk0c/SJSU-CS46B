package dotlab;


public class Dot {
    private static String[] LEGAL_COLOR_NAMES = {"RED", "YELLOW", "BLUE", "CYAN", "GREEN", "MAGENTA", "ORANGE", "BLACK"};

    public String colorName;
    public int x;
    public int y;
    public int radius;

    private static boolean isColorNameValid(String colorName) {
        for (String legalColorName : LEGAL_COLOR_NAMES) {
            if (legalColorName.equals(colorName)) {
                return true;
            }
        }
        return false;
    }

    public Dot(String colorName, int x, int y, int radius) {
        if (!isColorNameValid(colorName)) {
            throw new IllegalArgumentException("Color name '" + colorName + "' is illegal");
        }
        this.colorName = colorName;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public String getColorName() {
        return colorName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Dot{" +
                "colorName='" + colorName + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                '}';
    }

    public static void main(String[] args) {
        var dot1 = new Dot("RED", 0, 0, 10);
        // Throws IllegalArgumentException
//        var dot2 = new Dot("HELLO", 0, 0, 10);
        System.out.println(dot1);
    }
}
