/**
 Created by Brian on 5/28/2015.
 */
public class Ball extends Rect {

    private int xVelocity;
    private int yVelocity;

    public Ball(int xStartPos, int yStartPos, int width, int height, int xStartVel, int yStartVel) {
        super(xStartPos, yStartPos, width, height);
        xVelocity = xStartVel;
        yVelocity = yStartVel;
    }

    public void move(Paddle paddle) {
        translate(xVelocity, yVelocity);
        checkForWallCollisions();
        checkForPaddleCollision1(paddle);
    }

    private void checkForWallCollisions() {
        if (getLeftEdge() <= Constants.WINDOW_LEFT_EDGE) {
            xVelocity = Math.abs(xVelocity);
        }
        if (getRightEdge() >= Constants.WINDOW_RIGHT_EDGE) {
            xVelocity = Math.abs(xVelocity) * -1;
        }
        if (getTopEdge() <= Constants.WINDOW_TOP_EDGE) {
            yVelocity = Math.abs(yVelocity);
        }
        if (getBottomEdge() >= Constants.WINDOW_BOTTOM_EDGE) {
            yVelocity = Math.abs(yVelocity) * -1;
        }
    }

    private void checkForPaddleCollision1(Paddle paddle) {
        boolean topLeftHit = paddle.contains(getTopLeft());
        boolean topRightHit = paddle.contains(getTopRight());
        boolean bottomLeftHit = paddle.contains(getBottomLeft());
        boolean bottomRightHit = paddle.contains(getBottomRight());

        if (topLeftHit && bottomLeftHit) {
            xVelocity = Math.abs(xVelocity);
        }

        else if (topRightHit && bottomRightHit) {
            xVelocity = Math.abs(xVelocity) * -1;
        }

        else if (bottomLeftHit || bottomRightHit) {
            yVelocity = Math.abs(yVelocity) * -1;
        }

        else if (topLeftHit || topRightHit) {
            yVelocity = Math.abs(yVelocity);
        }

    }

}
