import java.awt.*;


public class Block extends RectDouble {

    private Color color;
    private int hitPoints;

    public Block(double xStartPos, double yStartPos, double width, double height, Color color, int hitPoints) {
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
