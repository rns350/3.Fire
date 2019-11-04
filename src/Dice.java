public class Dice{
    private int sides;

    public Dice(int sides){
        this.sides = sides;
    }

    public int roll(){
        return Dice.roll(sides);
    }

    public static int roll(int i){
        return (int) (Math.random() * i + 1);
    }
}