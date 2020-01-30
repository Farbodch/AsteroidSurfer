package src.main.model;

import src.main.model.asteroid.Asteroid;
import src.main.model.field.AsteroidField;
import src.main.model.field.Field;
import src.main.model.player.OutOfBoundsException;
import src.main.model.player.Player;

import java.sql.SQLOutput;


//represents a new game instance
public class newGame {
    private Player player;
    private Field field;
    private AsteroidField asteroidField;
    private SpeedIncrements speedIncrements;
    private boolean isOver;

    //Effect: initializes a new game by initializing the declared field and player
    //Modifies: this
    public newGame(int height, int width){
        this.field = new Field(height,width);
        this.player = new Player(height,width);
        this.asteroidField = new AsteroidField(width,height);
        this.isOver = false;
        this.speedIncrements = new SpeedIncrements();
    }

    //Effect: calls all the model update methods to update the whole game parameters including
    //        player speed, and based on that, calls speedIncrements to get change for asteroid parameters
    //        and their change in distance, radius, generation, and asteroid field update
    public void updateGame() {
        //this.player.getShipLocation();
        this.speedIncrements.updateCurrentSpeed(this.player.getSpeed());
        this.asteroidField.generateAsteroid();
        for(Asteroid asteroid : asteroidField.getFieldAsteroids()) {
            this.speedIncrements.updateCurrentDistances(asteroid.getDistance());
            this.speedIncrements.updateCurrentSpeed(this.player.getSpeed());
            int dRed = this.speedIncrements.distanceChangeSpeed().get(0);
            int dBlue = this.speedIncrements.distanceChangeSpeed().get(2);
            int radiusIncrement = this.speedIncrements.radiusChangeSpeed();
            asteroid.updateDistance(dRed,0,dBlue);
            asteroid.updateRadius(radiusIncrement);
            asteroid.checkAsteroidKillStatus();
        }
        asteroidField.checkForDeadAsteroid();
        if(this.player.getLifePoints() == 0) {
            this.isOver = true;
        }
    }

    //Effect: changes player speed based on input char cmd (command)
    //Modifies: this
    public void changeSpeed(char cmd) throws OutOfBoundsException {
        this.player.changeSpeed(cmd);
    }

    public AsteroidField getAsteroidField() {
        return asteroidField;
    }

    public boolean getIsOver(){
        return this.isOver;
    }

    public void playerHit(){}

}
