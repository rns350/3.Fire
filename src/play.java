import java.util.*;
import java.io.*;

class Play{
    ArrayList<GameClass> classes = new ArrayList<GameClass>();
    public static void main(String args[]){
            classReader();
    }

    public static void classReader(){
            File classFile = new File("classes.csv");
            Scanner scan = makeScan(classFile);
            scan.nextLine();
            String data;
            String className;
            int[] fortLevels;
            int[] refLevels;
            int[] willLevels;
            int[] attackLevels;
            int hitDie;
            int baseSkills;
            while(scan.hasNextLine()){
                className = scan.nextLine();
                fortLevels = (scan.nextLine()).split(",");
            }
    }

    public static Scanner makeScan(File f){
        Scanner scan = null;
        try{
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.err.println(f + " file missing; Program cannot continue");
            System.exit(1);
        }
        return scan;
    }
}