package cr.ac.tec.trees;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import cr.ac.tec.userObjects.User;

public class BinaryTree {
    Node root = null;
    
    public void writeFile() {
    	
    }
    
    public void createUser(String name, String lastname, String email, int age, String password) throws NoSuchAlgorithmException {
    	User newest = new User(email, name, lastname, age);
    	newest.setPassword(password);
    	this.insert(newest);
    }

    public void insert(User profile) {
        this.add(root, profile);
    }

    private void add(Node current, User profile) {
        if(current == null) {
            current.setData(profile);
        }else if(current.getData().getEmail().compareTo(profile.getEmail()) == -1 || current.getData().getEmail().compareTo(profile.getEmail()) == 0) {
            this.add(current.right, profile);
        }else {
            this.add(current.left, profile);
        }
    }

    public User  find(String value) {
        return this.find(root, value);
    }

    private User find(Node current, String value) {
    	if(current == null) {
        	return null;
        }else if(current.getData().getEmail().compareTo(value) == -1 ) {
            this.find(current.right, value);
        } else if(current.getData().getEmail().compareTo(value) == 1){
            this.find(current.left, value);
        } else {return current.getData();}

        return null;
    }
}
