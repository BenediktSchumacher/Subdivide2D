import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Subdivision Tool");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ViewController viewController = new ViewController();
        View view = new View(viewController);
        jFrame.add(view);
        jFrame.pack();
        jFrame.setVisible(true);
    }

}
