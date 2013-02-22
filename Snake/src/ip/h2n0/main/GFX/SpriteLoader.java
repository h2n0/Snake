package ip.h2n0.main.GFX;

import java.awt.image.BufferedImage;

public class SpriteLoader {

    public Sprite cutTile(int x, int y, int width, int height, BufferedImage sheet) {
        Sprite result = new Sprite();
        result.sprite = sheet.getSubimage(x * width, y * height, width, height);
        return result;
    }
}