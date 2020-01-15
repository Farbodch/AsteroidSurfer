package main.model.asteroid;
import main.model.MathFunctions;
import java.util.ArrayList;
import java.util.List;

//represents the distance between asteroid and the screen, through color-code-mapping of blue -> red representing
//far -> close
public class Distance extends MathFunctions {
    private List<Integer> distance = new ArrayList<>();

    //Effect: creates a new Distance instance with initialized Red, Green, Blue parameters
    //Modifies: this
    //Requires: RGB integers to be between 0 and 255, inclusive
    public Distance(int red, int green, int blue){
        this.distance.add(red);
        this.distance.add(green);
        this.distance.add(blue);
    }

    //Effect: returns the color value at specific index of Red @ 0, Green @ 1, Blue @ 2
    public int getDistance(int index){
        return (this.distance.get(index));
    }

    //Effect: checks whether the color value at a specific index, after being incremented/decremented by the changeBy
    //        integer, is still within the [0,255] bounds or not.
    //        If:    below the min bound throws exception
    //               above the max bound throws exception
    //               if within bounds returns 0
    int checkDistanceBound(int index, int changeBy) throws OutOfUpperBoundsException, OutOfLowerBoundsException {
        if((changeBy + (this.distance.get(index)) < 0)) {
           throw new OutOfLowerBoundsException("OutOfLowerBoundLower Thrown!");
        }
        if((changeBy + (this.distance.get(index)) > 255)) {
            throw new OutOfUpperBoundsException("OutOfUpperBoundException Thrown!");
        }
        return 0;
    }

    //Effect: changes the color value at the specific index (Red @ 0, Green @ 1, Blue @ 2) in distance by the passed in
    //        changeBy integer.
    //Modifies: this
    //Requires: value at index + changeBy remain within [0,255] boundary
    @Override
    public void increment(int index, int changeBy) throws OutOfLowerBoundsException, OutOfUpperBoundsException {
        if(checkDistanceBound(index,changeBy) == 0) {
            this.distance.set(index, (distance.get(index) + changeBy));
        }
    }

    public void setDistance(int index, int setBy) {
        this.distance.set(index,setBy);
    }
}
