package com.example.graphics;

import android.R.color;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener{
	
	View block;
	View ballView;
	View firstBallView;
	boolean isBlack = true;
	Button button;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    //RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);
	    
	    button = (Button) findViewById(R.id.button1);
	    button.setOnClickListener(this);
	    button.requestFocus();
//	    button.setText("pause");
//	    button.setHeight(30);
//	    button.setWidth(30);
//	    
//	    layout.addView(button);

		System.out.println("eeee");
		//block = new Block(this);
		System.out.println("aaaaaa");
		
//		LinearLayout layout = (LinearLayout) findViewById(R.id.mainLayout);
//		System.out.println("bbbbb");
//	    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,0);
//	    lp.height=0;
//	    lp.weight= 1;
//		
//		block.setLayoutParams(lp);

      // layout.addView(block);
		System.out.println("ccccccc");
		//layout.addView(block);
		System.out.println("dddd");

		//firstBallView = new Ball(this);
//		firstBallView = findViewById(R.id.ballView);
//		firstBallView.setOnClickListener(this);
//		firstBallView.setBackgroundColor(Color.YELLOW);
//		
//		ballView = findViewById(R.id.anotherBallView);
//		
//		//ballView = new AnotherBall(this);
//		System.out.println("this is the actual width "+ballView.getWidth());
//		ballView.setOnClickListener(this);
//	    ballView.setBackgroundColor(Color.BLACK);
	    
	    

	 //   LinearLayout layout = (LinearLayout) findViewById(R.id.mainLayout);
	    //layout.addView(firstBallView);
	    //layout.addView(firstBallView);
	    
	}

	@Override
	public void onClick(View v) {
		System.out.println("something was clicked");
		if (v==button){
			try {
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(v==ballView){
			System.out.println("the ball shoulve been clicked");
			if(isBlack){
				ballView.setBackgroundColor(Color.BLUE);
				isBlack=!isBlack;
			}
			else{
				ballView.setBackgroundColor(Color.BLACK);
				isBlack=!isBlack;
			}
		}
	}
	
	
}