package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

public class Axe extends Entity {
    public Axe(GamePanel gp) throws ImageSetupException {
        super(gp);
        name = "axe";
        type = type_axe;
        description = STR."[\{name}]\npoate sparge anumiti pereti";
        attackValue = 2;
        attackArea.width = 60;
        attackArea.height = 60;
        jos1 = setup("/objects/axe",width * gp.tile_size,height * gp.tile_size);
        knockPower = 10;
        motion1_duration = 20;
        motion2_duration = 40;
    }
}
