package environment;
import items.*;
import java.util.Random;
public class Maze {

    /*
    * The maze will be 7x7 2d array
    * the array will be 2d and different locations will have money in it and
    * some other things inheritied from the items class.
     */

    private Tile[][] maze;
    public Maze(){
        this.maze = generateMaze();
    }

    private Tile[][] generateMaze(){
        Tile[][] maze = new Tile[7][7];
        //add walls and items and a gun;
        generateWalls();
        generateCashItems();
        return maze;
    }

    private void generateCashItems() {
        //distribute random items around 10-20 different tiles
        int maxItems = giveRanNum(10,20);
        int prevRow = 0, prevColumn = 0;
        for (int i = 0; i <maxItems ; i++) {
            int row, column;
            while (true){
                row = giveRanNum(0,6);
                column = giveRanNum(0,6);
                if (prevColumn != column && prevRow != row && !maze[row][column].isWall()){
                    break;
                }
            }
            maze[row][column].setItem(new cash());
            prevRow = row;
            prevColumn = column;
        }
    }

    //function to make walls
    private void generateWalls(){
        //max walls should be around 10-20
        int row = giveRanNum(0,6);
        int column = giveRanNum(0,6);
        int maxWalls = giveRanNum(10,20);
        for (int i = 0; i < maxWalls; i++) {
            Tile currentTile = maze[row][column];;
            while (!currentTile.isWall()){
                row = rowWithProbability(row,column,69);
                column = columnWithProbability(row,column,69);
                currentTile = maze[row][column];
            }
            currentTile.setWall(true);
        }
    }

    private int giveRanNum(int start, int end){
        Random random = new Random();
        int num = random.nextInt(end+1)+start;
        return num;
    }
    private int rowWithProbability(int row, int column, int probalility){
        int num = 0;
        int threshold = giveRanNum(0,100);
        if (threshold < probalility){
            //check for which side to go negative or positive
            //if one side is filled go other, if both filled go random, else if both unfilled
            //go to any one 50-50

            if (!maze[row+1][column].isWall() && !maze[row-1][column].isWall() ){
                return row + giveRanNum(-1,1);
            }
            else if (maze[row+1][column].isWall() && !maze[row-1][column].isWall())
            {
                return row - 1 ;
            }
            else if (!maze[row+1][column].isWall() && maze[row-1][column].isWall())
            {
                return row + 1 ;
            }
            else
                return giveRanNum(0,6);
        }
        else
            return giveRanNum(0,6);
    }
    private int columnWithProbability(int row, int column, int probalility){
        int num = 0;
        int threshold = giveRanNum(0,100);
        if (threshold < probalility){
            //check for which side to go negative or positive
            //if one side is filled go other, if both filled go random, else if both unfilled
            //go to any one 50-50
            if (!maze[row][column+1].isWall() && !maze[row][column-1].isWall())
            {
                return row + giveRanNum(-1,1);
            }
            else if (maze[row][column+1].isWall() && !maze[row][column-1].isWall())
            {
                return row - 1 ;
            }
            else if (!maze[row][column+1].isWall() && maze[row][column-1].isWall())
            {
                return row + 1 ;
            }
            else
                return giveRanNum(0,6);
        }
        else
            return giveRanNum(0,6);
    }

    public Tile[][] getTiles() {
        return maze;
    }
}
