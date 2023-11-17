package transport;

public class Vehicle {
    // Distance from start. Range is
    // -1000 to +1000. If Rover travels
    // beyond this range, it falls off
    // a cliff.
    private double xPosition;
    private double yPosition;
    private int nWheels;

    public Vehicle(int nWheels) {
        this.nWheels = nWheels;
        System.out.println("Vehicle constructor");
    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public void setPosition(double xPos, double yPos) {
        this.xPosition = xPos;
        this.yPosition = yPos;
    }

    public void changePositionBy(double xDelta, double yDelta) {
        this.xPosition += xDelta;
        this.yPosition += yDelta;
    }

    @Override
    public String toString() {
        return String.format("Vehicle{x=%f, y=%f, nWheels=%d}",
                xPosition, yPosition, nWheels);
    }

    public static void main(String[] args) {
        // No longer valid after step 1, since default constructor is not implicitly generated if any user-defined constructors exist
//        transport.Vehicle v = new transport.Vehicle();
    }
}
