package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * @author Aaron Banobi
 * @version November 4, 2015
 */


/* class defining the ScissorObject of the abstract class RpsObject
* only updates constructor and draw method as they are the only methods
* unique to each type of object*/
public class RockObject extends RpsObject {

    //specific constructor for RockObject,
    public RockObject(){
        int color = Color.rgb(200, 200, 200);
        myPaint = new Paint();
        myPaint.setColor(color);

        objectType = 0;
    }


    @Override
    //draws rock image representing rock on canvas--a gray circle
    public void draw(Canvas canvas){
        if(this.destroyed == true){
            return; //does not draw object if it has been destroyed
        }

        int color = Color.rgb(100, 100, 100);
        myPaint = new Paint();
        myPaint.setColor(color);
        canvas.drawCircle(xCoord, yCoord, size, myPaint);

    }




}
