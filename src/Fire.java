import java.util.*;
import java.io.*;
import java.nio.file.*;
public class Fire{
    static String currentFile;
    static SaveFile currentSave;
    static ArrayList<Character> party;
    static Scanner in = new Scanner(System.in);
    public static void main(String [] args){
        currentSave = MainMenu.launch();
        System.out.println(currentSave);
    }
}