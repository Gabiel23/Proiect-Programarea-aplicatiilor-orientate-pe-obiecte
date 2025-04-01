package entity;

import main.GamePanel;
import main.ImageSetupException;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class NPC extends Entity{
    public NPC(GamePanel gp, int i) throws ImageSetupException {
        super(gp);
        direction = "stanga";
        speed = 1;
        solid_area = new Rectangle(0,0,48,48);

        solid_area.x = 8;
        solid_area.y = 16;
        solidAreaDeafaultX = solid_area.x;
        solidAreaDeafaultY = solid_area.y;
        solid_area.width = 30;
        solid_area.height = 30;

        String s = "";

        if(i == 1)
        {
            s = "Salut\nTrebuie sa scapi din clasa\nPentru asta ai nevoie de chei";
        }
        if(i == 2)
        {
            s = "Nu poti trece pe aicea\nMai ai nevoie de ceva\nVino dupa mine";
        }

        getImage();
        setDialogues(s);
    }

    public void getImage() throws ImageSetupException {
        jos1 = setup("/npc/oldman_down_1", width * gp.tile_size, height * gp.tile_size);
        jos2 = setup("/npc/oldman_down_2", width * gp.tile_size, height * gp.tile_size);
        sus1 = setup("/npc/oldman_up_1", width * gp.tile_size, height * gp.tile_size);
        sus2 = setup("/npc/oldman_up_2", width * gp.tile_size, height * gp.tile_size);
        mers1_dreapta = setup("/npc/oldman_right_1", width * gp.tile_size, height * gp.tile_size);
        mers2_dreapta = setup("/npc/oldman_right_2", width * gp.tile_size, height * gp.tile_size);
        mers1_stanga = setup("/npc/oldman_left_1", width * gp.tile_size, height * gp.tile_size);
        mers2_stanga = setup("/npc/oldman_left_2", width * gp.tile_size, height * gp.tile_size);

    }

    public void setDialogues(String s)
    {
        dialogues[0] = s;
    }
    public void setAction()
    {
        if(onPath)
        {
            int goalCol = 13;
            int goalRow = 19;

            searchPath(goalCol,goalRow);
        }
        else {
            actionLockCounter++;
            if(actionLockCounter == 120)
            {
                Random random = new Random();
                int  i = random.nextInt(100)+1;

                if(i <= 25)
                {
                    direction = "sus";
                }
                if(i > 25 && i <=50)
                {
                    direction = "jos";
                }
                if(i > 50 && i <=75)
                {
                    direction = "stanga";
                }
                if(i > 75)
                {
                    direction = "dreapta";
                }

                actionLockCounter = 0;
            }
        }
    }
    public void speak()
    {
       super.speak();

       onPath = true;
    }
}
