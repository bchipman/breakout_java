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
    private Blocks blocks;


    private class MyKeyListener implements KeyListener {
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() == '`') {
                Globals.DEBUG_TEXT_ON = !Globals.DEBUG_TEXT_ON;
            }
            if (e.getKeyChar() == '-') {
                Globals.PAUSE_TIME += 1;
            }
            if (e.getKeyChar() == '+') {
                Globals.PAUSE_TIME -= 1;
                Globals.PAUSE_TIME = (Globals.PAUSE_TIME < 1) ? 1 : Globals.PAUSE_TIME;
            }
            if (e.getKeyChar() == 'c') {
                Globals.COLLISION_ON = !Globals.COLLISION_ON;
            }
            if (e.getKeyChar() == 'm') {
                Globals.BALL_MOVEMENT_ON = !Globals.BALL_MOVEMENT_ON;
            }
            if (e.getKeyChar() == 'p') {
                Globals.PRINT_PADDLE_INFO = true;
            }
        }
        public void keyPressed(KeyEvent e) {
            if (e.getKeyChar() == 'v') {
                Globals.PADDLE_VERTICAL_MOVEMENT_ONLY = true;
            }
            if (e.getKeyChar() == 'h') {
                Globals.PADDLE_HORIZONTAL_MOVEMENT_ONLY = true;
            }
        }
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == 'v') {
                Globals.PADDLE_VERTICAL_MOVEMENT_ONLY = false;
            }
            if (e.getKeyChar() == 'h') {
                Globals.PADDLE_HORIZONTAL_MOVEMENT_ONLY = false;
            }
        }
    }

    private class MyMouseInputListener implements MouseInputListener {
        public void mousePressed(MouseEvent e) {
        }
        public void mouseReleased(MouseEvent e) {
        }
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                Globals.PAUSE_TIME += 5;
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                Globals.PAUSE_TIME -= 5;
                Globals.PAUSE_TIME = (Globals.PAUSE_TIME < 0) ? 0 : Globals.PAUSE_TIME;
            }
            else if (e.getButton() == MouseEvent.BUTTON2) {
                Globals.COLLISION_ON = !Globals.COLLISION_ON;
            }

        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
            Paddle[] updatedPaddles = Physics.movePaddle(paddle, ball, new Point(paddle.x, paddle.y));
            oldPaddle = updatedPaddles[0];
            paddle = updatedPaddles[1];
            unionPaddle = updatedPaddles[2];
        }
        public void mouseDragged(MouseEvent e) {
        }
        public void mouseMoved(MouseEvent e) {
            Paddle[] updatedPaddles = Physics.movePaddle(paddle, ball, new Point(e.getX(), Globals.PADDLE_Y_POSITION));
            oldPaddle = updatedPaddles[0];
            paddle = updatedPaddles[1];
            unionPaddle = updatedPaddles[2];

            if (Globals.PRINT_PADDLE_BALL_COLLISION_INFO) {
                printPaddleBallCollisionInfo();
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

        oldPaddle = new Paddle(Globals.PADDLE_X_POSITION, Globals.PADDLE_Y_POSITION, Globals.PADDLE_LENGTH, Globals.PADDLE_HEIGHT);
        unionPaddle = new Paddle(Globals.PADDLE_X_POSITION, Globals.PADDLE_Y_POSITION, Globals.PADDLE_LENGTH, Globals.PADDLE_HEIGHT);
        paddle = new Paddle(Globals.PADDLE_X_POSITION, Globals.PADDLE_Y_POSITION, Globals.PADDLE_LENGTH, Globals.PADDLE_HEIGHT);
        ball = new Ball(Globals.BALL_X_POSITION, Globals.BALL_Y_POSITION, Globals.BALL_SIZE, Globals.BALL_SIZE, Globals.BALL_X_VELOCITY, Globals.BALL_Y_VELOCITY);
        blocks = new Blocks();
    }

    public void animate() {
        class MyRunnable implements Runnable {
            public void run() {
                while (true) {
                    if (Globals.BALL_MOVEMENT_ON) {
                        Physics.moveBall(ball, unionPaddle, blocks);
                    }
                    repaint();
                    pause(Globals.PAUSE_TIME);
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

        Blocks blocksCopy = new Blocks(blocks);
        for (Block block : blocksCopy.getBlocks()) {
            g2.setColor(block.getColor());
            g2.fill(block);
            g2.setColor(Color.BLACK);
            g2.draw(block);
        }

        g2.setColor(Color.WHITE);
        g2.fill(ball);
        g2.setColor(Color.RED);
        if (Globals.BALL_PADDLE_COLLISION_NUM_FRAMES_DISPLAY > 0) {
            g2.fill(new Rectangle(Globals.BALL_PADDLE_COLLISION_X, Globals.BALL_PADDLE_COLLISION_Y, 2, 2));
            --Globals.BALL_PADDLE_COLLISION_NUM_FRAMES_DISPLAY;
        }

        if (Globals.DEBUG_TEXT_ON) {
            g2.setColor(Color.WHITE);
            drawDebugText(g2);
        }

        if (Globals.PRINT_PADDLE_INFO) {
            String s0 = String.valueOf(oldPaddle);
            String s1 = String.valueOf(paddle);
            String s2 = String.valueOf(unionPaddle);
            System.out.println("oldPaddle:   " + s0);
            System.out.println("paddle:      " + s1);
            System.out.println("unionPaddle: " + s2);
            System.out.println();
            Globals.PRINT_PADDLE_INFO = false;
        }
    }

    private void drawDebugText(Graphics2D g2) {
        Font font = new Font("Consolas", Font.PLAIN, 10);
        g2.setFont(font);

        g2.drawString("Pause: " + Globals.PAUSE_TIME, 10, 10);
        g2.drawString("Collision: " + Globals.COLLISION_ON, 10, 25);

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

        g2.drawString("ball velocity", ballX+150, ballY - 15);
        g2.drawString(String.valueOf(ball.getxVel()), ballX+150+25, ballY);
        g2.drawString(String.valueOf(ball.getyVel()), ballX+150+25, ballY+30);

    }

    private void printPaddleBallCollisionInfo() {
        if (Globals.PADDLE_BALL_COLLISION_INFO != null) {
            System.out.println(Globals.PADDLE_BALL_COLLISION_INFO);
        }
        Globals.PADDLE_BALL_COLLISION_INFO = null;
    }

}
