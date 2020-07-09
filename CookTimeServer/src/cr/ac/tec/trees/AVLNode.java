package cr.ac.tec.trees;

import cr.ac.tec.userObjects.User;

public class AVLNode extends Node{

	private int height;
	
	AVLNode(User data) {
		super(data);
		// TODO Auto-generated constructor stub
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return this.height;
	}

}
