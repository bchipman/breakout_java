import java.awt.*;

/**
 Created by Brian on 6/3/2015.
 */

public class Physics {

    private enum Axis {
        X, Y
    }

    // -------------------------------------------------------------------------

    public static Paddle[] movePaddle(Paddle paddle, Ball ball, Point mousePos) {
        if (Globals.PADDLE_HORIZONTAL_MOVEMENT_ONLY) {
            paddle.setLocation(mousePos.x, paddle.y);
        } else if (Globals.PADDLE_VERTICAL_MOVEMENT_ONLY) {
            paddle.setLocation(paddle.x, mousePos.y);
        } else {
            paddle.setLocation(mousePos.x, mousePos.y);
        }

        handleCollision(paddle);

        Paddle oldPaddle = new Paddle(paddle, paddle.x-paddle.getxVel(), paddle.y-paddle.getyVel());
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

        Axis largerChange = Math.abs(paddle.getxVel()) > Math.abs(paddle.getyVel()) ? Axis.X : Axis.Y;
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

    public static void moveBall(Ball ball, Paddle paddle, Blocks blocks) {
        ball.translate(ball.getxVel(), ball.getyVel());
        handleCollision(ball);
        if (Globals.COLLISION_ON) {
            handleCollision(ball, paddle);
        }
        handleCollision(ball, blocks);
    }

    private static void handleCollision(Ball ball) {
        if (ball.getLeftEdge() <= Globals.WINDOW_LEFT_EDGE) {
            ball.setxDir(Globals.Dir.RIGHT);
        }
        if (ball.getRightEdge() >= Globals.WINDOW_RIGHT_EDGE) {
            ball.setxDir(Globals.Dir.LEFT);
        }
        if (ball.getTopEdge() <= Globals.WINDOW_TOP_EDGE) {
            ball.setyDir(Globals.Dir.DOWN);
        }
        if (ball.getBottomEdge() >= Globals.WINDOW_BOTTOM_EDGE) {
            ball.setyDir(Globals.Dir.UP);
        }
    }

    private static void handleCollision(Ball ball, Paddle paddle) {
        boolean topLeftHit = paddle.contains(ball.getTopLeft());
        boolean topRightHit = paddle.contains(ball.getTopRight());
        boolean bottomLeftHit = paddle.contains(ball.getBottomLeft());
        boolean bottomRightHit = paddle.contains(ball.getBottomRight());

        // Ball's LEFT hit
        if (topLeftHit && bottomLeftHit) {
            ball.setxDir(Globals.Dir.RIGHT);
        }

        // Ball's RIGHT hit
        else if (topRightHit && bottomRightHit) {
            ball.setxDir(Globals.Dir.LEFT);
        }

        // Ball's BOTTOM hit
        else if (bottomLeftHit || bottomRightHit) {
            int xSize, xHit;


            // Ball's left side hanging over edge
            // []
            //  ------------
            if (ball.getLeftEdge() < paddle.getLeftEdge()) {
                xSize = ball.getRightEdge() - paddle.getLeftEdge();
                xHit = (xSize / 2) + paddle.getLeftEdge();
            }

            // Ball's right side hanging over edge
            //             []
            //  ------------
            else if (ball.getRightEdge() > paddle.getRightEdge()) {
                xSize = paddle.getRightEdge() - ball.getLeftEdge();
                xHit = (xSize / 2) + ball.getLeftEdge();
            }

            // Ball completely within paddle's x
            else {
                xSize = ball.getRightEdge() - ball.getLeftEdge();
                xHit = (xSize / 2) + ball.getLeftEdge();
            }

            Globals.BALL_PADDLE_COLLISION_X = xHit;
            Globals.BALL_PADDLE_COLLISION_Y = ball.getBottomEdge();
            Globals.BALL_PADDLE_COLLISION_NUM_FRAMES_DISPLAY = 50;

            // Determine ball's new velocity depending on where ball hit on paddle
            double howFarInRel = (double) (xHit - paddle.getLeftEdge()) / paddle.getWidth();
            System.out.printf("howFarInRel: %.2f\n", howFarInRel);

            if (howFarInRel < 0.10) {
                ball.setxVel(2);
                ball.setxDir(Globals.Dir.LEFT);
            }
            else if (howFarInRel >= 0.10 && howFarInRel < 0.45) {
                ball.setxVel(1);
                ball.setxDir(Globals.Dir.LEFT);
            }
            else if (howFarInRel >= 0.45 && howFarInRel < 0.55) {
                ball.setxVel(0);
            }
            else if (howFarInRel >= 0.55 && howFarInRel < 0.90) {
                ball.setxVel(1);
                ball.setxDir(Globals.Dir.RIGHT);
            }
            else if (howFarInRel >= 0.90) {
                ball.setxVel(2);
                ball.setxDir(Globals.Dir.RIGHT);
            }
            ball.setyDir(Globals.Dir.UP);
        }

        // Ball's TOP hit
        else if (topLeftHit || topRightHit) {
            ball.setyDir(Globals.Dir.DOWN);
        }

    }

    private static void handleCollision(Ball ball, Blocks blocks) {

        Blocks blocksCopy = new Blocks(blocks);

        for (Block block : blocksCopy.getBlocks()) {
            boolean topLeftHit = block.contains(ball.getTopLeft());
            boolean topRightHit = block.contains(ball.getTopRight());
            boolean bottomLeftHit = block.contains(ball.getBottomLeft());
            boolean bottomRightHit = block.contains(ball.getBottomRight());

            // Ball's LEFT hit
            if (topLeftHit && bottomLeftHit) {
                ball.setxDir(Globals.Dir.RIGHT);
            }

            // Ball's RIGHT hit
            else if (topRightHit && bottomRightHit) {
                ball.setxDir(Globals.Dir.LEFT);
            }

            // Ball's BOTTOM hit
            else if (bottomLeftHit || bottomRightHit) {
                ball.setyDir(Globals.Dir.UP);
            }

            // Ball's TOP hit
            else if (topLeftHit || topRightHit) {
                ball.setyDir(Globals.Dir.DOWN);
            }

            if (topLeftHit || topRightHit || bottomLeftHit || bottomRightHit) {
                block.takeHit();
            }

        }
        blocks.removeDestroyedBlocks();

    }


}
