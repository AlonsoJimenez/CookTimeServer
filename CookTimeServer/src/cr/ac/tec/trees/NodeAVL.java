package cr.ac.tec.trees;

import cr.ac.tec.userObjects.Enterprise;
import cr.ac.tec.userObjects.Recipe;
import cr.ac.tec.userObjects.User;

public class NodeAVL {
	public int height = 1;
	private Recipe data = null;
    public NodeAVL left = null;
    public NodeAVL right = null;

    NodeAVL (Recipe data){
        this.data = data;
    }

    public Recipe getData() {
        return data;
    }
    
    public void setData(Recipe profile) {
    	this.data = profile;
    }


}
