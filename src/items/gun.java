package items;

public class gun extends item {

    private String asciiGun =
            " +--^----------,--------,-----,--------^-,\n" +
            " | |||||||||   `--------'     |          O\n" +
            " `+---------------------------^----------|\n" +
            "   `\\_,---------,---------,--------------'\n" +
            "     / XXXXXX /'|       /'\n" +
            "    / XXXXXX /  `\\    /'\n" +
            "   / XXXXXX /`-------'\n" +
            "  / XXXXXX /\n" +
            " / XXXXXX /\n" +
            "(________(\n" +
            " `------'";
    public gun() {
        super("Glock-9","This is a gun loaded with bullets be careful while" +
                "using it!");
        setAsciiArt(asciiGun);
    }
}
