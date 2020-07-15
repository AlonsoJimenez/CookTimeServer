package cr.ac.tec.trees;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import cr.ac.tec.userObjects.User;

public class BinaryTree {
    private Node root =null;
        
    
    public void insert(User profile) {
        root = this.add(root, profile);
    }

    private Node add(Node current, User profile) {
        if(current == null) {
            current = new Node(profile);
        }else if(current.getData().getEmail().compareTo(profile.getEmail()) < 0) {
        	current.left = add(current.left, profile);
        }else {
            current.right = add(current.right, profile);
        }
        return current;
    }

    public User  find(String value) {
        return this.find(root, value);
    }

    private User find(Node current, String value) {
    	if(current == null) {
    		System.out.println("opcion1");
        	return null;
        }else if(current.getData().getEmail().compareTo(value) < 0) {
        	System.out.println("opcion2");
            return this.find(current.left, value);
        } else if(current.getData().getEmail().compareTo(value) > 0){
        	System.out.println("opcion3");
            return this.find(current.right, value);
        } else {
        	System.out.println("opcion4");
        	return current.getData();
        }

        
    }
}
