package transport;

public class MarsRover extends UnmannedVehicle {
    public MarsRover() {
        System.out.println("transport.MarsRover constructor");
    }

    public static void main(String[] args) {
        // Output:
        //     transport.Vehicle constructor
        //     transport.UnmannedVehicle constructor
        //     transport.MarsRover constructor
        @SuppressWarnings("unused")
        var rover = new MarsRover();
    }
}
