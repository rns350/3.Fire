import java.io.Serializable;
public class AVLTree<E extends Comparable<E>> implements Serializable{
    public final static long serialVersionUID = 8729301928374728L;
    private class Node<E> implements Serializable{
        public final static long serialVersionUID = 1290872495623975L;
        int height;
        E data;
        Node<E> left, right;
        Node(E data){
            this.data = data;
            height = 1;
        }
    }

    Node<E> root;
    int size;

    public int size(){
        return size;
    }

    public boolean add(E data){
        if(root == null){
            root = new Node<E>(data);
            size ++;
            return true;
        }
        int beforeSize = size;
        root = recAdd(data, root);
        if(beforeSize == size){ return false;}
        return true;
    }

    private Node<E> recAdd(E data, Node<E> current){
        if(current == null) {
            size ++;
            return new Node<E>(data);
        }
        int i = data.compareTo(current.data);
        if(i < 0){
            current.left = recAdd(data, current.left);
        }
        else if(i > 0){
            current.right = recAdd(data, current.right);
        }
        else{
            return current;
        }

        calcHeight(current);
        int balanceFactor = balanceFactor(current);
        if(balanceFactor > 1){
            if(data.compareTo(current.left.data) < 0){
                return rotateRight(current);
            }
            else{
                current.left = rotateLeft(current.left);
                return rotateRight(current);
            }
        }
        else if(balanceFactor < -1){
            if(data.compareTo(current.right.data) > 0){
                return rotateLeft(current);
            }
            else{
                current.right = rotateRight(current.right);
                return rotateLeft(current);
            }
        }
        return current;
    }

    public E get(E data){
        Node<E> temp = getNode(data);
        if(temp == null){
            return null;
        }
        return temp.data;
    }

    private Node<E> getNode(E data){
        Node<E> temp = root;
        int i = data.compareTo(root.data);
        while(temp != null && i != 0){
            if(data.compareTo(temp.data) == 0){ return temp; }
            if(i < 0){
                temp = temp.left;
            }
            else{
                temp = temp.right;
            }
        }
        return temp;
    }

    public boolean remove(E data){
        if(root == null){
            return false;
        }
        int beforeSize = size;
        root = removeRec(data, root);
        if(beforeSize == size){return false;}
        return true;
    }

    public Node<E> removeRec(E data, Node<E> current){
        if(current == null) {return current;}
        int i = data.compareTo(current.data);
        if(i < 0){
            current.left = removeRec(data, current.left);
        }
        else if(i > 0){
            current.right = removeRec(data, current.right);
        }
        else{
            size --;
            if(current.left == null && current.right == null){current = null;}
            else if(current.right == null){current = current.left;}
            else if(current.left == null){current = current.right;}
            else{
                Node<E> replace = current.left;
                while(replace.right != null){
                    replace = replace.right;
                }
                replace.right = current.right;
                replace.left = current.left;
                replace.left = removeRec(replace.data, replace.left);
                current = replace;
            }
        }
        if(current == null) { return current; }
        System.out.println("Node " + current.data + ": " + current.height);
        calcHeight(current);
        int balanceFactor = balanceFactor(current);
        System.out.println(this);

        if(balanceFactor > 1){
            if(balanceFactor(root.left) >= 0){
                return rotateRight(current);
            }
            else{
                current.left = rotateLeft(current.left);
                return rotateRight(current);
            }
        }
        else if(balanceFactor < -1){
            if(balanceFactor(root.right) <= 0){
                return rotateLeft(current);
            }
            else{
                current.right = rotateRight(current.right);
                return rotateLeft(current);
            }
        }
        return current;
    }

    private void calcHeight(Node<E> node){
        if(node.left == null && node.right == null){
            node.height = 1;
            return;
        }
        if(node.left == null){
            node.height = node.right.height + 1;
            return;
        }
        if(node.right == null){
            node.height = node.left.height + 1;
            return;
        }
        node.height = Math.max(node.right.height, node.left.height) + 1;
    }

    private int balanceFactor(Node<E> n){
        if(n == null || (n.left == null && n.right == null)){
            return 0;
        }
        if(n.left == null){return -n.right.height;}
        if(n.right == null){return n.left.height;}
        return n.left.height - n.right.height;
    }

    private Node<E> rotateRight(Node<E> e){
        Node<E> newRoot = e.left;
        e.left = newRoot.right;
        newRoot.right = e;

        calcHeight(e);
        calcHeight(newRoot);
        return newRoot;
    }

    private Node<E> rotateLeft(Node<E> e){
        Node<E> newRoot = e.right;
        e.right = newRoot.left;
        newRoot.left = e;

        calcHeight(e);
        calcHeight(newRoot);
        return newRoot;
    }

    public String toString(){
        return inOrder();
    }

    public String inOrderTree(){
        if(root == null) {return "";};
        StringBuilder build = new StringBuilder();
        inOrderTreeRec(root, build, 0);
        return build.substring(0, build.length() - 1);
    }

    public void inOrderTreeRec(Node<E> current, StringBuilder build, int indent){
        if(current.left != null){ inOrderTreeRec(current.left, build, indent + 3); }
        for(int i = 0; i < indent; i ++) { build.append(" ");}
        build.append(current.data + " " + current.height + "\n");
        if(current.right != null){ inOrderTreeRec(current.right, build, indent + 3); }
    }

    public String inOrder(){
        if(root == null) {return "";}
        StringBuilder build = new StringBuilder();
        inOrderRec(root, build);
        return build.substring(0, build.length() - 1);
    }

    private void inOrderRec(Node<E> current, StringBuilder build){
        if(current.left != null){inOrderRec(current.left, build);}
        build.append(current.data.toString() + "\n");
        if(current.right != null){inOrderRec(current.right, build);}
        return;
    }
}