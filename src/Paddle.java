import java.awt.*;

/**
 Created by Brian on 5/28/2015.
 */
public class Paddle extends Rectangle {

    private int leftEdge;
    private int rightEdge;
    private int topEdge;
    private int bottomEdge;

    public Paddle(int startX, int startY, int width, int height) {
        super(startX, startY, width, height);
        leftEdge = x;
        rightEdge = x + width;
        topEdge = y;
        bottomEdge = y + height;
    }

    public void refresh() {
        updateEdges();
    }

    private void updateEdges() {
        leftEdge = x;
        rightEdge = x + width;
        topEdge = y;
        bottomEdge = y + height;
    }

    public int getLeftEdge() {
        return leftEdge;
    }

    public int getRightEdge() {
        return rightEdge;
    }

    public int getTopEdge() {
        return topEdge;
    }

    public int getBottomEdge() {
        return bottomEdge;
    }
}
