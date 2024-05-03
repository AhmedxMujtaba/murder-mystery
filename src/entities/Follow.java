package entities;

//take a murderer and change its cords to follow and return the murderer
public class Follow {

    private int rowToFollow;
    private int colToFollow;
    private int row;
    private int column;
    private Follow previous;
    public Follow(Murderer murderer, Follow previous){
        this.rowToFollow = murderer.getRowToFollow();
        this.colToFollow = murderer.getColumnToFollow();
        this.previous = previous;
    }

    @Override
    public String toString() { return String.format("(%d, %d)", rowToFollow, colToFollow); }

    @Override
    public boolean equals(Object o) {
        Follow point = (Follow) o;
        return rowToFollow == point.rowToFollow && colToFollow == point.colToFollow;
    }

}
