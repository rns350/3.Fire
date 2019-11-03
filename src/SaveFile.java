import java.util.*;
import java.io.*;
import java.nio.file.*;

public class SaveFile implements Serializable {
    final static long serialVersionUID = 8372057649023L;
    private String name;
    private Party party;

    public SaveFile(String name, Party party) {
        this.name = name;
        this.party = party;
    }

    public boolean verifySave() {
        if(party == null){
            return false;
        }
        while (true) {
            System.out.println(this);
            System.out.println("\nDoes this look correct? (Y/N) (Q to quit)\n");
            switch (Fire.in.nextLine().toLowerCase()) {
            case "y":
                System.out.println();
                return true;
            case "n":
                System.out.println();
                editSave();
                break;
            case "q":
                System.out.println();
                return false;
            default:
                System.out.println("\nInvalid input.  Try again.");
                break;
            }
        }
    }

    public boolean editParty(){
        return party.editParty();
    }

    public void editSave(){
        while (true) {
            System.out.println("select an option\n----------------\n1 - edit party\n2 - replace party\n3 - edit name\n4 - view current save file\n5 - done\n");
            switch (Fire.in.nextLine().toLowerCase()) {
            case "1":
                System.out.println();
                party.editParty();
                break;
            case "2":
                System.out.println();
                Party temp = Party.inputParty();
                if(temp != null) {
                    System.out.println("Replacing the old party...\n");
                    party = temp;
                }
                break;
            case "3":
                String message;
                String tempS;
                while(true){
                    System.out.println("\nWhat will the name of this campaign be? (\\Q to quit)\n");
                    tempS = Fire.in.nextLine();
                    System.out.println();
                    if(tempS.equalsIgnoreCase("\\q")) {break;}
                    message = SaveFile.validateName(tempS);
                    if(message == null) {break;}
                    else {System.out.println(message);}
                }
                if(!tempS.equalsIgnoreCase("\\q")) {
                    System.out.println("Replacing name \"" + name + "\" with name \"" + tempS + "\"\n");
                    this.name = tempS;
                }
                break;
            case "4":
                System.out.println("\n" + this + "\n");
                break;
            case "5":
                System.out.println();
                return;
            default:
                System.out.print("\nInvalid input.  Try again.");
                break;
            }
        }
    }

    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append(name + "\n");
        for (int i = 0; i < name.length(); i++) {
            build.append("-");
        }
        build.append("\n\n");
        build.append(party.toString());
        return build.toString();
    }

    public boolean saveFile(){
        try{
            Path saveDirectory = Paths.get(MainMenu.SAVE_FILE_LOCATION);
            if(!Files.exists(saveDirectory)){
                Files.createDirectory(saveDirectory);
            }
            String[] files = MainMenu.getSaves();
            for(String s : files){
                if(s.equals(name + ".txt")){
                    while(true){
                        System.out.println("WARNING: SAVE FILE \"" + name + "\" EXISTS.\n"
                                            + "WOULD YOU LIKE TO OVERWRITE IT? (Y/N)\n");
                        String input = Fire.in.nextLine().toLowerCase();
                        if(input.equals("y")) {break;}
                        if(input.equals("n")) {return false;}
                        System.out.println("\nInvalid input. Try again.");
                    }
                    break;
                }
            }
            System.out.println();
            File save = new File(MainMenu.SAVE_FILE_LOCATION + "/" + name + ".txt");
            FileOutputStream dest = new FileOutputStream(save);
            ObjectOutputStream out = new ObjectOutputStream(dest);

            out.writeObject(this);
            out.close();
            dest.close();

            return true;
        }
        catch (Exception e){
            System.out.println("Saving file failed");
            return false;
        }
    }

    public static String validateName(String name){
        if(name.length() == 0) {
            return "A Campaign needs a name.  Try again";
        }
        int i;
        char c;
        for(i = 0; i < name.length(); i ++){
            c = name.charAt(i);
            if(c < ' ' || c > '~'){
                return "A Campaign's name can only contain printable ASCII characters.\n"
                        + "Spaces are the only acceptable white space.\n"
                        + "Try again.";
            }
        }
        int length = name.trim().length();
        if(length == 0){
            return "A Campaigns's name can not only be white space.  Try again.";
        }
        if(length > 20){
            return "A Campaign's name can not be more than 20 characters,\n"
                    + "excluding leading and trailing white space. Try again.";
        }
        return null;
    }
}