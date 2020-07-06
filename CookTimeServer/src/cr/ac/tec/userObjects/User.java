package cr.ac.tec.userObjects;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import cr.ac.tec.resources.Trees;

public class User implements Comparable<String>{
	
	public User(String email, String password, String name, String lastname, int age){
		this.age = age;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
	private boolean isChef;
	private ArrayList<String> notifications = new ArrayList<String>();
	private String profileDescription;
	private int age;
	private String name;
	private String lastname;
	private ArrayList<Integer> myMenu;
	private ArrayList<User> followers = new ArrayList<User>();
	private ArrayList<User> following = new ArrayList<User>();
    private String email = null;
    private String password = null;
    
    private JsonArray ListStringToJson (ArrayList<String> list) {
    	JsonArrayBuilder arrayJson = Json.createArrayBuilder();
    	for(String pointer: list) {
    		arrayJson.add(pointer);
    	}
    	return arrayJson.build();
    }
    
    private JsonArray ListUserToJson (ArrayList<User> list) {
    	JsonArrayBuilder arrayJson = Json.createArrayBuilder();
    	for(User pointer: list) {
    		arrayJson.add(pointer.getName());
    	}
    	return arrayJson.build();
    }
    public  String getName() {
    	return this.name;
    }

    public String getPassword() {
    	return this.password;
    }
    
    public void setPassword (String password) {
    	this.password = password;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public JsonObject checkNotifications(){
    	if(notifications.isEmpty()) {
    		return null;
    	}else {
    		JsonArrayBuilder notifcationsArray = Json.createArrayBuilder();
    		JsonObjectBuilder notify = Json.createObjectBuilder();
    		for(String note: notifications) {
    			notifcationsArray.add(note);
    		}
    		notifications.clear();
    		return notify.add("notifications", notifcationsArray.build()).build();
    	}
    }
    
    public void receiveNotification(String description) {
    	this.notifications.add(description);
    }
    
    public int compareTo (String compare) {
        for(int index = 0; index < this.email.length() ; index++) {
        	if(index <= compare.length()) {
        		return 1;
        	}
            if(Character.getNumericValue(compare.charAt(index))>Character.getNumericValue(this.email.charAt(index))) {
                return -1;
            }else if(Character.getNumericValue(compare.charAt(index))<Character.getNumericValue(this.email.charAt(index))) {
                return 1;
            }
        }return 0;
    }
    
    public  JsonObject getUserJSON()  {
        return Json.createObjectBuilder().add("email", email).
        		add("name",name).
        		add("lastname", lastname).
        		add("age", Integer.toString(age)).
        		add("followers", ListUserToJson(this.followers)).
        		add("profileDescription", profileDescription).build();
    }

    public  String getUserString() {
        String jsonString = null;
        try(Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(getUserJSON());
            jsonString = writer.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            jsonString = "User does not exist";
        }
        return jsonString;
    }
    
    public void setNewFollower(User follower) {
    	this.followers.add(follower);
    }
	
    public void follow(User toFollow) {
    	following.add(toFollow);
    	toFollow.setNewFollower(this);
    	toFollow.receiveNotification("The user " + this.name +" " +this.lastname + " has started following you");
    }
    
    public void newFeed(int recipeCode) {
    	myMenu.add(recipeCode);
    }
    
    public void newRecipe() {
    	int codeTemp = Trees.recipeTree.newRecipe();
    	for(User eachUser: followers) {
    		eachUser.newFeed(codeTemp);
    	}
    	this.newFeed(codeTemp);
    }
    

	
    
    
}
