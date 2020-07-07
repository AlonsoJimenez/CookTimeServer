package cr.ac.tec.userObjects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class Recipe {
	
	private int difficulty;
	private int code;
	private User author;
	private int stars;
	private Date publish;
	private String dishName;
	private int portionsSize;
	private int preparationMinutes;
	private String dishType;
	private ArrayList<String> tags = new ArrayList<String>();
	private ArrayList<String> ingridients = new ArrayList<String>();
	private ArrayList<String> steps = new ArrayList<String>();
	
	
	public int getDifficulty() {
		return this.difficulty;
	}	
	
	public void updateStars(int grade) {
		this.stars = (this.stars+grade)/2;
		author.receiveNotification("Your recipe "+ dishName +" has been reviewed");
	}
	
	public int getCode() {
		return this.code;
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
