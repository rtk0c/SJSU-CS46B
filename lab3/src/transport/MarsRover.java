package transport;

public class MarsRover extends UnmannedVehicle {
    public MarsRover() {
        System.out.println("MarsRover constructor");
    }

    public static void main(String[] args) {
        // Output:
        //     Vehicle constructor
        //     UnmannedVehicle constructor
        //     MarsRover constructor
        @SuppressWarnings("unused")
        var rover = new MarsRover();
    }
}
