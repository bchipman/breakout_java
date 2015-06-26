import java.awt.*;
import java.awt.image.BufferedImage;


public class Main {

    public static void main(String[] args) {
        Frame frame = new Frame("Breakout!");
        Component component = new Component();
        frame.add(component);

        component.setFocusable(true);
        component.requestFocus();

        BufferedImage cursorImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0,0), "blank cursor");
        frame.getContentPane().setCursor(blankCursor);

        component.animate();

    }
}
