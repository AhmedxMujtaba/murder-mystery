package items;

public class item {
    protected String name;
    protected String description;
    protected String asciiArt;

    public item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setAsciiArt(String asciiArt) {
        this.asciiArt = asciiArt;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAsciiArt() {
        return asciiArt;
    }
}
