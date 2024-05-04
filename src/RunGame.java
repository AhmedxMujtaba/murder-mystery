import entities.*;
import environment.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
import items.Gun;
import UI.MapUI;
import items.cash;

import javax.swing.*;

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
        Gun gun = setGunSpawn(new Gun(), maze);
        // Create and display the map GUI
        MapUI mapUI = new MapUI(maze.getTiles());
        SwingUtilities.invokeLater(() -> {
            mapUI.setVisible(true);
        });
        greetings(player);
        int noOfTurns = 0;
        while (true) {
            noOfTurns++;
            System.out.println("Player: " + " " +player.getRow() + " "+player.getColumn());
            System.out.println("Murderer: " + " " +murderer.getRow() + " "+murderer.getColumn());
            System.out.println("Murderer Following: " + " " +murderer.getRowToFollow() + " "+murderer.getColumnToFollow());

            maze.printMazeLayout(tiles);
            mapUI.updateMapUI();
            //update maze map
            displayCash(player);
            directionsOptions();
            movePlayer(player,maze);
            Tile currentTile = player.getCurrentTile(tiles);
            //collect cash
            collectCash(player,currentTile);
            //check if there is a Gun on the tile
            pickGunUp(player, currentTile);
            murderer.setPlayerCordsToFollow(player.getRow(),player.getColumn());
            if (winGame(currentTile, player)){
                System.out.println("You Win " + player.getName());
                System.out.println("Turns: " + noOfTurns);
                System.out.println("Cash Collected: " + player.getCash() );
                break;
            }
            maze.removeMazeCordsForMurderer(murderer);
            murderer.followPlayer(maze.getTiles());
            maze.setMazeCordsForMurderer(murderer);
            if (loseGame(currentTile,player))
                break;
        }
        System.exit(0);
    }

    private static boolean loseGame(Tile currentTile, Player player) {
        if (currentTile.hasPlayer() && currentTile.hasMurderer()){
            System.out.println("Game Over " + player.getName());
            return true;
        }
        return false;
    }

    private static boolean winGame(Tile currentTile, Player player) {
        if (currentTile.hasPlayer() && currentTile.hasMurderer() && player.hasGun())
        {
            System.out.println("1-Shoot");
            System.out.println("2-Let Go");
            // Handling input for integer
            int option = 0;
            while(true){
                try {
                    option = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Choice, Try again");
                    scanner.next();
                }
                break;
            }
            switch (option)
            {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    break;
            }
        }

        return false;
    }

    private static void pickGunUp(Player player, Tile tile) {

        //check if gun is on tile
        //if on tile, display option pick gun up
        //if yest, remove gun from tile and set hasGun true
        if (tile.getItem() instanceof Gun)
        {
            System.out.println("You See a Gun on the floor.");
            System.out.println("Pick it up?");
            System.out.println("1- Yes");
            System.out.println("2- No");

            int option;
            option = scanner.nextInt();
                switch (option){
                    case 1:
                        player.pickGunUp();
                        tile.setItem(null);
                        break;
                    default:
                        System.out.println("Your Choice..");
                        break;
                }
        }
    }
    private static void collectCash(Player player, Tile tile){
        if (tile.getItem() instanceof cash)
        {
            int cash = ((cash)tile.getItem()).getCashAmount();
            player.addMoney(cash);
            tile.setItem(null);
            System.out.println("Cash Collected: " + cash);
        }
    }
    private static void displayCash(Player player){
        System.out.println("Cash: " + player.getCash() );
    }
    private static Gun setGunSpawn(Gun gun, Maze maze) {
        Random random = new Random();
        double min = 0;
        double max = 6;
        //keep loop on until the murderer spawns on a block where there is no wall and player.
        while(true) {

            int randomRow = (int) (min + (max - min) * random.nextDouble());
            int randomColumn = (int) (min + (max - min) * random.nextDouble());
            Tile[][] tiles = maze.getTiles();
            Tile tile = tiles[randomRow][randomColumn];
            if (!tile.isWall())
            {
                tile.setItem(gun);
                break;
            }
        }

        return gun;

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
                tile.setMurderer(true);
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
                tile.setPlayer(true);
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
    public static void movePlayer(Player player,Maze maze){

        int option;
        System.out.println("Enter Choice: ");
        option = scanner.nextInt();
        switch (option){
            case 1:
                maze.removeMazeCordsForPLayer(player);
                player.movePlayerNorth(maze.getTiles());
                maze.setMazeCordsForPLayer(player);
                break;
            case 2:
                maze.removeMazeCordsForPLayer(player);
                player.movePlayerEast(maze.getTiles());
                maze.setMazeCordsForPLayer(player);
                break;
            case 3:
                maze.removeMazeCordsForPLayer(player);
                player.movePlayerSouth(maze.getTiles());
                maze.setMazeCordsForPLayer(player);
                break;
            case 4:
                maze.removeMazeCordsForPLayer(player);
                player.movePlayerWest(maze.getTiles());
                maze.setMazeCordsForPLayer(player);
                break;
            case 5:
                maze.removeMazeCordsForPLayer(player);
                player.movePlayerNorthEast(maze.getTiles());
                maze.setMazeCordsForPLayer(player);
                break;
            case 6:
                maze.removeMazeCordsForPLayer(player);
                player.movePlayerNorthWest(maze.getTiles());
                maze.setMazeCordsForPLayer(player);
                break;
            case 7:
                maze.removeMazeCordsForPLayer(player);
                player.movePlayerSouthEast(maze.getTiles());
                maze.setMazeCordsForPLayer(player);
                break;
            case 8:
                maze.removeMazeCordsForPLayer(player);
                player.movePlayerSouthWest(maze.getTiles());
                maze.setMazeCordsForPLayer(player);
                break;
            default:
                System.out.println("Invalid Option.");
                break;
        }

    }

}
