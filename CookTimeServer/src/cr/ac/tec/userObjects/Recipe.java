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
	private double stars;
	private int[] publish;
	private String dishName;
	private int portionsSize;
	private int preparationMinutes;
	private String dishType;
	private ArrayList<String> tags = new ArrayList<String>();
	private ArrayList<String> ingridients = new ArrayList<String>();
	private ArrayList<String> steps = new ArrayList<String>();
	private ArrayList<String> comments = new ArrayList<String>();
	private int difficulty;
	
	
	public ArrayList<String> getComments() {
		return comments;
	}

	public void setComments(ArrayList<String> comments) {
		this.comments = comments;
	}
	
	public String getImageBytes() {
		return imageBytes;
	}

	public void setImageBytes(String imageBytes) {
		this.imageBytes = imageBytes;
	}

	public int[] getPublish() {
		return publish;
	}

	public void setPublish(int[] publish) {
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

	public void setStars(double stars) {
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
	
	/**
	 * @param grade
	 * algoritmo para calificar receta
	 */
	public void updateStars(double grade) {
		this.stars = (this.stars+grade)/2;
		Trees.getTrees().profileTree.find(author).receiveNotification("Your recipe "+ dishName +" has been reviewed");
	}
	
	/**
	 * @return
	 * devuelve el dia en calendario cristiano de publicacion
	 */
	public int publicationDay() {
		return publish[0] + publish[1]*30 + publish[2]*365;
	}
	
	public double getStars() {
		return this.stars;
	}
	
	/**
	 * @param comment
	 * agrega comentario
	 */
	public void addComment(String comment) {
		this.comments.add(comment);
	}
	

}
