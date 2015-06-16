import java.awt.*;
import java.util.*;
import java.util.List;

/**
 Created by Brian on 6/11/2015.
 */


public class Blocks {

    private List<Block> blocks;
    public Map<Color, Integer> colorHitPointsMap;

    public Blocks() {
        colorHitPointsMap = new HashMap<>();
        colorHitPointsMap.put(Globals.BLOCK_COLOR_1, 1);
        colorHitPointsMap.put(Globals.BLOCK_COLOR_2, 2);
        colorHitPointsMap.put(Globals.BLOCK_COLOR_3, 3);
        colorHitPointsMap.put(Globals.BLOCK_COLOR_4, 4);

        blocks = new ArrayList<>();
        for (int j=0; j<250; j+=10) { // y
            for (int i=0; i<Globals.WINDOW_RIGHT_EDGE; i+=50) { // x
                Color randColor = Globals.randomBlockColor();
                blocks.add(new Block(i, j, 50, 10, randColor, colorHitPointsMap.get(randColor)));
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

    public void removeDestroyedBlocks() {
        Blocks blocksCopy = new Blocks(this);
        for (Block block : blocksCopy.getBlocks()) {
            if (block.destroyed()) {
                blocks.remove(block);
            }
        }
    }

}

