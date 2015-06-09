/**
 Created by Brian on 5/28/2015.
 */
public class Ball extends MovingRect {

    public Ball(int xStartPos, int yStartPos, int width, int height, int xStartVel, int yStartVel) {
        super(xStartPos, yStartPos, width, height, xStartVel, yStartVel);
    }

    public void move() {
        translate(getxVel(), getyVel());
        checkForWallCollisions();
    }

    public void move(Paddle paddle) {
        translate(getxVel(), getyVel());
        checkForWallCollisions();
        checkForPaddleCollision1(paddle);
    }

    private void checkForWallCollisions() {
        if (getLeftEdge() <= Globals.WINDOW_LEFT_EDGE) {
            setxVel(Math.abs(getxVel()));
        }
        if (getRightEdge() >= Globals.WINDOW_RIGHT_EDGE) {
            setxVel(Math.abs(getxVel()) * -1);
        }
        if (getTopEdge() <= Globals.WINDOW_TOP_EDGE) {
            setyVel(Math.abs(getyVel()));
        }
        if (getBottomEdge() >= Globals.WINDOW_BOTTOM_EDGE) {
            setyVel(Math.abs(getyVel()) * -1);
        }
    }

    private void checkForPaddleCollision1(Paddle paddle) {
        boolean topLeftHit = paddle.contains(getTopLeft());
        boolean topRightHit = paddle.contains(getTopRight());
        boolean bottomLeftHit = paddle.contains(getBottomLeft());
        boolean bottomRightHit = paddle.contains(getBottomRight());

        // Ball's LEFT hit
        if (topLeftHit && bottomLeftHit) {
            setxVel(Math.abs(getxVel()));
        }

        // Ball's RIGHT hit
        else if (topRightHit && bottomRightHit) {
            setxVel(Math.abs(getxVel()) * -1);
        }

        // Ball's BOTTOM hit
        else if (bottomLeftHit || bottomRightHit) {
            setyVel(Math.abs(getyVel()) * -1);
        }

        // Ball's TOP hit
        else if (topLeftHit || topRightHit) {
            setyVel(Math.abs(getyVel()));
        }

    }

}
