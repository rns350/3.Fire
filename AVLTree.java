import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;

public class AVLTree<E extends Comparable<E>>{
    private class Node<E extends Comparable<E>>{
        Node<E> left, right, parent;
        E data;
        int height;
        Node(E data){
            this.data = data;
        }
    }

    Node<E> head;
    int size;
    
    private int height(Node<E> check){
        if (check == null){
            return 0;
        }
        else{
            return check.height;
        }
    }

    private void findHeight(Node<E> check){
        check.height = Math.max(height(check.left), height(check.right)) + 1;
    }

    public int insert(E data){

        if(data == null){
            return -1;
        }

        if(head == null){
            head = new Node<E>(data);
            head.height = 0;
            size ++;
            return 0;
        }

        Node<E> previous = null;
        Node<E> current = head;
        int compare = 0;
        while(current != null){
            previous = current;
            compare = data.compareTo(current.data);
            if(compare > 0){ current = current.right; }
            else if(compare < 0){ current = current.left; }
            else{ return -2;}
        }

        current = new Node<E>(data);
        current.parent = previous;

        if(compare > 0){ previous.right = current; }
        else{ previous.left = current; }

        current.height = 1;
        size ++;
        update(current.parent);
        return 0;
    }

    private void update(Node<E> current){
        int older, newer;
        do{
            older = height(current);
            if(!isBalanced(current)){
                current = rotate(current);
                findHeight(current.left);
                findHeight(current.right);
            }
            findHeight(current);
            newer = height(current);
            current = current.parent;
        }
        while(older != newer && current != null);
    }

    private boolean isBalanced(Node<E> check){
        return Math.abs(height(check.left) - height(check.right)) < 2;
    }

    public String toString(){
        StringBuilder info = new StringBuilder();
        inOrder(head, info, 0);
        return info.toString();
    }

    public void inOrder(Node<E> current, StringBuilder info, int tabs){
        if(current == null){
            return;
        }
        inOrder(current.left, info, tabs + 1);
        for(int i = tabs; i > 0; i --){
            info.append("\t");
        }
        info.append(current.data.toString() + " " + height(current) + "\n");
        inOrder(current.right, info, tabs + 1);
    }
}
