import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
class Fight{
  int numEnemies;
  String [] players;
  ArrayList<Player> actives;

  Fight(String [] players){
    Scanner scn = new Scanner(System.in);

    this.players = players;
    this.actives =  new ArrayList<Player>();
    System.out.println("It's fightin' time");
    System.out.print("Enter number of enemies: ");
    this.numEnemies = scn.nextInt();
  }

  public void rollInitiative(){
    System.out.printf("Rolling initiative for %d enemies\n", numEnemies);
    Dice d20 = new Dice(20);
    int enemyNumber = 0;
    for(int i = 0; i < numEnemies; i++){
      int inRoll = d20.roll();
      actives.add(new Player("Enemy" + (++enemyNumber), inRoll));
    }
  }

  private void rollPlayerInitiative(){
    Scanner scn = new Scanner(System.in);
    System.out.println("Enter initatives for each player then press enter.");
    for(int i = 0; i < players.length; i++){
      System.out.print(players[i] + ": ");
      int inRoll = scn.nextInt();
      actives.add(new Player(players[i], inRoll));
    }
    scn.close();
  }

  public void start(){
    this.rollInitiative();
    this.rollPlayerInitiative();
    Collections.sort(actives);
    System.out.println();
    for(Player player : actives){
      System.out.println(player.name + " " + player.initiative);
    }
  }
}

