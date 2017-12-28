import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ViewController {

    public ViewController() {

    }

    public MouseListener getSubdivideListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Model2D.getInstance().cornerCutting();
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
