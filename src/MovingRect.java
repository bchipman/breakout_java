import java.awt.*;

/**
 Created by Brian on 6/2/2015.
 */

public class MovingRect extends Rect {

    private int xVel;
    private int yVel;

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

}
