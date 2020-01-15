package main.model.asteroid;
import main.model.Coordinates;

//represents an asteroid object with max/currentRadius, coordinates, and distance from screen
public class Asteroid extends AsteroidObservable implements circularObj {
    private int currentRadius;
    private int maxRadius;
    private Coordinates coordinates;
    private Distance distance;
    private final double PI = 3.14159;
    private boolean killMe = false;

    //Effect: creates an asteroid instance based on input maxRadius and (x,y) coordinates on the field
    //        and sets the distance to the default color-mapped value of pure blue - representing "far away from
    //        screen"
    //Modifies: this
    //Requires: an x,y value within the boundaries of the game field -- NEED TO CREATE EXCEPTION HANDLING!
    public Asteroid(int maxRadius, int x, int y){
        this.distance = new Distance(0,0,255);
        this.currentRadius = 10;
        this.coordinates = new Coordinates(x, y);
        this.maxRadius = maxRadius;
    }

    //Effect: updates the currentRadius through calling incrementRadius and the input integer radIncrement,
    //        and if the new value exceeds regular bounds, will throw and exception, setting currentRadius
    //        to maxRadius.
    //Modifies: this
    public void updateRadius(int radIncrement){
        try {
            this.currentRadius = incrementRadius(radIncrement);
        }
        catch (OutOfUpperBoundsException e) {
            this.currentRadius = this.maxRadius;
        }
        catch (Exception e) {
            System.out.println("Unknown Exception Thrown by incrementRadius!");
        }
    }
    //Effect: updates the color-mapped distance values by the input amount for each base colors of RGB, stored
    //        in the distance object.
    //        Throws an exception and sets value to the bounds 0 and 255 if the modified color goes below/above
    //        those bounds respectively.
    //Modifies: this distance
    @Override
    public void updateDistance(int dRed, int dGreen, int dBlue) {
        int index = 0;
        int changeBy = dRed;
        if (dRed == 0) {
            index = 2;
            changeBy = dBlue;
        }
        try {
            this.distance.increment(index, changeBy);
        }
        catch (OutOfLowerBoundsException e) {
            this.distance.setDistance(index,0);
        }
        catch (OutOfUpperBoundsException e) {
            this.distance.setDistance(index,255);
        }
        catch (Exception e) {
            System.out.println("Unknown Exception Thrown by updateDistance!");
        }
        finally {
            //commented out test for reaching finally.
            //System.out.println("reached finally!");
        } //Observable calling update to Observer (Asteroid Field)
    }

    //Effect: sets the coordinates of the asteroid
    //Modifies: this coordinates
    @Override
    public void setLocationCoordinates(int x, int y) {
        coordinates.setCoordinates(x,y);
    }

    public Distance getDistance() {
        return distance;
    }

    public int getXCoordinate() {
        return this.coordinates.getXCoordinate();
    }

    public int getYCoordinate() {
        return this.coordinates.getYCoordinate();
    }

    public int getMaxRadius(){
        return this.maxRadius;
    }

    public int getCurrentRadius() {
        return this.currentRadius;
    }


    //Effect: returns the area value of the asteroid circle object
    @Override
    public double area() {
        return (PI * this.currentRadius * this.currentRadius);
    }

    //Effect: returns the perimeter value fo the asteroid circle object
    @Override
    public double perimeter() {
        return (2 * PI * this.currentRadius);
    }

    //Effect: updates the asteroid's state to be at closest distance and is ready to be deleted from the Asteroid Field
    @Override
    public void updateKillMeState() {
        this.killMe = true;
    }

    @Override
    public boolean getKillMeState() {
        return this.killMe;
    }

    //Effect: checks whether asteroid is ready to be kills, calls updateKillMeState if yes
    public void checkAsteroidKillStatus() {
        if (this.distance.getDistance(0) == 255) {
            updateKillMeState();
        }
    }

    //Effect: increments and returns the currentRadius by the passed in changeBy double
    //        and throws an OutOfUpperBoundsException if the new value exceeds maxRadius.
    //Requires: sum of changeBy & currentRadius to not exceed maxRadius
    public int incrementRadius(int changeBy) throws OutOfUpperBoundsException {
        if (this.currentRadius + changeBy >= this.maxRadius) {
            throw new OutOfUpperBoundsException("maxRad Reached!");
        }
        return this.currentRadius + changeBy;
    }
}
