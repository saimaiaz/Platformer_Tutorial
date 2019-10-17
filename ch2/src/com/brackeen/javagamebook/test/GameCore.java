package com.brackeen.javagamebook.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;

import javax.swing.ImageIcon;
import com.brackeen.javagamebook.graphics.ScreenManager;


/**
    Simple abstract class used for testing. Subclasses should
    implement the draw() method.
*/
public abstract class GameCore {

    protected static final int FONT_SIZE = 24;

    private boolean isRunning;
    protected ScreenManager screen;

    /**
        Signals the game loop that it's time to quit
    */
    public void stop() {
        isRunning = false;
    }


    /**
        Calls init() and gameLoop()
    */
    public void run() {
        try {
            init();
            gameLoop();
        }
        finally {
             screen.restoreScreen();
        }
    }


    /**
        Sets full screen mode and initiates and objects.
    */
    public void init() {
        screen = new ScreenManager();
        
        screen.setFullScreen();
        Window window = screen.getFullScreenWindow();        
        window.setFont(new Font("Dialog", Font.PLAIN, FONT_SIZE));
        window.setBackground(Color.black);
        window.setForeground(Color.white);

        isRunning = true;
    }


    public Image loadImage(String fileName) {
        return new ImageIcon(fileName).getImage();
    }


    /**
        Runs through the game loop until stop() is called.
    */
    public void gameLoop() {
        long startTime = System.currentTimeMillis();
        long currTime = startTime;

        while (isRunning) {
            long elapsedTime = System.currentTimeMillis() - currTime;
            currTime += elapsedTime;

            // update of GameCore is implementing in child class
            update(elapsedTime);

            // draw the screen of GameCore is also implementing in child class 
            Graphics2D g = screen.getGraphics();
            draw(g);
            g.dispose();
            screen.update(); // update JFrame 

            // take a nap
            try {
                Thread.sleep(25);
            }
            catch (InterruptedException ex) { }
        }
    }

    /**
        Updates the state of the game/animation based on the
        amount of elapsed time that has passed.
    */
    public  void update(long elapsedTime) {
        // do nothing
    	// implementing in child class yet
    }


    /**
        Draws to the screen. Subclasses must override this
        method.
    */
    public abstract void draw(Graphics2D g);
 // implementing in child class yet
}
