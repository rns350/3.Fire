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
            return current;
        }
        if(i > 0){
            current.right = recAdd(data, current.right);
            return current;
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
            return current;
        }
        if(i > 0){
            current.right = removeRec(data, current.right);
            return current;
        }
        size --;
        if(current.left == null && current.right == null){return null;}
        else if(current.right == null){return current.left;}
        else if(current.left == null){return current.right;}
        Node<E> replace = current.left;
        if(replace.right != null){
            Node<E> parent = replace;
            replace = replace.right;
            while(replace.right != null){
                parent = parent.right;
                replace = replace.right;
            }
            parent.right = null;
        }
        replace.right = current.right;
        replace.left = current.left;
        return replace;
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
        System.out.println(tree.get(5) + "\n");
        tree.add(3);
        tree.add(2);
        tree.add(4);
        tree.add(7);
        tree.add(6);
        tree.add(8);
        System.out.println(tree + "\n");
        tree.remove(6);
        tree.remove(2);
        System.out.println(tree + "\n");
        tree.remove(5);
        System.out.println(tree + "\n");
        System.out.println(tree.get(8) + "\n");
        System.out.println(tree.remove(5) + "\n");
        System.out.println(tree + "\n");

    }
}