package edu.up.cs301.rockpaperscissors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * @author Aaron Banobi
 * @version November 4, 2015
 */


/* class defining the ScissorObject of the abstract class RpsObject
* only updates constructor and draw method as they are the only methods
* unique to each type of object*/
public class PaperObject extends RpsObject {

    //specific constructor for PaperObject
    public PaperObject(){

        int color = Color.rgb(200, 200, 200);
        myPaint = new Paint();
        myPaint.setColor(color);

        objectType = 1;

    }

    @Override
    //draws image representing paper on canvas--a white square
    public void draw(Canvas canvas){

        if(this.destroyed){
            return; //does not draw object if it has been destroyed
        }
        int color = Color.rgb(255, 255, 255);
        myPaint = new Paint();
        myPaint.setColor(color);
        canvas.drawRect(xCoord-size, yCoord-size, xCoord+size, yCoord+size, myPaint);

    }
}
