import java.lang.*;
import java.util.*;

class GameClass{
    enum growth{
        POOR, AVG, GOOD;
    }
    private growth fortGrowth, refGrowth, willGrowth, attackGrowth;
    int hitDie, skillGain;

    public GameClass(String atk, String fort, String ref, String will, int skillGain, int hitDice){
        attackGrowth = getGrowth(atk);
        fortGrowth = getGrowth(fort);
        refGrowth = getGrowth(ref);
        willGrowth = getGrowth(will);
        if(fortGrowth == AVG || refGrowth == AVG || willGrowth == AVG){
            throw new IllegalArgumentException("save growth can only be good or poor");
        }
        this.skillGain = skillGain;
        this.hitDice = hitDice;
    }

    private growth getGrowth(String grow){
        switch (grow) {
            case grow.equalsIgnoreCase("poor"):
                return POOR;
            case grow.equalsIgnoreCase("avg"):
                return AVG;
            case grow.equalsIgnoreCase("good"):
                return GOOD;
            default:
                throw new IllegalArgumentException("input must be of form poor, avg, or good");
        }
    }

    public int getFort(int level){
        return getSave(fortGrowth, level);
    }

    public int getRef(int level){
        return getSave(refGrowth, level);
    }

    public int getWill(int level){
        return getSave(willGrowth, level);
    }

    public int getBaseAtt(int level){
        switch (attackGrowth){
            case BAD:
                return level / 2;
            case AVG:
                return .75 * level;
            case GOOD:
                return level;
            default:
                return INTEGER.MIN_VALUE;
        }
    }

    public int getSave(growth speed, int level){
        if(growth == POOR){
            return level / 3;
        }
        else{
            return (level / 2) + 2;
        }
    }

}