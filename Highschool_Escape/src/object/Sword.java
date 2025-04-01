package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

public class Sword extends Entity {


    public Sword(GamePanel gp) throws ImageSetupException {
        super(gp);

        name = "sabie";
        type = type_sword;
        description = STR."[\{name}]\nSabie de lemn";
        jos1 = setup("/objects/sword_normal",width * gp.tile_size,height * gp.tile_size);
        attackArea.width = 72;
        attackArea.height = 72;
        attackValue = 1;
        knockPower = 2;
        motion1_duration = 5;
        motion2_duration = 25;
    }
}
