import javafx.scene.shape.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class View extends JPanel {

    private JPanel graphicsPanel;
    private JPanel historyPanel;
    private JButton subdivideButton;

    public View(ViewController viewController) {
        super();
        this.graphicsPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                RenderingHints rh = new RenderingHints(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHints(rh);

                // TODO: for all Points drae a line between each pair

                Line2D lin = new Line2D.Float(100, 100, 250, 260);
                g2.draw(lin);
            }
        };
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
