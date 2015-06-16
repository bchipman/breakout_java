import java.awt.*;

public class Block extends Rect {

    private Color color;
    private boolean alive;

    public Block(int xStartPos, int yStartPos, int width, int height) {
        super(xStartPos, yStartPos, width, height);
        alive = true;
    }

    public Block(int xStartPos, int yStartPos, int width, int height, Color color) {
        super(xStartPos, yStartPos, width, height);
        this.color = color;
        alive = true;
    }

    public void death() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Color getColor() {
        return color;
    }

}
