import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ViewController {

    private View view;

    public ViewController() {

    }

    public void addView(View view) {
        this.view = view;
    }

    public MouseListener getListListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Model2D.getInstance().resetToInsance(((JList)e.getSource()).getSelectedIndex());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    public MouseListener getSubdivideListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (view.getCurrentAlgorithm()) {
                    case "Corner-Cutting":
                        Model2D.getInstance().cornerCutting();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }
}
