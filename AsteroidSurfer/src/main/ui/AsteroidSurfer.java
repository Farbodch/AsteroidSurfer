package main.ui;

import main.model.asteroid.OutOfLowerBoundsException;
import main.model.asteroid.OutOfUpperBoundsException;
import main.model.newGame;
import main.model.player.OutOfBoundsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AsteroidSurfer extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final Color GAME_OVER_COLOR = new Color(0,102,204);
    private newGame game;
    private AsteroidSurferRenderer renderer;

    //Effect: sets up window in which the AsteroidSurfer game will be played
    AsteroidSurfer() {
        super("Surfer");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        this.game = new newGame(HEIGHT,WIDTH);
        renderer = new AsteroidSurferRenderer(game);
        addKeyListener(new KeyHandler());
        centreOnScreen();
        gameTracker();
        setVisible(true);
    }

    //Effect: clears screen and paints game onto graphics
    //Modifies: graphics
    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(game.getIsOver() ? GAME_OVER_COLOR : getBackground());
        graphics.fillRect(0,0,WIDTH,HEIGHT);
        renderer.draw(graphics);
    }

    // EFFECTS: main timer that updates the field
    //          INTERVAL milliseconds
    private void gameTracker() {
        final Timer gameTimer = new Timer(200, null);
        gameTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (game.getIsOver()) {
                    gameTimer.stop();
                } else {
                    game.updateGame();
                    repaint();
                }
            }
        });
        gameTimer.start();
    }

    //Effect:  frame is centred on desktop
    //Modifies: this
    private void centreOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
    }

    // Represents a key handler that responds to keyboard events
    private class KeyHandler extends KeyAdapter {
        @Override
        //Effect:  updates game in response to a keyboard event
        //Modifies: this
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_KP_UP:
                    try {
                        game.changeSpeed ('U');

                    } catch (OutOfBoundsException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_KP_DOWN:
                    try {
                        game.changeSpeed('D');
                    } catch (OutOfBoundsException ex) {
                        ex.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
	    new AsteroidSurfer();
    }
}
