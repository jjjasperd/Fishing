package com.Activity;



import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Controller.StaticData;
import com.Entity.FishInf;

public class Atlantic extends Activity {
	private int fishcount = 0;    //记录钓到鱼的分数
	private int recLen = 90;
	 FishInf fish=null;
    private TextView txtView;
    ProgressBar bar1;
    ProgressBar bar2;
    ProgressBar bar3;
    private int i = 10;
    private int j = 0;
    boolean runflag = true;
    private AnimationDrawable animationDrawable;
    final int MAX_SEC = 5;
    
    public int[] hasfish = new int[] { 1, 2, 3 }; // 创建一个数组 如果随机到1就有鱼
	@Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arctic);
		StaticData.beginGame(1);
		new Thread(new MyThread()).start();     
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.atlantic, menu);
		return true;
	}
	final Handler handler = new Handler(){          // handle  
        public void handleMessage(Message msg){  

            if(msg.what==1) {    
                recLen--; 

                
                //txtView.setText("" + recLen);   //倒计时的框框 可以改成其他的

        		
            	//txtView.setText("" + i);
            	bar1 = (ProgressBar) findViewById(R.id.progress);
            	bar1.incrementProgressBy(-1);
            	setProgress(100 * bar1.getSecondaryProgress());
            	 
                if(recLen == 0){
   					new AlertDialog.Builder(Atlantic.this)  
                	               .setTitle("时间到！")
                	               .setMessage("回到主界面！")
                	               .setPositiveButton("确定", new OnClickListener(){
                	            	   public void onClick(DialogInterface arg0,int arg1){
                	            	   //跳回主界面
                							Intent intent = new Intent();
                							intent.setClass(Atlantic.this, Main.class);
                							startActivity(intent);
                							Atlantic.this.finish();
                	            	   
                	            	   }
                	               })
                	               .show();   				 
                }

            }  
            super.handleMessage(msg);  
        }  
    };  
    
	final Handler handler2 = new Handler(){          // handle  
        @SuppressWarnings("deprecation")
		public void handleMessage(Message msg){  
            if(msg.what==1) {
        		animationDrawable = (AnimationDrawable) getResources().getDrawable(
        				R.anim.havefish);
        		ImageView imageview = (ImageView) findViewById(R.id.imageview);
        		imageview.setBackgroundDrawable(animationDrawable);
        		animationDrawable.start();
            		i--;    
                	bar2 = (ProgressBar) findViewById(R.id.progress1);
                	bar2.setVisibility(View.VISIBLE);
                	bar2.incrementSecondaryProgressBy(-1);
                	setSecondaryProgress(10 * bar2.getSecondaryProgress());
                	j++;
                	bar3 = (ProgressBar) findViewById(R.id.progress2);
                	bar3.setVisibility(View.VISIBLE);
                	bar3.incrementSecondaryProgressBy(+30);
                	setSecondaryProgress(10 * bar2.getSecondaryProgress());
                	if(j == MAX_SEC){
                		bar3.incrementSecondaryProgressBy(-180);
                		j=0;
                	}
                	if(i == 0 ){
                		bar2.setVisibility(View.INVISIBLE);
                		bar3.setVisibility(View.INVISIBLE);
                		runflag=true;
                		i=10;
                		bar2.incrementSecondaryProgressBy(+10);
                		animationDrawable.stop();
            	}
            		imageview.setOnTouchListener(new OnTouchListener() {

            			@Override
            			public boolean onTouch(View v, MotionEvent event) {
            				bar2.setVisibility(View.INVISIBLE);
            				bar3.setVisibility(View.INVISIBLE);

            				//判断是否钓到
            				if(j>=4){
            				//显示钓到的鱼在这里
            					fish=StaticData.randomFish(3);
               					new AlertDialog.Builder(Atlantic.this)  
             	               .setTitle("钓到" + fish.getName())
             	               .setMessage(fish.getIntro())
             	               .setPositiveButton("确定", new OnClickListener(){
             	            	   public void onClick(DialogInterface arg0,int arg1){
             	            	   //继续
             							
             	            	   
             	            	   }
             	               })
             	               .show();
            				Toast.makeText(Atlantic.this, fish.getName(),
            						Toast.LENGTH_SHORT).show(); 
            				animationDrawable.stop();
            				animationDrawable = (AnimationDrawable) getResources().getDrawable(
            						R.anim.fish);
            				ImageView imageview1 = (ImageView) findViewById(R.id.imageview1);
            				imageview1.setBackgroundDrawable(animationDrawable);
            				
            				animationDrawable.start();
            				}
            				else{
            					Toast.makeText(Atlantic.this, "没钓到！",
                						Toast.LENGTH_SHORT).show(); 
            					
            				}
                    		i=10;
                    		bar2.incrementSecondaryProgressBy(+10);
                    		animationDrawable.stop();
            				//runflag =true;
							return false;
							
            			}
            		});
            }  
            super.handleMessage(msg);  
        }  
    };
    public class MyThread implements Runnable{      // thread  
        @Override  
        public void run(){  
        	int index = 0;//设置一个数用于随机
            for(int k= 90; k>0 ;k--){  
            	index = new Random().nextInt(hasfish.length);
                try{
                		
                		Thread.sleep(1000);     // sleep 1000ms  
                		Message message = new Message();  
                		message.what = 1; 
                		if(index == 1 && runflag==true){       //判断 index 为1就表示有鱼可以钓
                			
                			new Thread(new HaveFish()).start();   // 保存是否随机到鱼
                		    
                		}
                		handler.sendMessage(message); 

                    
                }catch (Exception e) {  
                }  
            }  
        }  
    } 
    public class HaveFish implements Runnable{      // thread  
        @Override  
        public void run(){  
        	
            runflag=false;
			for(int k=10; k>0; k--){  
                try{
                		
                		Thread.sleep(1000);     // sleep 1000ms  
                		Message m2 = new Message();  
                		m2.what = 1; 
                		handler2.sendMessage(m2); 
                }catch (Exception e) {  
                } 

            }  
        }  
    } 

}
