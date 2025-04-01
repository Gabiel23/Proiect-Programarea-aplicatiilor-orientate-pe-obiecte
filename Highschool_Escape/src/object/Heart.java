package object;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Heart extends Entity {
    GamePanel gp;
    public Heart(GamePanel gp) throws ImageSetupException {
        super(gp);
        this.gp = gp;
        type = type_pickupOnly;
        name = "heart";
        value = 2;
        image1 = setup("/heart/heart_full",width*gp.tile_size, height*gp.tile_size);
        jos1 =  image1;
        image2 = setup("/heart/heart_half",width*gp.tile_size, height*gp.tile_size);
        image3 = setup("/heart/heart_blank",width*gp.tile_size, height*gp.tile_size);
    }

    public boolean use(Entity entity)
    {
        gp.ui.addMessage("life +" + value);
        entity.life += value;
        return true;
    }
}
