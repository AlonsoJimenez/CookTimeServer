package cr.ac.tec.resources;


import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import cr.ac.tec.trees.BinaryTree;
import cr.ac.tec.userObjects.User;

@Path("login")
public class Login {
	@GET 
	public Response getProfile(@QueryParam("username") String username, @QueryParam("password") String password) {
		
		return ;
	}
	
	private Response loginAnalizer(User profile, String password) {
		if(profile==null) {
			return Response.status(204).build();
		}else if(profile.getUserJSON().getString("password").equals(password)) {
			return Response.ok(profile.getUserString()).build();
		}else {
			return Response.status(201).build();
		}
	}

	
}
