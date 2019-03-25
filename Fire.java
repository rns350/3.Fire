import java.util.*;
import java.io.*;
import java.lang.*;

public class Fire{
  public static void main(String [] args){
    String [] players = args;

    AVLTree<Integer> binary = new AVLTree<Integer>();
    System.out.println(binary.insert(5));
    System.out.println(binary.insert(6));
    System.out.println(binary.insert(7));
    System.out.println(binary.insert(3));
    System.out.println(binary.insert(9));
    System.out.println(binary.insert(4));
    System.out.println(binary.insert(2));
    System.out.println(binary.insert(8));
    System.out.println(binary.insert(0));
    System.out.println(binary.insert(1));

    System.out.println(binary.toString());

    //Fight fight = new Fight(players);
    //fight.start();
  }
}

