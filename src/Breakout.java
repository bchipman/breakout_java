import javax.swing.*;
import java.awt.*;

/**
 Created by Brian on 5/15/2015.
 */
public class Breakout {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(700,500);
        frame.setTitle("Breakout!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
        frame.setResizable(true);
        Paddle box = new Paddle();
        frame.add(box);

    }


    public static class Paddle extends JComponent {
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Rectangle box = new Rectangle(100,200,50,150);
            g2.draw(box);
        }
    }








}
