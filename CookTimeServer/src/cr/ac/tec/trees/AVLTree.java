package cr.ac.tec.trees;

import cr.ac.tec.userObjects.Recipe;
import cr.ac.tec.userObjects.User;

public class AVLTree {
    public NodeAVL root;

    public AVLTree(){
        this.root = null;
    }

    int height(NodeAVL N) {
        if (N == null)
            return 0;

        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    NodeAVL rightRotate(NodeAVL y) {
    	NodeAVL x = y.left;
    	NodeAVL T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    NodeAVL leftRotate(NodeAVL x) {
    	NodeAVL y = x.right;
    	NodeAVL T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }


    int getBalance(NodeAVL N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    public void insert(Recipe toin){
        if(root!=null){
            insert_aux(toin, root);
        }else {
            root = new NodeAVL(toin);
        }
    }

    private void insert_aux(Recipe toin, NodeAVL current){
        if(toin.getDishName().compareTo(current.getData().getDishName()) >0){
            if(current.right!= null){
                insert_aux(toin, current.right);
            }else {
                current.right = new NodeAVL(toin);
            }
        }else {
            if(current.left!= null){
                insert_aux(toin, current.left);
            }else {
                current.left = new NodeAVL(toin);
            }
        }

        /* 2. Update height of this ancestor node */
        root.height = 1 + max(height(root.left),
                height(root.right));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */


        int balance = getBalance(root);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case <
        if (balance > 1 && toin.getDishName().compareTo(root.left.getData().getDishName())<0)
            rightRotate(root);

        // Right Right Case
        if (balance < -1 && toin.getDishName().compareTo(root.right.getData().getDishName())>0)
            leftRotate(root);

        // Left Right Case
        if (balance > 1 && toin.getDishName().compareTo(root.left.getData().getDishName())>0) {
            root.left = leftRotate(root.left);
            rightRotate(root);
        }

        // Right Left Case
        if (balance < -1 && toin.getDishName().compareTo(root.left.getData().getDishName())<0) {
            root.right = rightRotate(root.right);
            leftRotate(root);
        }
    }


	public Recipe find(String svalue){
    	if(root != null) {
    		if(root.getData().getDishName().compareTo(svalue) == 0){
    			return root.getData();
    		}else {
    			return search_aux(svalue, root);
        }
    	}else {return null;}
    }

    public Recipe search_aux(String svalue, NodeAVL current){
        if(svalue.compareTo(current.getData().getDishName()) == 0){
            return current.getData();
        }else {
            if(current.getData().getDishName().compareTo(svalue) > 0){
                if(current.left!=null){
                    return search_aux(svalue, current.left);
                }else {
                    return null;
                }
            }else {
                if(current.right!=null){
                    return search_aux(svalue, current.right);
                }else {
                    return null;
                }
            }
        }

    }

    public void preOrder(){
        preOrder_aux(root);
    }

    void preOrder_aux(NodeAVL node) {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.getData().getDishName() + " ");

        /* then recur on left sutree */
        preOrder_aux(node.left);

        /* now recur on right subtree */
        preOrder_aux(node.right);
    }
}
