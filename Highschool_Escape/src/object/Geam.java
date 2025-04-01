package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

public class Geam extends Entity {
    GamePanel gp;
    public Geam(GamePanel gp, int col, int row) throws ImageSetupException {
        super(gp);
        this.gp = gp;

        width = 2;
        height = 2;
        type = type_obstacle;
        name = "geam";
        image1 = setup("/objects/geam", width*gp.tile_size, height*gp.tile_size);
        jos1 = image1;
        collision = true;

        worldX = col *gp.tile_size;
        worldY = row * gp.tile_size;
    }
}
