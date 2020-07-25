package cr.ac.tec.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import cr.ac.tec.userObjects.Enterprise;
import cr.ac.tec.userObjects.Recipe;
import cr.ac.tec.userObjects.User;

@Path("recipe/")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class RecipeData {
	/**
	 * @param username
	 * @param name
	 * @param recipe
	 * @return actualiza la receta de un usuario o compania
	 */
	@Path("edit/{name}")
	@PUT
	public Response updateRecipe(@HeaderParam("x-user")String username, @PathParam("name") String name, Recipe recipe) {
		User temp = Trees.getTrees().profileTree.find(username);
		if(temp.hasRecipe(name)){
			Trees.getTrees().recipeTree.find(name).setAuthor(recipe.getAuthor());
			Trees.getTrees().recipeTree.find(name).setDifficulty(recipe.getDifficulty());
			Trees.getTrees().recipeTree.find(name).setDishName(recipe.getDishName());
			Trees.getTrees().recipeTree.find(name).setDishType(recipe.getDishType());
			Trees.getTrees().recipeTree.find(name).setImageBytes(recipe.getImageBytes());
			Trees.getTrees().recipeTree.find(name).setIngridients(recipe.getIngridients());
			Trees.getTrees().recipeTree.find(name).setPortionsSize(recipe.getPortionsSize());
			Trees.getTrees().recipeTree.find(name).setPreparationMinutes(recipe.getPreparationMinutes());
			Trees.getTrees().recipeTree.find(name).setSteps(recipe.getSteps());
			Trees.getTrees().recipeTree.find(name).setTags(recipe.getTags());
		}
		return Response.ok().build();
	}

}
