import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Blocks {

    private List<Block> blocks;
    private Map<Color, Integer> colorHitPointsMap;

    public Blocks() {
        colorHitPointsMap = new HashMap<>();
        colorHitPointsMap.put(Globals.BLOCK_COLOR_0, 10000);
        colorHitPointsMap.put(Globals.BLOCK_COLOR_1, 1);
        colorHitPointsMap.put(Globals.BLOCK_COLOR_2, 2);
        colorHitPointsMap.put(Globals.BLOCK_COLOR_3, 3);
        colorHitPointsMap.put(Globals.BLOCK_COLOR_4, 4);

        blocks = new ArrayList<>();

        for (int j=Globals.WINDOW_HEIGHT/4; j<Globals.WINDOW_HEIGHT/2; j+=Globals.BLOCK_HEIGHT) { // y
            for (int i=0; i<Globals.WINDOW_WIDTH; i+=Globals.BLOCK_WIDTH) { // x
                Color randColor = Globals.randomBlockColor();
                blocks.add(new Block(i, j, Globals.BLOCK_WIDTH, Globals.BLOCK_HEIGHT, randColor, colorHitPointsMap.get(randColor)));
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

