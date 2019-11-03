import java.util.*;
import java.io.*;
import java.nio.file.*;
public class MainMenu{
    public static final String SAVE_FILE_LOCATION = "./../data/adventureData";

    public static SaveFile launch(){
        SaveFile currentSave;
        System.out.println();
        while(true) {
            if (filesExist()) {
                currentSave = launchReturningMenu();
                if(currentSave != null) {return currentSave;}
            }
            else{
                if((currentSave = firstTimeLaunch()) != null) {return currentSave;}
            }
        }
    }

    public static SaveFile launchReturningMenu(){
        SaveFile current;
        System.out.println("Select an option\n----------------\n" 
                            + "1 - Load a save file\n2 - Create a new campaign"
                            + "\n3 - Delete a save file\n4 - Quit\n");
        switch (Fire.in.nextLine()) {
        case "1":
            System.out.println();
            if ((current = loadSave()) != null) {return current;}
            break;
        case "2":
            System.out.println();
            if ((current = newCampaign()) != null) {return current;}
            break;
        case "3":
            System.out.println();
            deleteSave();
            if (!filesExist()) {return null;}
            break;
        case "4":
            System.out.println("\nGoodbye!");
            System.exit(0);
        default:
            System.out.println("\nInvalid input.  Try again.\n");
            break;
        }
        return null;
    }

    public static SaveFile firstTimeLaunch(){
        SaveFile current;
        System.out.println("No saves detected.  Would you like to create a campaign? (Y/N)\n");
        switch (Fire.in.nextLine().toLowerCase()) {
        case "y":
            System.out.println();
            if ((current = newCampaign()) != null) {return current;}
            break;
        case "n":
            System.out.println("\nGoodbye!");
            System.exit(0);
        default:
            System.out.print("\nInvalid input.  Try again.\n");
            break;
        }
        return null;
    }

    public static boolean filesExist(){
        File saveDirectory = new File(SAVE_FILE_LOCATION);
        return (saveDirectory.exists() && saveDirectory.list().length != 0);
        
    }

    public static String [] getSaves(){
        File saveDirectory = new File(SAVE_FILE_LOCATION);
        return (saveDirectory.list());
    }

    private static SaveFile loadSave() {
        boolean getInput = true;
        String input;
        int index = 0;
        String [] saveFiles = getSaves();
        while (getInput) {
            System.out.println("Existing saves\n--------------");
            for (String f : saveFiles) {System.out.println(f.substring(0, f.length() - 4));}
            System.out.println("\nPlease select a campaign to load (or Q to quit loading)\n");
            input = Fire.in.nextLine() + ".txt";
            if (input.equalsIgnoreCase("q.txt")){
                System.out.println();
                return null;
            }
            for (String f : saveFiles) {
                if (f.equalsIgnoreCase(input)) {break;}
                index++;
            }
            if (index < saveFiles.length) {getInput = false;}
            else {
                System.out.println("\nInvalid input; try again");
                index = 0;
            }
        }

        try {
            FileInputStream file = new FileInputStream("./../data/adventureData/" + saveFiles[index]);
            ObjectInputStream load = new ObjectInputStream(file);

            SaveFile s = (SaveFile) load.readObject();

            load.close();
            file.close();
            System.out.println();
            return s;
        } 
        catch (ClassNotFoundException e) {System.out.println("Class not found.");} 
        catch (InvalidClassException e) {System.out.println("Invalid Class");} 
        catch (StreamCorruptedException e) {System.out.println("Stream Corrupted");} 
        catch (OptionalDataException e) {System.out.println("Optional data exception");} 
        catch (IOException e) {System.out.println("IO Exception");}
        return null;
    }

    private static SaveFile newCampaign() {
        String name;
        String message;
        while(true){
            System.out.println("What will the name of this campaign be? (\\Q to quit)\n");
            name = Fire.in.nextLine();
            if(name.equalsIgnoreCase("\\q")) {
                System.out.println();
                return null;
            }
            message = SaveFile.validateName(name);
            if(message == null) {break;}
            else {System.out.println("\n" + message);}
        }
        System.out.println();
        Party party = Party.inputParty();

        SaveFile current = new SaveFile(name, party);
        if(!current.verifySave()){return null;}
        boolean getInput = true;
        while (getInput) {
            System.out.println("Save Adventure? (Y/N)\n");
            switch (Fire.in.nextLine().toLowerCase()) {
            case "y":
                System.out.println();
                current.saveFile();
                getInput = false;
                break;
            case "n":
                getInput = false;
                break;
            default:
                System.out.println("\nInvalid input.  Try again.");
            }
        }
        getInput = true;
        while(getInput){
            System.out.println("\nWould you like to load this Adventure? (Y/N)\n");
            switch (Fire.in.nextLine().toLowerCase()) {
            case "y":
                System.out.println();
                return current;
            case "n":
                System.out.println();
                return null;
            default:
                System.out.println("\nInvalid input.  Try again.");
            }
        }
        return null;
    }

    private static void deleteSave() {
        boolean getInput = true;
        String input;
        int index = 0;
        String [] saveFiles = getSaves();
        while (getInput) {
            System.out.println("Existing saves\n--------------");
            for (String f : saveFiles) {System.out.println(f.substring(0, f.length() - 4));}
            System.out.println("\nPlease select a campaign to delete (or Q to quit deleting)\n");
            input = Fire.in.nextLine() + ".txt";
            if (input.equalsIgnoreCase("q.txt")){
                System.out.println();
                return;
            }
            for (String f : saveFiles) {
                if (f.equalsIgnoreCase(input)) {break;}
                index++;
            }
            if (index < saveFiles.length) {getInput = false;}
            else {
                System.out.println("\nInvalid input; try again");
                index = 0;
            }
        }
        File toDelete = new File(SAVE_FILE_LOCATION + '/' + saveFiles[index]);
        getInput = true;
        while(getInput){
            System.out.println("\nWARNING: CONTINUING WILL PERMANANTLY DELETE SAVE FILE: " + saveFiles[index].substring(0, saveFiles[index].length() - 4)
                                + "\nARE YOU SURE YOU WISH TO CONTINUE (Y/N)\n");
            switch(Fire.in.nextLine()){
                case "y":
                    toDelete.delete();
                case "n":
                    System.out.println();
                    return;
                default:
                    System.out.println("\nInvalid input.  Try Again.");
            }
        }

    }
}