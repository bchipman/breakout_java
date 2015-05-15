import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 Created by Brian on 5/15/2015.
 */
public class Breakout {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(700, 500);
        frame.setTitle("Breakout!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setVisible(true);
        frame.setResizable(false);
        MyComponent box = new MyComponent();
        frame.add(box);

        BufferedImage cursorImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0,0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);

    }
}


class MyComponent extends JComponent {
    private Rectangle paddle;
    private final int PADDLE_Y_POSITION = 425;


    public MyComponent() {
        System.out.println("Paddle{}()");

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

        paddle = new Rectangle(100,PADDLE_Y_POSITION,100,15);

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


