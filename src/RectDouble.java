import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 Created by Brian on 5/28/2015.
 */
public class RectDouble extends Rectangle2D.Double {

    //public double x;
    //public double y;
    private int xDrawPos;
    private int yDrawPos;
    private double leftEdge;
    private double rightEdge;
    private double topEdge;
    private double bottomEdge;
    private Point2D.Double topLeft;
    private Point2D.Double topRight;
    private Point2D.Double bottomLeft;
    private Point2D.Double bottomRight;


    public RectDouble(double xStartPos, double yStartPos, double width, double height) {
        super(xStartPos, yStartPos, width, height);
        update();
    }

    public RectDouble(RectDouble rectDouble) {
        super(rectDouble.x, rectDouble.y, rectDouble.width, rectDouble.height);
        update();
    }

    public RectDouble(Rectangle oldRectangle) {
        super(oldRectangle.x, oldRectangle.y, oldRectangle.width, oldRectangle.height);
        update();
    }

    public String toString() {
        return String.format("x=%d, y=%d, w=%d, h=%d  |  left=%d, right=%d, top=%d, bottom=%d  |  tl=%s, tr=%s, bl=%s, br=%s", x, y, width, height, getLeftEdge(), getRightEdge(), getTopEdge(), getBottomEdge(), String.valueOf(getTopLeft()), String.valueOf(getTopRight()), String.valueOf(getBottomLeft()), String.valueOf(getBottomRight()));
    }

    public void update() {
        updateEdges();
        updatePoints();
        updatePixelPosition();
    }

    //public void setLocation(int newX, int newY) {
    //    super.setLocation(newX, newY);
    //    update();
    //}

    //public void translate(int dx, int dy) {
    //    super.translate(dx, dy);
    //    update();
    //}

    private void updateEdges() {
        leftEdge = x;
        rightEdge = x + width;
        topEdge = y;
        bottomEdge = y + height;
    }

    private void updatePoints() {
        topLeft = new Point2D.Double(leftEdge, topEdge);
        topRight = new Point2D.Double(rightEdge, topEdge);
        bottomLeft = new Point2D.Double(leftEdge, bottomEdge);
        bottomRight = new Point2D.Double(rightEdge, bottomEdge);
    }

    private void updatePixelPosition() {
        xDrawPos = (int) Math.round(x);
        yDrawPos = (int) Math.round(y);
    }

    public double getLeftEdge() {
        return leftEdge;
    }
    public double getRightEdge() {
        return rightEdge;
    }
    public double getTopEdge() {
        return topEdge;
    }
    public double getBottomEdge() {
        return bottomEdge;
    }

    public Point2D.Double getTopLeft() {
        return topLeft;
    }
    public Point2D.Double getTopRight() {
        return topRight;
    }
    public Point2D.Double getBottomLeft() {
        return bottomLeft;
    }
    public Point2D.Double getBottomRight() {
        return bottomRight;
    }

    public void setLeftEdge(double newLeftEdge) {
        x = newLeftEdge;
        update();
    }
    public void setRightEdge(double newRightEdge) {
        x = newRightEdge - width;
        update();
    }
    public void setTopEdge(double newTopEdge) {
        y = newTopEdge;
        update();
    }
    public void setBottomEdge(double newBottomEdge) {
        y = newBottomEdge - height;
        update();
    }

    public void setTopLeft(Point2D.Double newTopLeft) {
        setLeftEdge(newTopLeft.x);
        setTopEdge(newTopLeft.y);
        update();
    }
    public void setTopRight(Point2D.Double newTopRight) {
        setRightEdge(newTopRight.x);
        setTopEdge(newTopRight.y);
        update();
    }
    public void setBottomLeft(Point2D.Double newBottomLeft) {
        setLeftEdge(newBottomLeft.x);
        setBottomEdge(newBottomLeft.y);
        update();
    }
    public void setBottomRight(Point2D.Double newBottomRight) {
        setRightEdge(newBottomRight.x);
        setBottomEdge(newBottomRight.y);
        update();
    }
}
