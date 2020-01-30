package src.main.model.player;

//represents the actual navigation center of the player, will all possible controllers declared
public class NavigationCenter {
    private ShipController rollControl;
    private ShipController speedControl;
    private final static int MAX_SHIP_SPEED = 10;
    private final static int MIN_SHIP_SPEED = 0;
    private final static int SHIP_LEFT_ROLL_BOUND = -5;
    private final static int SHIP_RIGHT_ROLL_BOUND = 5;

    //Effect: creates a navigation center instance and initializes the ship controllers to their appropriate bounds
    //Modifies: this
    public NavigationCenter() {
        this.rollControl = new ShipController(SHIP_RIGHT_ROLL_BOUND, SHIP_LEFT_ROLL_BOUND);
        this.speedControl = new ShipController(MAX_SHIP_SPEED, MIN_SHIP_SPEED);
    }

    //Effect: if input is dec, then decreases the ship's speed towards 0
    //        and if input is inc, then increases ships's speed, causing a rightward movement
    //        throws an out of bounds exception if roll is larger than 5 or
    //        less than -5, and sets roll value to that bound limit
    //Modifies: this
    public void setSpeedControl(String navCommand) throws OutOfBoundsException {
        this.speedControl.navigate(navCommand, "MAX SPEED!", "WARNING, SHIP STOPPED! MAY GET CRUSHED!");
    }

    //Effect: if input is dec, then decreases the navigator roll causing a leftward movement
    //        and if input is inc, then increases roll, causing a rightward movement
    //        throws an out of bounds exception if roll is larger than 5 or
    //        less than -5, and sets roll value to that bound limit
    //Modifies: this
    public void setRollControl(String navCommand) throws OutOfBoundsException {
        this.rollControl.navigate(navCommand, "R-BEEP!", "L-BEEP!");
    }

    public int getRollVal() {
        return this.speedControl.getControllerVal();
    }

    public int getSpeedVal() {
        return this.speedControl.getControllerVal();
    }
}
