/**
 Created by Brian on 5/28/2015.
 */
public class Ball extends MovingRect {

    public Ball(int xStartPos, int yStartPos, int width, int height, int xStartVel, int yStartVel) {
        super(xStartPos, yStartPos, width, height, xStartVel, yStartVel);
    }

    public void checkForPaddleCollision1(Paddle paddle) {
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
