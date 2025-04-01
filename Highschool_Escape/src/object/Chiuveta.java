package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

public class Chiuveta extends Entity {
    GamePanel gp;
    public Chiuveta(GamePanel gp, int col, int row) throws ImageSetupException {
        super(gp);
        this.gp = gp;

        width = 1;
        height = 2;
        type = type_obstacle;
        name = "chiuveta";
        image1 = setup("/objects/chiuveta", width*gp.tile_size, height*gp.tile_size);
        jos1 = image1;
        collision = true;

        solid_area.x = 0;
        solid_area.y = 48;
        solid_area.height = height*gp.tile_size - solid_area.y;
        solid_area.width = width * gp.tile_size;


        worldX = col *gp.tile_size;
        worldY = row * gp.tile_size;
    }
}
