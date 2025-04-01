package inamici;

import entity.Entity;
import main.GamePanel;
import main.ImageSetupException;
import object.Heart;
import object.Key;
import object.Minge;
import object.Rock;

import java.util.Random;

public class Orc extends Entity {
    GamePanel gp;
    public Orc(GamePanel gp) throws ImageSetupException {
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "zombi";
        speed = 1;
        maxLife = 10;
        life = maxLife;
        attack = 8;
        defense = 0;
        exp = 10;

        height = 1;
        width = 1;

        solid_area.x = 4;
        solid_area.y = 4;
        solid_area.width = 40;
        solid_area.height = 44;
        solidAreaDeafaultX = solid_area.x;
        solidAreaDeafaultY = solid_area.y;
        motion1_duration = 40;
        motion2_duration = 85;

        attackArea.width = 48;
        attackArea.height = 48;

        getImage();
        getAttackImage();
    }
    public void getImage() throws ImageSetupException {
        sus1 = setup("/monster/orc_down_1", width * gp.tile_size, height * gp.tile_size);
        sus2 = setup("/monster/orc_down_2", width * gp.tile_size, height * gp.tile_size);
        jos1 = setup("/monster/orc_down_1", width * gp.tile_size, height * gp.tile_size);
        jos2 = setup("/monster/orc_down_2", width * gp.tile_size, height * gp.tile_size);
        mers1_stanga = setup("/monster/orc_down_1", width * gp.tile_size, height * gp.tile_size);
        mers2_stanga = setup("/monster/orc_down_2", width * gp.tile_size, height * gp.tile_size);
        mers1_dreapta = setup("/monster/orc_down_1", width * gp.tile_size, height * gp.tile_size);
        mers2_dreapta = setup("/monster/orc_down_2", width * gp.tile_size, height * gp.tile_size);
    }
    public void getAttackImage() throws ImageSetupException {
        atacsus1 = setup("/monster/orc_attack_up_1", gp.tile_size , gp.tile_size * 2);
        atacsus2 = setup("/monster/orc_attack_up_2", gp.tile_size , gp.tile_size * 2);
        atacjos1 = setup("/monster/orc_attack_down_1", gp.tile_size , gp.tile_size * 2);
        atacjos2 = setup("/monster/orc_attack_down_2", gp.tile_size , gp.tile_size * 2);
        atacdreapta1 = setup("/monster/orc_attack_right_1", gp.tile_size * 2, gp.tile_size );
        atacdreapta2 = setup("/monster/orc_attack_right_2", gp.tile_size * 2, gp.tile_size );
        atacstanga1 = setup("/monster/orc_attack_left_1", gp.tile_size * 2, gp.tile_size );
        atacstanga2 = setup("/monster/orc_attack_left_2", gp.tile_size * 2, gp.tile_size );
    }

    public void setAction() {
        if(onPath)
        {
            checkStopChasingOrNot(gp.player, 10, 100);

            searchPath(getGoalCol(gp.player),getGoalRow(gp.player));
        }
        else
        {
            checkStartChasingOrNot(gp.player,5,100);

            getRandomDirection();
        }

        if(!attacking)
        {
            checkAttackOrNot(30,gp.tile_size * 4, gp.tile_size);
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
