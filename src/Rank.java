import java.io.Serializable;

public class Rank implements Comparable<Rank>, Serializable{
    private static final long serialVersionUID = 436861726163741111L;
    private Skill primary;
    private float ranks;
    private int points;
    private boolean classSkill = false;
    public Rank(Skill primary){
        this.primary = primary;
    }

    public float increase(int levels){
        if(classSkill){
            ranks += levels;
        }
        else{
            ranks += (double) levels / 2;
        }
        points += levels;
        return ranks;
    }

    public float decrease(int levels){
        if(classSkill){
            if(ranks - levels < 0) {return -1;}
            ranks -= levels;
        }
        else{
            if(ranks - ((double) levels / 2) < 0) {return -1;}
            ranks -= (double) levels / 2;
        }
        points -= levels;
        return ranks;
    }

    public int clear(){
        ranks = 0;
        oldPoints = points;
        points = 0;
        return oldPoints;
    }

    public Skill skillType(){
        return primary;
    }

    public boolean classSkill(){
        return classSkill;
    }

    public float ranks(){
        return ranks;
    }

    public int compareTo(Rank r){
        return this.primary.compareTo(r.primary);
    }

    public boolean equals(Rank r){
        return this.primary.equals(r.primary);
    }
}