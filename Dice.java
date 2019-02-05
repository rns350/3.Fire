import java.util.Random;

class Dice{
  int numSides;

  Dice(int numSides){
    this.numSides = numSides;
  }

  int roll(){
    return (int)(20*Math.random()) + 1;
  }

}
