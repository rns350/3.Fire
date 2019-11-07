public class AVLTree<E extends Comparable<E>>{
    private class Node<E>{
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
        recAdd(data, root);
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
            return current.left;
        }
        if(i > 0){
            current.right = recAdd(data, current.right);
            return current.right;
        }
        return current;
    }

    public E get(E data){
        return getNode(data).data;
    }

    private Node<E> getNode(E data){
        Node<E> temp = root;
        int i = data.compareTo(root.data);
        while(temp != null && i != 0){
            if(i < 0){
                temp = temp.left;
                i = data.compareTo(temp.data);
            }
            else{
                temp = temp.right;
                i = data.compareTo(temp.data);
            }
        }
        return temp;
    }

    public E remove(E data){
        if(root == null) {return null;}
        Node<E> temp = getNode(data);
        if(temp == null) {return null;}
        if(temp.right == null && temp.left == null){
            size --;
            if(temp.parent == null){return temp.data;}
        }
    }

    private Node<E> getMax(Node<E> current){
        if(current.right == null){return current;}
        return getMax(current.right);
    }

    public String toString(){
        return inOrder();
    }

    public String inOrder(){
        if(root == null) {return "";}
        StringBuilder build = new StringBuilder();
        inOrderRec(root, build);
        return build.substring(0, build.length() - 1);
    }

    public void inOrderRec(Node<E> current, StringBuilder build){
        if(current.left != null){inOrderRec(current.left, build);}
        build.append(current.data.toString() + "\n");
        if(current.right != null){inOrderRec(current.right, build);}
        return;
    }

    public static void main(String [] args){
        AVLTree<Integer> tree = new AVLTree<Integer>();
        tree.add(5);
        System.out.println(tree.get(5));
        tree.add(3);
        tree.add(2);
        tree.add(4);
        tree.add(7);
        tree.add(6);
        tree.add(8);
        System.out.println(tree);
        tree.remove(6);
        tree.remove(2);
        System.out.println(tree);
    }
}