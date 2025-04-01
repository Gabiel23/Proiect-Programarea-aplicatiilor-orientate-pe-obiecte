package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

public class Hidrant extends Entity {
    GamePanel gp;
    public Hidrant(GamePanel gp, int col, int row) throws ImageSetupException {
        super(gp);
        this.gp = gp;

        type = type_obstacle;
        name = "hidrant";
        image1 = setup("/objects/hidrant", width*gp.tile_size, height*gp.tile_size);
        jos1 = image1;
        collision = false;

        worldX = col *gp.tile_size;
        worldY = row * gp.tile_size;
    }
}
