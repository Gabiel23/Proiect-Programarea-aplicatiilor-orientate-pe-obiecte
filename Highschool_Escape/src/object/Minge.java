package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Minge extends Entity {

    GamePanel gp;
    public Minge(GamePanel gp)throws ImageSetupException
    {
        super(gp);
        this.gp = gp;
        name = "minge";
        type = type_pickupOnly;
        jos1 = setup("/objects/minge",width*gp.tile_size, height*gp.tile_size);
        value = 1;
//        collision = true;
//        solid_area.x = 8;
//        solid_area.width = width*gp.tile_size - 2 * solid_area.x;
//        solid_area.y = 8;
//        solid_area.height = height*gp.tile_size - solid_area.y;
//        solidAreaDeafaultX = solid_area.x;
//        solidAreaDeafaultY = solid_area.y;
    }

    public boolean use(Entity entity)
    {
        gp.ui.addMessage(STR."Coin +\{value}");
        entity.coin += value;
        return true;
    }
}
