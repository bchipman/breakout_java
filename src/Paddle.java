import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 Created by Brian on 5/28/2015.
 */
public class Paddle extends MovingRectDouble {

    private enum Axis {
        X, Y
    }

    public Paddle() {
        super(0,0,0,0);
    }

    public Paddle(double startX, double startY, double width, double height) {
        super(startX, startY, width, height);
    }

    public Paddle(Paddle oldPaddle, double startX, double startY) {
        super(startX, startY, oldPaddle.width, oldPaddle.height);
    }

    public Paddle(Rectangle2D.Double rectangle2D) {
        super(rectangle2D.x, rectangle2D.y, rectangle2D.width, rectangle2D.height);
    }

    public void setLocation(double newX, double newY) {
        double oldX = x;
        double oldY = y;
        //super.setLocation(newX, newY);
        x = newX;
        y = newY;
        update();
        setVel(x - oldX, y - oldY);
    }

    public Paddle union(Paddle prevPaddle) {
        Paddle newPaddle = new Paddle((Rectangle2D.Double) createUnion(prevPaddle));
        //Paddle newPaddle = new Paddle(super.union(prevPaddle));
        newPaddle.setVel(x - prevPaddle.x, y - prevPaddle.y);
        newPaddle.update();
        return newPaddle;
    }


    // -----------------------------------------------------------------------------
    // -----------------------------------------------------------------------------


    public Paddle[] move(Ball ball, Point2D.Double mousePos) {
        if (Globals.PADDLE_HORIZONTAL_MOVEMENT_ONLY) {
            setLocation(mousePos.x, y);
        } else if (Globals.PADDLE_VERTICAL_MOVEMENT_ONLY) {
            setLocation(x, mousePos.y);
        } else {
            setLocation(mousePos.x, mousePos.y);
        }

        // Used for ghost paddle bug fix (see a few lines below)
        double adjWidth = width;
        if (getRightEdge() >= Globals.WINDOW_RIGHT_EDGE) {
            adjWidth = (Globals.WINDOW_RIGHT_EDGE - getLeftEdge()) / 2.0;
            adjWidth = adjWidth < 0 ? 0 : adjWidth;
            System.out.println("adj width: " + adjWidth);
        }

        handleCollision();

        // Fix for bug where ghost paddle would extend from left of paddle when
        //  paddle was up against the right wall but the mouse was still within the window.
        Paddle oldPaddle, unionPaddle;
        if (adjWidth != width) {
            oldPaddle = new Paddle();
            unionPaddle = new Paddle();
        } else {
            oldPaddle = new Paddle(x - getxVel(), y - getyVel(), adjWidth, height);
            unionPaddle = union(oldPaddle);
        }

        if (Globals.COLLISION_ON) {
            handleCollision(unionPaddle, ball);
        }

        return new Paddle[]{oldPaddle, this, unionPaddle};

    }

    private void handleCollision() {
        if (getRightEdge() > Globals.WINDOW_RIGHT_EDGE) {
            setRightEdge(Globals.WINDOW_RIGHT_EDGE);
        }
        if (getBottomEdge() > Globals.WINDOW_BOTTOM_EDGE) {
            setBottomEdge(Globals.WINDOW_BOTTOM_EDGE);
        }
    }

    private void handleCollision(Paddle paddle, Ball ball) {

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

}
