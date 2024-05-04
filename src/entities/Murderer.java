package entities;
import environment.Tile;

import java.util.List;
import java.util.Random;


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

    public void setPlayerCordsToFollow(int r, int c){
        this.rowToFollow = r;
        this.columnToFollow = c;
    }


    public void followPlayer(Tile[][] map) {
        Point playerPosition = new Point(rowToFollow,columnToFollow,null);
        // Get the current position of the Murderer
        Point murdererPosition = new Point(row, column, null);
        // Find a path from the Murderer's position to the player's position
        List<Point> path = Point.FindPath(map, murdererPosition, playerPosition);
        // If a valid path is found, move the Murderer along the path
        if (path != null && !path.isEmpty()) {
            Point nextPosition = path.get(0);
            row = nextPosition.x;
            column = nextPosition.y;
        }
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumnToFollow(int columnToFollow) {
        this.columnToFollow = columnToFollow;
    }

    public void setRowToFollow(int rowToFollow) {
        this.rowToFollow = rowToFollow;
    }

    public Tile getCurrentTile(Tile[][] tiles){
        Tile currentTile = tiles[row][column];
        return currentTile;
    }

    public int getColumnToFollow() {
        return columnToFollow;
    }

    public int getRowToFollow() {
        return rowToFollow;
    }
}
