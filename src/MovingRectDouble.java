import java.awt.geom.Point2D;

/**
 Created by Brian on 6/2/2015.
 */

public class MovingRectDouble extends RectDouble {

    private double xVel;
    private double yVel;
    private Globals.Dir xDir;
    private Globals.Dir yDir;

    public MovingRectDouble(double xStartPos, double yStartPos, double width, double height) {
        super(xStartPos, yStartPos, width, height);
        xVel = 0;
        yVel = 0;
    }

    public MovingRectDouble(double xStartPos, double yStartPos, double width, double height, double xStartVel, double yStartVel) {
        super(xStartPos, yStartPos, width, height);
        xVel = xStartVel;
        yVel = yStartVel;
    }

    public boolean movingLeft() {
        return xVel < 0;
    }
    public boolean movingRight() {
        return xVel > 0;
    }
    public boolean movingUp() {
        return yVel < 0;
    }
    public boolean movingDown() {
        return yVel > 0;
    }

    public boolean movingLeftUp() {
        return movingLeft() && movingUp();
    }
    public boolean movingLeftDown() {
        return movingLeft() && movingDown();
    }
    public boolean movingRightUp() {
        return movingRight() && movingUp();
    }
    public boolean movingRightDown() {
        return movingRight() && movingDown();
    }

    public double getxSpeed() {
        return Math.abs(xVel);
    }
    public double getySpeed() {
        return Math.abs(yVel);
    }

    public double getxVel() {
        return xVel;
    }
    public double getyVel() {
        return yVel;
    }
    public void setxVel(double xVel) {
        this.xVel = xVel;
        if (xVel > 0) {
            xDir = Globals.Dir.RIGHT;
        } else {
            xDir = Globals.Dir.LEFT;
        }
    }
    public void setyVel(double yVel) {
        this.yVel = yVel;
        if (yVel > 0) {
            yDir = Globals.Dir.DOWN;
        } else {
            yDir = Globals.Dir.UP;
        }
    }
    public void setVel(double xVel, double yVel) {
        setxVel(xVel);
        setyVel(yVel);
    }
    public void setVel(Point2D.Double vel) {
        setxVel(vel.x);
        setyVel(vel.y);
    }

    public Globals.Dir getxDir() {
        return xDir;
    }
    public Globals.Dir getyDir() {
        return yDir;
    }
    public void setxDir(Globals.Dir xDir) {
        this.xDir = xDir;
        if (xDir == Globals.Dir.RIGHT) {
            xVel = Math.abs(xVel);
        } else {
            xVel = Math.abs(xVel) * -1;
        }
    }
    public void setyDir(Globals.Dir yDir) {
        this.yDir = yDir;
        if (yDir == Globals.Dir.DOWN) {
            yVel = Math.abs(yVel);
        } else {
            yVel = Math.abs(yVel) * -1;
        }
    }


}
