package com.Activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SeaActivity extends Activity {
	private Button Button1;
	private Button Button2;
	private Button Button3;
	private Button Button4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sea);
		Button1 = (Button) this.findViewById(R.id.button1);
		
		Button1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				 //Creating alert Dialog with three Buttons
				Intent intent = new Intent();
				intent.setClass(SeaActivity.this, Pacific.class);
				startActivity(intent);
				//SeaActivity.this.finish();
			}
		});
		Button2 = (Button) this.findViewById(R.id.button2);
		
		Button2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				 //Creating alert Dialog with three Buttons
				Intent intent = new Intent();
				intent.setClass(SeaActivity.this, Atlantic.class);
				startActivity(intent);
				//SeaActivity.this.finish();
			}
		});
		
		Button3 = (Button) this.findViewById(R.id.button4);
		
		Button3.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				 //Creating alert Dialog with three Buttons
				Intent intent = new Intent();
				intent.setClass(SeaActivity.this, Arctic.class);
				startActivity(intent);
				//SeaActivity.this.finish();
			}
		});
		
		Button4 = (Button) this.findViewById(R.id.button3);
		
		Button4.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				 //Creating alert Dialog with three Buttons
				Intent intent = new Intent();
				intent.setClass(SeaActivity.this, Indian.class);
				startActivity(intent);
				//SeaActivity.this.finish();
			}
		});
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sea, menu);
		return true;
	}
	

}
