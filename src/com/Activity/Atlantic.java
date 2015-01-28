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
	private int fishcount = 0;    //��¼������ķ���
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
    
    public int[] hasfish = new int[] { 1, 2, 3 }; // ����һ������ ��������1������
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

                
                //txtView.setText("" + recLen);   //����ʱ�Ŀ�� ���Ըĳ�������

        		
            	//txtView.setText("" + i);
            	bar1 = (ProgressBar) findViewById(R.id.progress);
            	bar1.incrementProgressBy(-1);
            	setProgress(100 * bar1.getSecondaryProgress());
            	 
                if(recLen == 0){
   					new AlertDialog.Builder(Atlantic.this)  
                	               .setTitle("ʱ�䵽��")
                	               .setMessage("�ص������棡")
                	               .setPositiveButton("ȷ��", new OnClickListener(){
                	            	   public void onClick(DialogInterface arg0,int arg1){
                	            	   //����������
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

            				//�ж��Ƿ����
            				if(j>=4){
            				//��ʾ��������������
            					fish=StaticData.randomFish(3);
               					new AlertDialog.Builder(Atlantic.this)  
             	               .setTitle("����" + fish.getName())
             	               .setMessage(fish.getIntro())
             	               .setPositiveButton("ȷ��", new OnClickListener(){
             	            	   public void onClick(DialogInterface arg0,int arg1){
             	            	   //����
             							
             	            	   
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
            					Toast.makeText(Atlantic.this, "û������",
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
        	int index = 0;//����һ�����������
            for(int k= 90; k>0 ;k--){  
            	index = new Random().nextInt(hasfish.length);
                try{
                		
                		Thread.sleep(1000);     // sleep 1000ms  
                		Message message = new Message();  
                		message.what = 1; 
                		if(index == 1 && runflag==true){       //�ж� index Ϊ1�ͱ�ʾ������Ե�
                			
                			new Thread(new HaveFish()).start();   // �����Ƿ��������
                		    
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
