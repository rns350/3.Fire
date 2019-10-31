public class Initiative extends Comparable<Initiative>{
    Character person;
    int iniRoll;

    public Initiative(Character p){
        iniRoll = p.rollInitiative();
        person = p;
    }

    public int compareTo(Character p){
        int result = iniRoll - p.iniRoll;
        if(result < 0) return -1;
        if(result > 0) return 1;
        result = person.dexMod() - p.dexMod();
        if(result < 0) return -1;
        if(result > 0) return 1;
        return 0;
    }
}