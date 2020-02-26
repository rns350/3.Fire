import java.io.Serializable;
public class Skill implements Comparable<Skill>, Serializable{
    public final static long serialVersionUID = 27819283742617L;
    private String name;
    private Character.StatType stat;
    boolean ACPenalty;
    boolean crossClass;
    double ACPenaltyMod;
    public Skill(String name, Character.StatType stat, boolean ACPenalty, double ACPenaltyMod){
        this.name = name;
        this.stat = stat;
        this.ACPenalty = ACPenalty;
        this.ACPenaltyMod = ACPenaltyMod;
    }

    public int roll(Character c){
        int mod = 0;
        switch(stat){
            case STR: mod = c.strMod(); break;
            case DEX: mod = c.dexMod(); break;
            case CON: mod = c.conMod(); break;
            case INTEL: mod = c.intMod(); break;
            case WIS: mod = c.wisMod(); break;
            case CHA: mod = c.chaMod(); break;
        }
        return mod + Dice.roll(20) + c.skillBonus(this);
    }

    public int compareTo(Skill compare){
        return this.name.compareToIgnoreCase(compare.name);
    }

    public boolean equals(Skill compare){
        return compareTo(compare) == 0;
    }

    public String toString(){
        return name;
    }
}