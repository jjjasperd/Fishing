package com.Entity;

import java.util.ArrayList;
import java.util.List;


public class UserInf {
	private String userName=null;
	private List<Integer> scores;
	private List<String> fishList=null;
	
	public UserInf(){
		super();
		scores=new ArrayList<Integer>();
		fishList=new ArrayList<String>();
	}
	public UserInf(String userName){
		this.userName=userName;
		scores=new ArrayList<Integer>();
		fishList=new ArrayList<String>();
	}
	
	/**
	 * set userName and get userName
	 * @param userName
	 */
	public void setUserName(String userName){
		this.userName=userName;
	}
	public String getUserName(){
		return this.userName;
	}
	
	/**
	 * 未完成，读取数据库
	 */
	public void addScores(int score){ 
		scores.add(score);
	}
	
	public List<Integer> getScores(){
		return this.scores;
	}
	
	/**
	 * Add fish that user got 
	 * @param fish
	 */
	public void addFish(String fish){	 
		fishList.add(fish);
	}
	
	/**
	 * Get Fish List
	 */
	public List<String> getFish(){
		return this.fishList;
	}
	
	
}
