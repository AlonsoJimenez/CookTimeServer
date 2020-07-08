package cr.ac.tec.userObjects;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Base64;
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
	
	private byte[] imageBytes;
	private static SortAlgorithms sorter = new SortAlgorithms();
	private boolean isChef = false;
	SortingType sortType = SortingType.date;
	private ArrayList<String> notifications = new ArrayList<String>();
	private String profileDescription;
	private int age;
	private String name;
	private String lastname;
	private ArrayList<Recipe> myMenu;
	private ArrayList<User> followers = new ArrayList<User>();
	private ArrayList<User> following = new ArrayList<User>();
    private String email = null;
    private String password = null;
    
    
    private JsonArray ListUserToJson (ArrayList<User> list) {
    	JsonArrayBuilder arrayJson = Json.createArrayBuilder();
    	for(User pointer: list) {
    		arrayJson.add(pointer.getName());
    	}
    	return arrayJson.build();
    }
    
    public void setImage(String bytes) {
    	imageBytes = Base64.getDecoder().decode(bytes);
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
        		add("isChef", Boolean.toString(isChef)).
        		add("lastname", lastname).
        		add("age", Integer.toString(age)).
        		add("followers", ListUserToJson(this.followers)).
        		add("following", ListUserToJson(this.following)).
        		add("profilePicture", Base64.getEncoder().encodeToString(imageBytes)).
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
    
    public void newFeed(Recipe recipeCode) {
    	myMenu.add(recipeCode);
    	this.arrayBy(SortingType.defaultType);
    }
    
    public void newRecipe() {
    	Recipe codeTemp = Trees.recipeTree.newRecipe();
    	for(User eachUser: followers) {
    		eachUser.newFeed(codeTemp);
    	}
    	this.newFeed(codeTemp);
    }
    
    public void arrayBy(SortingType type) {
    	SortingType temp = this.sortType;
    	this.sortType = type;
    	if(type == SortingType.stars) {
    		this.myMenu = sorter.quickSort(myMenu);
    	}else if(type == SortingType.date) {
    		this.myMenu = sorter.bubbleSort(myMenu);
    	}else if(type == SortingType.defaultType){
    		this.sortType = temp;
    		this.myMenu = sorter.insertionSort(myMenu, temp);
    	}else {
    		this.myMenu = sorter.radixSort(myMenu);
    	}
    }
    
    public JsonObject getRecipeOrganized(SortingType type) {
    	JsonArrayBuilder builder = Json.createArrayBuilder();
    	arrayBy(type);
    	for(Recipe recipe : myMenu) {
			builder.add(recipe.createJson());
		}
		return Json.createObjectBuilder().add("recipes", builder.build()).build();
    }
    
    
    
    public JsonObject getMenu() {
    	JsonArrayBuilder builder = Json.createArrayBuilder();
    	for(Recipe recipe : myMenu) {
    		builder.add(recipe.createJson());
    	}
    	return Json.createObjectBuilder().add("menu", builder.build()).build();
    }
    
    

	
    
    
}
