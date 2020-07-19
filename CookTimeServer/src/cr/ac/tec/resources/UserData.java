package cr.ac.tec.resources;


import java.security.NoSuchAlgorithmException;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import cr.ac.tec.userObjects.Enterprise;
import cr.ac.tec.userObjects.Recipe;
import cr.ac.tec.userObjects.SortingType;
import cr.ac.tec.userObjects.User;

@Path("user/")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class UserData {
	@Path("profile")
	@GET 
	public Response getProfile(@HeaderParam("x-user")String username) {
		System.out.println("rayos");
		System.out.println(username);
		User toResponse = Trees.getTrees().profileTree.find(username);
		return Response.ok(toResponse).build();
	}
	
	@Path("notifications")
	@GET
	public Response getNotifications(@HeaderParam("x-user") String username) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		return Response.ok(toResponse.getNotifications()).build();
	}
	
	@Path("companies")
	@GET
	public Response getCompanies(@HeaderParam("x-user") String username) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		return Response.ok(toResponse.getCompanies()).build();
	}
	
	@Path("newsFeed")
	@GET
	public Response getMenu(@HeaderParam("x-user") String username) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		return Response.ok(toResponse.recipes()).build();
	}
	
	@Path("stars")
	@GET
	public Response getStars(@HeaderParam("x-user") String username) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		toResponse.arrayBy(SortingType.stars);
		return Response.ok(toResponse.recipes()).build();
	}
	
	@Path("date")
	@GET
	public Response getDate(@HeaderParam("x-user") String username) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		toResponse.arrayBy(SortingType.date);
		return Response.ok(toResponse.recipes()).build();
	}
	
	@Path("difficulty")
	@GET
	public Response getDifficulty(@HeaderParam("x-user") String username) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		toResponse.arrayBy(SortingType.difficulty);
		return Response.ok(toResponse.recipes()).build();
	}
	
	@Path("profiles/{search}")
	@GET
	public Response getSearch(@PathParam("search") String search) {
		User result = Trees.getTrees().profileTree.find(search);
		return Response.ok(result).build();
	}
	
	@Path("enterprise/{search}")
	@GET
	public Response getComSearch(@PathParam("search") String search) {
		Enterprise result = Trees.getTrees().enterpriseTree.find(search);
		return Response.ok(result).build();
	}
	
	@Path("menu/{search}")
	@GET
	public Response getMenuSearch(@PathParam("search") String search) {
		Recipe result = Trees.getTrees().recipeTree.find(search);
		return Response.ok(result).build();
	}
	
	@Path("recipe")
	@POST
	public Response newPersonalRecipe(@HeaderParam("x-user") String username, Recipe newRecipe) {
		if(Trees.getTrees().recipeTree.find(newRecipe.getDishName())==null) {
			User toResponse = Trees.getTrees().profileTree.find(username);
			Trees.getTrees().recipeTree.insert(newRecipe);
			toResponse.newRecipe(newRecipe);
			return Response.ok().build();
		}else {
			return Response.status(400).build();
		}
	}
	
	@Path("company")
	@POST
	public Response newCompany(@HeaderParam("x-user") String username, Enterprise company) {
		if(Trees.getTrees().enterpriseTree.find(company.getEnterpriseName())==null) {
			User toResponse = Trees.getTrees().profileTree.find(username);
			toResponse.addCompany(company);
			Trees.getTrees().enterpriseTree.insert(company);
			return Response.ok().build();
		}else {
			return Response.status(400).build();
		}
	}
	
	@Path("follow/{email}")
	@POST
	public Response follow(@HeaderParam("x-user") String username,@PathParam("email") String email) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		User toFollow = Trees.getTrees().profileTree.find(email);
		toResponse.follow(toFollow);
		return Response.ok().build();
	}
	
	@Path("newUser")
	@POST
	public Response newUser(User toCreate) throws NoSuchAlgorithmException {
		if(Trees.getTrees().profileTree.find(toCreate.getEmail())==null) {
			toCreate.setPasswordAux(toCreate.getPassword());
			Trees.getTrees().profileTree.insert(toCreate);
			return Response.ok().build();
		}else {
			return Response.status(400).build();
		}
	}
	
	@Path("update")
	@PUT
	public Response update(@HeaderParam("x-user") String username, User toCreate){
		User toUpdate = Trees.getTrees().profileTree.find(username);
		toUpdate.setEmail(toCreate.getEmail());
		toUpdate.setAge(toCreate.getAge());
		toUpdate.setImageBytes(toCreate.getImageBytes());
		toUpdate.setLastname(toCreate.getLastName());
		toUpdate.setName(toCreate.getName());
		return Response.ok().build();
	}
	
	@Path("followCompany")
	@POST
	public Response followComp(@HeaderParam("x-user") String username, @QueryParam("company")String  compName) {
		Trees.getTrees().profileTree.find(username).followNewComp(Trees.getTrees().enterpriseTree.find(compName));
		return Response.ok().build();
	}
	
	@Path("comment/{recipe}")
	@POST
	public Response commentRecipe(@QueryParam("comment")String  comment, @PathParam("recipe")String recipe) {
		Trees.getTrees().recipeTree.find(recipe).addComment(comment);
		return Response.ok().build();
	}
	
	@Path("stars/{recipe}")
	@POST
	public Response rate(@QueryParam("rate")int  rate, @PathParam("recipe")String recipe) {
		Trees.getTrees().recipeTree.find(recipe).updateStars(rate);
		return Response.ok().build();
	}
	
	
	
	
	
	
	
}
