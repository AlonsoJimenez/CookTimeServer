package cr.ac.tec.userObjects;

import java.util.ArrayList;
import java.util.Base64;

public class RecipeBuilder {
	
	public RecipeBuilder(boolean isCompany) {
		this.isCompany = isCompany;
		if(isCompany) {
			this.recipe = new EnterpriseRecipe();
		}else {
			this.recipe =  new Recipe();
		}
	}
	private boolean isCompany;
	
	private Recipe recipe;
	
	public RecipeBuilder withImage(String bytes) {
    	recipe.setImage(bytes);
    	return this;
    }
	
	public RecipeBuilder withName(String name) {
		recipe.setName(name);
		return this;
	}
	
	public RecipeBuilder withAuthor(User user) {
		recipe.setAuthor(user);
		return this;
	}
	
	public RecipeBuilder withDishType(String type) {
		recipe.setDishType(type);
		return this;
	}
	
	public RecipeBuilder withPortions(int port) {
		recipe.setPortionSize(port);
		return this;
	}
	
	public RecipeBuilder withMinutes(int min) {
		recipe.setPreparationMinutes(min);
		return this;
	}
	
	public RecipeBuilder withDifficulty(int difficulty) {
		recipe.setDifficulty(difficulty);
		return this;
	}
	
	public RecipeBuilder withTags(ArrayList<String> tags) {
		recipe.setTags(tags);
		return this;
	}
	
	public RecipeBuilder withIngridients(ArrayList<String> ingridients) {
		recipe.setIngridients(ingridients);
		return this;
	}
	
	public RecipeBuilder withSteps(ArrayList<String> steps) {
		recipe.setSteps(steps);
		return this;
	}
	
	public RecipeBuilder withPrice(int price) {
		if(isCompany) {
			((EnterpriseRecipe) recipe).setPrice(price);
		}
		return this;
	}
	
	public RecipeBuilder withIspublic(boolean isPublic) {
		if(isCompany) {
			((EnterpriseRecipe) recipe).setIsPublic(isPublic);
		}
		return this;
	}
	
	public Recipe build() {
		return this.recipe;
	}
	
	
}
