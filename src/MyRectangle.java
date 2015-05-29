import java.awt.*;

/**
 Created by Brian on 5/28/2015.
 */
public class MyRectangle extends Rectangle {

    private int leftEdge;
    private int rightEdge;
    private int topEdge;
    private int bottomEdge;
    private Point topLeft;
    private Point topRight;
    private Point bottomLeft;
    private Point bottomRight;

    public MyRectangle(int xStartPos, int yStartPos, int width, int height) {
        super(xStartPos, yStartPos, width, height);
        updateEdges();
        updatePoints();
    }

    private void updateEdges() {
        leftEdge = x;
        rightEdge = x + width;
        topEdge = y;
        bottomEdge = y + height;
    }

    private void updatePoints() {
        topLeft = new Point(leftEdge, topEdge);
        topRight = new Point(rightEdge, topEdge);
        bottomLeft = new Point(leftEdge, bottomEdge);
        bottomRight = new Point(rightEdge, bottomEdge);
    }

}
