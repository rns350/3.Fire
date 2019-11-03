import java.util.ArrayList;
import java.io.Serializable;
public class Party extends ArrayList<Character> implements Serializable{
    static final long serialVersionUID = 278594039485673L;

    public boolean add(Character c) throws IllegalArgumentException {
        if (this.containsName(c.getName())) {throw new IllegalArgumentException("Error: You cannot add two characters with the same name to the same party");}
        return super.add(c);
    }
    
    public void add(int index, Character c) throws IllegalArgumentException {
        if (this.containsName(c.getName())) {throw new IllegalArgumentException("Error: You cannot add two characters with the same name to the same party");}
        super.add(index, c);
    }

    public Character set(int index, Character c) throws IllegalArgumentException {
        int place = getName(c.getName());
        if(place != index && place != -1) {throw new IllegalArgumentException("Error: You cannot add two characters with the same name to the same party");}
        return super.set(index, c);
    }

    public int getName(String name){
        int index = 0;
        for( Character c : this ){ 
            if(c.getName().equals(name)) {return index; }
            index ++;
        }
        return -1;
    }

    public boolean containsName(String name){
        for( Character c : this ){ if(c.getName().equals(name)) { return true; }}
        return false;
    }

    public boolean containsName(Character c){
        return this.containsName(c.getName());
    }

    public boolean editCharacter(){
        String name;
        int index;
        while(true){
            System.out.println("Please enter the name of the character you wish to edit, or Q to quit\n");
            name = Fire.in.nextLine();
            System.out.println();
            index = this.getName(name);
            if(name.equalsIgnoreCase("q")){return false;}
            if(index != -1){
                get(index).edit(this);
                return true;
            }
            else{
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    public boolean editParty(){
        Character c;
        while(true){
            System.out.println("Please select an option\n-----------------------\n"
                                + "1 - add character\n2 - remove character\n3 - replace character\n"
                                + "4 - edit character\n5 - see current party\n6 - finish editing party\n");
            switch(Fire.in.nextLine()){
            case "1":
                System.out.print("\nMaking party member " + (size() + 1) + "\n--------------------");
                int i = size() + 1;
                while(i != 0){
                    System.out.print("-");
                    i /= 10;
                }
                System.out.println();
                c = Character.inputPartyCharacter(this);
                if (c != null) {add(c);}
                break;
            case "2":
                System.out.println();
                removeCharacter();
                break;
            case "3":
                System.out.println();
                replaceCharacter();
                break;
            case "4":
                System.out.println();
                editCharacter();
                break;
            case "5":
                System.out.println("\n" + this + "\n");
                break;
            case "6":
                if(size() == 0){
                    System.out.println("\nThere must be at least 1 member in a party. Try again.\n");
                    break;
                }
                System.out.println();
                return true;
            default:
                System.out.println("\nInvalid input. Try again");
                break;
            }
        }
    }

    public static Party inputParty(){
        System.out.println("Creating party");
        Character c;
        Party party = new Party();
        while(true){
            System.out.println("Please select an option\n-----------------------\n"
                                + "1 - add character\n2 - remove character\n3 - replace character\n4 - edit character\n5 - see current party"
                                + "\n6 - finish inputting party\n7 - quit party creation\n");
            switch(Fire.in.nextLine()){
            case "1":
                System.out.print("\nMaking party member " + (party.size() + 1) + "\n--------------------");
                int i = party.size() + 1;
                while(i != 0){
                    System.out.print("-");
                    i /= 10;
                }
                System.out.println();
                c = Character.inputPartyCharacter(party);
                if (c != null) {party.add(c);}
                break;
            case "2":
                System.out.println();
                party.removeCharacter();
                break;
            case "3":
                System.out.println();
                party.replaceCharacter();
                break;
            case "4":
                System.out.println();
                party.editCharacter();
                break;
            case "5":
                System.out.println("\n" + party + "\n");
                break;
            case "6":
                if(party.size() == 0){
                    System.out.println("\nThere must be at least 1 member in a party. Try again.\n");
                    break;
                }
                System.out.println();
                return party;
            case "7":
                System.out.println();
                return null;
            default:
                System.out.println("\nInvalid input. Try again");
                break;
            }
        }
    }

    public boolean removeCharacter(){
        String input;
        int index;
        if(size() == 0) {
            System.out.println("No characters to remove\n");
            return false;
        }
        while (true) {
            System.out.println("Enter the name of the character you would like to remove (or Q to quit removing)\n");
            input = Fire.in.nextLine();
            if (input.equalsIgnoreCase("q")) {
                System.out.println();
                return false;
            }
            index = getName(input);
            if (index == -1) {
                System.out.println("\nInvalid Character Name; try again");
            } else {
                System.out.println("\nRemoving Character" + get(index) + "...\n");
                remove(index);
                return true;
            }
        }
    }

    public boolean replaceCharacter() {
        if(size() == 0){
            System.out.println("No characters to replace\n");
            return false;
        }
        boolean getInput = true;
        String input;
        int index = 0;
        while (getInput) {
            System.out.println("Enter the name of the character you would like to replace\n(or Q to quit replacement)\n");
            input = Fire.in.nextLine();
            if (input.equalsIgnoreCase("q")) {
                System.out.println();
                return false;
            }
            index = getName(input);
            if (index == -1) {
                System.out.println("\nInvalid Character Name; try again");
            } else {
                getInput = false;
            }
        }
        System.out.println("\nReplacing character:\n\n" + get(index) + "\n\nwith new character:");
        Character c = Character.inputPartyCharacter(this);
        if (c == null)
            return false;
        set(index, c);
        System.out.println();
        return true;
    }

    public String toString(){
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            build.append(this.get(i).toString() + "\n\n");
        }
        if(build.length() > 0){
            build.deleteCharAt(build.length() - 1);
            build.deleteCharAt(build.length() - 1);
        }
        return build.toString();
    }
}