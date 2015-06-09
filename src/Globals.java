/**
 Created by Brian on 5/28/2015.
 */
public class Globals {
    public static final int SET_WINDOW_WIDTH = 700;
    public static final int SET_WINDOW_HEIGHT = 500;
    public static int WINDOW_WIDTH;
    public static int WINDOW_HEIGHT;
    public static int WINDOW_LEFT_EDGE;
    public static int WINDOW_RIGHT_EDGE;
    public static int WINDOW_TOP_EDGE;
    public static int WINDOW_BOTTOM_EDGE;

    public static final boolean PRINT_PADDLE_BALL_COLLISION_INFO = false;
    public static boolean COLLISION_ON = true;
    public static boolean DEBUG_TEXT_ON = true;
    public static boolean BALL_MOVEMENT_ON = true;
    public static boolean PADDLE_VERTICAL_MOVEMENT_ONLY = false;
    public static boolean PADDLE_HORIZONTAL_MOVEMENT_ONLY = false;
    public static boolean PRINT_PADDLE_INFO = false;

    public static int PAUSE_TIME = 15;

    public static final int PADDLE_X_POSITION = 100;
    public static final int PADDLE_Y_POSITION = 300;
    public static final int PADDLE_LENGTH = 100;
    public static final int PADDLE_HEIGHT = 10;

    public static final int BALL_SIZE = 10;
    public static final int BALL_X_POSITION = PADDLE_X_POSITION + PADDLE_LENGTH / 2 - BALL_SIZE / 2;
    public static final int BALL_Y_POSITION = PADDLE_Y_POSITION - BALL_SIZE;
    public static final int BALL_X_VELOCITY = 1;
    public static final int BALL_Y_VELOCITY = -2;

}
