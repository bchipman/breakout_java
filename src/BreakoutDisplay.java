import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 Created by Brian on 5/28/2015.
 */
public class BreakoutDisplay extends JComponent {

    private Paddle paddle;
    private static final int PADDLE_Y_POSITION = 425;

    public BreakoutDisplay() {

        class Cat implements MouseInputListener {
            public void mousePressed(MouseEvent e) {
                System.out.println("Pressed!");
                repaint();
            }
            public void mouseReleased(MouseEvent e) {
                System.out.println("Released!");
            }
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicked!");
            }
            public void mouseEntered(MouseEvent e) {
                System.out.println("Entered!");
                repaint();
            }
            public void mouseExited(MouseEvent e) {
                System.out.println("Exited!");
                repaint();
            }
            public void mouseDragged(MouseEvent e) {
                System.out.println("Dragged!");
                repaint();
            }
            public void mouseMoved(MouseEvent e) {
                System.out.println("Moved!");

                int x = e.getX();
                paddle.setLocation(x,PADDLE_Y_POSITION);
                repaint();
            }
        }

        Cat cat = new Cat();
        addMouseListener(cat);
        addMouseMotionListener(cat);
        paddle = new Paddle(PADDLE_Y_POSITION);

    }

    public void paintComponent(Graphics g) {
        System.out.println("paintComponent()");
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.GREEN.darker());
        g2.fill(paddle);
        g2.setColor(Color.BLUE);
        g2.draw(paddle);
    }

}
