package tile_interactive;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

public class Masa extends InteractiveTile{
    GamePanel gp;
    public Masa(GamePanel gp, int col, int row, int i) throws ImageSetupException {
        super(gp,col,row);
        this.gp = gp;

        this.worldX = gp.tile_size*col;
        this.worldY = gp.tile_size*row;
        name = "masa";
        height = 2;
        width = 2;

        if(i == 0)
        {
            jos1 = setup("/objects/masa",width*gp.tile_size, height*gp.tile_size);

        }
        else
        {
            jos1 = setup("/objects/dulap",width*gp.tile_size, height*gp.tile_size);

        }
        destructible = true;
        life = 3;

//        solid_area.x = 0;
//        solid_area.width = width*gp.tile_size - 2 * solid_area.x;
//        solid_area.y = 0;
//        solid_area.height = height*gp.tile_size - solid_area.y;
//        solidAreaDeafaultX = solid_area.x;
//        solidAreaDeafaultY = solid_area.y;
    }
    public boolean isCorrectItem(Entity entity)
    {
        boolean isCorrectItem = false;
        if(entity.currentWeapon.type == type_axe)
        {
            isCorrectItem = true;
        }
        return isCorrectItem;
    }
}
