package cast;

public class Primitives {
    public static void dumpMaxValues() {
        System.out.println("byte max value: " + Byte.MAX_VALUE);
        System.out.println("short max value: " + Short.MAX_VALUE);
        System.out.println("int max value: " + Integer.MAX_VALUE);
        System.out.println("long max value: " + Long.MAX_VALUE);
        System.out.println("float max value: " + Float.MAX_VALUE);
        System.out.println("double max value: " + Double.MAX_VALUE);
    }

    public static void main(String[] args) {
        dumpMaxValues();

        {
            // int = long;
            long l = Long.MAX_VALUE;
            int i = (int) l;
            System.out.println("long to int: " + l + " => " + i);
        }
        {
            // int = long;
            long l = Long.MAX_VALUE - 5;
            int i = (int) l;
            System.out.println("long to int: " + l + " => " + i);
        }
        {
            // long = int;
            int i = Integer.MAX_VALUE;
            long l = i; // implicit cast
            System.out.println("int to long: " + i + " => " + l);
        }
        {
            // double = byte;
            byte b = 100;
            double d = b;
            System.out.println("double = byte: " + b + " => " + d);
        }
        {
            // byte = double;
            double d = 45.67;
            byte b = (byte) d;
            System.out.println("byte = double: " + d + " => " + b);
        }
        {
            // long = float;
            float f = 12345.6789f;
            long l = (long) f;
            System.out.println("long = float: " + f + " => " + l);
        }
        {
            // long = float;
            float f = Float.MAX_VALUE;
            long l = (long) f;
            System.out.println("long = float: " + f + " => " + l);
        }
        {
            // float = long;
            long l = Long.MAX_VALUE;
            float f = l;
            System.out.println("float = long: " + f + " => " + l);
        }

    }
}
