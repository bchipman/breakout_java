public class Block extends Rect {

    private boolean alive;

    public Block(int xStartPos, int yStartPos, int width, int height) {
        super(xStartPos, yStartPos, width, height);
        alive = true;
    }

    public void death() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

}
