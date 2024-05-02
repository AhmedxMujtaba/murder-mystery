package environment;
import entities.Murderer;
import entities.Player;
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
        //add walls and items
        generateWalls(20);
        generateCashItems();
    }
    private Tile[][] generateMaze()
    {
        int rows = 7, columns = 7;
        maze = new Tile[rows][columns];
        // Initialize the maze
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze[i][j] = new Tile();
            }
        }

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

    public void generateWalls(int maxWalls) {
        Random random = new Random();
        for (int i = 0; i < maxWalls; i++) {
            int row = random.nextInt(maze.length);
            int column = random.nextInt(maze[0].length);
            Tile currentTile = maze[row][column];
            while (!currentTile.isWall()) {
                row = rowWithProbability(row, column, 69);
                column = columnWithProbability(row, column, 69);
                if (row >= 0 && row < maze.length && column >= 0 && column < maze[0].length) {
                    currentTile = maze[row][column];
                    currentTile.setWall(true);
                } else {
                    break;
                }
            }
        }
        printMazeLayout(maze);
    }

    private int rowWithProbability(int row, int column, int probability) {
        Random random = new Random();
        int threshold = random.nextInt(100);
        if (threshold < probability) {
            if (row > 0 && !maze[row - 1][column].isWall()) {
                return row - 1;
            } else if (row < maze.length - 1 && !maze[row + 1][column].isWall()) {
                return row + 1;
            }
        }
        return row;
    }

    private int columnWithProbability(int row, int column, int probability) {
        Random random = new Random();
        int threshold = random.nextInt(100);
        if (threshold < probability) {
            if (column > 0 && !maze[row][column - 1].isWall()) {
                return column - 1;
            } else if (column < maze[0].length - 1 && !maze[row][column + 1].isWall()) {
                return column + 1;
            }
        }
        return column;
    }

        public Tile[][] getTiles() {
        return maze;
    }

    public static void printMazeLayout(Tile[][] array) {
        // Iterate over each row of the array
        System.out.println("------");
        for (int i = 0; i < array.length; i++) {
            // Print the elements of the current row
            for (int j = 0; j < array[0].length; j++) {
                String entity;
                Tile currentTile = array[i][j];
                if (currentTile.isWall())
                    entity = "[]";
                else if (currentTile.hasMurderer())
                    entity = "'M'";
                else if (currentTile.hasPlayer()) {
                    entity = "'P'";
                }
                else if (currentTile.getItem() instanceof Gun) {
                    entity = "'G'";
                }
                else
                    entity = ".";
                System.out.printf("%2s ", entity);
            }
            System.out.println();
        }
    }
    private int giveRanNum(int start, int end){
        Random random = new Random();
        int num = random.nextInt(end+1)+start;
        return num;
    }

    public void setMazeCordsForPLayer(Player player){
        int row = player.getRow();
        int col = player.getColumn();
        maze[row][col].setPlayer(true);
    }
    public void setMazeCordsForMurderer(Murderer murderer){
        int row = murderer.getRow();
        int col = murderer.getColumn();
        maze[row][col].setMurderer(true);
    }
    public void removeMazeCordsForPLayer(Player player){
        int row = player.getRow();
        int col = player.getColumn();
        maze[row][col].setPlayer(false);
    }
    public void removeMazeCordsForMurderer(Murderer murderer){
        int row = murderer.getRow();
        int col = murderer.getColumn();
        maze[row][col].setMurderer(false);
    }


}
