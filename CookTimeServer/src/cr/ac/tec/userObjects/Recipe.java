package cr.ac.tec.userObjects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class Recipe {
	
	private int difficulty;
	private User author;
	private byte[] imageBytes;
	private int stars;
	private Date publish;
	private String dishName;
	private int portionsSize;
	private int preparationMinutes;
	private String dishType;
	private ArrayList<String> tags = new ArrayList<String>();
	private ArrayList<String> ingridients = new ArrayList<String>();
	private ArrayList<String> steps = new ArrayList<String>();
	
	public void setImage(String bytes) {
    	imageBytes = Base64.getDecoder().decode(bytes);
    }
	
	public void setName(String name) {
		this.dishName = name;
	}	
	
	public void setAuthor(User user) {
		this.author = user;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public void setPortionSize(int size) {
		this.portionsSize = size;
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
		author.receiveNotification("Your recipe "+ dishName +" has been reviewed");
	}
	
	public int getDay() {
		return 0;
	}
	
	public int getStars() {
		return this.stars;
	}
	
	public JsonObject createJson() {
		return Json.createObjectBuilder().
		add("difficulty", Integer.toString(difficulty)).
		add("author", author.getName()).
		add("rating",Integer.toString(stars)).
		add("dishName", dishName).
		add("portionSize", Integer.toString(portionsSize)).
		add("minutes", Integer.toString(preparationMinutes)).
		add("dishType", dishType).
		add("tags", jsonList(tags)).
		add("ingridients", jsonList(ingridients)).
		add("picture", Base64.getEncoder().encodeToString(imageBytes)).
		add("steps", jsonList(steps)).build();
	}
	
	private JsonArray jsonList(ArrayList<String> list) {
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for(String index: list) {
			builder.add(index);
		}
		return builder.build();
	}
	
	

	
	
	
	
	
	
	
	
	
}
