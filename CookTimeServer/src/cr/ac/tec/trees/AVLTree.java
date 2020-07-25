package cr.ac.tec.trees;

import cr.ac.tec.userObjects.Recipe;
import cr.ac.tec.userObjects.User;

public class AVLTree {
    public NodeAVL root;

    public AVLTree(){
        this.root = null;
    }

    /**
     * @param N
     * @return altura del nodo del arbol
     */
    int height(NodeAVL N) {
        if (N == null)
            return 0;

        return N.height;
    }

    /**
     * @param a
     * @param b
     * @return valor maximo en comapracion
     */
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    /**
     * @param y
     * @return realiza la rotacion derecha y devuelve el nodo principal
     */
    NodeAVL rightRotate(NodeAVL y) {
    	NodeAVL x = y.left;
    	NodeAVL T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    /**
     * @param x
     * @return realiza rotacion a la izquierda y devuelve el nodo principal
     */
    NodeAVL leftRotate(NodeAVL x) {
    	NodeAVL y = x.right;
    	NodeAVL T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }


    /**
     * @param N
     * @return devuelve la diferencia en alturas
     */
    int getBalance(NodeAVL N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    /**
     * @param toin
     * inserta valores al arbol
     */
    public void insert(Recipe toin){
        if(root!=null){
            insert_aux(toin, root);
        }else {
            root = new NodeAVL(toin);
        }
    }

    /**
     * @param toin
     * @param current
     * funcion auxiliar para insertar
     */
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

        root.height = 1 + max(height(root.left),
                height(root.right));

    

        int balance = getBalance(root);

        if (balance > 1 && toin.getDishName().compareTo(root.left.getData().getDishName())<0)
            rightRotate(root);

        if (balance < -1 && toin.getDishName().compareTo(root.right.getData().getDishName())>0)
            leftRotate(root);

        if (balance > 1 && toin.getDishName().compareTo(root.left.getData().getDishName())>0) {
            root.left = leftRotate(root.left);
            rightRotate(root);
        }

        if (balance < -1 && toin.getDishName().compareTo(root.left.getData().getDishName())<0) {
            root.right = rightRotate(root.right);
            leftRotate(root);
        }
    }


	/**
	 * @param svalue
	 * @return resultado de la busqueda en el arbol
	 */
	public Recipe find(String svalue){
    	if(root != null) {
    		if(root.getData().getDishName().compareTo(svalue) == 0){
    			return root.getData();
    		}else {
    			return search_aux(svalue, root);
        }
    	}else {return null;}
    }

    /**
     * @param svalue
     * @param current
     * @return resultado de busqueda de manera auxiliar
     */
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

        System.out.print(node.getData().getDishName() + " ");

        preOrder_aux(node.left);

        preOrder_aux(node.right);
    }
    
    NodeAVL minValueNode(NodeAVL node)
    {
        NodeAVL current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    /**
     * @param delete
     * ejecuta el borrado del nodo
     */
    public void Deletion(String delete){
        if(root == null){
            System.out.println("Raíz nula, no se procede a eliminar");
        }else {
            deleteNode(root, delete);
        }
    }
    
    /**
     * @param current
     * @param key
     * @return ejecucion del nodo a borrar de manera auxiliar
     */
    private NodeAVL deleteNode(NodeAVL current, String key)
    {

        if(current == null){
            return null;
        }

        if (key.compareTo(current.getData().getDishName()) < 0)
            current.left = deleteNode(current.left, key);

        else if (key.compareTo(current.getData().getDishName()) > 0)
            current.right = deleteNode(current.right, key);

        else
        {
            if ((current.left == null) || (current.right == null))
            {
            	NodeAVL temp = null;
                if (temp == current.left)
                    temp = current.right;
                else
                    temp = current.left;

                if (temp == null) {
                    temp = current;
                    current = null;
                }
                else 
                    current = temp; 
            }
            else
            {
            	NodeAVL temp = minValueNode(current.right);

                current.setData(temp.getData());

                current.right = deleteNode(current.right, temp.getData().getDishName());
            }
        }
        if(current == null){
            return null;
        }
        current.height = max(height(current.left), height(current.right)) + 1;

        int balance = getBalance(current);

        if (balance > 1 && getBalance(current.left) >= 0)
            return rightRotate(current);

        if (balance > 1 && getBalance(current.left) < 0)
        {
            current.left = leftRotate(current.left);
            return rightRotate(current);
        }

        if (balance < -1 && getBalance(current.right) <= 0)
            return leftRotate(current);

        if (balance < -1 && getBalance(current.right) > 0)
        {
            current.right = rightRotate(current.right);
            return leftRotate(current);
        }

        return current;
    }
   }
