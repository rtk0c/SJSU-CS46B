package transport;

public class Vehicle {
    private int nWheels;

    public Vehicle(int nWheels) {
        this.nWheels = nWheels;
        System.out.println("transport.Vehicle constructor");
    }

    public static void main(String[] args) {
        // No longer valid after step 1, since default constructor is not implicitly generated if any user-defined constructors exist
//        transport.Vehicle v = new transport.Vehicle();
    }
}
