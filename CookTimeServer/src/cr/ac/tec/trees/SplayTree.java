package cr.ac.tec.trees;

import cr.ac.tec.userObjects.Enterprise;
import cr.ac.tec.userObjects.Recipe;
import cr.ac.tec.userObjects.User;

public class SplayTree {
    public NodeSplay root;


    private void splay(NodeSplay tosplay){
        if(tosplay.getData().getEnterpriseName().compareTo(root.getData().getEnterpriseName()) != 0){
            //zig
            if(tosplay.getData().getEnterpriseName().compareTo(root.getData().getEnterpriseName()) > 0){

            	NodeSplay temp = root;
                root = root.right;
                temp.right = root.left;
                root.left = temp;

                splay(tosplay);
                System.out.println("La nueva raiz "+ root.getData().getEnterpriseName());

                //zag
            }else {
            	NodeSplay temp = root;
                root = root.left;
                temp.left = root.right;
                root.right = temp;
                System.out.println("La nueva raiz"+ root.getData().getEnterpriseName());

                splay(tosplay);
            }
        }else {
            System.out.println("Splay Finalizado.");
        }
    }

	public Enterprise find(String svalue) {
		if (root != null) {
			if (root.getData().getEnterpriseName().compareTo(svalue) == 0) {
				return root.getData();
			} else {
				return search_aux(svalue, root);
			}
		} else {
			return null;
		}
	}

    public Enterprise search_aux(String svalue, NodeSplay current){
        if(svalue.compareTo(current.getData().getEnterpriseName()) == 0){
            splay(current);
            return current.getData();
        }else {
            if(current.getData().getEnterpriseName().compareTo(svalue) > 0){
                if(current.left!=null){
                    return search_aux(svalue, current.left);
                }else {
                    splay(current);
                    return null;
                }
            }else {
                if(current.right!=null){
                    return search_aux(svalue, current.right);
                }else {
                    splay(current);
                    return null;
                }
            }
        }

    }

    public void insert(Enterprise toin){
        if(root!=null){
            insert_aux(toin, root);
        }else {
            root = new NodeSplay(toin);
        }
    }

    private void insert_aux(Enterprise toin, NodeSplay current){
        if(toin.getEnterpriseName().compareTo(current.getData().getEnterpriseName()) >0){
            if(current.right!= null){
                insert_aux(toin, current.right);
            }else {
                current.right = new NodeSplay(toin);
                splay(current.right);
            }
        }else {
            if(current.left!= null){
                insert_aux(toin, current.left);
            }else {
                current.left = new NodeSplay(toin);
                splay(current.left);
            }
        }


    }

    public void postOrder(){
        postOrder_aux(root);
    }

    private void postOrder_aux(NodeSplay node) {
        if (node == null)
            return;

        // first recur on left subtree
        postOrder_aux(node.left);

        // then recur on right subtree
        postOrder_aux(node.right);

        // now deal with the node
        System.out.print(node.getData().getEnterpriseName() + " ");
    }

    public void preOrder(){
        preOrder_aux(root);
    }

    void preOrder_aux(NodeSplay node) {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.getData().getEnterpriseName() + " ");

        /* then recur on left sutree */
        preOrder_aux(node.left);

        /* now recur on right subtree */
        preOrder_aux(node.right);
    }


    public void inOrder(){
        inOrder_aux(root);
    }
    private void inOrder_aux(NodeSplay node) {
        if (node == null)
            return;

        /* first recur on left child */
        inOrder_aux(node.left);

        /* then print the data of node */
        System.out.print(node.getData().getEnterpriseName() + " ");

        /* now recur on right child */
        inOrder_aux(node.right);
    }
}