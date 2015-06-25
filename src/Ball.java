/**
 Created by Brian on 5/28/2015.
 */
public class Ball extends MovingRectDouble {

    public Ball(double xStartPos, double yStartPos, double width, double height, double xStartVel, double yStartVel) {
        super(xStartPos, yStartPos, width, height, xStartVel, yStartVel);
    }

    public void move(Paddle paddle, Blocks blocks) {
        translate();

        handleCollision();
        if (Globals.COLLISION_ON) {
            handleCollision(paddle);
        }
        handleCollision(blocks);
    }

    private void handleCollision() {
        if (getLeftEdge() <= Globals.WINDOW_LEFT_EDGE) {
            setxDir(Globals.Dir.RIGHT);
        }
        if (getRightEdge() >= Globals.WINDOW_RIGHT_EDGE) {
            setxDir(Globals.Dir.LEFT);
        }
        if (getTopEdge() <= Globals.WINDOW_TOP_EDGE) {
            setyDir(Globals.Dir.DOWN);
        }
        if (getBottomEdge() >= Globals.WINDOW_BOTTOM_EDGE) {
            setyDir(Globals.Dir.UP);
        }
    }

    private void handleCollision(Paddle paddle) {
        boolean topLeftHit = paddle.contains(getTopLeft());
        boolean topRightHit = paddle.contains(getTopRight());
        boolean bottomLeftHit = paddle.contains(getBottomLeft());
        boolean bottomRightHit = paddle.contains(getBottomRight());

        // Ball's LEFT hit
        if (topLeftHit && bottomLeftHit) {
            setxDir(Globals.Dir.RIGHT);
        }

        // Ball's RIGHT hit
        else if (topRightHit && bottomRightHit) {
            setxDir(Globals.Dir.LEFT);
        }

        // Ball's BOTTOM hit
        else if (bottomLeftHit || bottomRightHit) {
            double xSize, xHit;


            // Ball's left side hanging over edge
            // []
            //  ------------
            if (getLeftEdge() < paddle.getLeftEdge()) {
                xSize = getRightEdge() - paddle.getLeftEdge();
                xHit = (xSize / 2) + paddle.getLeftEdge();
            }

            // Ball's right side hanging over edge
            //             []
            //  ------------
            else if (getRightEdge() > paddle.getRightEdge()) {
                xSize = paddle.getRightEdge() - getLeftEdge();
                xHit = (xSize / 2) + getLeftEdge();
            }

            // Ball completely within paddle's x
            else {
                xSize = getRightEdge() - getLeftEdge();
                xHit = (xSize / 2) + getLeftEdge();
            }

            // Set global variables for displaying red dot on paddle where collision occurred
            Globals.BALL_PADDLE_COLLISION_X = (int) Math.round(xHit);
            Globals.BALL_PADDLE_COLLISION_Y = (int) Math.round(getBottomEdge());
            Globals.BALL_PADDLE_COLLISION_NUM_FRAMES_DISPLAY = 50;

            // Determine ball's new velocity depending on where ball hit on paddle
            double newxVel = (getxCenter() - paddle.getxCenter()) / (paddle.width / 2.0);

            // allow max/min angles of 22.5 degrees instead of 45
            newxVel *= 2;

            // Normalize vector
            double vectorLength = Math.sqrt((newxVel*newxVel) + (-1.0*-1.0));
            double newxVelNormalized = newxVel / vectorLength;
            double newyVelNormalized = -1 / vectorLength;

            // Set new velocity (multiply by 3 just to increase speeds overall)
            setxVel(newxVelNormalized * 3);
            setyVel(newyVelNormalized * 3);
        }

        // Ball's TOP hit
        else if (topLeftHit || topRightHit) {
            setyDir(Globals.Dir.DOWN);
        }

    }

    private void handleCollision(Blocks blocks) {

        Blocks blocksCopy = new Blocks(blocks);

        for (Block block : blocksCopy.getBlocks()) {
            boolean topLeftHit = block.contains(getTopLeft());
            boolean topRightHit = block.contains(getTopRight());
            boolean bottomLeftHit = block.contains(getBottomLeft());
            boolean bottomRightHit = block.contains(getBottomRight());

            // Ball's LEFT hit
            if (topLeftHit && bottomLeftHit) {
                setxDir(Globals.Dir.RIGHT);
            }

            // Ball's RIGHT hit
            else if (topRightHit && bottomRightHit) {
                setxDir(Globals.Dir.LEFT);
            }

            // Ball's BOTTOM hit
            else if (bottomLeftHit || bottomRightHit) {
                setyDir(Globals.Dir.UP);
            }

            // Ball's TOP hit
            else if (topLeftHit || topRightHit) {
                setyDir(Globals.Dir.DOWN);
            }

            if (topLeftHit || topRightHit || bottomLeftHit || bottomRightHit) {
                block.takeHit();
            }

        }
        blocks.removeDestroyedBlocks();

    }



}
