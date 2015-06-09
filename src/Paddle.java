import java.awt.*;

/**
 Created by Brian on 5/28/2015.
 */
public class Paddle extends MovingRect {

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

    public Paddle(Paddle oldPaddle, int startX, int startY) {
        super(startX, startY, oldPaddle.width, oldPaddle.height);
    }

    public Paddle(Rectangle oldRect) {
        super(oldRect.x, oldRect.y, oldRect.width, oldRect.height);
    }

    public void setLocation(int newX, int newY) {
        int oldX = x;
        int oldY = y;
        super.setLocation(newX, newY);
        setVel(x - oldX, y - oldY);
        checkForWallCollisions();
    }

    private void checkForWallCollisions() {
        if (getRightEdge() > Globals.WINDOW_RIGHT_EDGE) {
            setRightEdge(Globals.WINDOW_RIGHT_EDGE);
        }
        if (getBottomEdge() > Globals.WINDOW_BOTTOM_EDGE) {
            setBottomEdge(Globals.WINDOW_BOTTOM_EDGE);
        }
    }

    public Paddle union(Paddle prevPaddle) {
        Paddle newPaddle = new Paddle(super.union(prevPaddle));
        newPaddle.setVel(x - prevPaddle.x, y - prevPaddle.y);
        newPaddle.update();
        return newPaddle;
    }

}
