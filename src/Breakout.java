import java.awt.*;
import java.awt.image.BufferedImage;

/**
 Created by Brian on 5/15/2015.
 */
public class Breakout {

    public static void main(String[] args) {
        BreakoutFrame frame = new BreakoutFrame("Breakout!");
        BreakoutDisplay display = new BreakoutDisplay();
        frame.add(display);

        BufferedImage cursorImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0,0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);

    }
}
