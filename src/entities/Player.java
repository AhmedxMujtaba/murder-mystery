package entities;

public class Player {
/*
* The player has options to run and hide. He has commands to move in 6 directions.
* If player face a wall it cannot move in that direction.
* if player moves into the direction of the murderer he dies
* if player find the gun then he needs to find the murderer and kill him
* else if the murderer finds the player first then it is game over
* since the player will be taken by surprise : )
*
 */
    private String name;
    private int cash;
    private boolean hasGun;
    private int row;
    private int column;

    //fuck are you laggin my niga

    //add money to the player after getting some cash
    public void addMoney(int money){
        this.cash = cash + money;
    }

    public void pickGunUp(){
        this.hasGun = true;
    }

    public int getCash() {
        return cash;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean doesHasGun() {
        return hasGun;
    }


    /*
     * Functions to make player move how will they work?
     * Make player cordinates change.
     * set limit to cords must be between 0-6
     * return player cords and shot after chaning them
     */

    public boolean movePlayerNorth(){
        int newRow = row - 1;
        return isValidMove(newRow,column);
    }
    public boolean movePlayerSouth(){
        int newRow = row + 1;
        return isValidMove(newRow,column);
    }
    public boolean movePlayerEast(){
        int newColumn = column + 1;
        return isValidMove(row,newColumn);
    }
    public boolean movePlayerWest(){
        int newColumn = column - 1;
        return isValidMove(row,newColumn);
    }

    //for moving in diagonals
    public boolean movePlayerNorthEast(){
        int newRow = row - 1;
        int newColumn = column + 1;
        return isValidMove(newRow,newColumn);
    }
    public boolean NorthWest(){
        int newRow = row - 1;
        int newColumn = column - 1;
        return isValidMove(newRow,newColumn);

    }
    public boolean movePlayerSouthEast(){
        int newRow = row + 1;
        int newColumn = column + 1;
        return isValidMove(newRow,newColumn);
    }
    public boolean movePlayerSouthWest(){
        int newRow = row + 1;
        int newColumn = column - 1;
        return isValidMove(newRow,newColumn);
    }

    private boolean isValidMove(int row, int column){
        if (row > 6 || row < 0 || column > 6 || column < 0)
            return false;
        else
            this.column = column;
            this.row = row;
            return true;
    }
}
