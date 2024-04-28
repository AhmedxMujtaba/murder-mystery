package environment;
import items.item;
public class tile {
    /*
    * The environment.tile has an item which can be any item
    * player and murderer
    * a wall or movable space depending
     */
    private boolean isWall;
    private item item;
    private boolean isPlayer;
    private boolean isMurderer;
    private final int row;
    private final int column;

     tile(int r, int c){
        this.item = generateRandomItem();
        this.row = r;
        this.column = c;
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

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
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
}
