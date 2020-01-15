package main.model;

import main.model.asteroid.Distance;

import java.util.ArrayList;
import java.util.List;

//calculates and returns the speeds with which objects in the game need to be updated, including asteroid radii and
//and their distance color map
public class SpeedIncrements {
    private int currentSpeed;
    private List<Integer> distanceChange = new ArrayList<>();
    private Distance currentDistances;
    private static final int RADIUS_SPEED_MULTIPLIER = 2;
    private final static int DISTANCE_SPEED_MULTIPLIER = 10;

    public SpeedIncrements() {
        this.distanceChange.add(0);
        this.distanceChange.add(0);
        this.distanceChange.add(0);
    }

    public int getCurrentSpeed() {
        return this.currentSpeed;
    }

    public void updateCurrentSpeed(int userSpeed) {
        this.currentSpeed = userSpeed;
    }

    public void updateCurrentDistances(Distance currentDistances){
        this.currentDistances = currentDistances;
    }

    public List<Integer> distanceChangeSpeed() {
        if(this.currentDistances.getDistance(2) > 0) {
            distanceChange.set(0,0);
            distanceChange.set(2,-1*this.currentSpeed*DISTANCE_SPEED_MULTIPLIER);
        }
        else if(this.currentDistances.getDistance(0) < 255) {
            distanceChange.set(2,0);
            distanceChange.set(0,this.currentSpeed*DISTANCE_SPEED_MULTIPLIER);
        }
        return distanceChange;
    }

    public int radiusChangeSpeed() {
        return (this.currentSpeed * RADIUS_SPEED_MULTIPLIER);
    }
}
