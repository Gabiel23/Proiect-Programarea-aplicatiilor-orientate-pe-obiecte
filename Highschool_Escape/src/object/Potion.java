package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

public class Potion extends Entity {
    GamePanel gp;
    public Potion(GamePanel gp) throws ImageSetupException {
        super(gp);
        value = 5;
        this.gp = gp;
        stackable = true;
        type = type_consumable;
        name = "potiune";
        jos1 = setup("/objects/potion_red", gp.tile_size*width, gp.tile_size * height);
        description = STR."[Red potion]\n iti da \{value} viata.";

    }

    public boolean use(Entity entity)
    {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = STR."Ai baut \{name}!\n Viata ti a crescut cu \{value}.";
        entity.life += value;
        return true;
    }
}
