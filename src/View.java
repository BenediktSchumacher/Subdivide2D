import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;

public class View extends JPanel implements Observer {

    private JPanel graphicsPanel;
    private JList historyPanel;
    private JButton subdivideButton;
    private JComboBox algorithmBox;
    private ViewController viewController;

    public View(ViewController viewController) {
        super();

        this.viewController = viewController;
        viewController.addView(this);

        Model2D model2D = Model2D.getInstance();
        model2D.addPoints(FileReader.readIn(), "Original");

        this.graphicsPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                RenderingHints rh = new RenderingHints(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHints(rh);

                int fakt = 4;

                Point[] points = model2D.getCurrentInstance();
                for (int i = 0; i < points.length; i++) {
                    Point one = points[i];
                    Point two;
                    if (i == points.length - 1)
                        two = points[0];
                    else
                        two = points[i+1];
                    Line2D lin = new Line2D.Float(((int)(one.getX() + 0.5)) * fakt,
                            ((int)(one.getY() + 0.5)) * fakt * (-1) + this.getHeight(),
                            ((int)(two.getX() + 0.5)) * fakt,
                            ((int)(two.getY() + 0.5)) * fakt * (-1) + this.getHeight());
                    g2.draw(lin);
                }
            }
        };

        this.setLayout(new BorderLayout());

        this.historyPanel = new JList(new DefaultListModel());
        this.historyPanel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.historyPanel.setLayoutOrientation(JList.VERTICAL);
        this.historyPanel.addMouseListener(this.viewController.getListListener());
        refreshHistory();

        JPanel pageEnd = new JPanel();
        pageEnd.setLayout(new FlowLayout());
        this.subdivideButton = new JButton("Subdivide");
        this.algorithmBox = new JComboBox();
        pageEnd.add(subdivideButton);
        pageEnd.add(algorithmBox);

        // TODO: better handling here
        algorithmBox.addItem("Corner-Cutting");
        algorithmBox.addItem("4-Point");

        this.graphicsPanel.setPreferredSize(new Dimension(800, 600));
        this.graphicsPanel.setVisible(true);
        this.graphicsPanel.setBackground(Color.WHITE);
        Dimension dim = new Dimension();
        dim.width = 150;
        this.historyPanel.setPreferredSize(new Dimension(180, 600));
        this.historyPanel.setVisible(true);

        this.add(graphicsPanel, BorderLayout.CENTER);
        this.add(historyPanel, BorderLayout.LINE_END);
        this.add(pageEnd, BorderLayout.PAGE_END);

        this.subdivideButton.addMouseListener(viewController.getSubdivideListener());
        Model2D.getInstance().addObserver(this);
    }

    private void refreshHistory() {
        ((DefaultListModel)this.historyPanel.getModel()).removeAllElements();
        for (String descr : Model2D.getInstance().getDescriptions())
            ((DefaultListModel)this.historyPanel.getModel()).addElement(descr);
        this.historyPanel.setSelectedIndex(((DefaultListModel)this.historyPanel.getModel()).size() - 1);
    }

    public String getCurrentAlgorithm() {
        return algorithmBox.getSelectedItem().toString();
    }

    @Override
    public void update(Observable o, Object arg) {
        refreshHistory();
        this.revalidate();
        this.repaint();
    }
}
