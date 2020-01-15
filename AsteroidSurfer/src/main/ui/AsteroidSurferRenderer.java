package main.ui;
import main.model.asteroid.Asteroid;
import main.model.newGame;

import java.awt.*;
import java.awt.geom.AffineTransform;

//renders the gui for the game
public class AsteroidSurferRenderer {
    private static final Color SHIP_COLOR = new Color(160,160,160);
    private newGame game;

    //Effect: creates a new instance of the gui class and sets game to render
    //        to the current game passed in
    AsteroidSurferRenderer(newGame game) {
        this.game = game;
    }

    //Effect: draws the game onto graphics
    //Modifies: graphics
    void draw(Graphics graphics){
        drawAsteroidField(graphics);
        //drawShip(graphics);
        //drawStats(graphics);
    }

    //Effect: draws asteroids onto graphics
    //Modifies: graphics
    private void drawAsteroidField(Graphics graphics) {
        for(Asteroid asteroid : game.getAsteroidField().getFieldAsteroids()){
            drawAsteroid(graphics, asteroid);
        }
    }

    //Effect: draws asteroid onto graphics
    //Modifies: graphics
    private void drawAsteroid(Graphics graphics, Asteroid asteroid) {
        int red = asteroid.getDistance().getDistance(0);
        int blue = asteroid.getDistance().getDistance(2);
        int myX = asteroid.getXCoordinate();
        int myY = asteroid.getYCoordinate();
        int myRadius = asteroid.getCurrentRadius();
        Color asteroidColor = new Color(red,0,blue);
        graphics.setColor(asteroidColor);
        graphics.fillOval(myX,myY,2*myRadius,2*myRadius);
    }

    //Effect: draws ship onto graphics
    //Modifies: graphics
    private void drawBackground(Graphics graphics) {

    }
}
