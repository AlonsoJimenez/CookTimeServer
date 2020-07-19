package cr.ac.tec.trees;

import cr.ac.tec.userObjects.User;

public class NodeBinary {
    private User data = null;
    public NodeBinary left = null;
    public NodeBinary right = null;

    NodeBinary (User data){
        this.data = data;
    }

    public User getData() {
        return data;
    }
    
    public void setData(User profile) {
    	this.data = profile;
    }

   



}