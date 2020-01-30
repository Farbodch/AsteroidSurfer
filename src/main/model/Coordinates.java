package src.main.model;

import java.util.ArrayList;
import java.util.List;


public class Coordinates {
    private List<Integer> coordinates;
    private MathFunctions mathFunctions = new MathFunctions() {
        @Override
        public void increment(int val, int changeBy) {}
    };

    public Coordinates(int x, int y) {
        coordinates = new ArrayList<>();
        coordinates.add(x);
        coordinates.add(y);

//         , int height, int width
//        if(!myMath.isEven(height)) {
//            coordinates.add((height+1)/2);
//        }
//        else
//            coordinates.add(height/2);
//        if(!myMath.isEven(width)){
//            coordinates.add(width+1/2);
//        }
//        else
//            coordinates.add(width/2);
    }

    public void setCoordinates(int x, int y) {
        this.coordinates.add(0, x);
        this.coordinates.add(1, y);
    }

    //ACCOUNT FOR EDGECASES!!!!
    public void incrementMyCoordinate(int incBy, char xy) {
        switch(xy) {
            case('x'): this.coordinates.add(0, (this.coordinates.get(0) + incBy));

            case('y'): this.coordinates.add(1, (this.coordinates.get(1) + incBy));
        }
    }

    public void incrementMyCoordinate(char xy) {
        switch(xy) {
            case('x'): this.coordinates.add(0, (this.coordinates.get(0) + 1));

            case('y'): this.coordinates.add(1, (this.coordinates.get(1) + 1));
        }
    }

    public int getXCoordinate() {return this.coordinates.get(0);}

    public int getYCoordinate() {return this.coordinates.get(1);}
}
