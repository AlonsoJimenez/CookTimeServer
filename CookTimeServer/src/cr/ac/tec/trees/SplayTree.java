package cr.ac.tec.trees;

import cr.ac.tec.userObjects.Enterprise;
import cr.ac.tec.userObjects.User;

public class SplayTree {
    public Node root;


    private void splay(Node tosplay){
        if(tosplay.key.compareTo(root.key) != 0){
            //zig
            if(tosplay.key.compareTo(root.key) > 0){

                Node temp = root;
                root = root.right;
                temp.right = root.left;
                root.left = temp;

                splay(tosplay);
                System.out.println("La nueva raiz "+ root.key);

                //zag
            }else {
                Node temp = root;
                root = root.left;
                temp.left = root.right;
                root.right = temp;
                System.out.println("La nueva raiz"+ root.key);

                splay(tosplay);
            }
        }else {
            System.out.println("Splay Finalizado.");
        }
    }

    public String search(String svalue){
        if(root.key.compareTo(svalue) == 0){
            return root.key;
        }else {
            return search_aux(svalue, root);
        }
    }

    public String search_aux(String svalue, Node current){
        if(svalue.compareTo(current.key) == 0){
            splay(current);
            return current.key;
        }else {
            if(current.key.compareTo(svalue) > 0){
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

    public void insert(String toin){
        if(root!=null){
            insert_aux(toin, root);
        }else {
            root = new Node(toin);
        }
    }

    private void insert_aux(String toin, Node current){
        if(toin.compareTo(current.key) >0){
            if(current.right!= null){
                insert_aux(toin, current.right);
            }else {
                current.right = new Node(toin);
                splay(current.right);
            }
        }else {
            if(current.left!= null){
                insert_aux(toin, current.left);
            }else {
                current.left = new Node(toin);
                splay(current.left);
            }
        }


    }

    public void postOrder(){
        postOrder_aux(root);
    }

    private void postOrder_aux(Node node) {
        if (node == null)
            return;

        // first recur on left subtree
        postOrder_aux(node.left);

        // then recur on right subtree
        postOrder_aux(node.right);

        // now deal with the node
        System.out.print(node.key + " ");
    }

    public void preOrder(){
        preOrder_aux(root);
    }

    void preOrder_aux(Node node) {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.key + " ");

        /* then recur on left sutree */
        preOrder_aux(node.left);

        /* now recur on right subtree */
        preOrder_aux(node.right);
    }


    public void inOrder(){
        inOrder_aux(root);
    }
    private void inOrder_aux(Node node) {
        if (node == null)
            return;

        /* first recur on left child */
        inOrder_aux(node.left);

        /* then print the data of node */
        System.out.print(node.key + " ");

        /* now recur on right child */
        inOrder_aux(node.right);
    }
}