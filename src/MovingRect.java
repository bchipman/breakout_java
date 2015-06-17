import java.awt.*;

/**
 Created by Brian on 6/2/2015.
 */

public class MovingRect extends Rect {

    private int xVel;
    private int yVel;
    private Globals.Dir xDir;
    private Globals.Dir yDir;

    public MovingRect(int xStartPos, int yStartPos, int width, int height) {
        super(xStartPos, yStartPos, width, height);
        xVel = 0;
        yVel = 0;
    }

    public MovingRect(int xStartPos, int yStartPos, int width, int height, int xStartVel, int yStartVel) {
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


    public int getxVel() {
        return xVel;
    }
    public int getyVel() {
        return yVel;
    }
    public void setxVel(int xVel) {
        this.xVel = xVel;
    }
    public void setyVel(int yVel) {
        this.yVel = yVel;
    }
    public void setVel(int xVel, int yVel) {
        setxVel(xVel);
        setyVel(yVel);
    }
    public void setVel(Point vel) {
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
        if (xDir == Globals.Dir.LEFT) {
            xVel = Math.abs(xVel);
        } else {
            xVel = Math.abs(xVel) * -1;
        }
    }
    public void setyDir(Globals.Dir yDir) {
        this.yDir = yDir;
        if (xDir == Globals.Dir.UP) {
            yVel = Math.abs(yVel);
        } else {
            yVel = Math.abs(yVel) * -1;
        }
    }


}
