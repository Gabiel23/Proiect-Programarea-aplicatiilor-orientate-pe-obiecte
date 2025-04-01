package inamici;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;
import object.Heart;
import object.Key;
import object.Minge;
import object.Rock;

import java.util.Random;

public class Doamna extends Entity {
    GamePanel gp;
    public Doamna(GamePanel gp) throws ImageSetupException {
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "doamna";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 5;
        projectile = new Rock(gp);

        height = 1;
        width = 1;

        solid_area.x = 3;
        solid_area.y = 18;
        solid_area.width = 42;
        solid_area.height = 30;
        solidAreaDeafaultX = solid_area.x;
        solidAreaDeafaultY = solid_area.y;

        getImage();
    }
    public void getImage() throws ImageSetupException {
        sus1 = setup("/monster/greenslime_down_1", width * gp.tile_size, height * gp.tile_size);
        sus2 = setup("/monster/greenslime_down_2", width * gp.tile_size, height * gp.tile_size);
        jos1 = setup("/monster/greenslime_down_1", width * gp.tile_size, height * gp.tile_size);
        jos2 = setup("/monster/greenslime_down_2", width * gp.tile_size, height * gp.tile_size);
        mers1_stanga = setup("/monster/greenslime_down_1", width * gp.tile_size, height * gp.tile_size);
        mers2_stanga = setup("/monster/greenslime_down_2", width * gp.tile_size, height * gp.tile_size);
        mers1_dreapta = setup("/monster/greenslime_down_1", width * gp.tile_size, height * gp.tile_size);
        mers2_dreapta = setup("/monster/greenslime_down_2", width * gp.tile_size, height * gp.tile_size);

    }
    public void setAction() {
        if(onPath)
        {
            checkStopChasingOrNot(gp.player, 10, 100);

            searchPath(getGoalCol(gp.player),getGoalRow(gp.player));

            checkShootOrNot(200, 30);
        }
        else
        {
            checkStartChasingOrNot(gp.player,5,100);

            getRandomDirection();
        }
    }
    public void damageReaction() {
        actionLockCounter = 0;
//        direction = gp.player.direction;
        onPath = true;
    }
    public void checkDrop() throws ImageSetupException {
        int i = new Random().nextInt(100)+1;

        if(i < 50)
        {
            dropItem(new Minge(gp));
        }

        if(i >= 50 && i < 75)
        {
            dropItem(new Key(gp));
        }
        if(i >= 75 && i < 100){
            dropItem(new Heart(gp));
        }
    }
}
