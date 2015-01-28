package com.Controller;

import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.Database.Database;
import com.Entity.FishInf;
import com.Entity.Fishes;
import com.Entity.UserInf;
import com.Entity.Users;


@SuppressLint("ShowToast") public class StaticData {
	public static  Fishes fishes=null;
	public static Setting setting=null;
	public static Users users=null;
	private static StaticData data=new StaticData();
	//private static boolean firstRun=true;
	private static List<FishInf> fishList=null;
	private static int index=0;
	public static final int TIME_STOP = 0x108;  
    public static final int TIME_CONTINUE= 0x109;  
	private StaticData(){
		//data=new StaticData();
		//Log.i("Test", "Static Data");
	}
	
	
	public static StaticData getData(){
		return data;
	}
	
	
	/**
	 * For initial Game
	 */
	public static void IniGame(Context context){
		setting=new Setting();
		users=new Users();
		fishes=new Fishes();
		Database.iniSDPath();
		CheckFile(context);
		 
		/*if(FirstRun(context)){
			Log.i("Test", "First Run");
		}
		else{
			Log.i("Test", "Not First Run");
		}*/
	}
	
	
	
	/**
	 * initial user information
	 */
	public static void LoadUserInf(){
		Database.LoadUserInf(users);
	}
	
	public static void addUserInf(UserInf userInf){
		Database.addUserInf(userInf);	
	}
	
	public static void addScoreInf(String userName,int score){
		Database.addScore(userName,score);
	}
	
	public static void addFishInf(String userName,String fish){
		Database.addFish(userName, fish);
	}
	
	/**
	 * initial fishes
	 */
	public static void LoadFishes(){
		Database.LoadFishesInf(fishes);
	}
	
	/**
	 * initial setting
	 */
	public static void LoadSetting(){
		Database.LoadSettingInf(setting);
	}
	
	/**
	 * Check if it is first time for running  
	 * @param context
	 * @return
	 */
	public static boolean FirstRun(Context context){
		boolean firstRun=false;
		SharedPreferences sp=context.getSharedPreferences("FirstRun", 0);
		firstRun=sp.getBoolean("firstRun",true);
		if(firstRun){
			Editor editor=sp.edit();
			editor.putBoolean("firstRun", false);
			editor.commit();
		}
		return firstRun;
	}
	
	/**
	 * Check  whether the File and the directory are exist 
	 * @param context
	 */
	private static void CheckFile(Context context){
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			Database.creatDir();
			Database.creatFile(context);
			Log.i("Test", "Create file finished!");
		}
		
		else{
			Toast.makeText(context, "SD card cannot found", Toast.LENGTH_SHORT);
		}
	}
	
	public static void beginGame(int index){
		if(fishes!=null){
			fishList=fishes.getFishes(index);
		}
	}
	
	public static FishInf randomFish(int index){
		FishInf fish=null;
		Random random=new Random();
		int num=Math.abs(random.nextInt()%fishList.size());
		fish=fishList.get(num);
		return fish;
		
	}
	
	
	
	
	
}
