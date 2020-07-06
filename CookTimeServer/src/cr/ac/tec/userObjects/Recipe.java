package cr.ac.tec.userObjects;

import java.util.ArrayList;
import java.util.Date;

public class Recipe {
	
	private boolean companyRecipe;
	private int code;
	private User author;
	private int stars;
	private Date publish;
	private String name;
	private String dishName;
	private int portionsSize;
	private int preparationMinutes;
	private String dishType;
	private ArrayList<String> tags = new ArrayList<String>();
	private ArrayList<String> ingridients = new ArrayList<String>();
	private ArrayList<String> steps = new ArrayList<String>();
	private double price = 0;
	
	public  Recipe(int code) {
		this.code = code;
	}
	
	public void updateStars(int grade) {
		this.stars = (this.stars+grade)/2;
		author.receiveNotification("Your recipe "+ dishName +" has been reviewed");
	}
	
	public int getCode() {
		return this.code;
	}
	
	
	
	
	
	
}
