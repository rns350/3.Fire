import java.util.Random;

class Dice{
  private int numSides;

  Dice(int numSides){
    this.numSides = numSides;
  }

  /** getter method for determining how many sides the dice has.
   * 
   *  @return numSides: the number of sides that the dice has (max roll number)
   *  @author Reed Nathaniel Schick
   */
  public int checkSides(){
    return numSides;
  }

  public int roll(){
    return (int)(this.numSides*Math.random()) + 1;
  }

    /** Provides functionality for getting a Dice roll without having to create a dice object
   * 
   *  @param sides sides determines the max roll value
   *  @return an integer value in the range of 1 to sides.
   *  @author Reed Nathaniel Schick
   */
  public static int roll(int sides){
    return (int)(sides*Math.random()) + 1;
  }
}