package com.Entity;

import com.Controller.StaticData;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Time implements Runnable{
	private Handler handler=null;
	private boolean flag=true;
	private int time=-1;
	private Message message=null;
	private int counter=-1;
	/*public TimeBar(Handler handler){
		this.handler=handler;
	}
	*/
	public Time(Handler handler,int time){
		this.handler=handler;
		this.time=time;
		message=new Message();
	}
	
	public void setFlag(boolean flag){
		this.flag=flag;
	}
	
	public boolean getFlag(){
		return this.flag;
	}
	
	public void setTime(int time){
		this.time=time;
	}
	public int getTime(){
		return this.time;
	}
	
	public void control(boolean flag){
		this.flag=flag;
		if(!this.flag){
			time=-1;
		    message=null;
		}
	}
	
	public int getCounter(){
		return counter;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(flag&&counter<time){			
			try {
				if(counter<=time){
					counter++;
					message.what=StaticData.TIME_CONTINUE;
					handler.sendMessage(message);
				}
				else{
					message.what=StaticData.TIME_STOP;
					handler.sendMessage(message);
					flag=false;
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
}
