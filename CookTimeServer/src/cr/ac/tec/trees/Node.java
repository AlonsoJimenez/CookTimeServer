package cr.ac.tec.trees;

import cr.ac.tec.userObjects.User;

public class Node {
    private User data = null;
    public Node left = null;
    public Node right = null;

    Node (User data){
        this.data = data;
    }

    public User getData() {
        return data;
    }
    
    public void setData(User profile) {
    	this.data = profile;
    }

   



}