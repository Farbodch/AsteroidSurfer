package main.model.player;


import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Integer> shipLocation = new ArrayList<>();
    private int score;
    private int lifePoints;
    private boolean alive;
    private NavigationCenter navigationCenter;
    private int speed;
    private ShipCommand shipCommand;

    //Effects: constructs a player object that is alive, with score 0, 3 lifePoints and
    //         a navigationCenter to control player location
    public Player(int fieldHeight, int fieldWidth){
        shipLocation.add(fieldHeight/2);
        shipLocation.add(fieldWidth/2);
        shipCommand = ShipCommand.BREAK;
        this.alive = true;
        this.score = 0;
        this.lifePoints = 3;
        this.navigationCenter = new NavigationCenter();
    }

    public int getSpeed() {
        return navigationCenter.getSpeedVal();
    }

    public List<Integer> getShipLocation() {
        return shipLocation;
    }
    //NEED THE ROLL POSIITON!!!

    //Modifies: this
    //Effects: decreases lifePoints by 1, and throws exception letting player you
    //         they died if lifePoints is 0
    public boolean decLifePoints() throws OutOfLifeException {
        if(this.lifePoints == 0) {
            this.alive = false;
            throw new OutOfLifeException("You have died!");
        }
        this.lifePoints--;
        return this.alive;
    }

    //Modifies: this
    //Effects: if the players dead, revives them,
    //         and resets lifePoints back to 3
    public void resetAlive() {
        if(!this.alive){
            this.alive = true;
        }
        this.lifePoints = 3;
    }

    //Modifies: this
    //Effect: changes ship's speed
    public void changeSpeed(char cmd) throws OutOfBoundsException {
        switch (cmd) {
            case 'U':
                navigationCenter.setSpeedControl("inc");
                break;
            case 'D':
                navigationCenter.setSpeedControl("dec");
                break;
            default:
                break;
        }
    }

    //Modifies: this
    //Effect: moves ship left and right
    public void changeRoll() throws OutOfBoundsException {
        switch (shipCommand) {
            case LEFTROLL:
                navigationCenter.setRollControl("dec");
            case RIGHTROLL:
                navigationCenter.setRollControl("dec");
        }
    }

    //Modifies: this
    //Effects: allows manually setting lifePoints to the input int
    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    //Modifies: this
    //Effects: increments the player score by the input int amount
    public void setScore(int incScoreBy) {
        this.score =+ incScoreBy;
    }

    //Effects: returns lifePoints
    public int getLifePoints() {
        return lifePoints;
    }

    //Effects: returns player score
    public int getScore() {
        return score;
    }
}
