package com.example.graphics;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


public class Block extends View{

	RectF blockBounds;
	
	public Block(Context context, AttributeSet attrs,float left,float top, float right, float bottom) {
		super(context,attrs);
		blockBounds= new RectF();
		blockBounds.set(left, top, right, bottom);
	
	}
	@Override
	   protected void onDraw(Canvas canvas) {
		System.out.println("drawing");
		Paint paint = new Paint();
		paint.setColor(Color.YELLOW);
		canvas.drawRect(blockBounds, paint);
	}
	

}
