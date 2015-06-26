import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame {

    public Frame(String title) {
        super(title);
        setSize(Globals.SET_WINDOW_WIDTH, Globals.SET_WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.DARK_GRAY.darker());
        setVisible(true);
        setResizable(false);
        setConstants();
    }

    private void setConstants() {
        Dimension dim = getContentPane().getSize();
        Globals.WINDOW_WIDTH = dim.width;
        Globals.WINDOW_HEIGHT = dim.height;
        Globals.WINDOW_LEFT_EDGE = 0;
        Globals.WINDOW_RIGHT_EDGE = Globals.WINDOW_WIDTH;
        Globals.WINDOW_TOP_EDGE = 0;
        Globals.WINDOW_BOTTOM_EDGE = Globals.WINDOW_HEIGHT;

    }

}
