package environment;
import items.item;
public class Tile {
    /*
    * The environment.tile has an item which can be any item
    * player and murderer
    * a wall or movable space depending
     */
    private boolean isWall;
    private item item;
    private boolean isPlayer;
    private boolean isMurderer;

     public Tile(){
        this.item = generateRandomItem();
    }

    private item generateRandomItem() {

         //todo make a list of item objects and select one form them
        return null;
    }

    public void setItem(items.item item) {
        this.item = item;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public void setMurderer(boolean murderer) {
        isMurderer = murderer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }
    public boolean isWall() {
        return isWall;
    }

    public item getItem() {
        return item;
    }
    public int whoIsOnTile(){
         if (isPlayer) return 0;
         else if (isMurderer) return 1;
         else if (isMurderer && isPlayer)return 2;
         else return -1;
    }
    public boolean hasPlayer() {
        return isPlayer;
    }
    public boolean hasMurderer() {
        return isMurderer;
    }

}
