package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

public class Mana extends Entity {
    GamePanel gp;
    public Mana(GamePanel gp) throws ImageSetupException {
        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        name = "mana";
        value = 2;
        image1 = setup("/objects/manacrystal_full", gp.tile_size*width, gp.tile_size*height);
        jos1 = image1;
        image2 = setup("/objects/manacrystal_blank", gp.tile_size*width, gp.tile_size*height);
    }

    public boolean use(Entity entity)
    {
        gp.ui.addMessage(STR."mana +\{value}");
        entity.mana += value;
        return true;
    }
}
