import java.awt.*;

/**
 Created by Brian on 5/28/2015.
 */
public class Paddle extends Rect {

    private int xVel;
    private int yVel;
    private enum Axis {
        X, Y
    }
    private String collisionInfo;

    public Paddle(int startX, int startY, int width, int height) {
        super(startX, startY, width, height);
    }

    public Paddle(Paddle oldPaddle) {
        super(oldPaddle.x, oldPaddle.y, oldPaddle.width, oldPaddle.height);
    }

    public Paddle(Rectangle oldRect) {
        super(oldRect.x, oldRect.y, oldRect.width, oldRect.height);
    }

    public void setLocation(int newX, int newY) {
        int oldX = x;
        int oldY = y;
        super.setLocation(newX, newY);
        setVelocity(x-oldX, y-oldY);

        if (getRightEdge() > Constants.WINDOW_RIGHT_EDGE) {
            setRightEdge(Constants.WINDOW_RIGHT_EDGE);
        }
        if (getBottomEdge() > Constants.WINDOW_BOTTOM_EDGE) {
            setBottomEdge(Constants.WINDOW_BOTTOM_EDGE);
        }
    }

    private void setVelocity(int xVel, int yVel) {
        this.xVel = xVel;
        this.yVel = yVel;
    }

    public Paddle union(Paddle prevPaddle) {
        Paddle newPaddle = new Paddle(super.union(prevPaddle));
        newPaddle.setVelocity(x-prevPaddle.x, y-prevPaddle.y);
        newPaddle.update();
        return newPaddle;
    }

    public void checkForBallCollision(Ball ball) {
        boolean ballLeftSideHit = contains(ball.getTopLeft()) && contains(ball.getBottomLeft());
        boolean ballRightSideHit = contains(ball.getTopRight()) && contains(ball.getBottomRight());
        boolean ballTopSideHit = contains(ball.getTopLeft()) && contains(ball.getTopRight());
        boolean ballBottomSideHit = contains(ball.getBottomLeft()) && contains(ball.getBottomRight());

        boolean paddleMovingLeft = (xVel < 0);
        boolean paddleMovingRight = (xVel > 0);
        boolean paddleMovingUp = (yVel < 0);
        boolean paddleMovingDown = (yVel > 0);

        Axis largerChange = Math.abs(xVel) > Math.abs(yVel) ? Axis.X : Axis.Y;


        // Paddle moving LEFT UP
        if ((ballRightSideHit && paddleMovingLeft) && (ballBottomSideHit && paddleMovingUp)) {
            if (largerChange == Axis.X) {
                ball.setRightEdge(getLeftEdge());
                collisionInfo = "Ball's RIGHT,BOTTOM sides hit while paddle moving LEFT,UP.  Setting to LEFT.";
            } else {
                ball.setBottomEdge(getTopEdge());
                collisionInfo = "Ball's RIGHT,BOTTOM sides hit while paddle moving LEFT,UP.  Setting to TOP.";
            }
        }

        // Paddle moving RIGHT UP
        else if ((ballLeftSideHit && paddleMovingRight) && (ballBottomSideHit && paddleMovingUp)) {
            if (largerChange == Axis.X) {
                ball.setLeftEdge(getRightEdge());
                collisionInfo = "Ball's LEFT,BOTTOM sides hit while paddle moving RIGHT,UP.  Setting to RIGHT.";
            } else {
                ball.setBottomEdge(getTopEdge());
                collisionInfo = "Ball's LEFT,BOTTOM sides hit while paddle moving RIGHT,UP.  Setting to TOP.";
            }
        }

        // Paddle moving LEFT DOWN
        else if ((ballRightSideHit && paddleMovingLeft) && (ballTopSideHit && paddleMovingDown)) {
            if (largerChange == Axis.X) {
                ball.setRightEdge(getLeftEdge());
                collisionInfo = "Ball's RIGHT,TOP sides hit while paddle moving LEFT,DOWN.  Setting to LEFT.";
            } else {
                ball.setTopEdge(getBottomEdge());
                collisionInfo = "Ball's RIGHT,TOP sides hit while paddle moving LEFT,DOWN.  Setting to BOTTOM.";
            }
        }

        // Paddle moving RIGHT DOWN
        else if ((ballLeftSideHit && paddleMovingRight) && (ballTopSideHit && paddleMovingDown)) {
            if (largerChange == Axis.X) {
                ball.setLeftEdge(getRightEdge());
                collisionInfo = "Ball's LEFT,TOP sides hit while paddle moving RIGHT,DOWN.  Setting to RIGHT.";
            } else {
                ball.setTopEdge(getBottomEdge());
                collisionInfo = "Ball's LEFT,TOP sides hit while paddle moving RIGHT,DOWN.  Setting to BOTTOM.";
            }
        }

        // Paddle moving LEFT
        else if (ballRightSideHit && paddleMovingLeft) {
            ball.setRightEdge(getLeftEdge());
            collisionInfo = "Ball's RIGHT side hit while paddle moving LEFT.  Setting to LEFT";
        }

        // Paddle moving RIGHT
        else if (ballLeftSideHit && paddleMovingRight) {
            ball.setLeftEdge(getRightEdge());
            collisionInfo = "Ball's LEFT side hit while paddle moving RIGHT.  Setting to RIGHT";
        }

        // Paddle moving UP
        else if (ballBottomSideHit && paddleMovingUp) {
            ball.setBottomEdge(getTopEdge());
            collisionInfo = "Ball's BOTTOM side hit while paddle moving UP.  Setting to TOP";
        }

        // Paddle moving DOWN
        else if (ballTopSideHit && paddleMovingDown) {
            ball.setTopEdge(getBottomEdge());
            collisionInfo = "Ball's TOP side hit while paddle moving DOWN.  Setting to BOTTOM";
        }

    }

    public void printPaddleBallCollisionInfo() {
        if (collisionInfo != null) {
            System.out.println(collisionInfo);
        }
        collisionInfo = null;
    }
}
