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

public class   Entity
{
    GamePanel gp; // Referință către panoul de joc în care se află entitatea
    public BufferedImage
            jos1, jos2, sus1, sus2,
            mers1_dreapta, mers2_dreapta, mers3_dreapta, mers4_dreapta,
            mers1_stanga, mers2_stanga, mers3_stanga, mers4_stanga,
            stat1_dreapta, stat2_dreapta, stat1_stanga, stat2_stanga;
    public BufferedImage atacsus1, atacsus2,atacjos1, atacjos2,atacdreapta1, atacdreapta2,atacstanga1, atacstanga2;
    public BufferedImage image1, image2, image3;
    public Rectangle solid_area = new Rectangle(0,0,96,96); // Zona solidă a entității pentru coliziuni
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDeafaultX, solidAreaDeafaultY; // Poziția implicită a zonei solide
    public boolean collision = false;
    String[] dialogues = new String[20];
    public Entity attacker;
    //STATE
    public int worldX, worldY; // Poziția lumii (coordonatele de pe harta globală)
    public String direction = "jos"; // Direcția în care se mișcă entitatea
    public int spriteNum = 1; // Numărul de cadre în animație
    int dialogueIndex = 0;
    public boolean collisionOn = false; // Indicator pentru coliziuni
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;;
    boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;
    //COUNTER
    public int spriteCounter = 0; // Contor pentru animație
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    int knockBackCounter = 0;
    //ATRIBUTES
    public int value;
    public int defaultSpeed;
    public int speed; // Viteza entității
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public String name;
    public int width = 1;
    public int height = 1;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;
    //ITEM ATTRIBUTES
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int knockPower;
    public boolean stackable = true;
    public int amount = 0;
    //TYPE
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacle = 8;
    public final int type_switch = 8;
    public Entity(GamePanel gp)
    {
        this.gp = gp; // Inițializarea panoului de joc
    }
    public int getLeftX()
    {
        return worldX + solid_area.x;
    }
    public int getRightX(){
        return getLeftX() + solid_area.width;
    }
    public int getTopY()
    {
        return worldY + solid_area.y;
    }
    public int getBottomY(){
        return getTopY() + solid_area.height;
    }
    public int getCol(){
        return (worldX + solid_area.x)/gp.tile_size;
    }
    public int getRow(){
        return (worldY + solid_area.y)/gp.tile_size;
    }
    public int getXdistance(Entity target) {
        return Math.abs(worldX - target.worldX);
    }
    public int getYdistance(Entity target) {
        return Math.abs(worldY - target.worldY);
    }
    public int getTileDistance(Entity target){
        return (getXdistance(target)+ getYdistance(target))/gp.tile_size;
    }
    public int getGoalCol(Entity target)
    {
        return (target.worldX + target.solid_area.x)/gp.tile_size;
    }
    public int getGoalRow(Entity target)
    {
        return (target.worldY + target.solid_area.y)/gp.tile_size;
    }
    public void setAction() {}
    public void damageReaction(){}
    public void speak() {
        if(dialogues[dialogueIndex] == null)
        {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction)
        {
            case "sus":
                direction = "jos";
                break;
            case "jos":
                direction = "sus";
                break;
            case "stanga":
                direction = "dreapta";
                break;
            case "dreapta":
                direction = "stanga";
                break;
        }
    }
    public void interact(){}
    public boolean use(Entity entity){
        return  false;
    }
    public void checkDrop() throws ImageSetupException {}
    public void dropItem(Entity droppedItem){
        for(int i = 0; i < gp.obj[1].length; i++)
        {
            if(gp.obj[gp.currentMap][i] == null)
            {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
//        gp.cChecker.checkDesign(this);
        gp.cChecker.checkEntity(this,gp.npc);
        gp.cChecker.checkEntity(this,gp.monster);
        gp.cChecker.checkEntity(this,gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == type_monster && contactPlayer)
        {
            damagePlayer(attack);
        }
    }
    public void update() {

        if(knockBack)
        {
            checkCollision();
            if(collisionOn)
            {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if (!collisionOn)
            {
                switch (knockBackDirection)
                {
                    case "sus":
                        worldY -= speed;
                        break;
                    case "jos":
                        worldY += speed;
                        break;
                    case "stanga":
                        worldX -= speed;
                        break;
                    case "dreapta":
                        worldX += speed;
                        break;
                }
            }
            knockBackCounter++;
            if(knockBackCounter == 10)
            {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }
        else if(attacking)
        {
            attacking();
        }
        else
        {
            setAction();
            checkCollision();

            if(!collisionOn)
            {
                switch (direction)
                {
                    case "sus":
                        worldY -= speed;
                        break;
                    case "jos":
                        worldY += speed;
                        break;
                    case "stanga":
                        worldX -= speed;
                        break;
                    case "dreapta":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 2)
                    spriteNum = 1;
                else if (spriteNum == 1) {
                    spriteNum = 2;
                }
                spriteCounter = 0;
            }
        }

        if(invincible)
        {
            invincibleCounter++;
            if(invincibleCounter > 40)
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30)
        {
            shotAvailableCounter++;
        }
    }
    public void checkAttackOrNot(int rate, int straight, int horizontal) {
        boolean targetInRange = false;

        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);

        switch (direction)
        {
            case "sus":
                if(gp.player.worldY < worldY && yDis < straight && xDis < horizontal)
                {
                    targetInRange = true;
                }
                break;
            case "jos":
                if(gp.player.worldY > worldY && yDis < straight && xDis < horizontal)
                {
                    targetInRange = true;
                }
                break;
            case "stanga":
                if(gp.player.worldX < worldX && xDis < straight && yDis < horizontal)
                {
                    targetInRange = true;
                }
                break;
            case "dreapta":
                if(gp.player.worldX > worldX && xDis < straight && yDis < horizontal)
                {
                    targetInRange = true;
                }
                break;
        }
        if(targetInRange)
        {
            int i = new Random().nextInt(rate);
            if(i == 0)
            {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }
    public void checkShootOrNot(int rate, int shotInterval) {
        int i = new Random().nextInt(rate);

        if(i == 0 && !projectile.alive && shotAvailableCounter == shotInterval)
        {
            projectile.set(worldX,worldY, direction, true, this);
            for(int ii =  0; ii < gp.projectile[1].length; ii++)
            {
                if(gp.projectile[gp.currentMap][ii] == null)
                {
                    gp.projectile[gp.currentMap][ii] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate) {
        if(getTileDistance(target) < distance)
        {
            int i = new Random().nextInt(rate);

            if( i == 0)
            {
                onPath = true;
            }
        }

    }
    public void checkStopChasingOrNot(Entity target, int distance, int rate) {
        if(getTileDistance(target) > distance)
        {
            int i = new Random().nextInt(rate);

            if( i == 0)
            {
                onPath = false;
            }
        }

    }
    public void getRandomDirection() {
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
    public void attacking() {
        spriteCounter++;

        if(spriteNum <=motion1_duration)
        {
            spriteNum = 1;
        }
        if(spriteCounter > motion1_duration && spriteCounter <= motion2_duration) {
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solid_area.width;
            int solidAreaHeight = solid_area.height;

            switch (direction)
            {
                case "sus": worldY -= attackArea.height;break;
                case "jos": worldY += attackArea.height;break;
                case "stanga": worldX -= attackArea.width;break;
                case "dreapta":worldY += attackArea.width;break;
            }
            solid_area.width = attackArea.width;
            solid_area.height = attackArea.height;

            if(type == type_monster) {
                if (gp.cChecker.checkPlayer(this))
                {
                    damagePlayer(attackValue);
                }
            }
            else
            {
                int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
                gp.player.damageMonster(monsterIndex,this, attack, currentWeapon.knockPower);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }

            worldX = currentWorldX;
            worldY = currentWorldY;
            solid_area.height = solidAreaHeight;
            solid_area.width = solidAreaWidth;
        }
        if(spriteCounter > 85)
        {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void damagePlayer(int attack) {
        if(!gp.player.invincible)
        {
//                gp.playMusic(4);
            int damage = attack - gp.player.defense;
            if(damage < 0)
            {
                damage = 0;
            }
            gp.player.life-=damage;
            gp.player.invincible = true;
        }
    }
    public void setknockBack(Entity target, Entity attacker, int knockPower){
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed +=knockPower;
        target.knockBack = true;
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + 2*gp.tile_size> gp.player.worldX - gp.player.screenX &&
                worldX -2*gp.tile_size< gp.player.worldX + gp.player.screenX &&
                worldY + 2*gp.tile_size> gp.player.worldY - gp.player.screenY &&
                worldY -2*gp.tile_size< gp.player.worldY + gp.player.screenY)
        {

            int tempScreenX = screenX;
            int tempScreenY = screenY;
            switch (direction) {
                case "sus":
                    if (!attacking) {
                        if (spriteNum % 2 == 1) image = sus1;
                        if (spriteNum % 2 == 0) image = sus2;
                    }
                    if (attacking) {
                        tempScreenY = screenY - (gp.tile_size);
                        if (spriteNum % 2 == 1) image = atacsus1;
                        if (spriteNum % 2 == 0) image = atacsus2;
                    }
                    break;
                case "jos":
                    if (!attacking) {
                        if (spriteNum % 2 == 1) image = jos1;
                        if (spriteNum % 2 == 0) image = jos2;
                    }
                    if (attacking) {
                        if (spriteNum % 2 == 1) image = atacjos1;
                        if (spriteNum % 2 == 0) image = atacjos2;
                    }
                    break;
                case "dreapta":
                    if (!attacking) {
                        if (spriteNum % 2 == 0) image = mers1_dreapta;
                        if (spriteNum % 2 == 1) image = mers2_dreapta;
                    }
                    if (attacking) {
                        if (spriteNum % 2 == 0) image = atacdreapta1;
                        if (spriteNum % 2 == 1) image = atacdreapta2;
                    }
                    break;
                case "stanga":
                    if (!attacking) {
                        if (spriteNum % 2 == 1) image = mers1_stanga;
                        if (spriteNum % 2 == 0) image = mers2_stanga;
                    }
                    if (attacking) {
                        tempScreenX = screenX - (gp.tile_size);
                        if (spriteNum % 2 == 1) image = atacstanga1;
                        if (spriteNum % 2 == 0) image = atacstanga2;
                    }
                    break;
            }
            if(type == 2 && hpBarOn)
            {
                double oneScale = (double) gp.tile_size/maxLife;
                double hpBarValue = oneScale * life;
                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX - 1,screenY - 16,gp.tile_size + 2, 12);

                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX,screenY - 15,(int)hpBarValue, 10);

                hpBarCounter++;

                if(hpBarCounter > 600)
                {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if(invincible)
            {
                hpBarOn = true;
                hpBarCounter = 0;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }

            if(dying)
            {
                dyingAnimation(g2);
            }

            g2.drawImage(image, tempScreenX,tempScreenY,null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;

        int i = 5;

        if(dyingCounter <= i) {changeAlpha(g2, 0f);}
        if(dyingCounter > i * 1 && dyingCounter <= i * 2) {changeAlpha(g2, 1f);}
        if(dyingCounter > i * 2 && dyingCounter <= i * 3) {changeAlpha(g2, 0f);}
        if(dyingCounter > i * 3 && dyingCounter <= i * 4) {changeAlpha(g2, 1f);}
        if(dyingCounter > i * 4 && dyingCounter <= i * 5) {changeAlpha(g2, 0f);}
        if(dyingCounter > i * 5 && dyingCounter <= i * 6) {changeAlpha(g2, 1f);}
        if(dyingCounter > i * 6 && dyingCounter <= i * 7) {changeAlpha(g2, 0f);}
        if(dyingCounter > i * 7 && dyingCounter <= i * 8) {changeAlpha(g2, 1f);}
        if(dyingCounter > i * 8){
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height) throws ImageSetupException {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(STR."\{imagePath}.png")));
            image = uTool.scaleImage(image,width, height);
        } catch (IOException e) {
            throw new ImageSetupException("Failed to set up the image at path: " + imagePath, e);
        } catch (NullPointerException e) {
            throw new ImageSetupException("Resource not found: " + imagePath, e);
        }

        return image;
    }
    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solid_area.x)/gp.tile_size;
        int startRow = (worldY + solid_area.y)/gp.tile_size;

        gp.pFinder.setNodes(startCol,startRow,goalCol,goalRow);

        if(gp.pFinder.search())
        {
            int nextX = gp.pFinder.pathList.get(0).col * gp.tile_size;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tile_size;

            int enLeftX = worldX + solid_area.x;
            int enRightX = worldX + solid_area.x + solid_area.width;

            int enTopY = worldY + solid_area.y;
            int enBottomY = worldY + solid_area.y + solid_area.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX +gp.tile_size)
            {
                direction = "sus";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX +gp.tile_size)
            {
                direction = "jos";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tile_size)
            {
                if(enLeftX > nextX)
                {
                    direction = "stanga";
                }
                if(enLeftX < nextX)
                {
                    direction = "dreapta";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX)
            {
                direction = "sus";
                checkCollision();
                if(collision)
                {
                    direction = "stanga";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX)
            {
                direction = "sus";
                checkCollision();
                if(collision)
                {
                    direction = "dreapta";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX)
            {
                direction = "jos";
                checkCollision();
                if(collision)
                {
                    direction = "stanga";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX)
            {
                direction = "jos";
                checkCollision();
                if(collision)
                {
                    direction = "dreapta";
                }
            }

            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;

            if(nextCol == goalCol && nextRow == goalRow)
            {
                onPath = false;
            }
        }
    }
    public int getDetected(Entity user, Entity target[][], String targetName) {
        int index = 999;

        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction)
        {
            case "up": nextWorldY = user.getTopY() - 1;break;
            case "jos": nextWorldY = user.getBottomY() + 1;break;
            case "stanga": nextWorldX = user.getLeftX() - 1;break;
            case "dreapta": nextWorldX = user.getRightX() + 1;break;
        }

        int col = nextWorldX/gp.tile_size;
        int row = nextWorldY/gp.tile_size;

        for(int i = 0; i < target[1].length; i++)
        {
            if(target[gp.currentMap][i] != null)
            {
                boolean touch = false;
                for(int j = 0; j < target[gp.currentMap][i].width; j++)
                {
                    for(int z = 0; z < target[gp.currentMap][i].height; z++)
                    {
                        if(target[gp.currentMap][i].getCol() + j == col && target[gp.currentMap][i].getRow() + z + 1 == row)
                        {
                            touch = true;
                        }
                    }
                }
                if(touch && target[gp.currentMap][i].name.equals(targetName))
                {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}
