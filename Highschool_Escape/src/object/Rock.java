package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import main.ImageSetupException;

public class Rock extends Projectile {
    GamePanel gp;
    public Rock(GamePanel gp) throws ImageSetupException {
        super(gp);
        this.gp = gp;

        name = "rock";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage() throws ImageSetupException {
        jos1 = setup("/projectile/rock_down_1", width * gp.tile_size, height * gp.tile_size);
        jos2 = setup("/projectile/rock_down_1", width * gp.tile_size, height * gp.tile_size);

        sus1 = setup("/projectile/rock_down_1", width * gp.tile_size, height * gp.tile_size);
        sus2 = setup("/projectile/rock_down_1", width * gp.tile_size, height * gp.tile_size);

        mers1_dreapta = setup("/projectile/rock_down_1", width * gp.tile_size, height * gp.tile_size);
        mers2_dreapta = setup("/projectile/rock_down_1", width * gp.tile_size, height * gp.tile_size);

        mers1_stanga = setup("/projectile/rock_down_1", width * gp.tile_size, height * gp.tile_size);
        mers2_stanga = setup("/projectile/rock_down_1", width * gp.tile_size, height * gp.tile_size);
    }

    public boolean haveResource(Entity user)
    {
//        boolean haveResource;
//
//        if(user.mana >= useCost)
//        {
//            haveResource = true;
//        }
//        return haveResource;
        return user.ammo >= useCost;
    }
    public void subtractResource(Entity user)
    {
        user.ammo -= useCost;
    }
}
