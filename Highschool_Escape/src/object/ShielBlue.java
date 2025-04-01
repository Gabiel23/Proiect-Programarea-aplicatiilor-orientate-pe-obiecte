package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

public class ShielBlue extends Entity {
    public ShielBlue(GamePanel gp) throws ImageSetupException {
        super(gp);

        name = "scut iron";
        type = type_shield;
        description = STR."[\{name}]\nScut de iron";
        jos1 = setup("/objects/shield_blue", width * gp.tile_size, height * gp.tile_size);
        defenseValue = 2;
    }
}
