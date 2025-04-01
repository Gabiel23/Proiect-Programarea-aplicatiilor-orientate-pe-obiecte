package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

public class Scaun extends Entity {
    GamePanel gp;
    public Scaun(GamePanel gp, int col, int row) throws ImageSetupException {
        super(gp);
        this.gp = gp;

        height = 2;
        type = type_obstacle;
        name = "scaun";
        image1 = setup("/objects/scaun", width*gp.tile_size, height*gp.tile_size);
        jos1 = image1;
        collision = true;

        solid_area.x = 4;
        solid_area.y = 32;
        solid_area.width = 40;
        solid_area.height = 64;
        solidAreaDeafaultX = solid_area.x;
        solidAreaDeafaultY = solid_area.y;

        worldX = col *gp.tile_size;
        worldY = row * gp.tile_size;
    }
}
