package cr.ac.tec.userObjects;

import java.util.ArrayList;

public class Enterprise {
	
	private String enterpriseName;
	private double[] coordinates;
	private String contactInfo;
	private String imageBytes;
	private String operationHours;
	private  ArrayList<EnterpriseRecipe> recipes = new ArrayList<EnterpriseRecipe>();
	private ArrayList<String> members = new ArrayList<String>();
	private ArrayList<String> followers = new ArrayList<String>();
	
	public double[] getCoordinates() {
		return this.coordinates;
	}
	
	public String getImageBytes() {
		return imageBytes;
	}
	
	public ArrayList<String> getFollowers() {
		return followers;
	}

	public void setFollowers(ArrayList<String> followers) {
		this.followers = followers;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getOperationHours() {
		return operationHours;
	}

	public void setOperationHours(String operationHours) {
		this.operationHours = operationHours;
	}

	public ArrayList<EnterpriseRecipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(ArrayList<EnterpriseRecipe> recipes) {
		this.recipes = recipes;
	}

	public ArrayList<String> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}
	
	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}
	
	public void setImageBytes(String imageBytes) {
		this.imageBytes = imageBytes;
	}
	
	/**
	 * @param newMember
	 * funcion para agregar miembro
	 */
	public void addMembers(User newMember) {
		this.members.add(newMember.getEmail());
		newMember.receiveNotification("You are now a member of " + this.enterpriseName);
		newMember.addCompany(this);
	}
	
	/**
	 * @param follow
	 * funcion para agregar seguior
	 */
	public void addFollower(User follow) {
		this.followers.add(follow.getEmail());
	}
	
	public void addRecipe(EnterpriseRecipe recipe) {
		this.recipes.add(recipe);
	}
	
	
	
}
