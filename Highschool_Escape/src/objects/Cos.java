package objects;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cos extends Objects{
    public Cos(BufferedImage image) {
        super(image);
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gw) {

        int screenX = worldX - gw.player.worldX + gw.player.screenX;
        int screenY = worldY - gw.player.worldY + gw.player.screenY;

        if (worldX + gw.tile_size > gw.player.worldX - gw.player.screenX &&
                worldX - gw.tile_size < gw.player.worldX + gw.player.screenX &&
                worldY + gw.tile_size > gw.player.worldY - gw.player.screenY &&
                worldY - gw.tile_size < gw.player.worldY + gw.player.screenY) {

            g2.drawImage(image, screenX, screenY, 25, 25, null);
        }
    }
}
