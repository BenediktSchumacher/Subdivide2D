import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Subdivision Tool");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        View view = new View();
        jFrame.add(view);
        jFrame.pack();
        jFrame.setVisible(true);
    }

}
