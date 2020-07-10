package cr.ac.tec.userObjects;

public class EnterpriseRecipe extends Recipe{
	
	private int price;
	private boolean isPublic;
	
	public boolean getIsPublic() {
		return this.isPublic;
	}
	
	public void setIsPublic(boolean publicRecipe) {
		this.isPublic = publicRecipe;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

}
