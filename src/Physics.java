import java.awt.*;

/**
 Created by Brian on 6/3/2015.
 */

public class Physics {

    private enum Axis {
        X, Y
    }

    public static Paddle[] movePaddle(Paddle paddle, Ball ball, Point mousePos) {
        if (Globals.PADDLE_HORIZONTAL_MOVEMENT_ONLY) {
            paddle.setLocation(mousePos.x, paddle.y);
        } else if (Globals.PADDLE_VERTICAL_MOVEMENT_ONLY) {
            paddle.setLocation(paddle.x, mousePos.y);
        } else {
            paddle.setLocation(mousePos.x, mousePos.y);
        }

        Paddle oldPaddle = new Paddle(paddle, paddle.x-paddle.getxVel(), paddle.y-paddle.getyVel());
        Paddle unionPaddle = paddle.union(oldPaddle);

        if (Globals.COLLISION_ON) {
            checkForPaddleBallCollision(unionPaddle, ball);
        }

        return new Paddle[]{oldPaddle, paddle, unionPaddle};

    }

    private static void checkForPaddleBallCollision(Paddle paddle, Ball ball) {

        boolean ballLeftSideHit = paddle.contains(ball.getTopLeft()) && paddle.contains(ball.getBottomLeft());
        boolean ballRightSideHit = paddle.contains(ball.getTopRight()) && paddle.contains(ball.getBottomRight());
        boolean ballTopSideHit = paddle.contains(ball.getTopLeft()) && paddle.contains(ball.getTopRight());
        boolean ballBottomSideHit = paddle.contains(ball.getBottomLeft()) && paddle.contains(ball.getBottomRight());

        boolean paddleMovingLeft = paddle.getxVel() < 0;
        boolean paddleMovingRight = paddle.getxVel() > 0;
        boolean paddleMovingUp = paddle.getyVel() < 0;
        boolean paddleMovingDown = paddle.getyVel() > 0;

        Axis largerChange = Math.abs(paddle.getxVel()) > Math.abs(paddle.getyVel()) ? Axis.X : Axis.Y;
        String collisionInfo = null;


        // Paddle moving LEFT UP
        if (ballRightSideHit && paddleMovingLeft && ballBottomSideHit && paddleMovingUp) {
            if (largerChange == Axis.X) {
                ball.setRightEdge(paddle.getLeftEdge());
                collisionInfo = "Ball's RIGHT,BOTTOM sides hit while paddle moving LEFT,UP.  Setting to LEFT.";
            } else {
                ball.setBottomEdge(paddle.getTopEdge());
                collisionInfo = "Ball's RIGHT,BOTTOM sides hit while paddle moving LEFT,UP.  Setting to TOP.";
            }
        }

        // Paddle moving RIGHT UP
        else if (ballLeftSideHit && paddleMovingRight && ballBottomSideHit && paddleMovingUp) {
            if (largerChange == Axis.X) {
                ball.setLeftEdge(paddle.getRightEdge());
                collisionInfo = "Ball's LEFT,BOTTOM sides hit while paddle moving RIGHT,UP.  Setting to RIGHT.";
            } else {
                ball.setBottomEdge(paddle.getTopEdge());
                collisionInfo = "Ball's LEFT,BOTTOM sides hit while paddle moving RIGHT,UP.  Setting to TOP.";
            }
        }

        // Paddle moving LEFT DOWN
        else if (ballRightSideHit && paddleMovingLeft && ballTopSideHit && paddleMovingDown) {
            if (largerChange == Axis.X) {
                ball.setRightEdge(paddle.getLeftEdge());
                collisionInfo = "Ball's RIGHT,TOP sides hit while paddle moving LEFT,DOWN.  Setting to LEFT.";
            } else {
                ball.setTopEdge(paddle.getBottomEdge());
                collisionInfo = "Ball's RIGHT,TOP sides hit while paddle moving LEFT,DOWN.  Setting to BOTTOM.";
            }
        }

        // Paddle moving RIGHT DOWN
        else if (ballLeftSideHit && paddleMovingRight && ballTopSideHit && paddleMovingDown) {
            if (largerChange == Axis.X) {
                ball.setLeftEdge(paddle.getRightEdge());
                collisionInfo = "Ball's LEFT,TOP sides hit while paddle moving RIGHT,DOWN.  Setting to RIGHT.";
            } else {
                ball.setTopEdge(paddle.getBottomEdge());
                collisionInfo = "Ball's LEFT,TOP sides hit while paddle moving RIGHT,DOWN.  Setting to BOTTOM.";
            }
        }

        // Paddle moving LEFT
        else if (ballRightSideHit && paddleMovingLeft) {
            ball.setRightEdge(paddle.getLeftEdge());
            collisionInfo = "Ball's RIGHT side hit while paddle moving LEFT.  Setting to LEFT";
        }

        // Paddle moving RIGHT
        else if (ballLeftSideHit && paddleMovingRight) {
            ball.setLeftEdge(paddle.getRightEdge());
            collisionInfo = "Ball's LEFT side hit while paddle moving RIGHT.  Setting to RIGHT";
        }

        // Paddle moving UP
        else if (ballBottomSideHit && paddleMovingUp) {
            ball.setBottomEdge(paddle.getTopEdge());
            collisionInfo = "Ball's BOTTOM side hit while paddle moving UP.  Setting to TOP";
        }

        // Paddle moving DOWN
        else if (ballTopSideHit && paddleMovingDown) {
            ball.setTopEdge(paddle.getBottomEdge());
            collisionInfo = "Ball's TOP side hit while paddle moving DOWN.  Setting to BOTTOM";
        }

        Globals.PADDLE_BALL_COLLISION_INFO = collisionInfo;
    }

}
