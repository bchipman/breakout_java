import java.awt.*;

public class Block extends Rect {

    private Color color;
    private int hitPoints;

    public Block(int xStartPos, int yStartPos, int width, int height, Color color, int hitPoints) {
        super(xStartPos, yStartPos, width, height);
        this.color = color;
        this.hitPoints = hitPoints;
    }

    public boolean destroyed() {
        return (hitPoints <= 0);
    }

    public Color getColor() {
        return color;
    }

    public void takeHit() {
        --hitPoints;
    }

}
