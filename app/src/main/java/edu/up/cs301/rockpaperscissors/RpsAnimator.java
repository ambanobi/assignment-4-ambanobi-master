package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.animation.Animator;


/**
 * rock-paper-scissors animation
 * 
 * @author Steve Vegdahl
 * @author Aaron Banobi
 * @version November 4, 2015
 */
public class  RpsAnimator implements Animator{


	private static final int SIZE_INCREASE = 20;
	// instance variables
	private int count = 0; // counts the number of logical clock ticks
	private boolean goBackwards = false; // whether clock is ticking backwards


	//array of RpsObjects to keep track of objects in the game
	private ArrayList<RpsObject> rpsArray = new ArrayList<RpsObject>(){{
		int i;
		//initializes array with random assortment of Rock, Paper, and Scissor objects
		for(i=0; i<=20; i++){
			double random = Math.random();
			if(random<=.33){
				add(new RockObject());
				Log.i("rock", "rock"+i);
			}
			else if(random<=.66){
				add(new PaperObject());
				Log.i("paper", "paper"+i);
			}
			else {
				add(new ScissorObject());
				Log.i("scissors", "scissors"+i);
			}
		}
	}};



	/**
	 * Interval between animation frames: .03 seconds (i.e., about 33 times
	 * per second).
	 *
	 * @return the time interval between frames, in milliseconds.
	 */
	public int interval() {
		return 30;
	}

	/**
	 * The background color: a light blue.
	 *
	 * @return the background color onto which we will draw the image.
	 */
	public int backgroundColor() {
		// create/return the background color
		return Color.rgb(140,180,255); // light blue
	}

	/**
	 * Tells the animation whether to go backwards.
	 *
	 * @param b true iff animation is to go backwards.
	 */
	public void goBackwards(boolean b) {
		// set our instance variable
		goBackwards = b;
	}

	/**
	 * Action to perform on clock tick
	 *
	 * @param g the graphics object on which to draw
	 */
	public void tick(Canvas g) {
		// bump our count either up or down by one, depending on whether
		// we are in "backwards mode".
		if (goBackwards) {
			count--;
		}
		else {
			count++;
		}



		int j;
		int k;

		//iterates through RpsArray to compare each pair of RpsObjects to check if they overlap
		for(j=0; j<rpsArray.size(); j++){
			for(k=j+1; k<rpsArray.size(); k++){

				/*if two objects of the same type collide, smaller object is destroyed, larger object
				has it's size variable increased by pre-defined SIZE_INCREASE
				if object one collides with object it beats, increases the size variable of first object by
				pre-defined SIZE_INCREASE, sets destroyed variable of second object to true
				if object one collides with object that beats it, increases the size variable of the second
				object by pre-defined SIZE_INCREASE, sets destroyed variable of first object to true */
				if(rpsArray.get(j).overlaps(rpsArray.get(k))){
					if(rpsArray.get(j).objectType == 0){
						if(rpsArray.get(k).objectType == 0){


							if(rpsArray.get(j).size > rpsArray.get(k).size){
								rpsArray.get(k).destroyed = true;
								rpsArray.get(j).size+=SIZE_INCREASE;
							}
							else{
								rpsArray.get(j).destroyed = true;
								rpsArray.get(k).size+=SIZE_INCREASE;
							}
						}
						else if(rpsArray.get(k).objectType == 1){
							rpsArray.get(j).destroyed = true;
							rpsArray.get(k).size+=SIZE_INCREASE;
						}
						else if(rpsArray.get(k).objectType == 2){
							rpsArray.get(k).destroyed = true;
							rpsArray.get(j).size+=SIZE_INCREASE;
						}
					}
					if(rpsArray.get(j).objectType == 1){
						if(rpsArray.get(k).objectType == 1){

							if(rpsArray.get(j).size > rpsArray.get(k).size){
								rpsArray.get(k).destroyed = true;
								rpsArray.get(j).size+=SIZE_INCREASE;
							}
							else{
								rpsArray.get(j).destroyed = true;
								rpsArray.get(k).size+=SIZE_INCREASE;
							}
						}
						else if(rpsArray.get(k).objectType == 2){
							rpsArray.get(j).destroyed = true;
							rpsArray.get(k).size+=SIZE_INCREASE;
						}
						else if(rpsArray.get(k).objectType == 0){
							rpsArray.get(k).destroyed = true;
							rpsArray.get(j).size+=SIZE_INCREASE;
						}
					}
					if(rpsArray.get(j).objectType == 2){
						if(rpsArray.get(k).objectType == 2){

							if(rpsArray.get(j).size > rpsArray.get(k).size){
								rpsArray.get(k).destroyed = true;
								rpsArray.get(j).size+=SIZE_INCREASE;
							}
							else{
								rpsArray.get(j).destroyed = true;
								rpsArray.get(k).size+=SIZE_INCREASE;
							}

						}
						else if(rpsArray.get(k).objectType == 0){
							rpsArray.get(j).destroyed = true;
							rpsArray.get(k).size+=SIZE_INCREASE;
						}
						else if(rpsArray.get(k).objectType == 1){
							rpsArray.get(k).destroyed = true;
							rpsArray.get(j).size+=SIZE_INCREASE;
						}
					}

				}
			}
		}

		//moves the object using the moveObject method and then draws moved state on the canvas
		int i;
		for(i=0; i<rpsArray.size(); i++){

			rpsArray.get(i).moveObject();
			rpsArray.get(i).draw(g);
			//Log.i("iteration: ", ""+i);
		}
	}

