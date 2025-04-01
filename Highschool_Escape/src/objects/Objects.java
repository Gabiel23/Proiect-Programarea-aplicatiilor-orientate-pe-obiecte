package objects;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Objects {

    protected BufferedImage image;
    private String name;
    public boolean collision = false;
    public int worldX;
    public int worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 30, 30);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public Objects(BufferedImage image) {
        this.image = image;
    }


    public abstract void draw(Graphics2D g2, GamePanel gw);

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return worldX;
    }

    public int getY() {
        return worldY;
    }

}




