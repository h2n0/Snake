package ip.h2n0.main;

import ip.h2n0.main.GFX.SpriteManager;

import java.awt.Graphics;

public class Segment {

    public int xPos;
    public int yPos;
    public int dir = Snake.dir;
    public int colour = Snake.colour;

    public Segment(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void render(Graphics g) {
        if (colour == 0) {
            if (dir == 0) {
                g.drawImage(SpriteManager.Segment0R.sprite, xPos, yPos, null);
            }
            if (dir == 1) {
                g.drawImage(SpriteManager.Segment1R.sprite, xPos, yPos, null);
            }
            if (dir == 2) {
                g.drawImage(SpriteManager.Segment2R.sprite, xPos, yPos, null);
            }
            if (dir == 3) {
                g.drawImage(SpriteManager.Segment3R.sprite, xPos, yPos, null);
            }
        }
        if (colour == 1) {
            if (dir == 0) {
                g.drawImage(SpriteManager.Segment0Y.sprite, xPos, yPos, null);
            }
            if (dir == 1) {
                g.drawImage(SpriteManager.Segment1Y.sprite, xPos, yPos, null);
            }
            if (dir == 2) {
                g.drawImage(SpriteManager.Segment2Y.sprite, xPos, yPos, null);
            }
            if (dir == 3) {
                g.drawImage(SpriteManager.Segment3Y.sprite, xPos, yPos, null);
            }
        }
        if (colour == 2) {
            if (dir == 0) {
                g.drawImage(SpriteManager.Segment0C.sprite, xPos, yPos, null);
            }
            if (dir == 1) {
                g.drawImage(SpriteManager.Segment1C.sprite, xPos, yPos, null);
            }
            if (dir == 2) {
                g.drawImage(SpriteManager.Segment2C.sprite, xPos, yPos, null);
            }
            if (dir == 3) {
                g.drawImage(SpriteManager.Segment3C.sprite, xPos, yPos, null);
            }
        }
        if (colour == 3) {
            if (dir == 0) {
                g.drawImage(SpriteManager.Segment0B.sprite, xPos, yPos, null);
            }
            if (dir == 1) {
                g.drawImage(SpriteManager.Segment1B.sprite, xPos, yPos, null);
            }
            if (dir == 2) {
                g.drawImage(SpriteManager.Segment2B.sprite, xPos, yPos, null);
            }
            if (dir == 3) {
                g.drawImage(SpriteManager.Segment3B.sprite, xPos, yPos, null);
            }
        }

    }

}