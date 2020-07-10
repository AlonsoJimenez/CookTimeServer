package cr.ac.tec.trees;

import cr.ac.tec.userObjects.Recipe;
import cr.ac.tec.userObjects.User;

public class AVLTree {
	private Node root;
	
	private int getHeight(AVLNode node) {
		return node.getHeight();
	}
	
	private int getMax(int a, int b) {
		if(a>b) {
			return a;
		}else {return b;}
	}
	
	
	
	public void insert(Recipe value) {
		this.insert(root, value);
	}
	
	private Node insert(AVLNode node, Recipe value) {
		
	}
	
	private int getBalance(AVLNode node) {
		if(node==null) {
			return 0;
		}else {
			return this.getHeight((AVLNode) node.left) - this.getHeight((AVLNode) node.right);
		}
	}
	
	private AVLNode checkBalance(AVLNode node) {
		if(node == null) {
			return node;
		}else if(getBalance(node) > 1) {
			
		}
	}

	public Recipe find(String search) {
		// TODO Auto-generated method stub
		return null;
	}
}
