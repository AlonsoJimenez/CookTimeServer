package cr.ac.tec.trees;

import cr.ac.tec.userObjects.Enterprise;
import cr.ac.tec.userObjects.Recipe;
import cr.ac.tec.userObjects.User;

public class SplayTree {
    public NodeSplay root;


    private void splay(NodeSplay tosplay){
        if(tosplay.getData().getEnterpriseName().compareTo(root.getData().getEnterpriseName()) != 0){
            if(tosplay.getData().getEnterpriseName().compareTo(root.getData().getEnterpriseName()) > 0){

            	NodeSplay temp = root;
                root = root.right;
                temp.right = root.left;
                root.left = temp;

                splay(tosplay);
                System.out.println("La nueva raiz "+ root.getData().getEnterpriseName());

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

	/**
	 * @param svalue
	 * @return empresa a buscar
	 */
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

    /**
     * @param svalue
     * @param current
     * @return funcion auxiliar de busqueda
     */
    private Enterprise search_aux(String svalue, NodeSplay current){
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

    /**
     * @param toin
     * funcion para insertar valores de nodos
     */
    public void insert(Enterprise toin){
        if(root!=null){
            insert_aux(toin, root);
        }else {
            root = new NodeSplay(toin);
        }
    }

    /**
     * @param toin
     * @param current
     * funcion auxuliar para insertar
     */
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

   
}