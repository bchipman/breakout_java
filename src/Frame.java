import javax.swing.*;
import java.awt.*;

/**
 Created by Brian on 5/27/2015.
 */
public class Frame extends JFrame {

    public Frame(String title) {
        super(title);
        setSize(Constants.SET_WINDOW_WIDTH, Constants.SET_WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.DARK_GRAY);
        setVisible(true);
        setResizable(false);
        setConstants();
    }

    private void setConstants() {
        Dimension dim = getContentPane().getSize();
        Constants.WINDOW_WIDTH = dim.width;
        Constants.WINDOW_HEIGHT = dim.height;
        Constants.WINDOW_LEFT_EDGE = 0;
        Constants.WINDOW_RIGHT_EDGE = Constants.WINDOW_WIDTH;
        Constants.WINDOW_TOP_EDGE = 0;
        Constants.WINDOW_BOTTOM_EDGE = Constants.WINDOW_HEIGHT;

    }

}
