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

    public void update() {
        updateEdges();
        updatePoints();
    }

    public void setLocation(int newX, int newY) {
        super.setLocation(newX, newY);
        update();
    }

    public void translate(int dx, int dy) {
        super.translate(dx, dy);
        update();
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
        update();
    }
    public void setRightEdge(int newRightEdge) {
        x = newRightEdge;
        update();
    }
    public void setTopEdge(int newTopEdge) {
        y = newTopEdge;
        update();
    }
    public void setBottomEdge(int newBottomEdge) {
        y = newBottomEdge;
        update();
    }

    public void setTopLeft(Point newTopLeft) {
        x = newTopLeft.x;
        y = newTopLeft.y;
        update();
    }
    public void setTopRight(Point newTopRight) {
        x = newTopRight.x;
        y = newTopRight.y;
        update();
    }
    public void setBottomLeft(Point newBottomLeft) {
        x = newBottomLeft.x;
        y = newBottomLeft.y;
        update();
    }
    public void setBottomRight(Point newBottomRight) {
        x = newBottomRight.x;
        y = newBottomRight.y;
        update();
    }
}
