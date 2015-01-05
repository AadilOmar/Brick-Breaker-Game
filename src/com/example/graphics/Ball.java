package com.example.graphics;

import java.util.Timer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Ball extends View {

		boolean forwardX=true;
		boolean forwardY=true;
	   private int xMin = 0;          // This view's bounds
	   private int xMax;
	   private int yMin = 0;
	   private int yMax;
	   private float ballRadius = 50; // Ball's radius
	   private float ballX = ballRadius + 20;  // Ball's center (x,y)
	   private float ballY = ballRadius + 40;
	   private RectF ballBounds;      // Needed for Canvas.drawOval
	   private Paint paint;           // The paint (e.g. style, color) used for drawing
	
	public Ball(Context context, AttributeSet attrs) {
		super(context,attrs);
		ballBounds = new RectF();
	    paint = new Paint();

	}
	 @Override
	   protected void onDraw(Canvas canvas) {
	      // Draw the ball
	      ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
	      paint.setColor(Color.GREEN);
	      canvas.drawOval(ballBounds, paint);
	      System.out.println("width!!!"+xMax);
	      update();
	      try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      invalidate();
	 }
	 public void update(){
		System.out.println("UPDATING");
		
		if(ballX+ballRadius>=xMax){
			System.out.println("jkfdjklafljkfdasjklfdas");
			forwardX=false;
		}
		else if(ballX-ballRadius<=xMin){
			forwardX=true;
		}
		if(forwardX){
			ballX+=10;
		}
		else{
			ballX-=10;
		}


		if(ballY+ballRadius>=yMax){
			System.out.println("jkfdjklafljkfdasjklfdas");
			forwardY=false;
		}
		else if(ballY-ballRadius<=yMin){
			forwardY=true;
		}
		if(forwardY){
			ballY+=10;
		}
		else{
			ballY-=10;
		}
		
		
		
	 }
	 
	 @Override
	 public boolean onTouchEvent(MotionEvent event) {
		if((event.getX()>=ballX-ballRadius)&&(event.getX()<=ballX+ballRadius)){ //inside x region
			if((event.getY()>=ballY-ballRadius)&&(event.getY()<=ballY+ballRadius)){
				System.out.println("///////////////////////////");
				this.setBackgroundColor(Color.MAGENTA);
			}
		}
		return forwardX;

	     
	 }
	 
	 @Override
	 protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
	     super.onSizeChanged(xNew, yNew, xOld, yOld);
	     
	     xMax = xNew;
	     yMax= yNew;
	}
	
	
}
