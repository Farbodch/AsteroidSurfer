package test.model.asteroidTest;

import main.model.asteroid.Asteroid;
import main.model.field.AsteroidField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AsteroidFieldTest {
    private AsteroidField asteroidFieldTest;

    @BeforeEach
    void runBefore() {
        asteroidFieldTest = new AsteroidField(800,600);
    }

    @Test
    void testConstructor() {
        assertEquals(asteroidFieldTest.getFieldAsteroids().size(),0);
    }

    @Test
    void testGenerateAsteroid () {
        asteroidFieldTest.generateAsteroid();
        asteroidFieldTest.generateAsteroid();
        assertEquals(asteroidFieldTest.getFieldAsteroids().size(),2);
        assertFalse(asteroidFieldTest.getFieldAsteroids().size()>2);
    }

    @Test
    void testGenerateCoordinate() {
        asteroidFieldTest.generateAsteroid();
        asteroidFieldTest.generateAsteroid();
        asteroidFieldTest.generateAsteroid();
        for(Asteroid asteroid : asteroidFieldTest.getFieldAsteroids()) {
            assertTrue(asteroid.getXCoordinate() <= 800 );
            assertTrue(asteroid.getXCoordinate() >= -800);
            assertTrue(asteroid.getYCoordinate() <= 600);
            assertTrue(asteroid.getYCoordinate() >= -600);
        }
    }

    @Test
    void killDeadAsteroids() {
        asteroidFieldTest.generateAsteroid();
        asteroidFieldTest.getFieldAsteroids().get(0).updateDistance(0,0,-255);
        asteroidFieldTest.getFieldAsteroids().get(0).updateDistance(255,0,0);
        asteroidFieldTest.checkForDeadAsteroid();
        assertEquals(asteroidFieldTest.getFieldAsteroids().size(),1);
    }
}
