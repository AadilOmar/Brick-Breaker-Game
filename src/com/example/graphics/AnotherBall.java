package com.example.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class AnotherBall extends View{
	boolean lose = false;
	boolean stopPaint = false;
	Bitmap newb;
	Canvas c;
	float moveX=10; //basically the speed
	float moveY=10;	//basically the speed
	boolean forwardX=true;
	boolean forwardY=true;
	private int xMin = 0;          // This view's bounds
	private int xMax = this.getWidth();
	private int yMin = this.getHeight();
	private int yMax;
	private float ballRadius = 90; // Ball's radius
	private float ballX = ballRadius + 20;  // Ball's center (x,y)
	private float ballY = ballRadius + 40;
	private RectF ballBounds;      // Needed for Canvas.drawOval
	private Paint paint;           // The paint (e.g. style, color) used for drawing
	private RectF squareBounds;
	Paint squarePaint;
	boolean addedBoxes = false;
	Bitmap myBitmap;
	Bitmap bitmap;
	RectF[] rectArray = new RectF[16];
	int arrayIndex =0;
	boolean addToArray1=true;
	boolean addToArray2=true;
	int temp=0;
	
	public AnotherBall(Context context, AttributeSet attrs) {
		super(context,attrs);
		this.setDrawingCacheEnabled(true);
		this.buildDrawingCache();
		bitmap = this.getDrawingCache();
		ballBounds = new RectF();
		squareBounds = new RectF();
		paint = new Paint();
		squarePaint = new Paint();
		squarePaint.setColor(Color.YELLOW);
	
	}
	@Override
	protected void onDraw(Canvas canvas) {

		this.c = canvas;
		if(temp==0){//does this only once
			createRectangleArray();
			temp++;
		}
		createBoxes(canvas);

		// Draw the ball 
		ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
		paint.setColor(Color.GREEN);
		canvas.drawOval(ballBounds, paint);
		update();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ballBounds.top==Color.YELLOW){
			System.out.println("IT IS DONE");
		}

		invalidate();
		
	}
	public void createBoxes(Canvas canvas){
		for(int x=0;x<rectArray.length;x++){
			if(rectArray[x]!=null){
				squareBounds.set(rectArray[x].left, rectArray[x].top, rectArray[x].right, rectArray[x].bottom); //sets bounds based on rects in array
				canvas.drawRect(squareBounds, squarePaint);
			}
		}
	}
	public void createRectangleArray(){
		int left=10;
		int top = 10;
		int right = 120;
		int bottom = 90;
		for(int x=0;x<8;x++){
			if(addToArray1){
				rectArray[arrayIndex]=new RectF(left, top, right, bottom);
				arrayIndex++;
			}
			left+=120;
			right +=120;
		}
		addToArray1=false;

		left=10;
		top +=90;
		right = 120;
		bottom +=90;
		for(int x=0;x<8;x++){
			if(addToArray2){
				rectArray[arrayIndex]=new RectF(left, top, right, bottom);
				arrayIndex++;
			}
			left+=120;
			right +=120;
		}
	}
	public void removeRectangle(int index){
		rectArray[index]=null;
	}
	
	public void update(){
		System.out.println("UPDATING");
		checkHitWall();
		checkHitBlock();

	}
	public void checkHitWall(){
		if(ballX+ballRadius>=xMax){
			System.out.println("jkfdjklafljkfdasjklfdas");
			forwardX=false;
		}
		else if(ballX-ballRadius<=xMin){
			forwardX=true;
		}
		if(lose!=true){
			if(forwardX){
				ballX+=moveX;
			}
			else{
				ballX-=moveX;
			}
		}


		if(ballY+ballRadius>=yMax){
			System.out.println("jkfdjklafljkfdasjklfdas");//lose
			lose=true;
			forwardY=false;
			//////////////maybe switch screen
		}
		else if(ballY-ballRadius<=yMin){
			forwardY=true;
		}
		if(lose!=true){
			if(forwardY){
				ballY+=moveY;
			}
			else{
				ballY-=moveY;
			}
		}
	}

	public void checkHitBlock(){
		for(int index=0;index<rectArray.length;index++){
			if(rectArray[index]!=null){
				if(ballBounds.intersect(rectArray[index])){
					System.out.println("ASDFASDF");
					//////////////////////////////////////HOW to tell what angle it has struck the block
					
					removeRectangle(index);
					stopPaint = true;
				}
			}
		}
		
	}
	public void bounceUp(float xClicked,float yClicked){		
		//positive when clicked in top and left of center
		float xDisplacement = Math.abs(ballX-xClicked);
		float yDisplacement = Math.abs(ballY-yClicked);
		if((ballX-xClicked)>0){ //clicked left side of ball
			forwardX=true;
			moveX += (xDisplacement/ballRadius)*10;
		}
		else{
			forwardX=false;
			moveX -= (xDisplacement/ballRadius)*10;
		}
		if((ballY-yClicked)>0){
			forwardY=true;
			moveY += (yDisplacement/ballRadius)*10;
		}
		else{
			forwardY=false;
			moveY -= (yDisplacement/ballRadius)*10;
		}
		
		double angle = Math.atan(((xDisplacement)/(yDisplacement))); //angle made by point clicked and middle of the circle
		double xSquared = Math.pow(xDisplacement, 2);
		double ySquared = Math.pow(yDisplacement, 2);

		double magnitude = Math.pow((xSquared+ySquared), .5);//distance formula to find magnitude
		
//		if(moveX>20)moveX=20;
//		if(moveY>20)moveY=20;
//		
//		if(moveX<-20)moveX=-20;
//		if(moveY<-20)moveY=-20;
		
//		double addingX = (xDisplacement)*10;
//		double addingY = (yDisplacement)*10;
		System.out.println("movex "+moveX);
		System.out.println("movey "+moveY);

		
	}
	

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if((event.getX()>=ballX-ballRadius)&&(event.getX()<=ballX+ballRadius)){ //inside x region
			if((event.getY()>=ballY-ballRadius)&&(event.getY()<=ballY+ballRadius)){
				
				bounceUp(event.getX(),event.getY());
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
