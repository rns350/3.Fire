import java.util.Scanner;
import java.io.*;
public class DataReader{
    public static final String SKILL_FILE_LOCATION = "./../data/ProgramData/skills.csv";
    public static final String SAVE_FILE_LOCATION = "./../data/adventureData";

    public static void initialize(SaveFile current){
        current.setSkills(getSkills());
    }

    public static AVLTree<Skill> getSkills(){
        File f = new File(SKILL_FILE_LOCATION);
        AVLTree<Skill> skills = new AVLTree<Skill>();
        try{
             Scanner scan = new Scanner(f);
             String[] line;
             String name;
             Character.StatType stat;
             boolean ACPenalty;
             double ACPenaltyMod;
             scan.nextLine();
             while(scan.hasNextLine()){
                line = scan.nextLine().split(",");
                name = line[0];
                switch(line[1]){
                    case "STR": {stat = Character.StatType.STR; break;}
                    case "DEX": {stat = Character.StatType.DEX; break;}
                    case "CON": {stat = Character.StatType.CON; break;}
                    case "WIS": {stat = Character.StatType.WIS; break;}
                    case "INT": {stat = Character.StatType.INTEL; break;}
                    case "CHA": {stat = Character.StatType.CHA;break;}
                    default:
                        System.out.println("Error - Skill File has been corrupted.\n" +
                                                        "Please redownload to repair.");
                        return null;
                }
                if(line[2].equals("T")){ ACPenalty = true; }
                else{ ACPenalty = false; }
                ACPenaltyMod = Double.parseDouble(line[3]);
                skills.add(new Skill(name, stat, ACPenalty, ACPenaltyMod));
             }
        }catch(NumberFormatException | IndexOutOfBoundsException e){
            System.out.println("ERROR - Skill File has been corrupted.\n" +
                                            "Please redownload to repair.");
            System.exit(0);
        }catch(FileNotFoundException e){
            System.out.println("ERROR - Skill File is missing.\n" +
                                    "Please redownload to repair.");
            System.exit(0);
        }
        return skills;
    }   
}