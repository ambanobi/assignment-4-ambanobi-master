package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * @author Aaron Banobi
 * @version November 4, 2015
 */


/*abstract class defining objects of the type Rock, Paper, and Scissors */
public abstract class RpsObject {

    //instance variables
    protected Paint myPaint;    //color of object
    protected float xCoord;
    protected float yCoord;
    protected float xVelocity;
    protected float yVelocity;
    protected int size;    //radius of the circle defining the objects height and width

    protected int objectType; //0=rock, 1=paper, 2=scissors
    protected boolean destroyed; //false when object has not been destroyed, true when destroyed from collision

    //constructor for RpsObjects, initializes random location, velocity, and size
    public RpsObject(){

        xCoord = ((int)(Math.random()*1400)+100); //initial random x location of object
        yCoord = ((int)(Math.random()*950)+100);  //initial random y location of object
        xVelocity = ((int)(Math.random()*25));  //initial random x velocity of object
        yVelocity = ((int)(Math.random()*25));  //initial random y velocity of object
        size = (int)(Math.random()*25+25);     //initial random size of object
        destroyed = false;  //initially all objects are not destroyed
    }

    /* method to draw RpsObject on specified canvas */
    public void draw(Canvas canvas){}


    //
    /* moves RpsObject based on it's velocity */
    public void moveObject(){

        //if object reaches the edge of the screen, bounces in the opposite direction
        if(xCoord + size>= 1800 || xCoord - size <=10){
            xVelocity = (float)(-(xVelocity*0.9));

            //move object away from edge of screen so it doesn't immediately bounce again
            xCoord += 2*xVelocity;
        }
        if(yCoord +size >= 1260){
            yVelocity = (float)(-(yVelocity*0.9));

            //move object away from edge of screen so it doesn't immediately bounce again
            yCoord += 2*yVelocity;
        }


        /*simulating gravity towards the bottom of the screen */
        if(Math.abs(yVelocity) < 1){
            yVelocity = 3f; //if the reaches 1 due to downwards gravity, turn the object around
        }
        if(yCoord > 1260){
            yVelocity = -yVelocity;
            yCoord = 1200-size; //makes sure object does not go off the bottom of the screen due to gravity
        }
        if(yVelocity > 0) {
            yVelocity *=1.05;  //if already moving down, increase velocity in y direction by .05%
        }

        if(yVelocity < 0) {
            yVelocity *=0.95; //if moving up decrease velocity in y direction by 0.5%
        }

        //adds velocities to current coordinates to simulate movement
        xCoord += xVelocity;
        yCoord +=yVelocity;
    }

    /* determines if two RpsObjects overlap; returns true if they overlap, false if not */
    public boolean overlaps(RpsObject other) {

        if(this.destroyed == true || other.destroyed == true){
            return false;
        }
        //Determine the square of the distance between the two objects
        float xDist = other.xCoord - this.xCoord;
        float yDist = other.yCoord - this.yCoord;
        float distSquared = xDist*xDist + yDist*yDist;

        // determine the sum of the square of the radii of the two objects
        float thisSize = this.size;
        float otherSize = other.size;
        float sumRadiiSquared = thisSize*thisSize + otherSize*otherSize;

        return distSquared <= sumRadiiSquared; //if distance is less that sum of radii, objects have overlapped
    }//overlaps
}
