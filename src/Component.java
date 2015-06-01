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

    private Paddle oldPaddle;
    private Paddle unionPaddle;
    private Paddle paddle;
    private Ball ball;

    private static final int PADDLE_X_POSITION = 100;
    private static final int PADDLE_Y_POSITION = 300;
    private static final int PADDLE_LENGTH = 100;
    private static final int PADDLE_HEIGHT = 10;

    private static final int BALL_SIZE = 10;
    private static final int BALL_X_POSITION = PADDLE_X_POSITION + PADDLE_LENGTH / 2 - BALL_SIZE / 2;
    private static final int BALL_Y_POSITION = PADDLE_Y_POSITION - BALL_SIZE;
    private static final int BALL_X_VELOCITY = 10;
    private static final int BALL_Y_VELOCITY = -20;

    private static final boolean PRINT_PADDLE_BALL_COLLISION_INFO = false;

    private int PAUSE_TIME = 250;
    private boolean COLLISION_ON = true;
    private boolean DEBUG_TEXT_ON = true;
    private boolean BALL_MOVEMENT_ON = true;
    private boolean PADDLE_VERTICAL_MOVEMENT_ONLY = false;
    private boolean PADDLE_HORIZONTAL_MOVEMENT_ONLY = false;
    private boolean PRINT_PADDLE_INFO = false;


    class MyKeyListener implements KeyListener {

        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == '`') {
                DEBUG_TEXT_ON = !DEBUG_TEXT_ON;
            }
            if (e.getKeyChar() == '-') {
                PAUSE_TIME += 1;
            }
            if (e.getKeyChar() == '+' || e.getKeyChar() == '=') {
                PAUSE_TIME -= 1;
                PAUSE_TIME = (PAUSE_TIME < 1) ? 1 : PAUSE_TIME;
            }
            if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
                COLLISION_ON = !COLLISION_ON;
            }
            if (e.getKeyChar() == 'm' || e.getKeyChar() == 'M') {
                BALL_MOVEMENT_ON = !BALL_MOVEMENT_ON;
            }
            if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
                PRINT_PADDLE_INFO = true;
            }
        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyChar() == 'v' || e.getKeyChar() == 'V') {
                PADDLE_VERTICAL_MOVEMENT_ONLY = true;
            }
            if (e.getKeyChar() == 'h' || e.getKeyChar() == 'H') {
                PADDLE_HORIZONTAL_MOVEMENT_ONLY = true;
            }
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == 'v' || e.getKeyChar() == 'V') {
                PADDLE_VERTICAL_MOVEMENT_ONLY = false;
            }
            if (e.getKeyChar() == 'h' || e.getKeyChar() == 'H') {
                PADDLE_HORIZONTAL_MOVEMENT_ONLY = false;
            }
        }

    }

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

            oldPaddle = new Paddle(paddle);

            if (PADDLE_VERTICAL_MOVEMENT_ONLY) {
                paddle.setLocation(paddle.x, e.getY());
            } else if (PADDLE_HORIZONTAL_MOVEMENT_ONLY) {
                paddle.setLocation(e.getX(), paddle.y);
            } else {
                paddle.setLocation(e.getX(), e.getY());
            }

            unionPaddle = paddle.union(oldPaddle);

            if (COLLISION_ON) {
                unionPaddle.checkForBallCollision(ball);
            }

            if (PRINT_PADDLE_BALL_COLLISION_INFO) {
                unionPaddle.printPaddleBallCollisionInfo();
            }

            repaint();
        }
    }

    public Component() {
        MyKeyListener myKeyListener = new MyKeyListener();
        addKeyListener(myKeyListener);

        MyMouseInputListener myMouseInputListener = new MyMouseInputListener();
        addMouseListener(myMouseInputListener);
        addMouseMotionListener(myMouseInputListener);

        oldPaddle = new Paddle(PADDLE_X_POSITION, PADDLE_Y_POSITION, PADDLE_LENGTH, PADDLE_HEIGHT);
        unionPaddle = new Paddle(PADDLE_X_POSITION, PADDLE_Y_POSITION, PADDLE_LENGTH, PADDLE_HEIGHT);
        paddle = new Paddle(PADDLE_X_POSITION, PADDLE_Y_POSITION, PADDLE_LENGTH, PADDLE_HEIGHT);
        ball = new Ball(BALL_X_POSITION, BALL_Y_POSITION, BALL_SIZE, BALL_SIZE, BALL_X_VELOCITY, BALL_Y_VELOCITY);
    }

    public void animate() {
        class MyRunnable implements Runnable {
            public void run() {
                while (true) {
                    if (BALL_MOVEMENT_ON) {
                        if (COLLISION_ON) {
                            ball.move(unionPaddle);
                        }
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

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.GREEN.darker().darker());
        g2.fill(unionPaddle);

        g2.setColor(Color.GREEN.darker().darker().darker());
        g2.fill(oldPaddle);

        g2.setColor(Color.GREEN.darker());
        g2.fill(paddle);
        g2.setColor(Color.BLUE);
        g2.draw(paddle);

        g2.setColor(Color.WHITE);
        g2.fill(ball);

        if (DEBUG_TEXT_ON) {
            drawDebugText(g2);
        }

        if (PRINT_PADDLE_INFO) {
            String s0 = String.valueOf(oldPaddle);
            String s1 = String.valueOf(paddle);
            String s2 = String.valueOf(unionPaddle);
            System.out.println("oldPaddle:   " + s0);
            System.out.println("paddle:      " + s1);
            System.out.println("unionPaddle: " + s2);
            System.out.println();
            PRINT_PADDLE_INFO = false;
        }
    }

    private void drawDebugText(Graphics2D g2) {
        Font font = new Font("Consolas", Font.PLAIN, 10);
        g2.setFont(font);

        g2.drawString("Pause: " + PAUSE_TIME, 10, 10);
        g2.drawString("Collision: " + COLLISION_ON, 10, 25);

        String paddleTop = String.valueOf(paddle.getTopEdge());
        String paddleLeft = String.valueOf(paddle.getLeftEdge());
        String paddleRight = String.valueOf(paddle.getRightEdge());
        String paddleBottom = String.valueOf(paddle.getBottomEdge());

        String ballTop = String.valueOf(ball.getTopEdge());
        String ballLeft = String.valueOf(ball.getLeftEdge());
        String ballRight = String.valueOf(ball.getRightEdge());
        String ballBottom = String.valueOf(ball.getBottomEdge());

        int paddleX = 150;
        int paddleY = 35;
        int ballX = 300;
        int ballY = 35;

        g2.drawString("paddle", paddleX, paddleY - 15);
        g2.drawString(paddleTop, paddleX + 25, paddleY);
        g2.drawString(paddleLeft, paddleX, paddleY + 15);
        g2.drawString(paddleRight, paddleX + 50, paddleY + 15);
        g2.drawString(paddleBottom, paddleX + 25, paddleY + 30);

        g2.drawString("ball", ballX, ballY - 15);
        g2.drawString(ballTop, ballX + 25, ballY);
        g2.drawString(ballLeft, ballX, ballY + 15);
        g2.drawString(ballRight, ballX + 50, ballY + 15);
        g2.drawString(ballBottom, ballX + 25, ballY + 30);

    }

}
