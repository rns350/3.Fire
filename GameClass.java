abstract class GameClass{
    int level;
    static final abstract int[] fortLevels, refLevels, willLevels, attackLevels;
    static final abstract int hitDie, skillGain;

    public int getFort(){
        return fortLevels[level - 1];
    }

    public int getRef(){
        return refLevels[level - 1];
    }

    public int getWill(){
        return willLevels[level - 1];
    }

    public int getBaseAtt(){
        return attackLevels[level - 1];
    }

    public int getLevel(){
        return level;
    }

    public abstract String Abilities();

}