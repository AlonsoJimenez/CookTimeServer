package cr.ac.tec.resources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest/")
public class RestAPI extends Application {
	/**
	 *devuelve las clases que contienen cada API
	 */
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> classList = new HashSet<>();
		classList.add(Authorization.class);
		classList.add(UserData.class);
		classList.add(EnterpriseData.class);
		classList.add(RecipeData.class);
		return classList;
	}	

}
