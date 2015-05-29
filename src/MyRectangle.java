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

    public Point getTopLeft() {
        return topLeft;
    }
    public Point getTopRight() {
        return topRight;
    }
    public Point getBottomLeft() {
        return bottomLeft;
    }
    public Point getBottomRight() {
        return bottomRight;
    }

    public void setLeftEdge(int newLeftEdge) {
        x = newLeftEdge;
        updateEdges();
        updatePoints();
    }
    public void setRightEdge(int newRightEdge) {
        x = newRightEdge;
        updateEdges();
        updatePoints();
    }
    public void setTopEdge(int newTopEdge) {
        y = newTopEdge;
        updateEdges();
        updatePoints();
    }
    public void setBottomEdge(int newBottomEdge) {
        y = newBottomEdge;
        updateEdges();
        updatePoints();
    }

    public void setTopLeft(Point newTopLeft) {
        x = newTopLeft.x;
        y = newTopLeft.y;
        updateEdges();
        updatePoints();
    }
    public void setTopRight(Point newTopRight) {
        x = newTopRight.x;
        y = newTopRight.y;
        updateEdges();
        updatePoints();
    }
    public void setBottomLeft(Point newBottomLeft) {
        x = newBottomLeft.x;
        y = newBottomLeft.y;
        updateEdges();
        updatePoints();
    }
    public void setBottomRight(Point newBottomRight) {
        x = newBottomRight.x;
        y = newBottomRight.y;
        updateEdges();
        updatePoints();
    }
}
