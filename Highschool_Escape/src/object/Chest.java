package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

public class Chest extends Entity {
    GamePanel gp;
    Entity loot;
    boolean opened = false;
    public Chest(GamePanel gp, Entity loot) throws ImageSetupException {
        super(gp);
        this.gp = gp;
        this.loot = loot;

        type = type_obstacle;
        name = "chest";
        image1 = setup("/objects/chest", width*gp.tile_size, height*gp.tile_size);
        image2 = setup("/objects/chest_opened", width*gp.tile_size, height*gp.tile_size);
        jos1 = image1;
        collision = true;

        solid_area.x = 4;
        solid_area.y = 16;
        solid_area.width = 40;
        solid_area.height = 32;
        solidAreaDeafaultX = solid_area.x;
        solidAreaDeafaultY = solid_area.y;
    }
    public void interact() {
        gp.gameState = gp.dialogueState;

        if(!opened){
            StringBuilder sb = new StringBuilder();
            sb.append("You opened the chest and find an " + loot.name+"!");

            if(!gp.player.canObtainItem(loot)){
                sb.append("\n...But you cannot carry any more!");
            }
            else{
                sb.append("\n...You obtain the loot!");
                jos1 = image2;
                opened = true;
            }
            gp.ui.currentDialogue = sb.toString();
        }
        else {
            gp.ui.currentDialogue = "It's empty!";
        }
    }
}
