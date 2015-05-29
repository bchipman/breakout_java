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

    public void move(Paddle paddle) {
        translate(xVelocity, yVelocity);
        checkForWallCollisions();
        checkForPaddleCollision1(paddle);
    }

    private void checkForWallCollisions() {
        if (getLeftEdge() <= Constants.WINDOW_LEFT_EDGE || getRightEdge() >= Constants.WINDOW_RIGHT_EDGE) {
            xVelocity *= REVERSE_DIRECTION;
        }
        if (getTopEdge() <= Constants.WINDOW_TOP_EDGE || getBottomEdge() >= Constants.WINDOW_BOTTOM_EDGE) {
            yVelocity *= REVERSE_DIRECTION;
        }
    }

    private void checkForPaddleCollision1(Paddle paddle) {
        boolean topLeftHit = paddle.contains(getTopLeft());
        boolean topRightHit = paddle.contains(getTopRight());
        boolean bottomLeftHit = paddle.contains(getBottomLeft());
        boolean bottomRightHit = paddle.contains(getBottomRight());

        if (topLeftHit && bottomLeftHit) {
            xVelocity *= REVERSE_DIRECTION;
        }

        else if (topRightHit && bottomRightHit) {
            xVelocity *= REVERSE_DIRECTION;
        }

        else if (bottomLeftHit || bottomRightHit) {
            yVelocity *= REVERSE_DIRECTION;
        }

        else if (topLeftHit || topRightHit) {
            yVelocity *= REVERSE_DIRECTION;
        }

        //System.out.println(topLeftHit + " " + topRightHit + " " + bottomLeftHit + " " + bottomRightHit);

    }

    private void checkForPaddleCollision2(Paddle paddle) {
        boolean rightEdgeInBetweenPaddle = paddle.getLeftEdge() <= getRightEdge() && paddle.getRightEdge() >= getRightEdge();
        boolean leftEdgeInBetweenPaddle =paddle.getLeftEdge() <= getLeftEdge() && paddle.getRightEdge() >= getLeftEdge();
        boolean topEdgeInBetweenPaddle = paddle.getTopEdge() <= getTopEdge() && paddle.getBottomEdge() >= getBottomEdge();
        boolean bottomEdgeInBetweenPaddle = paddle.getTopEdge() <= getBottomEdge() && paddle.getBottomEdge() >= getTopEdge();

    }

}
