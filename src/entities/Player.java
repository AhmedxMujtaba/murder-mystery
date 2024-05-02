package entities;

import environment.Maze;
import environment.Tile;

public class Player {
/*
* The player has options to run and hide. He has commands to move in 6 directions.
* If a player faces a wall, it cannot move in that direction.
* If a player moves into the direction of the murderer, he dies
*  if the player finds the gun then he needs to find the murderer and kill him
* else if the murderer finds the player first, then it is game over
* since surprise will take the player: )
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

    public boolean hasGun() {
        return hasGun;
    }


    /*
     * Functions to make player move how will they work?
     * Make player cordinates change.
     * set limit to cords must be between 0-6
     * return player cords and shot after chaning them
     */

    public boolean movePlayerNorth(Tile[][] tiles){
        int newRow = row - 1;
        return isValidMove(newRow,column,tiles);
    }
    public boolean movePlayerSouth(Tile[][] tiles){
        int newRow = row + 1;
        return isValidMove(newRow,column,tiles);
    }
    public boolean movePlayerEast(Tile[][] tiles){
        int newColumn = column + 1;
        return isValidMove(row,newColumn,tiles);
    }
    public boolean movePlayerWest(Tile[][] tiles){
        int newColumn = column - 1;
        return isValidMove(row,newColumn,tiles);
    }

    //for moving in diagonals
    public boolean movePlayerNorthEast(Tile[][] tiles){
        int newRow = row - 1;
        int newColumn = column + 1;
        return isValidMove(newRow,newColumn,tiles);
    }
    public boolean movePlayerNorthWest(Tile[][] tiles){
        int newRow = row - 1;
        int newColumn = column - 1;
        return isValidMove(newRow,newColumn,tiles);

    }
    public boolean movePlayerSouthEast(Tile[][] tiles){
        int newRow = row + 1;
        int newColumn = column + 1;
        return isValidMove(newRow,newColumn,tiles);
    }
    public boolean movePlayerSouthWest(Tile[][] tiles){
        int newRow = row + 1;
        int newColumn = column - 1;
        return isValidMove(newRow,newColumn,tiles);
    }

    private boolean isValidMove(int row, int column,Tile[][] tiles){
        if (row > 6 || row < 0 || column > 6 || column < 0)
            return false;
        if (tiles[row][column].isWall()) {
            System.out.println("Wall");
            return false;
        } else
            this.column = column;
            this.row = row;
            return true;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setHasGun(boolean hasGun) {
        this.hasGun = hasGun;
    }

    public Tile getCurrentTile(Tile[][] tiles){
        Tile currentTile = tiles[row][column];
        return currentTile;
    }
}
