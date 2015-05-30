import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

/**
 Created by Brian on 5/28/2015.
 */
public class Component extends JComponent {

    private Paddle paddle;
    private Ball ball;

    private static final int PADDLE_X_POSITION = 100;
    private static final int PADDLE_Y_POSITION = 300;
    private static final int PADDLE_LENGTH = 100;
    private static final int PADDLE_HEIGHT = 100;

    private static final int BALL_SIZE = 10;
    private static final int BALL_X_POSITION = PADDLE_X_POSITION + PADDLE_LENGTH / 2 - BALL_SIZE / 2;
    private static final int BALL_Y_POSITION = PADDLE_Y_POSITION - BALL_SIZE;
    private static final int BALL_X_VELOCITY = 1;
    private static final int BALL_Y_VELOCITY = -2;

    private int PAUSE_TIME = 15;
    private boolean COLLISION_ON = true;

    class MyMouseInputListener implements MouseInputListener {
        public void mousePressed(MouseEvent e) {
            repaint();
        }
        public void mouseReleased(MouseEvent e) {
        }
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                PAUSE_TIME += 5;
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                PAUSE_TIME -= 5;
                PAUSE_TIME = (PAUSE_TIME < 0) ? 0 : PAUSE_TIME;
            }
            else if (e.getButton() == MouseEvent.BUTTON2) {
                COLLISION_ON = !COLLISION_ON;
            }

        }
        public void mouseEntered(MouseEvent e) {
            repaint();
        }
        public void mouseExited(MouseEvent e) {
            repaint();
        }
        public void mouseDragged(MouseEvent e) {
            repaint();
        }
        public void mouseMoved(MouseEvent e) {
            //paddle.setLocation(e.getX(), PADDLE_Y_POSITION);
            paddle.setLocation(e.getX(), e.getY());
            if (COLLISION_ON) {
                paddle.checkForBallCollision(ball);
            }
            repaint();
        }
    }
    class MyKeyListener implements KeyListener {
        public void keyTyped(KeyEvent e) {
            System.out.println(e.getKeyChar());
        }
        public void keyPressed(KeyEvent e) {
        }
        public void keyReleased(KeyEvent e) {
        }
    }

    public Component() {
        MyKeyListener myKeyListener = new MyKeyListener();
        addKeyListener(myKeyListener);

        MyMouseInputListener myMouseInputListener = new MyMouseInputListener();
        addMouseListener(myMouseInputListener);
        addMouseMotionListener(myMouseInputListener);

        paddle = new Paddle(PADDLE_X_POSITION, PADDLE_Y_POSITION, PADDLE_LENGTH, PADDLE_HEIGHT);
        ball = new Ball(BALL_X_POSITION, BALL_Y_POSITION, BALL_SIZE, BALL_SIZE, BALL_X_VELOCITY, BALL_Y_VELOCITY);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        //System.out.println(g2.getFont());
        Font font = new Font("Consolas", Font.PLAIN, 10);
        g2.setFont(font);

        g2.setColor(Color.GREEN.darker());
        g2.fill(paddle);
        g2.setColor(Color.BLUE);
        g2.draw(paddle);

        g2.setColor(Color.WHITE);
        g2.fill(ball);

        g2.drawString("Pause: " + PAUSE_TIME, 10, 10);
        g2.drawString("Collision: " + COLLISION_ON, 10, 25);

        g2.drawString("Ball:   topLeft:    " + ball.getTopLeft(), 115, 10);
        g2.drawString("topRight:    " + ball.getTopRight(), 425, 10);
        g2.drawString("Ball:   bottomLeft: " + ball.getBottomLeft(), 115, 25);
        g2.drawString("bottomRight: " + ball.getBottomRight(), 425, 25);

        g2.drawString("Paddle: topLeft:   " + paddle.getTopLeft(), 115, 60);
        g2.drawString("topRight:    " + paddle.getTopRight(), 425, 60);
        g2.drawString("Paddle: bottomLeft: " + paddle.getBottomLeft(), 115, 75);
        g2.drawString("bottomRight: " + paddle.getBottomRight(), 425, 75);
    }

    public void animate() {
        class MyRunnable implements Runnable {
            public void run() {
                while (true) {
                    if (COLLISION_ON) {
                        ball.move(paddle);
                    }
                    else {
                        ball.move();
                    }
                    repaint();
                    pause(PAUSE_TIME);
                }
            }
        }
        Thread t = new Thread(new MyRunnable());
        t.start();
    }

    private void pause(int millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
        }
    }



}
