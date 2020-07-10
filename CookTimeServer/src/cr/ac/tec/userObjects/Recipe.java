package cr.ac.tec.userObjects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import cr.ac.tec.resources.Trees;

public class Recipe {
	
	private String author;
	private String imageBytes;
	private int stars;
	private Date publish;
	private String dishName;
	private int portionsSize;
	private int preparationMinutes;
	private String dishType;
	private ArrayList<String> tags = new ArrayList<String>();
	private ArrayList<String> ingridients = new ArrayList<String>();
	private ArrayList<String> steps = new ArrayList<String>();
	private int difficulty;
	
	public String getImageBytes() {
		return imageBytes;
	}

	public void setImageBytes(String imageBytes) {
		this.imageBytes = imageBytes;
	}

	public Date getPublish() {
		return publish;
	}

	public void setPublish(Date publish) {
		this.publish = publish;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public int getPortionsSize() {
		return portionsSize;
	}

	public void setPortionsSize(int portionsSize) {
		this.portionsSize = portionsSize;
	}

	public String getAuthor() {
		return author;
	}

	public int getPreparationMinutes() {
		return preparationMinutes;
	}

	public String getDishType() {
		return dishType;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public ArrayList<String> getIngridients() {
		return ingridients;
	}

	public ArrayList<String> getSteps() {
		return steps;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
	
	public void setName(String name) {
		this.dishName = name;
	}	
	
	public void setAuthor(String user) {
		this.author = user;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	
	
	public void setPreparationMinutes(int minutes) {
		this.preparationMinutes = minutes;
	}
	
	public void setDishType(String type) {
		this.dishType = type;
	}
	
	public void setIngridients(ArrayList<String> list) {
		this.ingridients = list;
	}
	
	public void setTags(ArrayList<String> list) {
		this.tags = list;
	}
	
	public void setSteps(ArrayList<String> list) {
		this.steps = list;
	}
	
	public int getDifficulty() {
		return this.difficulty;
	}	
	
	public void updateStars(int grade) {
		this.stars = (this.stars+grade)/2;
		Trees.getTrees().profileTree.find(author).receiveNotification("Your recipe "+ dishName +" has been reviewed");
	}
	
	public int getDay() {
		return 0;
	}
	
	public int getStars() {
		return this.stars;
	}
	

}
