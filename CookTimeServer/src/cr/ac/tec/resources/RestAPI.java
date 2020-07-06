package cr.ac.tec.resources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest/")
public class RestAPI extends Application {
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> classList = new HashSet<>();
		classList.add(Login.class);
		return classList;
	}	

}
