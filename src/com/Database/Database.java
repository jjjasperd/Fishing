package com.Database;


 
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import com.Activity.R;
import com.Controller.Setting;
import com.Entity.FishInf;
import com.Entity.Fishes;
import com.Entity.UserInf;
import com.Entity.Users;

public class Database {
	private static String SDPath="";
	public static String userInfPath="/userInf.xml";
	public static String dirPath="/AndroidFish"; 
	public static String settingPath="/setting.xml";
	private static Context context=null;
	
	
	
	public Database(Context context){
		Database.context=context;
	}
	
	
	
	/**
	 * For loading the user information
	 * @param users
	 */
	public static void LoadUserInf(Users users){
		try{		 
			File file=new File(userInfPath);
			DocumentBuilder docBuilder =DocumentBuilderFactory.newInstance().newDocumentBuilder();  
			Document doc=docBuilder.parse(file);
			NodeList tagList=doc.getElementsByTagName("user");
			for(int j=0;j<tagList.getLength();j++){
				Node userNode = tagList.item(j);
				Node scores=userNode.getChildNodes().item(1);
				Node fishes=userNode.getChildNodes().item(3);
				UserInf userInf=new UserInf();
				userInf.setUserName(userNode.getAttributes().item(0).getNodeValue());
			
				//Add the score
				for(int i=1;i<=(scores.getChildNodes().getLength()-2);i=i+2){
					int score=Integer.parseInt(scores.getChildNodes().item(i).getTextContent());
					userInf.addScores(score);
					Log.i("Test","i: "+i+"  score: "+score);
				}
				
				//Add the fish
				for(int i=1;i<=(fishes.getChildNodes().getLength()-2);i=i+2){
					String fishName=fishes.getChildNodes().item(i).getTextContent();
					userInf.addFish(fishName);
					Log.i("Test", "fish name: "+fishName);
				}
			}
			
		}catch(Exception e){
			Log.e("Error", "LoadUserInf :"+e.getMessage());
		}
		
		Log.i("Database", "LoadUserInf");
	}
	
	
	public static void addUserInf(UserInf userInf){
		try{
			File file=new File(userInfPath);
			DocumentBuilder docBuilder =DocumentBuilderFactory.newInstance().newDocumentBuilder();  
			Document doc=docBuilder.parse(file);
			NodeList nodeList=doc.getElementsByTagName("users");
			Node root=nodeList.item(0);
			
			Element user=doc.createElement("user");
			
			user.setAttribute("name", userInf.getUserName());
			Element scores=doc.createElement("scores");
			Element fishes=doc.createElement("fishes");
			Iterator<Integer> it1=userInf.getScores().iterator();
			while(it1.hasNext()){
				Element score=doc.createElement("score");
				Text te=doc.createTextNode(it1.next().toString());
				score.appendChild(te);
				scores.appendChild(score);
			}
			Iterator<String> it2=userInf.getFish().iterator();
			while(it2.hasNext()){
				Element fish=doc.createElement("fish");
				Text te=doc.createTextNode(it2.next().toString());
				fish.appendChild(te);
				fishes.appendChild(fish);
			}
			user.appendChild(scores);
			user.appendChild(fishes);
			root.appendChild(user);
			 
			
			Transformer transformer=TransformerFactory.newInstance().newTransformer();
			Source source=new DOMSource(doc);
			StreamResult result=new StreamResult(file);
			 
		 
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
			transformer.transform(source, result);
			
			 
			
			
		}catch(Exception e){
			Log.e("Error", "addUserInf :"+e.getMessage());
		}
	}
	
	
	public static void addScore(String userName,int score){
		try{
			
			File file=new File(userInfPath);
			DocumentBuilder docBuilder =DocumentBuilderFactory.newInstance().newDocumentBuilder();  
			Document doc=docBuilder.parse(file);
			NodeList nodeList=doc.getElementsByTagName("user");
			for(int i=0;i<nodeList.getLength();i++){
				if(nodeList.item(i).getAttributes().item(0).getNodeValue().equals(userName)){
					Node scores=nodeList.item(i).getChildNodes().item(1);
					Log.i("Test", "Name: "+scores.getNodeName() );
					Text te=doc.createTextNode(Integer.toString(score));
					Node scoreNode=doc.createElement("score");
					scoreNode.appendChild(te);
					scores.appendChild(scoreNode);
				}
			}
			
			Transformer transformer=TransformerFactory.newInstance().newTransformer();
			Source source=new DOMSource(doc);
			StreamResult result=new StreamResult(file);
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
			transformer.transform(source, result);
			
		}catch(Exception e){
			Log.e("Error", "addScore: "+e.getMessage());
		}
	}
	
	
	public static void addFish(String userName,String fish){
		try{
			File file=new File(userInfPath);
			DocumentBuilder docBuilder =DocumentBuilderFactory.newInstance().newDocumentBuilder();  
			Document doc=docBuilder.parse(file);
			NodeList nodeList=doc.getElementsByTagName("user");
			for(int i=0;i<nodeList.getLength();i++){
				if(nodeList.item(i).getAttributes().item(0).getNodeValue().equals(userName)){
					Node fishes=nodeList.item(i).getChildNodes().item(3);
					Text te=doc.createTextNode(fish);
					Node fishNode=doc.createElement("fish");
					fishNode.appendChild(te);
					fishes.appendChild(fishNode);
				}
			}
			
			Transformer transformer=TransformerFactory.newInstance().newTransformer();
			Source source=new DOMSource(doc);
			StreamResult result=new StreamResult(file);
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
			transformer.transform(source, result);
			
		}catch(Exception e){
			Log.e("Error", "addFish: "+e.getMessage());
		}
	}
	
