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
	
	private static SortAlgorithms sorter = new SortAlgorithms();
	SortingType sortType = SortingType.date;
	private boolean isChef = false;
	private ArrayList<String> notifications = new ArrayList<String>();
	private String imageBytes = "";
	private String profileDescription;
	private int age;
	private String name;
	private String lastname;
	private ArrayList<String> myMenu = new ArrayList<String>();
	private ArrayList<String> recipes = new ArrayList<String>();
	private ArrayList<String> followers = new ArrayList<String>();
	private ArrayList<Enterprise> companies = new ArrayList<Enterprise>();
	private ArrayList<String> following = new ArrayList<String>();
	private ArrayList<String> followingComp = new ArrayList<String>();
	private String email;
    private String password;
    
    public ArrayList<String> getRecipes(){
    	return recipes;
    }
	
    public ArrayList<String> getFollowingComp() {
		return followingComp;
	}

	public void setFollowingComp(ArrayList<String> followingComp) {
		this.followingComp = followingComp;
	}

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
    
    public ArrayList<String> getFollowers() {
    	return this.followers;
    }
    
    public ArrayList<String> getFollowing() {
    	return this.following;
    }
    
    public ArrayList<Enterprise> getCompanies() {
    	return this.companies;
    }
    public ArrayList<String> getMyMenu() {
    	return this.myMenu;
    }
    
    /**
     * @return valores de recetas en listas
     */
    public ArrayList<Recipe> feed() {
    	ArrayList<Recipe> toReturn = new ArrayList<Recipe>();
    	for(String order: myMenu) {
    		toReturn.add(Trees.getTrees().recipeTree.find(order));
    	}
    	return toReturn;
    }
    
    /**
     * @return el menu del usuario
     */
    public ArrayList<Recipe> own(){
    	ArrayList<Recipe> toReturn = new ArrayList<Recipe>();
    	for(String order: recipes) {
    		toReturn.add(Trees.getTrees().recipeTree.find(order));
    	}
    	return toReturn;
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
    
    public void setRecipes(ArrayList<String> recipes) {
    	this.recipes = recipes;
    }
    
    /**
     * @param password
     * @throws NoSuchAlgorithmException
     * genera contrasena encriptada
     */
    public void setPasswordAux (String password) throws NoSuchAlgorithmException {
    	MessageDigest hashPassword = MessageDigest.getInstance("MD5");
    	hashPassword.update(password.getBytes());
    	String encryptedPassword = DatatypeConverter.printHexBinary(hashPassword.digest());
    	this.password = encryptedPassword;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setLastname(String lastname) {
    	this.lastname = lastname;
    }
    
    public void setAge(int age) {
    	this.age = age;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public void receiveNotification(String description) {
    	this.notifications.add(description);
    }
    
        
    public void setNewFollower(User follower) {
    	this.followers.add(follower.getEmail());
    }
    
    public void setProfileDescription(String pp) {
    	this.profileDescription = pp;
    }
	
    public void follow(User toFollow) {
    	following.add(toFollow.getEmail());
    	toFollow.setNewFollower(this);
    	toFollow.receiveNotification("The user " + this.name +" " +this.lastname + " has started following you");
    }
    
    public void newFeed(Recipe recipeCode) {
    	myMenu.add(recipeCode.getDishName());
    	this.arrayBy(SortingType.defaultType);
    }
    
    
    public void newRecipe(Recipe codeTemp) {
    	if(followers.size()!=0) {
    		for(String eachUser: followers) {
    			Trees.getTrees().profileTree.find(eachUser).newFeed(codeTemp);
    		}
    	}
    	this.newFeed(codeTemp);
    }
    
    public void addOwn(String name) {
    	this.recipes.add(name);
    }
    
    public void arrayBy(SortingType type) {
    	SortingType temp = this.sortType;
    	this.sortType = type;
    	if(type == SortingType.stars) {
    		this.myMenu = sorter.quickSort(this.feed());
    	}else if(type == SortingType.date) {
    		this.myMenu = sorter.bubbleSort(this.feed());
    	}else if(type == SortingType.defaultType){
    		this.sortType = temp;
    		this.myMenu = sorter.insertionSort(this.feed(), temp);
    	}else {
    		this.myMenu = sorter.radixSort(this.feed());
    	}
    }
    
    public void createEnterprise(Enterprise company) {
    	company.addMembers(this);
    	this.addCompany(company);
    }
    
    public void addCompany(Enterprise company) {
    	this.companies.add(company);
    }
    
    public boolean hasRecipe(String recipe) {
    	for(Recipe i : this.feed()) {
    		if(i.getDishName().equals(recipe)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean hasCompany(String name) {
    	for(Enterprise i : this.companies) {
    		if(i.getEnterpriseName().equals(name)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public void followNewComp(Enterprise company) {
    	company.addFollower(this);
    	this.followingComp.add(company.getEnterpriseName());
    }   
    
}
