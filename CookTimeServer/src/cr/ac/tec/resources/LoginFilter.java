package cr.ac.tec.resources;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import cr.ac.tec.userObjects.User;

@WebFilter(filterName = "authFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authorizationHeader = httpRequest.getHeader("Authorization");
		try {
			if (validateAuthorization(authorizationHeader)) {			
				chain.doFilter(request, response);
			} else {
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.setStatus(401);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean validateAuthorization(String authorization) throws NoSuchAlgorithmException {
		if (authorization == null || authorization.equals("")) {
			return false;
		}
		String[] authComponents = authorization.split(" ");
		if (authComponents.length != 2) {
			return false;
		}		
		String userNamePass = new String(Base64.getDecoder().decode(authComponents[1]));
		String[] userNameComponents = userNamePass.split(":");
		MessageDigest hash = MessageDigest.getInstance("MD5");
		hash.update(userNameComponents[1].getBytes());
		String tempPassword = DatatypeConverter.printHexBinary(hash.digest());
		User temp = Trees.profileTree.find(userNameComponents[0]);
		if (temp == null) {
			return false;
		}else if(!tempPassword.equals(temp.getPassword())) {
			return false;
		}else {return true;}		
	}
}
	
