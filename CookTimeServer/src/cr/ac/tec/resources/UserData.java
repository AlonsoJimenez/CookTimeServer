package cr.ac.tec.resources;


import java.security.NoSuchAlgorithmException;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import cr.ac.tec.userObjects.Enterprise;
import cr.ac.tec.userObjects.EnterpriseRecipe;
import cr.ac.tec.userObjects.Recipe;
import cr.ac.tec.userObjects.SortingType;
import cr.ac.tec.userObjects.User;

@Path("user/")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class UserData {
	/**
	 * @param username
	 * @return respuesta con el contenido del perfil
	 */
	@Path("profile")
	@GET 
	public Response getProfile(@HeaderParam("x-user")String username) {
		System.out.println("rayos");
		System.out.println(username);
		User toResponse = Trees.getTrees().profileTree.find(username);
		return Response.ok(toResponse).build();
	}
	
	/**
	 * @param username
	 * @return respuesta con las notificaciones del usuario
	 */
	@Path("notifications")
	@GET
	public Response getNotifications(@HeaderParam("x-user") String username) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		return Response.ok(toResponse.getNotifications()).build();
	}
	
	/**
	 * @param username
	 * @return respuesta con el contenido de las empresas propias del usuario
	 */
	@Path("companies")
	@GET
	public Response getCompanies(@HeaderParam("x-user") String username) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		return Response.ok(toResponse.getCompanies()).build();
	}
	
	/**
	 * @param username
	 * @return respuesta con el menu propio del usuario
	 */
	@Path("own")
	@GET
	public Response getOwn(@HeaderParam("x-user") String username) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		return Response.ok(toResponse.own()).build();
	}
	
	/**
	 * @param username
	 * @return devuelve las noticias en el board de manera actualizada
	 */
	@Path("newsFeed")
	@GET
	public Response getMenu(@HeaderParam("x-user") String username) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		return Response.ok(toResponse.feed()).build();
	}
	
	/**
	 * @param username
	 * @return noticias basandose a las estrellas
	 */
	@Path("stars")
	@GET
	public Response getStars(@HeaderParam("x-user") String username) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		toResponse.arrayBy(SortingType.stars);
		return Response.ok(toResponse.feed()).build();
	}
	
	/**
	 * @param username
	 * @return noticias basandose en orden de fecha
	 */
	@Path("date")
	@GET
	public Response getDate(@HeaderParam("x-user") String username) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		toResponse.arrayBy(SortingType.date);
		return Response.ok(toResponse.feed()).build();
	}
	
	/**
	 * @param username
	 * @return noticias ordenadas en base a la dificultad
	 */
	@Path("difficulty")
	@GET
	public Response getDifficulty(@HeaderParam("x-user") String username) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		toResponse.arrayBy(SortingType.difficulty);
		return Response.ok(toResponse.feed()).build();
	}
	
	/**
	 * @param search
	 * @return resultados de busqueda por el usuario
	 */
	@Path("profiles/{search}")
	@GET
	public Response getSearch(@PathParam("search") String search) {
		User result = Trees.getTrees().profileTree.find(search);
		return Response.ok(result).build();
	}
	
	/**
	 * @param search
	 * @return resultado de busqueda de companias
	 */
	@Path("enterprise/{search}")
	@GET
	public Response getComSearch(@PathParam("search") String search) {
		Enterprise result = Trees.getTrees().enterpriseTree.find(search);
		return Response.ok(result).build();
	}
	
	/**
	 * @param search
	 * @return resultado de busqueda de receta
	 */
	@Path("menu/{search}")
	@GET
	public Response getMenuSearch(@PathParam("search") String search) {
		Recipe result = Trees.getTrees().recipeTree.find(search);
		return Response.ok(result).build();
	}
	
	/**
	 * @param username
	 * @param newRecipe
	 * @return resultado si se logro publicar la respuesta
	 */
	@Path("recipe")
	@POST
	public Response newPersonalRecipe(@HeaderParam("x-user") String username, Recipe newRecipe) {
		if(Trees.getTrees().recipeTree.find(newRecipe.getDishName())==null) {
			User toResponse = Trees.getTrees().profileTree.find(username);
			Trees.getTrees().recipeTree.insert(newRecipe);
			toResponse.newRecipe(newRecipe);
			toResponse.addOwn(newRecipe.getDishName());
			return Response.ok().build();
		}else {
			return Response.status(400).build();
		}
	}
	
	/**
	 * @param username
	 * @param company
	 * @return resultado si se logro publicar la empresa
	 */
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
	
	/**
	 * @param username
	 * @param email
	 * @return resultado si logro seguir el usuario
	 */
	@Path("follow/{email}")
	@POST
	public Response follow(@HeaderParam("x-user") String username,@PathParam("email") String email) {
		User toResponse = Trees.getTrees().profileTree.find(username);
		User toFollow = Trees.getTrees().profileTree.find(email);
		toResponse.follow(toFollow);
		return Response.ok().build();
	}
	
	/**
	 * @param toCreate
	 * @return resultado para publicar un usuario nuevo
	 * @throws NoSuchAlgorithmException
	 */
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
	
	/**
	 * @param username
	 * @param toCreate
	 * @return actualiza la informacion del usuario
	 */
	@Path("update")
	@PUT
	public Response update(@HeaderParam("x-user") String username, User toCreate){
		User toUpdate = Trees.getTrees().profileTree.find(username);
		toUpdate.setEmail(toCreate.getEmail());
		toUpdate.setAge(toCreate.getAge());
		toUpdate.setProfileDescription(toCreate.getProfileDescription());
		toUpdate.setImageBytes(toCreate.getImageBytes());
		toUpdate.setLastname(toCreate.getLastName());
		toUpdate.setName(toCreate.getName());
		return Response.ok().build();
	}
	
	/**
	 * @param username
	 * @param compName
	 * @return resultado si logro seguir una empresa
	 */
	@Path("followCompany")
	@POST
	public Response followComp(@HeaderParam("x-user") String username, @QueryParam("company")String  compName) {
		Trees.getTrees().profileTree.find(username).followNewComp(Trees.getTrees().enterpriseTree.find(compName));
		return Response.ok().build();
	}
	
	/**
	 * @param comment
	 * @param recipe
	 * @return resultado sobre la publicacion de un comentario
	 */
	@Path("comment/{recipe}")
	@POST
	public Response commentRecipe(@QueryParam("comment")String  comment, @PathParam("recipe")String recipe) {
		Trees.getTrees().recipeTree.find(recipe).addComment(comment);
		return Response.ok().build();
	}
	
	/**
	 * @param rate
	 * @param recipe
	 * @return resultado sobre el rating de una receta
	 */
	@Path("stars/{recipe}")
	@POST
	public Response rate(@QueryParam("rate")double  rate, @PathParam("recipe")String recipe) {
		Trees.getTrees().recipeTree.find(recipe).updateStars(rate);
		return Response.ok().build();
	}
	
	/**
	 * @param username
	 * @param dishName
	 * @return resultado en la eliminacion de la receta
	 */
	@Path("delete")
	@DELETE
	public Response delete(@HeaderParam("x-user") String username, @QueryParam("recipe") String dishName) {
		Recipe temp = Trees.getTrees().recipeTree.find(dishName);
		if(temp.getAuthor().equals(username)) {
			Trees.getTrees().recipeTree.Deletion(dishName);
			return Response.ok().build();
		}else {
			return Response.status(401).build();
		}
	}
	
	/**
	 * @param username
	 * @param user
	 * @return respuesta en cambio de chef
	 */
	@Path("nowChef")
	@PUT
	public Response editChef(@HeaderParam("x-user") String username, @QueryParam("user") String user) {
		if (username.equals("alonso.jimenez@hotmail.com")) {
			Trees.getTrees().profileTree.find(user).setIsChef();
			return Response.ok().build();
		} else {
			return Response.status(401).build();
		}
	}
	
	
	
	
	
	
	
}
