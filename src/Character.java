import java.util.*;
import java.io.Serializable;
import java.nio.charset.Charset;

enum Skill 
    {
        APPRAISE(1), BALANCE(2), BLUFF(3), CLIMB(4), CONCENTRATION(5), 
        DECIPHER_SCRIPT(6), DIPLOMACY(7), DISABLE_DEVICE(8),
        DISGUISE(9), ESCAPE_ARTIST(10), fORGERY(11), GATHER_INFORMATION(12),
        HANDLE_ANIMAL(13), HEAL(14), HIDE(15), INTIMIDATE(16), JUMP(17), 
        LISTEN(18), MOVE_SILENTLY(19), OPEN_LOCK(20), RIDE(21), 
        SEARCH(22), SENSE_MOTIVE(23), SLEIGHT_OF_HAND(24), SPELLCRAFT(25), 
        SPOT(26), SURVIVAL(27), SWIM(28), TUMBLE(29), USE_MAGIC_DEVICE(30), 
        USE_ROPE(31), KNOW_ARCANA(32), KNOW_DUNGEONEERING(33), KNOW_ENGENEERING(34), 
        KNOW_GEOGRAPHY(35), KNOW_HISTORY(36), KNOW_LOCAL(37), KNOW_NATURE(38), 
        KNOW_NOBILITY(39), KNOW_PLANES(40), KNOW_RELIGION(41);

        public static final EnumMap<Skill, Integer> skillValue = new EnumMap<Skill, Integer>(Skill.class);
        static {
            for(Skill s : Skill.values()){
                skillValue.put(s, s.getValue());
            }
        }

        private int value;
        private Skill(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

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

public class Character implements Serializable{
    private static final long serialVersionUID = 436861726163746572L;
    private String name;
    private int str, dex, con, intel, wis, cha;
    private int [] skillRanks;
    private int skillPoints;
    public Character(String name, int str, int dex, int con, int intel, int wis, int cha){
        this.name = name.trim();
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
        skillRanks = new int[Skill.KNOW_RELIGION.getValue()];
    }

    public int rollSkill(Skill type){

        switch(type){
        case CLIMB:
        case JUMP:
        case SWIM:
            return Dice.roll(20) + getRanks(type) + strMod();
        case BALANCE:
        case ESCAPE_ARTIST:
        case HIDE:
        case MOVE_SILENTLY:
        case OPEN_LOCK:
        case RIDE:
        case SLEIGHT_OF_HAND:
        case TUMBLE:
        case USE_ROPE:
            return Dice.roll(20) + getRanks(type) + dexMod();
        case APPRAISE:
        case DECIPHER_SCRIPT:
        case DISABLE_DEVICE:
        case fORGERY:
        case SEARCH:
        case SPELLCRAFT:
        }
        return 0;
    }

    public int getRanks(Skill type){
        return skillRanks[type.getValue()];
    }

    public void enterRanks(){
        while (true){
            System.out.println();
        }
    }

    public String getName(){
        return name;
    }

    public boolean equalsName(String s){
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
        return wis/5 - 2;
    }

    public int chaMod(){
        return cha/2 - 5;
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
            message = validateName(name, party);
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
        if(party.containsName(name.trim())) { 
            return "Two Characters in the same party can't share a name.  Try again.";
        }
        return validateName(name);
    }

    private static String validateName(String name){
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

    public static void main(String [] args){
        System.out.println(Skill.skillValue.get(Skill.APPRAISE));
    }
}