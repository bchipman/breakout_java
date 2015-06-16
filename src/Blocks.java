import java.util.ArrayList;
import java.util.List;

/**
 Created by Brian on 6/11/2015.
 */


public class Blocks {

    private List<Block> blocks;

    public Blocks() {
        blocks = new ArrayList<>();

        for (int j=0; j<50; j+=10) { // y
            for (int i=0; i<Globals.WINDOW_RIGHT_EDGE; i+=50) { // x
                blocks.add(new Block(i, j, 50, 10));
            }
        }
    }

    public Blocks(Blocks blocks) {
        this.blocks = new ArrayList<>();
        for (Block block : blocks.getBlocks()) {
            this.blocks.add(block);
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void removeBlock(Block block) {
        blocks.remove(block);
    }

}

