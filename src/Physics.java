import java.awt.geom.Point2D;

/**
 Created by Brian on 6/3/2015.
 */

public class Physics {

    private enum Axis {
        X, Y
    }

    // -------------------------------------------------------------------------

    public static Paddle[] movePaddle(Paddle paddle, Ball ball, Point2D.Double mousePos) {
        if (Globals.PADDLE_HORIZONTAL_MOVEMENT_ONLY) {
            paddle.setLocation(mousePos.x, paddle.y);
        } else if (Globals.PADDLE_VERTICAL_MOVEMENT_ONLY) {
            paddle.setLocation(paddle.x, mousePos.y);
        } else {
            paddle.setLocation(mousePos.x, mousePos.y);
        }

        handleCollision(paddle);

        Paddle oldPaddle = new Paddle(paddle, (int) (paddle.x-paddle.getxVel()), (int) (paddle.y-paddle.getyVel()));
        Paddle unionPaddle = paddle.union(oldPaddle);

        if (Globals.COLLISION_ON) {
            handleCollision(unionPaddle, ball);
        }

        return new Paddle[]{oldPaddle, paddle, unionPaddle};

    }

    private static void handleCollision(Paddle paddle) {
        if (paddle.getRightEdge() > Globals.WINDOW_RIGHT_EDGE) {
            paddle.setRightEdge(Globals.WINDOW_RIGHT_EDGE);
        }
        if (paddle.getBottomEdge() > Globals.WINDOW_BOTTOM_EDGE) {
            paddle.setBottomEdge(Globals.WINDOW_BOTTOM_EDGE);
        }
    }

    private static void handleCollision(Paddle paddle, Ball ball) {

        boolean ballLeftSideHit = paddle.contains(ball.getTopLeft()) && paddle.contains(ball.getBottomLeft());
        boolean ballRightSideHit = paddle.contains(ball.getTopRight()) && paddle.contains(ball.getBottomRight());
        boolean ballTopSideHit = paddle.contains(ball.getTopLeft()) && paddle.contains(ball.getTopRight());
        boolean ballBottomSideHit = paddle.contains(ball.getBottomLeft()) && paddle.contains(ball.getBottomRight());

        Axis largerChange = paddle.getxSpeed() > paddle.getySpeed() ? Axis.X : Axis.Y;
        String collisionInfo = null;


        // Paddle moving LEFT UP
        if (paddle.movingLeftUp() && ballRightSideHit && ballBottomSideHit) {
            if (largerChange == Axis.X) {
                ball.setRightEdge(paddle.getLeftEdge());
                collisionInfo = "Ball's RIGHT,BOTTOM sides hit while paddle moving LEFT,UP.  Setting to LEFT.";
            } else {
                ball.setBottomEdge(paddle.getTopEdge());
                collisionInfo = "Ball's RIGHT,BOTTOM sides hit while paddle moving LEFT,UP.  Setting to TOP.";
            }
        }

        // Paddle moving RIGHT UP
        else if (paddle.movingRightUp() && ballLeftSideHit && ballBottomSideHit) {
            if (largerChange == Axis.X) {
                ball.setLeftEdge(paddle.getRightEdge());
                collisionInfo = "Ball's LEFT,BOTTOM sides hit while paddle moving RIGHT,UP.  Setting to RIGHT.";
            } else {
                ball.setBottomEdge(paddle.getTopEdge());
                collisionInfo = "Ball's LEFT,BOTTOM sides hit while paddle moving RIGHT,UP.  Setting to TOP.";
            }
        }

        // Paddle moving LEFT DOWN
        else if (paddle.movingLeftDown() && ballRightSideHit && ballTopSideHit) {
            if (largerChange == Axis.X) {
                ball.setRightEdge(paddle.getLeftEdge());
                collisionInfo = "Ball's RIGHT,TOP sides hit while paddle moving LEFT,DOWN.  Setting to LEFT.";
            } else {
                ball.setTopEdge(paddle.getBottomEdge());
                collisionInfo = "Ball's RIGHT,TOP sides hit while paddle moving LEFT,DOWN.  Setting to BOTTOM.";
            }
        }

        // Paddle moving RIGHT DOWN
        else if (paddle.movingRightDown() && ballLeftSideHit && ballTopSideHit) {
            if (largerChange == Axis.X) {
                ball.setLeftEdge(paddle.getRightEdge());
                collisionInfo = "Ball's LEFT,TOP sides hit while paddle moving RIGHT,DOWN.  Setting to RIGHT.";
            } else {
                ball.setTopEdge(paddle.getBottomEdge());
                collisionInfo = "Ball's LEFT,TOP sides hit while paddle moving RIGHT,DOWN.  Setting to BOTTOM.";
            }
        }

        // Paddle moving LEFT
        else if (paddle.movingLeft() && ballRightSideHit) {
            ball.setRightEdge(paddle.getLeftEdge());
            collisionInfo = "Ball's RIGHT side hit while paddle moving LEFT.  Setting to LEFT";
        }

        // Paddle moving RIGHT
        else if (paddle.movingRight() && ballLeftSideHit) {
            ball.setLeftEdge(paddle.getRightEdge());
            collisionInfo = "Ball's LEFT side hit while paddle moving RIGHT.  Setting to RIGHT";
        }

        // Paddle moving UP
        else if (paddle.movingUp() && ballBottomSideHit) {
            ball.setBottomEdge(paddle.getTopEdge());
            collisionInfo = "Ball's BOTTOM side hit while paddle moving UP.  Setting to TOP";
        }

        // Paddle moving DOWN
        else if (paddle.movingDown() && ballTopSideHit) {
            ball.setTopEdge(paddle.getBottomEdge());
            collisionInfo = "Ball's TOP side hit while paddle moving DOWN.  Setting to BOTTOM";
        }

        Globals.PADDLE_BALL_COLLISION_INFO = collisionInfo;
    }

    // -------------------------------------------------------------------------


}
