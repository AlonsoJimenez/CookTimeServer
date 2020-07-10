package cr.ac.tec.resources;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import cr.ac.tec.trees.*;
import cr.ac.tec.userObjects.Recipe;
import cr.ac.tec.userObjects.User;

public class Trees {
	private static Trees singleton = null;
	private Trees() {
		profileTree = new BinaryTree();
		recipeTree = new AVLTree();
		enterpriseTree = new SplayTree();
		justOnce();
		
		/*
		profileTree = loadFromProfileFile();
		recipeTree = loadFromRecipeFile();
		enterpriseTree = loadFromCompanyFile();
		*/
	}
	
	public BinaryTree profileTree;
	public AVLTree recipeTree;
	public SplayTree enterpriseTree;
	
	
	public static synchronized Trees getTrees() {
		if(singleton == null) {
			singleton = new Trees();
			return singleton;
		}else {return singleton;}
	}
	
	private void justOnce() {
		try {
			User temp1 = new User();
			temp1.setPasswordAux("hola");
			temp1.setAge(18);
			temp1.setEmail("alonso.jimenez@hotmail.com");
			temp1.setName("Alonso");
			temp1.setLastname("Jimenez");
			profileTree.insert(temp1);
			
			User temp2 = new User();
			temp2.setPasswordAux("boba");
			temp2.setAge(18);
			temp2.setEmail("buba");
			temp2.setName("Alonso");
			temp2.setLastname("Jimenez");
			Recipe m = new Recipe();
			m.setAuthor("Alonso");
			m.setDifficulty(5);
			m.setDishName("cc");
			m.setDishType("bla");
			m.setName("lms");
			m.setPortionsSize(4);
			m.setPreparationMinutes(5);
			temp2.newRecipe(m);
			profileTree.insert(temp2);
			
			User temp3 = new User();
			temp3.setPasswordAux("juanPedos");
			temp3.setAge(18);
			temp3.setEmail("jaja");
			temp3.setName("Alonso");
			temp3.setLastname("Jimenez");
			profileTree.insert(temp3);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
