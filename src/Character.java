import java.util.*;
public class Character{
    int str, dex, con, intel, wis, cha;
    public Character(int str, int dex, int con, int intel, int wis, int cha){
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.cha = cha;
    }

    public int dexMod(){
        return dex/2 - 5;
    }

    public int rollInitiative(){
        return dexMod() + (int)(Math.random() * 20 + 1);
    }

    public static void main(String [] args){
        Character Raffi = new Character(10, 20, 12, 14, 13, 16);
        int[] results = new int[20];
        for(int i = 0; i < 1000000; i ++){
            results[Raffi.rollInitiative() - 6] ++;
        }
        System.out.println(Arrays.toString(results));

    }
}