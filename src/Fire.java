import java.util.*;
import java.io.*;
import java.nio.file.*;
public class Fire{
    static String currentFile;
    static SaveFile currentSave;
    static Scanner in = new Scanner(System.in);
    public static void main(String [] args){
        System.out.println();
        currentSave = MainMenu.launch();
        System.out.println(currentSave + "\n");
        while(true){
            System.out.println("Please select an option\n-----------------------\n"
                                + "1 - edit party\n2 - view current save\n3 - save\n"
                                 + "4 - main menu\n");
            switch(in.nextLine()){
            case "1":
                System.out.println();
                currentSave.editParty();
                break;
            case "2":
                System.out.println("\n" + currentSave + "\n");
                break;
            case "3":
                System.out.println();
                currentSave.saveFile();
                break;
            case "4":
                System.out.println();
                if(currentSave.safeToExit()){
                    currentSave = MainMenu.launch();
                    System.out.println(currentSave + "\n");
                }
                break;
            default: 
                System.out.println("Invalid input. Try again.");
            }
        }
    }
}