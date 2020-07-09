package cr.ac.tec.userObjects;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.DatatypeConverter;

import cr.ac.tec.resources.Trees;

public class User {
	
	public User(String email, String name, String lastname, int age){
		this.age = age;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		
	}
	
	private static SortAlgorithms sorter = new SortAlgorithms();
	SortingType sortType = SortingType.date;
	private boolean isChef = false;
	private ArrayList<String> notifications = new ArrayList<String>();
	private String imageBytes;
	private String profileDescription;
	private int age;
	private String name;
	private String lastname;
	private ArrayList<Recipe> myMenu;
	private ArrayList<User> followers = new ArrayList<User>();
	private ArrayList<Enterprise> companies = new ArrayList<Enterprise>();
	private ArrayList<User> following = new ArrayList<User>();
    private String email = null;
    private String password = null;
    
    /*
    private JsonArray ListUserToJson (ArrayList<User> list) {
    	JsonArrayBuilder arrayJson = Json.createArrayBuilder();
    	for(User pointer: list) {
    		arrayJson.add(pointer.getName());
    	}
    	return arrayJson.build();
    }
    */
    
    
    public  String getName() {
    	return this.name;
    }
    
    public String getLastName() {
    	return this.lastname;
    }

    public String getPassword() {
    	return this.password;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public ArrayList<User> getFollowers() {
    	return this.followers;
    }
    
    public ArrayList<User> getFollowing() {
    	return this.following;
    }
    
    public ArrayList<Enterprise> getCompanies() {
    	return this.companies;
    }
    
    public ArrayList<Recipe> getMyMenu() {
    	return this.myMenu;
    }
    
    public boolean getIsChef() {
    	return this.isChef;
    }
    
    public String getProfileDescription() {
    	return this.profileDescription;
    }
    
    public String getImageBytes() {
    	return this.imageBytes;
    }
    
    public int getAge() {
    	return this.age;
    }
    
    public ArrayList<String> getNotifications(){
    	return this.notifications;
    }
    
    
    public void setImageBytes(String bytes) {
    	imageBytes = bytes;
    }
    
    public void setPassword (String password) throws NoSuchAlgorithmException {
    	MessageDigest hashPassword = MessageDigest.getInstance("MD5");
    	hashPassword.update(password.getBytes());
    	String encryptedPassword = DatatypeConverter.printHexBinary(hashPassword.digest());
    	this.password = encryptedPassword;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    
    public void receiveNotification(String description) {
    	this.notifications.add(description);
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
    
    
    public void newRecipe(Recipe codeTemp) {
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
    
    public void createEnterprise(Enterprise company) {
    	company.addMembers(this);
    	this.addCompany(company);
    }
    
    public void addCompany(Enterprise company) {
    	this.companies.add(company);
    }
    
    
    /*
    public JsonObject getMenu() {
    	JsonArrayBuilder builder = Json.createArrayBuilder();
    	for(Recipe recipe : myMenu) {
    		builder.add(recipe.createJson());
    	}
    	return Json.createObjectBuilder().add("menu", builder.build()).build();
    }
    */
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
    
    public JsonObject getRecipeOrganized(SortingType type) {
    	JsonArrayBuilder builder = Json.createArrayBuilder();
    	arrayBy(type);
    	for(Recipe recipe : myMenu) {
			builder.add(recipe.createJson());
		}
		return Json.createObjectBuilder().add("recipes", builder.build()).build();
    }
    
    /*
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
    */

     
    
}
