package cr.ac.tec.trees;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import cr.ac.tec.userObjects.User;

public class BinaryTree {
    private NodeBinary root =null;
        
    
    /**
     * @param profile
     * funcion para insertar nodos con valores
     */
    public void insert(User profile) {
        root = this.add(root, profile);
    }

    /**
     * @param current
     * @param profile
     * @return funcion auxiliar para agregar nodos
     */
    private NodeBinary add(NodeBinary current, User profile) {
        if(current == null) {
            current = new NodeBinary(profile);
        }else if(current.getData().getEmail().compareTo(profile.getEmail()) < 0) {
        	current.left = add(current.left, profile);
        }else {
            current.right = add(current.right, profile);
        }
        return current;
    }

    /**
     * @param value
     * @return funcion para encontrar valores
     */
    public User  find(String value) {
        return this.find(root, value);
    }

    /**
     * @param current
     * @param value
     * @return funcion para buscar auxiliar
     */
    private User find(NodeBinary current, String value) {
    	if(current == null) {
        	return null;
        }else if(current.getData().getEmail().compareTo(value) < 0) {
            return this.find(current.left, value);
        } else if(current.getData().getEmail().compareTo(value) > 0){
            return this.find(current.right, value);
        } else {
        	return current.getData();
        }

        
    }
}
