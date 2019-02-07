class Player implements Comparable<Player>{ //change to character later in development
  int initiative;
  String name;

  Player(String n, int in){
    this.initiative = in;
    this.name = n;
  }

  @Override
  public int compareTo(Player other){
    if(this.initiative < other.initiative) return 1;
    else if(this.initiative > other.initiative) return -1;
    else return 0;

  }
}