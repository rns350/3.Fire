class Barabarian Extends GameClass{
     fortLevels = [2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12];
     refLevels = [0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6];
     willLevels = [0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6];
     attackLevels = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20];

    Barbarian(int level){
        this.level = level;
        hitDie = 12;
        skillGain = 4;
    }

    public String Abilities(){
         return "Strength is important for Barbarians because of its role in combat, and 
         several barbarian class skills are based on strength.  Dexterity is also useful to barbarians, 
         especially those who wear light armor.  Wisdom is also important for several of the barbarian's 
         class skills.  A high constitution score lets a barbarian rage longer (and live longer, because 
         it gives him more hit points)";
    }
}