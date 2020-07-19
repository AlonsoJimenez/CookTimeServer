package cr.ac.tec.trees;

import cr.ac.tec.userObjects.Enterprise;

public class NodeSplay {
	private Enterprise data = null;
    public NodeSplay left = null;
    public NodeSplay right = null;

    NodeSplay (Enterprise data){
        this.data = data;
    }

    public Enterprise getData() {
        return data;
    }
    
    public void setData(Enterprise profile) {
    	this.data = profile;
    }
}
