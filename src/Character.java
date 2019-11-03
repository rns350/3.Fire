import java.util.*;
import java.io.Serializable;
public class Character implements Serializable{
    private static final long serialVersionUID = 436861726163746572L;
    private String name;
    private int str, dex, con, intel, wis, cha;
    public Character(String name, int str, int dex, int con, int intel, int wis, int cha){
        this.name = name.trim();
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
    }

    public String getName(){
        return name;
    }

    public boolean equalsName(String s){
        return name.equals(s);
    }

    public int dexMod(){
        return dex/2 - 5;
    }

    public int rollInitiative(){
        return dexMod() + (int)(Math.random() * 20 + 1);
    }

    public String toString(){
        StringBuilder build = new StringBuilder();
        build.append(name + "\n");
        for(int i = 0; i < name.length(); i ++){
            build.append("-");
        }
        build.append("\n");
        build.append("str: " + str + "\n");
        build.append("dex: " + dex + "\n");
        build.append("con: " + con + "\n");
        build.append("int: " + intel + "\n");
        build.append("wis: " + wis + "\n");
        build.append("cha: " + cha);
        return build.toString();
    }

    public void edit(Party party){
        int choice = 0;
        while(true){
            System.out.println("Choose a feature to edit (Q to quit, NAME for name)"
                            + "\n--------------------------------------------------");
            System.out.println("\n" + this + "\n");
            switch(Fire.in.nextLine().toLowerCase()){
            case "name":
                System.out.println();
                editName(party);
                break;
            case "str":
                System.out.println();
                choice = scanAbilityScore("new strength");
                if(choice == 0) {continue;}
                else {str = choice;}
                break;
            case "dex":
                System.out.println();
                choice = scanAbilityScore("new dexterity");
                if(choice == 0) {continue;}
                else {dex = choice;}
                break;
            case "con":
                System.out.println();
                choice = scanAbilityScore("new constitution");
                if(choice == 0) {continue;}
                else {con = choice;}
                break;
            case "int":
                System.out.println();
                choice = scanAbilityScore("new intelligence");
                if(choice == 0) {continue;}
                else {intel = choice;}
                break;
            case "wis":
                System.out.println();
                choice = scanAbilityScore("new wisdom");
                if(choice == 0) {continue;}
                else {wis = choice;}
                break;
            case "cha":
                System.out.println();
                choice = scanAbilityScore("new charisma");
                if(choice == 0) {continue;}
                else {cha = choice;}
                break;
            case "q":
                System.out.println();
                return;
            default:
                System.out.println("\nPlease choose a valid number from the list");
                break;
            }
        }
    }

    public void editName(Party party){
        String name;
        String message;
        while(true){
            System.out.println("Please input a new name (\\Q to quit)\n");
            name = Fire.in.nextLine();
            System.out.println();
            if(name.equalsIgnoreCase("\\q")){
                return;
            }
            message = validateName(name, party);
            if(message == null){
                this.name = name.trim();
                return;
            }
            System.out.println(message);

        }
    }

    public static Character inputPartyCharacter(Party party){
        String name;
        int str, dex, con, intel, wis, cha;
        while (true){
            System.out.print("Enter the Character's name (\\Q to quit): ");
            name = Fire.in.nextLine();
            if(name.equalsIgnoreCase("\\q")) {System.out.println(); return null;}
            String message = validateName(name, party);
            if(message == null) {break;}
            else{System.out.println("\n" + message);}
        }
        name = name.trim();
        if ((str = scanAbilityScore("strength")) == 0) {return null;}
        if ((dex = scanAbilityScore("dexterity")) == 0) {return null;}
        if ((con = scanAbilityScore("constitution")) == 0) {return null;}
        if ((intel = scanAbilityScore("intelligence")) == 0) {return null;}
        if ((wis = scanAbilityScore("wisdom")) == 0) {return null;}
        if ((cha = scanAbilityScore("charisma")) == 0) {return null;}
        System.out.println();
        return new Character(name, str, dex, con, intel, wis, cha);
    }

    private static String validateName(String name, Party party){
        if(party.containsName(name)) { 
            return "Two Characters in the same party can't share a name.  Try again.";
        }
        return validateName(name);
    }

    private static String validateName(String name){
        if(name.length() == 0) {
            return "A Character needs a name.  Try again";
        }
        String temp = name.trim();
        if(temp.length() == 0){
            return "A Character's name can not only be white space.  Try again.";
        }
        int i;
        char c;
        for(i = 0; i < temp.length(); i ++){
            c = temp.charAt(i);
            if(c == '[' || c == ']'){
                return "A Character's name cannot include [, ], any arrow keys,\nor any functional keyboard buttons";
            }
            if(c >= '!' && c <= '~'){
                break;
            }
        }
        if(i == temp.length()){
            System.out.println("Hi");
            return "A Character's name must have at least one readable character. Try again.";
        }
        return null;
    }

    public static Character inputCharacter() {
        String name;
        int str, dex, con, intel, wis, cha;
        System.out.print("Enter the Character's name: ");
        name = Fire.in.nextLine();
        if ((str = scanAbilityScore("strength")) == 0) {return null;}
        if ((dex = scanAbilityScore("dexterity")) == 0) {return null;}
        if ((con = scanAbilityScore("constitution")) == 0) {return null;}
        if ((intel = scanAbilityScore("intelligence")) == 0) {return null;}
        if ((wis = scanAbilityScore("wisdom")) == 0) {return null;}
        if ((cha = scanAbilityScore("charisma")) == 0) {return null;}
        System.out.println();
        return new Character(name, str, dex, con, intel, wis, cha);
    }

    public static int scanAbilityScore(String s) {
        String temp;
        int score;
        while (true) {
            System.out.print("Enter the character's " + s + " score (or Q to quit): ");
            temp = Fire.in.nextLine();
            if (temp.equalsIgnoreCase("q")) {
                System.out.println();
                return 0;
            }
            try {
                score = Integer.parseInt(temp);
                if (score <= 0 || score > 20) {
                    System.out.println("\nError: Score must be between 1 and 20.  Try again.\n");
                    continue;
                }
                return score;
            } catch (NumberFormatException e) {
                System.out.println("\nYou must enter an integer.  Try again.\n");
            }
        }
    }

    public static void main(String [] args){
        Character Raffi = new Character("Raffi", 10, 20, 12, 14, 13, 16);
        int[] results = new int[20];
        for(int i = 0; i < 1000000; i ++){
            results[Raffi.rollInitiative() - 6] ++;
        }
        System.out.println(Arrays.toString(results));

    }
}