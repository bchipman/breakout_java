import java.awt.*;

/**
 Created by Brian on 5/28/2015.
 */
public class Ball extends MyRectangle {

    private static final int REVERSE_DIRECTION = -1;

    private int xVelocity;
    private int yVelocity;

    public Ball(int xStartPos, int yStartPos, int width, int height, int xStartVel, int yStartVel) {
        super(xStartPos, yStartPos, width, height);
        xVelocity = xStartVel;
        yVelocity = yStartVel;
    }

    public void refresh(Paddle paddle) {
        translate(xVelocity, yVelocity);
        update();
        checkForWallCollisions();
        checkForPaddleCollision(paddle);
    }

    private void checkForWallCollisions() {
        if (getLeftEdge() <= Constants.WINDOW_LEFT_EDGE || getRightEdge() >= Constants.WINDOW_RIGHT_EDGE) {
            xVelocity *= REVERSE_DIRECTION;
        }
        if (getTopEdge() <= Constants.WINDOW_TOP_EDGE || getBottomEdge() >= Constants.WINDOW_BOTTOM_EDGE) {
            yVelocity *= REVERSE_DIRECTION;
        }
    }

    private void checkForPaddleCollision(Paddle paddle) {


    }

}
