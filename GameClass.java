Public Abstract Class GameClass{
    int level;
    static final Abstract int[] fortLevels, refLevels, willLevels, attackLevels;

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

}