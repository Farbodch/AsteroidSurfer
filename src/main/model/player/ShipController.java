package src.main.model.player;

//an abstract-high level representation of a player's generic ship controller, including min and max values &
//controller navigation commands
public class ShipController {
    private int controllerVal;
    private int myMax;
    private int myMin;

    //Effect: creates a shipController instance, with min and max navigation bound values
    //Modifies: this
    public ShipController(int maxBound, int minBound) {
        this.controllerVal = 0;
        this.myMax = maxBound;
        this.myMin = minBound;
    }


    //Effect: if input is a string "dec", then decreases the controller value
    //        and if input is a string "inc", then increases controller value
    //        throws an out of bounds exception if navigation value exceeds the set controller bounds
    //        causing the controllerVal to be set to that respective boundary value, and display
    //        an appropriate exception message, passed into the function as appropriate by the type of controller
    //Modifies: this
    public void navigate(String navDirective, String outOfBoundMaxMsg, String outOfBoundMinMsg) throws OutOfBoundsException {
        if (this.controllerVal > this.myMax) {
            this.controllerVal = this.myMax;
            throw new OutOfBoundsException(outOfBoundMaxMsg);
        }

        if (this.controllerVal < this.myMin) {
            this.controllerVal = this.myMin;
            throw new OutOfBoundsException(outOfBoundMinMsg);
        }

        switch (navDirective) {
            case ("dec"):
                if(this.controllerVal != 0) {
                    this.controllerVal--;
                }
                break;
            case ("inc"):
                this.controllerVal++;
                break;
        }
    }

    public int getControllerVal() {
        return this.controllerVal;
    }
}
