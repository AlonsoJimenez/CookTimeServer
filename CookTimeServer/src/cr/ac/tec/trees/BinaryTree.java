package cr.ac.tec.trees;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import cr.ac.tec.userObjects.User;

public class BinaryTree {
    Node root = new Node(null);
    
    public void createUser(String name, String lastname, String email, int age, String password) throws NoSuchAlgorithmException {
    	MessageDigest hashPassword = MessageDigest.getInstance("MD5");
    	hashPassword.update(password.getBytes());
    	String encryptedPassword = DatatypeConverter.printHexBinary(hashPassword.digest());
    	User newest = new User(email, encryptedPassword, name, lastname, age);
    	this.insert(newest);
    }

    public void insert(User profile) {
        this.add(root, profile);
    }

    private void add(Node current, User profile) {
        if(current.getData () == null) {
            current.setData(profile);
            return;
        }else if(current.getData().compareTo(profile.getEmail()) == -1 || current.getData().compareTo(profile.getEmail()) == 0) {
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
        }else if(current.getData().compareTo(value) == -1 ) {
            this.find(current.right, value);
        } else if(current.getData().compareTo(value) == 1){
            this.find(current.left, value);
        } else {return current.getData();}

        return null;
    }
}
