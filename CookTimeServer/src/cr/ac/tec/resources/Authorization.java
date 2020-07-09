package cr.ac.tec.resources;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.annotation.Priority;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;

import cr.ac.tec.userObjects.User;

@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class Authorization implements ContainerRequestFilter {


private boolean validateAuthorization(String[] userNameComponents) {

	try {
		MessageDigest hash = MessageDigest.getInstance("MD5");
		hash.update(userNameComponents[1].getBytes());
		String tempPassword = DatatypeConverter.printHexBinary(hash.digest());
		User temp = Trees.profileTree.find(userNameComponents[0]);
		if (temp == null) {
			return false;
		} else if (!tempPassword.equals(temp.getPassword())) {
			return false;
		} else {
			return true;
		}
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		throw new WebApplicationException(500);
	}
}

	private String[] getUserAndPassword(String userAndPassword) {
		String[] authComponents = userAndPassword.split(" ");
		if (authComponents.length != 2) {
			
			throw new IllegalArgumentException();
		}		
		String userNamePass = new String(Base64.getDecoder().decode(authComponents[1]));
		return userNamePass.split(":");
		
	}

	@Override
	public void filter(ContainerRequestContext contextFilter) throws IOException {
		String authorizationHeader = contextFilter.getHeaderString("Authorization");
		String[] userAndPassword = getUserAndPassword(authorizationHeader);
		if (!validateAuthorization(userAndPassword)) {			
			throw new WebApplicationException(401);
		}
		
		contextFilter.getHeaders().add("x-user", userAndPassword[0]);
		System.out.println("aqui");
	}
}
	
