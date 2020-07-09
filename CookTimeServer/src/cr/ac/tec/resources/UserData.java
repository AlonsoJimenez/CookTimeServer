package cr.ac.tec.resources;


import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.media.jfxmedia.Media;

import cr.ac.tec.trees.BinaryTree;
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
		User toResponse = Trees.profileTree.find(username);
		return Response.ok(toResponse).build();
	}
	
	@Path("notifications")
	@GET
	public Response getNotifications(@HeaderParam("x-user") String username) {
		User toResponse = Trees.profileTree.find(username);
		return Response.ok(toResponse.getNotifications()).build();
	}
	
	@Path("companies")
	@GET
	public Response getCompanies(@HeaderParam("x-user") String username) {
		User toResponse = Trees.profileTree.find(username);
		return Response.ok(toResponse.getNotifications()).build();
	}
	
	@Path("newsFeed")
	@GET
	public Response getMenu(@HeaderParam("x-user") String username) {
		User toResponse = Trees.profileTree.find(username);
		return Response.ok(toResponse.getMyMenu()).build();
	}
	
	@Path("stars")
	@GET
	public Response getStars(@HeaderParam("x-user") String username) {
		User toResponse = Trees.profileTree.find(username);
		toResponse.arrayBy(SortingType.stars);
		return Response.ok(toResponse.getMyMenu()).build();
	}
	
	@Path("date")
	@GET
	public Response getDate(@HeaderParam("x-user") String username) {
		User toResponse = Trees.profileTree.find(username);
		toResponse.arrayBy(SortingType.date);
		return Response.ok(toResponse.getMyMenu()).build();
	}
	
	@Path("difficulty")
	@GET
	public Response getDifficulty(@HeaderParam("x-user") String username) {
		User toResponse = Trees.profileTree.find(username);
		toResponse.arrayBy(SortingType.difficulty);
		return Response.ok(toResponse.getMyMenu()).build();
	}
	
	@Path("profiles/{search}")
	@GET
	public Response getSearch(@PathParam("search") String search) {
		User result = Trees.profileTree.find(search);
		return Response.ok(result).build();
	}
	
	@Path("enterprise/{search}")
	@GET
	public Response getComSearch(@PathParam("search") String search) {
		User result = Trees.enterpriseTree.find(search);
		return Response.ok(result).build();
	}
	
	@Path("menu/{search}")
	@GET
	public Response getMenuSearch(@PathParam("search") String search) {
		User result = Trees.recipeTree.find(search);
		return Response.ok(result).build();
	}
	
	@Path("recipe")
	@POST
	public Response newPersonalRecipe(@HeaderParam("x-user") String username, Recipe newRecipe) {
		//recipeTree.insert(newRecipe);
		User toResponse = Trees.profileTree.find(username);
		toResponse.newRecipe(newRecipe);
		return Response.ok().build();
	}
	
	@Path("company")
	@POST
	public Response newCompany(@HeaderParam("x-user") String username, Enterprise company) {
		//companyTree.insert(company);
		User toResponse = Trees.profileTree.find(username);
		toResponse.addCompany(company);
		return Response.ok().build();
	}
	
	@Path("follow/{email}")
	@POST
	public Response follow(@HeaderParam("x-user") String username,@PathParam("email") String email) {
		User toResponse = Trees.profileTree.find(username);
		User toFollow = Trees.profileTree.find(email);
		toResponse.follow(toFollow);
		return Response.ok().build();
	}
	
	
	
	
	
}
