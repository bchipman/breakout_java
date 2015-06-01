/**
 Created by Brian on 5/28/2015.
 */
public class Paddle extends Rect {

    private int lastX;
    private int lastY;

    public Paddle(int startX, int startY, int width, int height) {
        super(startX, startY, width, height);
        lastX = startX;
        lastY = startY;
    }

    public void setLocation(int newX, int newY) {
        lastX = x;
        lastY = y;
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

        boolean ballLeftSideHit = topLeftHit && bottomLeftHit;
        boolean ballRightSideHit = topRightHit && bottomRightHit;
        boolean ballTopSideHit = topLeftHit && topRightHit;
        boolean ballBottomSideHit = bottomLeftHit && bottomRightHit;

        int xVel = x - lastX;
        boolean paddleMovingLeft = (xVel < 0);
        boolean paddleMovingRight = (xVel > 0);

        int yVel = y - lastY;
        boolean paddleMovingUp = (yVel < 0);
        boolean paddleMovingDown = (yVel > 0);


        // Ball's LEFT side hit while paddle moving RIGHT
        //  -> set ball's LEFT edge to that of paddle's RIGHT
        if (ballLeftSideHit && paddleMovingRight) {
            ball.setLeftEdge(getRightEdge());
            System.out.println("Ball's LEFT side hit!");
        }

        // Ball's RIGHT side hit while paddle moving LEFT
        //  -> set ball's RIGHT edge to that of paddle's LEFT
        if (ballRightSideHit && paddleMovingLeft) {
            ball.setRightEdge(getLeftEdge());
            System.out.println("Ball's RIGHT side hit!");
        }

        // Ball's TOP side hit while paddle moving DOWN
        //  -> set ball's TOP edge to that of paddle's BOTTOM
        if (ballTopSideHit && paddleMovingDown) {
            ball.setTopEdge(getBottomEdge());
            System.out.println("Ball's TOP side hit!");
        }

        // Ball's BOTTOM side hit while paddle moving UP
        //  -> set ball's BOTTOM edge to that of paddle's TOP
        if (ballBottomSideHit && paddleMovingUp) {
            ball.setBottomEdge(getTopEdge());
            System.out.println("Ball's BOTTOM side hit!");
        }

    }

}