	/**
	 * Tells that we never pause.
	 *
	 * @return indication of whether to pause
	 */
	public boolean doPause() {
		return false;
	}

	/**
	 * Tells that we never stop the animation.
	 *
	 * @return indication of whether to quit.
	 */
	public boolean doQuit() {
		return false;
	}

	/* onTouch method to handle the a touch and drag event causing gravity towards the location of the touch */
	public void onTouch(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_MOVE)
		{
			float x = (int)event.getX(); // x location of touch
			float y = (int)event.getY(); // y location of touch
            float distanceX;
            float distanceY;


			//for each object in array, apply gravity of touch to the objects velocity
			for(int i=0; i<rpsArray.size(); i++){
                distanceX = rpsArray.get(i).xCoord - x;
                distanceY = rpsArray.get(i).yCoord - y;




				if(distanceX == 0){
					distanceX = 1; //ensures no divide by zero bugs
				}
				if(distanceY == 0){
					distanceY = 1; //ensures no divide by zero bugs

				}

				// force 1/distance^2 scaled to match velocity
				float forceX = (5000000/(distanceX*distanceX));
				float forceY = (5000000/(distanceY*distanceY));

				//if force is too great, scale back to 1
				if(forceX>1){
					forceX = 1f;
				}
				if(forceY>1){
					forceY = 1f;
				}


				//if touch location occurs on opposite side of object, reverse direction of the force
				if(distanceX<0){
					forceX = -forceX;
				}
				if(distanceY<0){
					forceY = -forceY;
				}

				// add negative force to velocity
				rpsArray.get(i).xVelocity -= forceX;
				rpsArray.get(i).yVelocity -= forceY;
			}
		}
	}

	/* addObject method that adds 5 random objects to the rpsArray to be drawn on the animation surface */
	public void addObjects(){
		for(int i=0; i<=5; i++){
			for(i=0; i<=5; i++){
				double random = Math.random();
				if(random<=.33){
					rpsArray.add(new RockObject());
					Log.i("rock", "rock"+i);
				}
				else if(random<=.66){
					rpsArray.add(new PaperObject());
					Log.i("paper", "paper"+i);
				}
				else {
					rpsArray.add(new ScissorObject());
					Log.i("scissors", "scissors"+i);
				}
			}
		}
	}




}//class RpsAnimator
