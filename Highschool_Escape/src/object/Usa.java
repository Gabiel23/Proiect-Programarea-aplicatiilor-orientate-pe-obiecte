package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

import javax.imageio.ImageIO;
import javax.swing.plaf.ComponentUI;
import java.io.IOException;
import java.util.Objects;

public class Usa extends Entity {
    GamePanel gp;
    public Usa(GamePanel gp, int i) throws ImageSetupException {
        super(gp);
        this.gp = gp;
        type = type_obstacle;
        name = "usa";
        height = 2;
        width = 2;

        if(i == 1)
        {
            image1 = setup("/objects/usa_inchisa",width*gp.tile_size, height*gp.tile_size);
            image2 = setup("/objects/usa_deschisa",width*gp.tile_size, height*gp.tile_size);
        }
        else
        {
            image1 = setup("/objects/usa_alba",width*gp.tile_size, height*gp.tile_size);
            image2 = setup("/objects/usa_alba_deschisa",width*gp.tile_size, height*gp.tile_size);
        }
        jos1 = image1;
        collision = true;
        solid_area.x = 0;
        solid_area.width = width*gp.tile_size - 2 * solid_area.x;
        solid_area.y = 32;
        solid_area.height = height*gp.tile_size - solid_area.y;
        solidAreaDeafaultX = solid_area.x;
        solidAreaDeafaultY = solid_area.y;
    }
    public void interact()
    {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You need a key";
    }
}
