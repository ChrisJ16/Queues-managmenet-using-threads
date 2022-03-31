import javax.swing.*;
import GUI.*;


public class MainClass {
    public static void main(String[] args) {
        JFrame frame = new View("Thread simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
