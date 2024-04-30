import entities.*;
import environment.*;
import java.util.Scanner;
import java.util.Random;
public class RunGame {
    //start game ask player for name.
    //make terrain.
    //display the terrain and give player functions to select where to move

    //============================================================================//
    static Scanner scanner = new Scanner(System.in);
    public static void play(){
        Maze maze = new Maze();
        Tile[][] tiles = maze.getTiles();
        Player player = setPlayerSpawn(new Player(),maze);
        Murderer murderer = setMurdererSpawn(new Murderer(),maze);

        greetings(player);
        while (true) {
            directionsOptions();
            movePlayer(player);
            Tile currentTile = player.getCurrentTile(tiles);
            if (currentTile.hasPlayer() && currentTile.hasMurderer() && player.hasGun())
            {
                //todo display total cash collected and no of turns
                //todo end game
                System.out.println("U win");
                break;
            }
            murderer.followPlayer();
            if (currentTile.hasPlayer() && currentTile.hasMurderer()){
                //todo game over retry
                System.out.println("U lose");
                break;
            }
        }
    }

    private static Murderer setMurdererSpawn(Murderer murderer, Maze maze) {
        Random random = new Random();
        double min = 0;
        double max = 6;
        //keep loop on until the murderer spawns on a block where there is no wall and player.
        while(true) {
            int randomRow = (int) (min + (max - min) * random.nextDouble());
            int randomColumn = (int) (min + (max - min) * random.nextDouble());
            Tile[][] tiles = maze.getTiles();
            Tile tile = tiles[randomRow][randomColumn];
            if (!tile.isWall() && !tile.hasPlayer())
            {
                murderer.setColumn(randomColumn);
                murderer.setRow(randomRow);
                break;
            }
        }

        return murderer;
    }

    private static Player setPlayerSpawn(Player player,Maze maze) {
        Random random = new Random();
        double min = 0;
        double max = 6;
        //keep loop on until the player spawns on a block where there is no wall.
        while(true) {
            int randomRow = (int) (min + (max - min) * random.nextDouble());
            int randomColumn = (int) (min + (max - min) * random.nextDouble());
            Tile[][] tiles = maze.getTiles();
            Tile tile = tiles[randomRow][randomColumn];
            if (!tile.isWall())
            {
                player.setColumn(randomColumn);
                player.setRow(randomRow);
                break;
            }
        }

        return player;
    }

    public static void greetings(Player player){
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        player.setName(name);
        System.out.println("There is someone following you " + name);
        System.out.println("Run");
        System.out.println("Find the Gun that is Hidden somewhere in the Maze");
        System.out.println("Before it is too late.");
    }
    public static void directionsOptions(){
        System.out.println("Where to go: ");
        System.out.println("" +
                "     N       \n" +
                "  NW | NE    \n" +
                "W----x----E  \n" +
                "  SW | SE    \n" +
                "     S       \n");
        System.out.println("1-North");
        System.out.println("2-East");
        System.out.println("3-South");
        System.out.println("4-West");
        System.out.println("5-North East");
        System.out.println("6-North West");
        System.out.println("7-South East");
        System.out.println("8-South West");
    }
    public static void movePlayer(Player player){

        int option;
        System.out.println("Enter Choice: ");
        option = scanner.nextInt();
        switch (option){
            case 1:
                player.movePlayerNorth();
                break;
            case 2:
                player.movePlayerEast();
                break;
            case 3:
                player.movePlayerSouth();
                break;
            case 4:
                player.movePlayerWest();
                break;
            case 5:
                player.movePlayerNorthEast();
                break;
            case 6:
                player.movePlayerNorthWest();
                break;
            case 7:
                player.movePlayerSouthEast();
                break;
            case 8:
                player.movePlayerSouthWest();
                break;
            default:
                System.out.println("Invalid Option.");
                break;
        }

    }

}
