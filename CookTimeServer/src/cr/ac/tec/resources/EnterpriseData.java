package cr.ac.tec.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import cr.ac.tec.userObjects.Enterprise;
import cr.ac.tec.userObjects.EnterpriseRecipe;
import cr.ac.tec.userObjects.Recipe;
import cr.ac.tec.userObjects.User;

@Path("company/")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class EnterpriseData {
	
	/**
	 * @param username
	 * @param name
	 * @param member
	 * @return respuesta sobre interes a agregar un nuevo usuario
	 */
	@Path("member/{name}")
	@POST
	public Response newMember(@HeaderParam("x-user")String username, @PathParam("name") String name, @QueryParam("username") String member) {
		if(Trees.getTrees().profileTree.find(username).hasCompany(name)) {
			if(Trees.getTrees().profileTree.find(member)!=null) {
				Trees.getTrees().enterpriseTree.find(name).addMembers(Trees.getTrees().profileTree.find(member));;
				return Response.ok().build();
			}
		}
		return Response.status(400).build();	
	}
	
	/**
	 * @param username
	 * @param name
	 * @param enterprise
	 * @return respuesta para editar la compania
	 */
	@Path("edit/{name}")
	@PUT
	public Response editCompany(@HeaderParam("x-user")String username, @PathParam("name") String name, Enterprise enterprise) {
		if(Trees.getTrees().profileTree.find(username).hasCompany(name)) {
			Trees.getTrees().enterpriseTree.find(name).setEnterpriseName(enterprise.getEnterpriseName());
			Trees.getTrees().enterpriseTree.find(name).setContactInfo(enterprise.getContactInfo());
			Trees.getTrees().enterpriseTree.find(name).setOperationHours(enterprise.getOperationHours());
			Trees.getTrees().enterpriseTree.find(name).setImageBytes(enterprise.getImageBytes());
			return Response.ok().build();
		}else {
			return Response.status(400).build();
		}
	}
	
	/**
	 * @param username
	 * @param name
	 * @param recipe
	 * @return respuesta para agregar receta a la compania
	 */
	@Path("recipe/{name}")
	@POST
	public Response newCompanyRecipe(@HeaderParam("x-user") String username, @PathParam("name") String name,
			EnterpriseRecipe recipe) {

		Enterprise temp = Trees.getTrees().enterpriseTree.find(name);
		Trees.getTrees().recipeTree.insert(recipe);
		temp.addRecipe(recipe);
		if (recipe.getIsPublic()) {
			for (String order : temp.getFollowers()) {
				Trees.getTrees().profileTree.find(order).newFeed(recipe);
			}
		}
		for (String order : temp.getMembers()) {
			Trees.getTrees().profileTree.find(order).newFeed(recipe);
		}
		return Response.ok().build();

	}
	
	/**
	 * @param username
	 * @param name
	 * @return recive las recetas propias
	 */
	@Path("menu/{name}")
	@GET
	public Response getOwn(@HeaderParam("x-user")String username, @PathParam("name") String name) {
		if(Trees.getTrees().profileTree.find(username).hasCompany(name)) {
			Enterprise temp = Trees.getTrees().enterpriseTree.find(name);
			return Response.ok(temp.getRecipes()).build();
		}else {
			return Response.status(400).build();
		}
	}
	
}