	/**
	 * For loading the fishes information
	 * @param fishes
	 */
	public static void LoadFishesInf(Fishes fishes){
		//Log.i("Test", "LoadFishesInf");
		int Area=-1;
		XmlResourceParser parser=context.getApplicationContext().getResources().getXml(R.xml.fishes);
		try{
			while(parser.getEventType()!=XmlResourceParser.END_DOCUMENT){
				//Log.i("Test","Text: "+parser.getText());
				FishInf fish=new FishInf();
				if(parser.getEventType()==XmlResourceParser.START_TAG){
					String name=parser.getName();
					//Log.i("Test", "LoadFishesInf "+name);
					if(name.equals("Area")){
						Area=parser.getAttributeIntValue(0, -1);
					}
					if(name.equals("fish")){
						fish.setName(parser.getAttributeValue(1));
						fish.setWeight(-1);
						fish.setArea(Area);
						parser.next();
						fish.setIntro(parser.getText());
						fishes.addFish(fish);
					}
				}			 
				parser.next();
			}
		}catch(Exception e){
			Log.i("Error", "Database LoadFishesInf: "+e.getMessage());
		}
		
		Log.i("Database", "FishesInf Finish");
	}
	
	
	/**
	 * Load the fishes information
	 * @param setting
	 */
	public static void LoadSettingInf(Setting setting){
		Log.i("Database", "SettingInf");
	}
	
	
	/**
	 * Initial the SD card path and the file path
	 */
	public static void iniSDPath(){
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			SDPath= Environment.getExternalStorageDirectory().getPath();
		}
		dirPath=SDPath+dirPath;
		userInfPath=dirPath+userInfPath;
		settingPath=dirPath+settingPath;
	}
	 
	
	/**
	 * Initial the user information file userInf.xml 
	 * @param context
	 * @param userXml
	 */
	public static void iniUserInf(Context context,File userXml){
		
		//For user information
			 try{
				
				 FileOutputStream fileIn=new FileOutputStream(userXml);
				 XmlSerializer userSerializer=Xml.newSerializer();
				 userSerializer.setOutput(fileIn,"UTF-8");
				 userSerializer.startDocument("UTF-8", true);
				 userSerializer.startTag(null,"users");
				 userSerializer.endTag(null, "users");
				 userSerializer.endDocument();
				 userSerializer.flush();
				 fileIn.close();
			  
				 
				 
			 }catch(IOException e){
				 Log.e("Error", "Initial user information failed: "+e.getMessage());
			 }catch(Exception e){
				 Log.e("Error", "Initial user information failed: "+e.getMessage());
			 }
		 
			
		}
	
	
	/**
	 * Create File if the userInf.xml and setting.xml are not exist
	 * @param context
	 */
	public static void creatFile(Context context){
		File userFile=new File(Database.userInfPath);
		File settingFile=new File(Database.settingPath);
		if(!userFile.exists()){// For user information
			try {
				userFile.createNewFile();
				Database.iniUserInf(context,userFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e("Error", "creatFile UserInf File: "+e.getMessage());
			} 
		}
		if(!settingFile.exists()){//for setting
			try{
				settingFile.createNewFile();
			}catch(IOException e){
				Log.e("Error", "creatFile Setting File: "+e.getMessage());
			}
			
		}
		 
	}
	
	/**
	 * Create directory AndroidFish if the directory is not create 
	 */
	public static void creatDir(){
		File file=new File(Database.dirPath);
		if(!file.exists()){
			file.mkdir();
		}
	}
	
}
