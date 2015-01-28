package com.Activity;

import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.Controller.StaticData;
import com.Database.Database;
import com.Entity.FishInf;

public class Main extends Activity {

	private Button Button11;
	private Button Button22;
	private Button Button33;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
		Button11 = (Button) this.findViewById(R.id.button11);
		
		Button11.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				 //Creating alert Dialog with three Buttons
				Intent intent = new Intent();
				intent.setClass(Main.this, SeaActivity.class);
				startActivity(intent);
				//Main.this.finish();
			}
		});
		
		Button22 = (Button) this.findViewById(R.id.button22);
		
		Button22.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				 //Creating alert Dialog with three Buttons
				Intent intent = new Intent();
				intent.setClass(Main.this, Ranking.class);
				startActivity(intent);
				//Main.this.finish();
			}
		});
		
		Button33 = (Button) this.findViewById(R.id.button33);
		
		Button33.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				 //Creating alert Dialog with three Buttons
				Intent intent = new Intent();
				intent.setClass(Main.this, Book.class);
				startActivity(intent);
				//Main.this.finish();
			}
		});
        new Database(this);
        StaticData.IniGame(this);
        StaticData.LoadFishes();
        StaticData.LoadUserInf();
       
        Iterator<FishInf> it=StaticData.fishes.getFishes(1).iterator();
       /* while(it.hasNext()){
        	Log.i("Test", "Start");
        	FishInf fish=it.next();
        	Log.i("Test", fish.getName()+" "+fish.getWeight()+" "+fish.getArea()+" "+fish.getIntro());
        }*/
        if(StaticData.FirstRun(this)){
        	Intent intent = new Intent();
        	intent.setClass(Main.this, GuideActivity.class);
        	startActivity(intent);
        }
         
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
