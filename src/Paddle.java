import java.awt.*;

/**
 Created by Brian on 5/28/2015.
 */
public class Paddle extends MyRectangle {

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

}
