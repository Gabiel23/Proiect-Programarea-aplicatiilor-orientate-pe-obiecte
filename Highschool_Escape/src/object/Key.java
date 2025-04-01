package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Key extends Entity
{
    GamePanel gp;
    public Key(GamePanel gp) throws ImageSetupException {
        super(gp);
        this.gp = gp;
        type = type_consumable;
        stackable = true;
        name = "key";
        description = STR."[\{name}]";
        jos1 = setup("/objects/key",width*gp.tile_size, height*gp.tile_size);
    }
    public boolean use(Entity entity)
    {
        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "usa");

        System.out.println(objIndex);

        if(objIndex != 999)
        {
            gp.ui.currentDialogue = "Next Level";
            gp.obj[gp.currentMap][objIndex].jos1 = gp.obj[gp.currentMap][objIndex].image2;
            gp.obj[gp.currentMap][objIndex].collision = false;
            return true;
        }
        else
        {
            gp.ui.currentDialogue = "What are you doing?";
            return false;
        }
    }
}
