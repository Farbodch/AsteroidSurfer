/**
 * Random number generation credit:
 * https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
 * Dec 12 '08 at 18:25
 * Greg Case
 **/

package main.model;
import main.model.asteroid.OutOfLowerBoundsException;
import main.model.asteroid.OutOfUpperBoundsException;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//contains most commonly used math functions in this app
public abstract class MathFunctions {
    private boolean isCorrect;
    private int answer;

    //Effect: creates a math function instance and initializes answer boolean to false and answer value to 0
    //Modifies: this
    public MathFunctions(){
        this.isCorrect = false;
        this.answer = 0;
    }

    //Effect: generates and returns a random integer between min and max
    //-credit above
    public int randomNum(int min, int max){
        return (ThreadLocalRandom.current().nextInt(min, max + 1));
    }

    //Effect: checks whether input integer is even or not, returns true if yes
    public boolean isEven(int numToCheck){
        if(numToCheck % 2 == 0) {return true;}
        return false;
    }

    //Effect: returns the round downed integer value of hypotenuse of a triangle of sides sideOne & sideTwo
    //        or diagonal of a rectangle with side lengths sideOne & sideTwo
    public int getDiagonal(int sideOne, int sideTwo){
        return (int)Math.sqrt((sideOne*sideOne) + (sideTwo*sideTwo));
    }

    //Effect: subtracts the x and y coordinates of points 1 and 2 to construct a new vector and then
    //        passes the resulting vector's <x,y> lengths to the getDiagonal function, returning the length
    //        between points 1 and 2
    public int getVectorDistance(int x1, int y1, int x2, int y2) {
        return getDiagonal((y2-y1),(x2-x1));
    }

    //Effect: an abstract method to increment/decrement the integer val by integer changeBy.
    //        - to be implemented as needed by extended method
    public abstract void increment(int val, int changeBy) throws OutOfLowerBoundsException, OutOfUpperBoundsException;
}
