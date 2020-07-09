package cr.ac.tec.resources;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import cr.ac.tec.trees.*;
import cr.ac.tec.userObjects.User;

public class Trees {
	private static Trees singleton = null;
	private Trees() {
		/*
		profileTree = loadFromProfileFile();
		recipeTree = loadFromRecipeFile();
		enterpriseTree = loadFromCompanyFile();
		*/
	}
	
	public static BinaryTree profileTree = new BinaryTree();
	public static AVLTree recipeTree = new AVLTree();
	public static SplayTree enterpriseTree= new SplayTree();
	
	
	
	public static synchronized Trees getTrees() {
		if(singleton == null) {
			return new Trees();
		}else {return singleton;}
	}
	
	static {
		try {
			profileTree.createUser("Alonso", "Jimenez", "alonso.jimenez@hotmail.com", 18, "hola");
			profileTree.createUser("Juan", "perez", "bomba", 18, "aloha");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
