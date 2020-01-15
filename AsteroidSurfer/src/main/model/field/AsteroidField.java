/*
 * Iterator credit:
 * https://www.java67.com/2014/03/2-ways-to-remove-elementsobjects-from-ArrayList-java.html#:~:targetText=There%20are%20two%20ways%20to,i.e.%20remove(Object%20obj).
 * accessed Dec 11/2019
 * Javin Paul
 */

package main.model.field;

import main.model.MathFunctions;
import main.model.asteroid.Asteroid;


import java.util.*;

//represents the asteroid field. Can contain asteroids, generate them, check validity of the new asteroids' parameters
//and insert it into the current Asteroid Field as needed.
public class AsteroidField implements AsteroidObserver {
    Random randNum = new Random(System.currentTimeMillis());
    private List<Asteroid> fieldAsteroids = new ArrayList<>();
    private int width, height;
    private static final int OVERLAP_BUFFER = 10;
    private MathFunctions mathFunctions = new MathFunctions() {
        @Override
        public void increment(int val, int changeBy) {}
    };

    //Effect: creates an asteroid generator instance
    //Modifies: this
    public AsteroidField(int width, int height) {
        this.width = width;
        this.height = height;
    }

    //Effect: randomly generates a test (x,y) coordinate set and a test maxRadius -within- boundaries of the field,
    //        and checks whether the test parameters (the circle created by the (x,y) center and maxRadius) is not
    //        in conflict with existing asteroids in the asteroid field. (no overlap with other asteroids and within bounds)
    //
    //        The test & check hierarchy starts with a random maxRadius, then a random X coordinate, against which
    //        y coordinates are guessed that don't exist in a HashSet keeping log of guessed Ys (to avoid duplication).
    //        When all y guesses fail (up to a max unique guess number (in the HashSet) by the number of maxRadii one
    //        can fit inside the field Height), then the Y HashSet is cleared, X is logged into it's own HashSet,
    //        re-guessed against it's respective HashSet (to avoid duplication), and the y coordinates are guessed
    //        & logged again. If all x guesses fail (up to a max unique guess number (in the HashSet) by the number of
    //        maxRadii one can fit inside the field width), then X HashSet is cleared, and a new maxRadius is guessed.
    //        If all test parameters pass, then a new Asteroid is created with these parameters, and is then added
    //        to the asteroid field (fieldAsteroids Asteroid List).
    //        A new maxRadius will only be guess up to a maximum number of times, and will exit loop without adding a
    //        new asteroid if reached.
    //Modifies: this fieldAsteroids
    public void generateAsteroid() {
        boolean asteroidOverlap = true;
        int maxRadiusGuess = (mathFunctions.getDiagonal(height, width))/10;
        int minRadiusGuess = 30;
        Set<Integer> previousTestXSet = new HashSet<Integer>();
        Set<Integer> previousTestYSet = new HashSet<Integer>();
        int testX = 0;
        int testY = 0;
        int guessSoFar = 0;
        int maxGuess = ((maxRadiusGuess - minRadiusGuess)/2);
        int testMaxRadius = 30;

        while(asteroidOverlap || (guessSoFar < maxGuess)) {
            testMaxRadius = mathFunctions.randomNum(minRadiusGuess, maxRadiusGuess);
            while(previousTestXSet.size() < width/testMaxRadius) {
                do {
                    testX = generateCoordinate(this.width,testMaxRadius);
                } while (previousTestXSet.contains(testX));
                previousTestXSet.add(testX);

                while(previousTestYSet.size() < height/testMaxRadius) {
                    do {
                        testY = generateCoordinate(this.height,testMaxRadius);
                    } while (previousTestXSet.contains(testY));
                    asteroidOverlap = checkOverlap(testX,testY,testMaxRadius); //checks whether asteroids overlap
                    if(!asteroidOverlap) {
                        break;
                    }
                    previousTestYSet.add(testY);
                }
                previousTestYSet.clear();
                if(!asteroidOverlap) {
                    break;
                }
            }
            previousTestXSet.clear();
            guessSoFar++;
        }
        Asteroid testAsteroid = new Asteroid(testMaxRadius,testX,testY);
        this.fieldAsteroids.add(testAsteroid);
    }

    //Effect: generates random coordinates such that the generated asteroid with it's center + radius remains
    //        within the height and with boundaries of the field.
    private int generateCoordinate(int boundValue, int testMaxRadius){
        return mathFunctions.randomNum((-(boundValue)+(testMaxRadius/2)),((boundValue)-(testMaxRadius/2)));
    }

    //Effect: loops through all existing asteroids in the field, and calls the getVectorDistance from mathFunctions to
    //        get the distance between generated (x,y) coordinates and each existing asteroid on the field.
    //        If the distance between centers is less than the sum of the existing asteroid's max radius, the test
    //        max radius, and an overlap buffer value, then the asteroids are overlapping and function returns true.
    //        Returns false otherwise, indicating no overlap.
    //        During Looping, if an asteroid has reached max, it will be deleted.
    private boolean checkOverlap(int testX, int testY, int testMaxRadius) {
        for (Asteroid fieldAsteroid : this.fieldAsteroids) {
            int fieldAsteroidX = fieldAsteroid.getXCoordinate();
            int fieldAsteroidY = fieldAsteroid.getYCoordinate();
            int distBetweenCenters = mathFunctions.getVectorDistance(testX,testY,fieldAsteroidX,fieldAsteroidY);
            if(distBetweenCenters < (testMaxRadius + fieldAsteroid.getMaxRadius() + OVERLAP_BUFFER)) {
                return true;
            }
        }
        return false;
    }


    public List<Asteroid> getFieldAsteroids() {
        return this.fieldAsteroids;
    }

    //Effect: Iterates over field to kill dead asteroids using iterator
    //        removes Asteroid from field if the distance of the field (the color mapped
    //        [blue -> red] : [far -> close])
    //        has reached the max value, being Red = 255, Green = 0, Red = 0.
    //Modifies: this
    //--credit above
    @Override
    public void checkForDeadAsteroid() {
        Iterator<Asteroid> itr = this.fieldAsteroids.iterator();
        while (itr.hasNext()){
            Asteroid asteroid = itr.next();
            if (asteroid.getKillMeState()) {
                itr.remove();
            }
        }
    }
}
