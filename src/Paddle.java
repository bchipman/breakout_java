/**
 Created by Brian on 5/28/2015.
 */
public class Paddle extends Rect {

    public Paddle(int startX, int startY, int width, int height) {
        super(startX, startY, width, height);
    }

    public void setLocation(int newX, int newY) {
        super.setLocation(newX, newY);
        if (getRightEdge() > Constants.WINDOW_RIGHT_EDGE) {
            setRightEdge(Constants.WINDOW_RIGHT_EDGE);
        }
        if (getBottomEdge() > Constants.WINDOW_BOTTOM_EDGE) {
            setBottomEdge(Constants.WINDOW_BOTTOM_EDGE);
        }
    }

    public void checkForBallCollision(Ball ball) {
        boolean topLeftHit = contains(ball.getTopLeft());
        boolean topRightHit = contains(ball.getTopRight());
        boolean bottomLeftHit = contains(ball.getBottomLeft());
        boolean bottomRightHit = contains(ball.getBottomRight());

        if (topLeftHit && bottomLeftHit) {
            ball.setLeftEdge(getRightEdge());
            System.out.println("Ball's LEFT side hit!");
        }

        else if (topRightHit && bottomRightHit) {
            ball.setRightEdge(getLeftEdge());
            System.out.println("Ball's RIGHT side hit!");
        }

        else if (bottomLeftHit || bottomRightHit) {
            ball.setBottomEdge(getTopEdge());
            System.out.println("Ball's BOTTOM side hit!");
        }

        else if (topLeftHit || topRightHit) {
            ball.setTopEdge(getBottomEdge());
            System.out.println("Ball's TOP side hit!");
        }

    }

}
