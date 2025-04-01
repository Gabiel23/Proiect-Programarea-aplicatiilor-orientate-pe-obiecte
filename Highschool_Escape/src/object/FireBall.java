package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import main.ImageSetupException;

public class FireBall extends Projectile {
    GamePanel gp;
    public FireBall(GamePanel gp) throws ImageSetupException {
        super(gp);
        this.gp = gp;

        name = "fireball";
        speed = 10;
        maxLife = 80;
        life = maxLife;
        knockPower = 0;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage() throws ImageSetupException {
        jos1 = setup("/projectile/fireball_down_1", width * gp.tile_size, height * gp.tile_size);
        jos2 = setup("/projectile/fireball_down_2", width * gp.tile_size, height * gp.tile_size);

        sus1 = setup("/projectile/fireball_up_1", width * gp.tile_size, height * gp.tile_size);
        sus2 = setup("/projectile/fireball_up_2", width * gp.tile_size, height * gp.tile_size);

        mers1_dreapta = setup("/projectile/fireball_right_1", width * gp.tile_size, height * gp.tile_size);
        mers2_dreapta = setup("/projectile/fireball_right_2", width * gp.tile_size, height * gp.tile_size);

        mers1_stanga = setup("/projectile/fireball_left_1", width * gp.tile_size, height * gp.tile_size);
        mers2_stanga = setup("/projectile/fireball_left_2", width * gp.tile_size, height * gp.tile_size);
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
        return user.mana >= useCost;
    }
    public void subtractResource(Entity user)
    {
        user.mana -= useCost;
    }
}
