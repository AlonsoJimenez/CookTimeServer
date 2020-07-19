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
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
