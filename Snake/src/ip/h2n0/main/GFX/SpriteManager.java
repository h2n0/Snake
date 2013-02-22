package ip.h2n0.main.GFX;

import ip.h2n0.main.Snake;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteManager {

    public static boolean initalized = false;

    public static Sprite Floor1;
    public static Sprite Floor2;
    public static Sprite Floor3;
    public static Sprite Floor4;

    public static Sprite Segment0R;
    public static Sprite Segment1R;
    public static Sprite Segment2R;
    public static Sprite Segment3R;

    public static Sprite Segment0Y;
    public static Sprite Segment1Y;
    public static Sprite Segment2Y;
    public static Sprite Segment3Y;

    public static Sprite Segment0C;
    public static Sprite Segment1C;
    public static Sprite Segment2C;
    public static Sprite Segment3C;

    public static Sprite Segment0B;
    public static Sprite Segment1B;
    public static Sprite Segment2B;
    public static Sprite Segment3B;
    public static Sprite Food;
    public static Sprite block;

    static {
        initialize();
    }

    public static void initialize() {
        try {
            SpriteLoader loader = new SpriteLoader();
            BufferedImage sheet = ImageIO.read(Snake.class.getResource("/Sheet.png"));
            Floor1 = loader.cutTile(0, 6, 20, 20, sheet);
            Floor2 = loader.cutTile(1, 6, 20, 20, sheet);
            Floor3 = loader.cutTile(2, 6, 20, 20, sheet);
            Floor4 = loader.cutTile(3, 6, 20, 20, sheet);


            Segment0R = loader.cutTile(0, 1, 20, 20, sheet);
            Segment1R = loader.cutTile(1, 1, 20, 20, sheet);
            Segment2R = loader.cutTile(2, 1, 20, 20, sheet);
            Segment3R = loader.cutTile(3, 1, 20, 20, sheet);

            Segment0Y = loader.cutTile(0, 2, 20, 20, sheet);
            Segment1Y = loader.cutTile(1, 2, 20, 20, sheet);
            Segment2Y = loader.cutTile(2, 2, 20, 20, sheet);
            Segment3Y = loader.cutTile(3, 2, 20, 20, sheet);

            Segment0C = loader.cutTile(0, 3, 20, 20, sheet);
            Segment1C = loader.cutTile(1, 3, 20, 20, sheet);
            Segment2C = loader.cutTile(2, 3, 20, 20, sheet);
            Segment3C = loader.cutTile(3, 3, 20, 20, sheet);

            Segment0B = loader.cutTile(0, 4, 20, 20, sheet);
            Segment1B = loader.cutTile(1, 4, 20, 20, sheet);
            Segment2B = loader.cutTile(2, 4, 20, 20, sheet);
            Segment3B = loader.cutTile(3, 4, 20, 20, sheet);
            Food = loader.cutTile(1, 0, 20, 20, sheet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        initalized = true;
    }
}