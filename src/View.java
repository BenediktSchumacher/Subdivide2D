import javax.swing.*;
import java.awt.*;

public class View extends JPanel {

    private JPanel graphicsPanel;
    private JPanel historyPanel;
    private JButton subdivideButton;

    public View() {
        super();
        this.graphicsPanel = new JPanel();
        this.historyPanel = new JPanel();
        this.subdivideButton = new JButton("Subdivide");

        this.graphicsPanel.setPreferredSize(new Dimension(800, 600));
        this.graphicsPanel.setVisible(true);
        Dimension dim = new Dimension();
        dim.width = 150;
        this.historyPanel.setPreferredSize(new Dimension(180, 600));
        this.historyPanel.setBackground(Color.CYAN);
        this.historyPanel.setVisible(true);

        this.add(graphicsPanel);
        this.add(historyPanel);
        this.add(subdivideButton);
    }

}
