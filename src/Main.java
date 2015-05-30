import java.awt.*;
import java.awt.image.BufferedImage;

/**
 Created by Brian on 5/15/2015.
 */
public class Main {

    public static void main(String[] args) {
        Frame frame = new Frame("Breakout!");
        Component display = new Component();
        frame.add(display);

        BufferedImage cursorImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0,0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);

        display.animate();

    }
}
