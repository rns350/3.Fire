import java.util.*;
import java.io.*;
import java.lang.*;

public class Fire{
  public static void main(String [] args){
    String [] players = args;

    AVLTree<Integer> binary = new AVLTree<Integer>();
    binary.insert(5);
    binary.insert(10);
    binary.insert(1);
    binary.insert(11);
    binary.insert(9);
    binary.insert(2);
    binary.insert(0);
    binary.insert(12);
    binary.insert(13);
    binary.insert(14);
    binary.insert(-5);
    binary.insert(-10);
    binary.insert(-1);
    binary.insert(-11);
    binary.insert(-9);
    binary.insert(-2);
    binary.insert(0);
    binary.insert(-12);
    binary.insert(-13);
    binary.insert(-14);

    System.out.println(binary.toString());

    //Fight fight = new Fight(players);
    //fight.start();
  }
}

