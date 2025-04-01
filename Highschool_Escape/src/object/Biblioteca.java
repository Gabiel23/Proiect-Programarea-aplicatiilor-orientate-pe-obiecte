package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

public class Biblioteca extends Entity {
    GamePanel gp;
    Entity loot;
    boolean opened = false;
    public Biblioteca(GamePanel gp, int col, int row) throws ImageSetupException {
        super(gp);
        this.gp = gp;

        width = 2;
        height = 3;
        type = type_obstacle;
        name = "biclioteca";
        image1 = setup("/objects/biblioteca", width*gp.tile_size, height*gp.tile_size);
        jos1 = image1;
        collision = true;

        solid_area.x = 0;
        solid_area.y = 30;
        solid_area.height = height*gp.tile_size - solid_area.y;
        solid_area.width = width * gp.tile_size;

        worldX = col *gp.tile_size;
        worldY = row * gp.tile_size;
    }
}
