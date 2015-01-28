package com.Entity;

public class FishInf {
	private String Name;
	private int Area;
	private int weight;
	private String intro=null;
	/**
	 *Constructor 
	 */
	public FishInf(){
		super();
	}
	
	/*public FishInf(String Name){
		this.Name=Name;
	}*/

	
	/**
	 * Set Name Or Get Name
	 * @param Name
	 */
	public void setName(String Name){
		this.Name=Name;
	}

	public String getName(){
		return this.Name;
	}
	
	/**
	 * Set Area or Get Area
	 * @param Area
	 */
	public void setArea(int Area){
		 this.Area=Area;
	}
	
	public int getArea(){
		return Area;
	}
	
	/**
	 * Set	Weight or Get Weight
	 * @param weight
	 */
	public void setWeight(int weight){
		this.weight=weight;
	}
	public int getWeight(){
		return this.weight;
	}

	/**
	 * set and get introduction of the fish
	 * @param intro
	 */
	public void setIntro(String intro){
		this.intro=intro;
	}
	public String getIntro(){
		return this.intro;
	}
	
	/*public int getScore(){
		 
		  // 改分数规则这里改！
		 
		return (weight*5);
	}*/
	
	
	
}
