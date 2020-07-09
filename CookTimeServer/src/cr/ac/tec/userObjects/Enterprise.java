package cr.ac.tec.userObjects;

import java.util.ArrayList;

public class Enterprise {
	
	private String enterpriseName;
	private String contactInfo;
	private String operationHours;
	private  ArrayList<EnterpriseRecipe> recipes = new ArrayList<EnterpriseRecipe>();
	private ArrayList<User> members = new ArrayList<User>();
	
	public void addMembers(User newMember) {
		this.members.add(newMember);
		newMember.receiveNotification("You are now a member of " + this.enterpriseName);
		newMember.addCompany(this);
	}
	
}
