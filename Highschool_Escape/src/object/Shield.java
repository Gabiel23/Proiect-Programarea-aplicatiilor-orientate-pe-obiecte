package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

public class Shield extends Entity {
    public Shield(GamePanel gp) throws ImageSetupException {
        super(gp);
        name = "scut";
        type = type_shield;
        description = STR."[\{name}]\nScut de lemn";
        jos1 = setup("/objects/shield_wood", width * gp.tile_size, height * gp.tile_size);
        defenseValue = 1;
    }
}
