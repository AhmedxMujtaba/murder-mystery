package UI;
import javax.swing.*;
import environment.Tile;
import environment.Maze;
import items.Gun;
import items.cash;

import java.awt.*;

public class MapUI extends JFrame {
    private Tile[][] map; // Assuming you have a 2D array of Tile objects
    private JPanel mapPanel;

    public MapUI(Tile[][] map) {
        this.map = map;

        setTitle("Map UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with GridLayout
        mapPanel = new JPanel(new GridLayout(7, 7));

        // Populate the panel with JLabels representing each cell in the map
        updateMapUI();

        add(mapPanel);

        pack(); // Resize the frame to fit the components
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    // Method to update the content of the map UI
    public void updateMapUI() {
        mapPanel.removeAll(); // Clear the existing content

        // Populate the panel with JLabels representing each cell in the map
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                JLabel cellLabel = new JLabel();
                cellLabel.setPreferredSize(new Dimension(50, 50)); // Set preferred size for each cell
                cellLabel.setOpaque(true); // Make JLabel opaque to set background color

                // Set background color based on the properties of the Tile object
                if (map[i][j].isWall()) {
                    cellLabel.setBackground(Color.BLACK); // Black color for walls
                } else if (map[i][j].hasMurderer()) {
                    cellLabel.setBackground(Color.RED); // Red color for cells with a murderer
                } else if (map[i][j].hasPlayer()) {
                    cellLabel.setBackground(Color.GREEN); // Green color for cells with a player
                } else if (map[i][j].getItem() instanceof Gun) {
                    cellLabel.setBackground(Color.BLUE); // Blue color for cells with a gun
                } else if (map[i][j].getItem() instanceof cash) {
                    cellLabel.setBackground(Color.YELLOW); // Blue color for cells with a gun
                } else {
                    cellLabel.setBackground(Color.WHITE); // Default color for empty cells
                }
                mapPanel.add(cellLabel);
            }
        }

        // Revalidate and repaint the panel to reflect the changes
        mapPanel.revalidate();
        mapPanel.repaint();
    }
}