import java.util.*;
import java.io.Serializable;
import java.nio.charset.Charset;

public class Character implements Serializable{
    enum StatType{
        STR("str"), DEX("dex"), CON("con"), WIS("wis"), INTEL("int"), CHA("cha");
    
        public static final EnumMap<StatType, String> statValue = new EnumMap<StatType, String>(StatType.class);
        static {
            for(StatType s : StatType.values()){
                statValue.put(s, s.getValue());
            }
        }
    
        private String value;
        private StatType(String value){
            this.value = value;
        }
    
        public String getValue(){
            return value;
        }
    }
    private static final long serialVersionUID = 436861726163746572L;
    private String name;
    private int str, dex, con, intel, wis, cha, level;
    private int skillPoints;
    private ArrayList<Rank> ranks;
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

    public boolean equals(String s){
        return name.equals(s);
    }

    public int strMod(){
        return str/2 - 5;
    }

    public int dexMod(){
        return dex/2 - 5;
    }

    public int conMod(){
        return con/2 - 5;
    }

    public int intMod(){
        return intel/2 - 5;
    }

    public int wisMod(){
        return wis/2 - 5;
    }

    public int chaMod(){
        return cha/2 - 5;
    }

    public int rollInitiative(){
        return dexMod() + Dice.roll(20);
    }

    public int skillBonus(Skill s){
        Rank temp = new rank(s);
        int index = ranks.indexOf(temp);
        if(index != -1){
            return (int) ranks.get(index).ranks();
        }
        return 0;
    }

    public float addPoints(Skill s, int points){
        if(points > skillPoints || points > (level + 3)) {return -1;}
        if(ranks == null){ ranks = new ArrayList<Rank>();}
        Rank temp = new Rank(s);
        int index = ranks.indexOf(temp);
        if(index != -1){
            Rank increase = ranks.get(index);
            float total = increase.ranks();
            if(increase.classSkill()) {total += points;}
            else {
                total *= 2;
                total += points;
            }
            if(total > level + 3){
                return -1;
            }
            increase.increase(points);
            return increase.ranks();
        }
        temp.increase(points);
        ranks.add(temp);
        return points;
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

    public boolean edit(Party party){
        int choice = 0;
        boolean wasEdited = false;
        while(true){
            System.out.println("Choose a feature to edit (Q to quit, NAME for name, STAT_NAME for stat)"
                            + "\n-----------------------------------------------------------------------");
            System.out.println("\n" + this + "\n");
            switch(Fire.in.nextLine().toLowerCase()){
            case "name":
                System.out.println();
                if(editName(party)) {wasEdited = true;}
                break;
            case "str":
                System.out.println();
                choice = scanAbilityScore("new strength");
                if(choice == 0) {continue;}
                else {
                    str = choice;
                    wasEdited = true;
                }
                break;
            case "dex":
                System.out.println();
                choice = scanAbilityScore("new dexterity");
                if(choice == 0) {continue;}
                else {
                    dex = choice;
                    wasEdited = true;
                }
                break;
            case "con":
                System.out.println();
                choice = scanAbilityScore("new constitution");
                if(choice == 0) {continue;}
                else {
                    con = choice;
                    wasEdited = true;
                }
                break;
            case "int":
                System.out.println();
                choice = scanAbilityScore("new intelligence");
                if(choice == 0) {continue;}
                else {
                    intel = choice;
                    wasEdited = true;
                }
                break;
            case "wis":
                System.out.println();
                choice = scanAbilityScore("new wisdom");
                if(choice == 0) {continue;}
                else {
                    wis = choice;
                    wasEdited = true;
                }
                break;
            case "cha":
                System.out.println();
                choice = scanAbilityScore("new charisma");
                if(choice == 0) {continue;}
                else {
                    cha = choice;
                    wasEdited = true;
                }
                break;
            case "q":
                System.out.println();
                return wasEdited;
            default:
                System.out.println("\nPlease choose a valid option from the list");
                break;
            }
        }
    }

    public boolean editName(Party party){
        String name;
        String message;
        while(true){
            System.out.println("Please input a new name (\\Q to quit)\n");
            name = Fire.in.nextLine();
            System.out.println();
            if(name.equalsIgnoreCase("\\q")){
                return false;
            }
            message = party.validPartyName(name);
            if(message == null){
                this.name = name.trim();
                return true;
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
            String message = party.validPartyName(name);
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

    public static String validName(String name){
        if(name.length() == 0) {
            return "A Character needs a name.  Try again";
        }
        int i;
        char c;
        for(i = 0; i < name.length(); i ++){
            c = name.charAt(i);
            if(c < ' ' || c > '~'){
                return "A Character's name can only contain printable ASCII characters.\n"
                        + "Spaces are the only acceptable white space.\n"
                        + "Try again.";
            }
        }
        int length = name.trim().length();
        if(length == 0){
            return "A Character's name cannot only be white space.  Try again.";
        }
        if(length > 20){
            return "A Character's name cannot be more than 20 characters,\n"
                    + "excluding leading and trailing white space. Try again.";
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
}