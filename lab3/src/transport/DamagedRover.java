package transport;

public class DamagedRover extends MarsRover {
    private final static int MAX_TRAVEL_METERS_BEFORE_EMPTY_BATTERY = 10000;
    private final static int METERS_FROM_START_TO_CLIFF = 1000;
    private final static int N_SIMULATIONS = 500;

    // Total meters traveled
    // back and forth.
    private double metersTraveled;
    // If true, an expensive loss.
    private boolean fell;

    private void move(double distance, boolean isForward) {
        if (isForward) {
            changePositionBy(distance, 0);
        } else {
            changePositionBy(-distance, 0);
        }
    }

    // Simulates travel under damage conditions. In each turn, travels forward or
    // backward either 1, 2, 3, or 4 meters. Continues until there's no more power
    // in the battery, or we fall off a cliff. Cliffs are at position = 1000 or
    // position = -1000.
    public void simulateStormDamageTravel() {
        // Reset instance variables here

        setPosition(0, 0);

        while (metersTraveled < MAX_TRAVEL_METERS_BEFORE_EMPTY_BATTERY) {
            // Random int: 1, 2, 3, or 4. Represents the
            // travel distance (maybe forward, maybe back) this turn
            double distanceNextTurn = (int) (1 + 4 * Math.random());
            // Random boolean for direction of travel this turn.
            boolean forwardNotBack = (Math.random() > 0.5);

            // Adjust position and metersTraveled.
            move(distanceNextTurn, forwardNotBack);
            metersTraveled += distanceNextTurn;

            // Check for falling off cliff. If Rover fell, set fell to true and
            // terminate (break out of) the loop.
            if (getXPosition() >= METERS_FROM_START_TO_CLIFF || getYPosition() <= -METERS_FROM_START_TO_CLIFF) {
                fell = true;
                break;
            }
        }
    }

    public double getMetersTraveled() {
        return metersTraveled;
    }

    // N.B. this is not standard practice, `hasFell` is better
    public boolean getFell() {
        return fell;
    }

    /**
     * @return Whether the rover fell or not.
     */
    private static boolean runSimulationOnce() {
        var rover = new DamagedRover();
        rover.simulateStormDamageTravel();
        return rover.getFell();
    }

    public static void main(String[] args) {
        // Rover running out of battery is much, much more likely than falling off
        if (runSimulationOnce()) {
            System.out.println("rover fell");
        }

        int nFalls = 0;
        for (int i = 0; i < N_SIMULATIONS; i++) {
            if (runSimulationOnce()) {
                nFalls++;
            }
        }
        System.out.printf("rover fell off the cliff %d times.\n", nFalls);
    }
}
