package com.Entity;

import java.util.ArrayList;
import java.util.List;

public class Fishes {
	//private List<FishInf> fishes=null;
	private List<FishInf> fishes=null;
 
	private int index=0;//record which area has been chosen
	
	public Fishes(){
		super();
		fishes=new ArrayList<FishInf>();	
	}
	
	 
	/**
	 * Initial the list from the Database and get the list
	 */
	public void setFishList(List<FishInf> fishes){
		  this.fishes=fishes;
	}
	
	/*public List<FishInf> getList(){
		return this.fishes;
	}
	
	*/
	
	
	 
	
	/*public FishInf randomFish(){	
		FishInf fish=null;
		fish=fishes.get(getRandom());
		return fish;
	}
	
	private int getRandom(){
		int ranNum;
		int size=fishes.size();
		ranNum=(int)(Math.random()*size);
		Log.i("Fishes", "getRandom :"+ranNum);
		return ranNum;
	}*/
	
	public List<FishInf> getFishes(int index){
		List<FishInf> fishArea=new ArrayList<FishInf>();
		for(FishInf fish:fishes){
			if(fish.getArea()==index){
				fishArea.add(fish);
			}
		}
		 return fishArea;
			
	}
	
	public void addFish(FishInf fish){
		fishes.add(fish);
	}
	
	/**
	 * Set  area 
	 * @param index
	 */
	/*public void setIndex(int index){
		this.index=index;
	}
	public int getIndex(){
		return this.index;
	}*/
	
}
