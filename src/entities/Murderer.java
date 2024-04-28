package entities;

public class Murderer {
    /*
    *
    * this is the random ai that will follow the player using his coordinates and
    * trying to match the player coordinates.
     */

    private int row;
    private int column;
    private int rowToFollow;
    private int columnToFollow;

    public void getPlayerCords(int r, int c){
        this.columnToFollow = c;
        this.rowToFollow = r;
    }

    public void followPlayer(){

    }


}
