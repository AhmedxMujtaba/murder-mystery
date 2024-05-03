package entities;
import environment.Tile;

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

    public void followPlayer(){
        Random random = new Random();
         double min;
         double max;
         int randomNo;

       while(true)
       {
           if (row<rowToFollow){
               min = 0;
               max = 2;
               randomNo = (int) (min + (max - min) * random.nextDouble());
               if (randomNo + row < 6 && randomNo + row > 0)
               {
                   row = row + randomNo;
                   break;
               }
           }
           else if (row > rowToFollow)
           {
               min = -2;
               max = 0;
               randomNo = (int) (min + (max - min) * random.nextDouble());
               if (randomNo + row < 6 && randomNo + row > 0)
               {
                   row = row + randomNo;
                   break;
               }
           }
       }
        while(true)
        {
            if (column < columnToFollow){
                min = 0;
                max = 2;
                randomNo = (int) (min + (max - min) * random.nextDouble());
                if (randomNo + column < 6 && randomNo + column > 0)
                {
                    column = column + randomNo;
                    break;
                }
            }
            else if (column > columnToFollow)
            {
                min = -2;
                max = 0;
                randomNo = (int) (min + (max - min) * random.nextDouble());
                if (randomNo + column < 6 && randomNo + column > 0)
                {
                    column = column + randomNo;
                    break;
                }
            }
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
