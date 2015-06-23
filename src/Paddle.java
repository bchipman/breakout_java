import java.awt.geom.Rectangle2D;

/**
 Created by Brian on 5/28/2015.
 */
public class Paddle extends MovingRectDouble {

    public Paddle(double startX, double startY, double width, double height) {
        super(startX, startY, width, height);
    }

    public Paddle(Paddle oldPaddle, double startX, double startY) {
        super(startX, startY, oldPaddle.width, oldPaddle.height);
    }

    public Paddle(Rectangle2D.Double rectangle2D) {
        super(rectangle2D.x, rectangle2D.y, rectangle2D.width, rectangle2D.height);
    }

    public void setLocation(double newX, double newY) {
        double oldX = x;
        double oldY = y;
        //super.setLocation(newX, newY);
        x = newX;
        y = newY;
        update();
        setVel(x - oldX, y - oldY);
    }

    public Paddle union(Paddle prevPaddle) {
        Paddle newPaddle = new Paddle((Rectangle2D.Double) createUnion(prevPaddle));
        //Paddle newPaddle = new Paddle(super.union(prevPaddle));
        newPaddle.setVel(x - prevPaddle.x, y - prevPaddle.y);
        newPaddle.update();
        return newPaddle;
    }

}
