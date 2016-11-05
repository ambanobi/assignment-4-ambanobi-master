package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

/**
 * @author Aaron Banobi
 * @version November 4, 2015
 */


/* class defining the ScissorObject of the abstract class RpsObject
* only updates constructor and draw method as they are the only methods
* unique to each type of object*/
public class ScissorObject extends RpsObject {

    //specific constructor for ScissorObject
    public ScissorObject(){

        int color = Color.rgb(255, 0, 0); //color scissors red
        myPaint = new Paint();
        myPaint.setColor(color);
        objectType = 2; //defines the objectType as scissors

    }

    @Override
    //draws image representing scissors on canvas--a red pointer shaped polygon
    public void draw(Canvas canvas){

        if(this.destroyed == true){
            return; //does not draw object if it has been destroyed
        }

        int color = Color.rgb(255, 0, 0);
        myPaint = new Paint();
        myPaint.setColor(color);
        Path scissor = new Path();  //draws scissors image using a path
        scissor.moveTo(xCoord-size, yCoord+size);
        scissor.lineTo(xCoord, yCoord-size);
        scissor.lineTo(xCoord, yCoord);
        scissor.lineTo(xCoord+size, yCoord);
        canvas.drawPath(scissor, myPaint);

    }
}
