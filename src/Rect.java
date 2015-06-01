import java.awt.*;

/**
 Created by Brian on 5/28/2015.
 */
public class Rect extends Rectangle {

    private int leftEdge;
    private int rightEdge;
    private int topEdge;
    private int bottomEdge;
    private MyPoint topLeft;
    private MyPoint topRight;
    private MyPoint bottomLeft;
    private MyPoint bottomRight;

    private class MyPoint extends Point {
        public MyPoint(int newX, int newY) {
            super(newX, newY);
        }
        public String toString() {
            return String.format("(%d,%d)", x, y);
        }
    }

    public Rect(int xStartPos, int yStartPos, int width, int height) {
        super(xStartPos, yStartPos, width, height);
        update();
    }

    public Rect(Rect oldRect) {
        super(oldRect.x, oldRect.y, oldRect.width, oldRect.height);
        update();
    }

    public Rect(Rectangle oldRectangle) {
        super(oldRectangle.x, oldRectangle.y, oldRectangle.width, oldRectangle.height);
        update();
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
        topLeft = new MyPoint(leftEdge, topEdge);
        topRight = new MyPoint(rightEdge, topEdge);
        bottomLeft = new MyPoint(leftEdge, bottomEdge);
        bottomRight = new MyPoint(rightEdge, bottomEdge);
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

    public MyPoint getTopLeft() {
        return topLeft;
    }
    public MyPoint getTopRight() {
        return topRight;
    }
    public MyPoint getBottomLeft() {
        return bottomLeft;
    }
    public MyPoint getBottomRight() {
        return bottomRight;
    }

    public void setLeftEdge(int newLeftEdge) {
        x = newLeftEdge;
        update();
    }
    public void setRightEdge(int newRightEdge) {
        x = newRightEdge - width;
        update();
    }
    public void setTopEdge(int newTopEdge) {
        y = newTopEdge;
        update();
    }
    public void setBottomEdge(int newBottomEdge) {
        y = newBottomEdge - height;
        update();
    }

    public void setTopLeft(MyPoint newTopLeft) {
        setLeftEdge(newTopLeft.x);
        setTopEdge(newTopLeft.y);
        update();
    }
    public void setTopRight(MyPoint newTopRight) {
        setRightEdge(newTopRight.x);
        setTopEdge(newTopRight.y);
        update();
    }
    public void setBottomLeft(MyPoint newBottomLeft) {
        setLeftEdge(newBottomLeft.x);
        setBottomEdge(newBottomLeft.y);
        update();
    }
    public void setBottomRight(MyPoint newBottomRight) {
        setRightEdge(newBottomRight.x);
        setBottomEdge(newBottomRight.y);
        update();
    }
}
