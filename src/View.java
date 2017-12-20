import javafx.scene.shape.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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

                Model2D model2D = Model2D.getInstance();
                model2D.addPoints(FileReader.readIn());

                int fakt = 4;

                Point[] points = model2D.getCurrentInstance();
                for (int i = 0; i < points.length; i++) {
                    Point one = points[i];
                    Point two;
                    if (i == points.length - 1)
                        two = points[0];
                    else
                        two = points[i+1];
                    Line2D lin = new Line2D.Float(one.getX()*fakt, one.getY()*fakt*(-1) + this.getHeight(), two.getX()*fakt, two.getY()*fakt*(-1) + this.getHeight());
                    g2.draw(lin);
                }
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

        this.subdivideButton.addMouseListener(viewController.getSubdivideListener());
    }
}
