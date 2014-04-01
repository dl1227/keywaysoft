package com.example.lvhttmutibutton;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	
	private Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		btn = (Button)this.findViewById(R.id.button1);
	
		btn.setOnTouchListener(listner);
	}

	private View.OnTouchListener listner = new View.OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
//			if(event.getAction() == MotionEvent.ACTION_DOWN)
//			{
//				v.setBackgroundResource(R.drawable.drawer_icon_self_service);
//			}
//			else
//			{
//				v.setBackgroundResource(R.drawable.drawer_icon_group);
//			}
			return false;
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Log.i("","no exit...");
		}
		return false;
	}
 

}
