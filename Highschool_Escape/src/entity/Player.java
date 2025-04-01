package entity;

import main.GamePanel;
import main.ImageSetupException;
import main.KeyInput;
import main.UtilityTool;
import object.*;
import tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Player extends Entity
{
    public static Player instance; // Instanța jucătorului
    KeyInput keyH; // Referință către gestionarea tastelor
    // Poziția inițială a jucătorului pe ecran
    public final int screenX;
    public final  int screenY;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int inventorySize = 20;
    // Variabile pentru stocarea cheilor și punctelor jucătorului
    public int hasKey = 0;
    int hasPoints = 0;
    // Constructorul clasei Player
    private Player(GamePanel gp, KeyInput keyH) throws ImageSetupException {
        super(gp); // Apelarea constructorului clasei de bază (Entity)
        this.keyH = keyH; // Inițializarea gestionării tastelor

        // Calcularea poziției inițiale a jucătorului pe ecran
        screenX = gp.screen_width/2-(gp.tile_size/2);
        screenY = gp.screen_height/2-(gp.tile_size/2);

        // Inițializarea zonei solide a jucătorului pentru coliziuni
        solid_area = new Rectangle();
//        solid_area.x = 42;
//        solid_area.y = 64;
//        solidAreaDeafaultX = solid_area.x;
//        solidAreaDeafaultY = solid_area.y;
//        solid_area.height = 12;
//        solid_area.width = 10;

        solid_area.x = 8;
        solid_area.y = 16;
        solidAreaDeafaultX = solid_area.x;
        solidAreaDeafaultY = solid_area.y;
        solid_area.width = 30;
        solid_area.height = 30;


//        attackArea.width = 72;
//        attackArea.height = 72;

//        width = 2;
//        height = 2;

        setDeaufaultValues(); // Setarea valorilor implicite pentru jucător
        getPlayerImage(); // Încărcarea imaginilor jucătorului
        getPlayerAttackImage();
        setItems();
    }
    // Metoda pentru obținerea instanței jucătorului
    public static Player getPlayer(GamePanel gp, KeyInput keyH) throws ImageSetupException {
        if (instance == null) {
            instance = new Player(gp, keyH); // Crearea unei noi instanțe dacă nu există deja una
        }
        return instance;
    }
    // Metoda pentru setarea valorilor implicite pentru jucător
    public void setDeaufaultValues() throws ImageSetupException {
        worldX = 50 * gp.tile_size; // Setarea poziției jucătorului pe axa X
        worldY = 7 * gp.tile_size; // Setarea poziției jucătorului pe axa Y
        direction = ""; // Direcția jucătorului
        defaultSpeed = 12;
        speed = defaultSpeed; // Viteza jucătorului
        level = 1;
        maxLife = 6;
        maxMana = 4;
        mana = 4;
        ammo = 10;
        life = maxLife;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new Sword(gp);
        currentShield = new Shield(gp);
        projectile = new FireBall(gp);
        attack = getAttack();
        defense = getDefense();
    }
    public void setDefaultPosition() {
        worldX = 50 * gp.tile_size; // Setarea poziției jucătorului pe axa X
        worldY = 7 * gp.tile_size; // Setarea poziției jucătorului pe axa Y
        direction = ""; // Direcția jucătorului
    }
    public void restoreLifeAndMan() {
        life = maxLife;
        mana = maxMana;
        invincible = false;
    }
    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
    }
    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return strength * currentWeapon.attackValue;
    }
    public int getDefense()
    {
        return dexterity * currentShield.defenseValue;
    }
    // Metoda pentru încărcarea imaginilor jucătorului
    public void getPlayerImage() throws ImageSetupException {
        jos1 = setup("/sprites/boy_down_1", width * gp.tile_size, height * gp.tile_size);
        jos2 = setup("/sprites/boy_down_2", width * gp.tile_size, height * gp.tile_size);
        sus1 = setup("/sprites/boy_up_1", width * gp.tile_size, height * gp.tile_size);
        sus2 = setup("/sprites/boy_up_2", width * gp.tile_size, height * gp.tile_size);
        mers1_dreapta = setup("/sprites/boy_right_1", width * gp.tile_size, height * gp.tile_size);
        mers2_dreapta = setup("/sprites/boy_right_2", width * gp.tile_size, height * gp.tile_size);
        mers1_stanga = setup("/sprites/boy_left_1", width * gp.tile_size, height * gp.tile_size);
        mers2_stanga = setup("/sprites/boy_left_2", width * gp.tile_size, height * gp.tile_size);
        stat1_dreapta = setup("/sprites/stat1_dreapta", width * gp.tile_size, height * gp.tile_size);
        stat2_dreapta = setup("/sprites/stat2_dreapta", width * gp.tile_size, height * gp.tile_size);
        stat1_stanga = setup("/sprites/stat1_stanga", width * gp.tile_size, height * gp.tile_size);
        stat2_stanga = setup("/sprites/stat2_stanga", width * gp.tile_size, height * gp.tile_size);
    }
    public void getPlayerAttackImage() throws ImageSetupException {
        if(currentWeapon.type == type_sword)
        {
            atacsus1 = setup("/sprites/attack/boy_attack_up_1", gp.tile_size , gp.tile_size * 2);
            atacsus2 = setup("/sprites/attack/boy_attack_up_2", gp.tile_size , gp.tile_size * 2);
            atacjos1 = setup("/sprites/attack/boy_attack_down_1", gp.tile_size , gp.tile_size * 2);
            atacjos2 = setup("/sprites/attack/boy_attack_down_2", gp.tile_size , gp.tile_size * 2);
            atacdreapta1 = setup("/sprites/attack/boy_attack_right_1", gp.tile_size * 2, gp.tile_size );
            atacdreapta2 = setup("/sprites/attack/boy_attack_right_2", gp.tile_size * 2, gp.tile_size );
            atacstanga1 = setup("/sprites/attack/boy_attack_left_1", gp.tile_size * 2, gp.tile_size );
            atacstanga2 = setup("/sprites/attack/boy_attack_left_2", gp.tile_size * 2, gp.tile_size );
        }
        if(currentWeapon.type == type_axe)
        {
            atacsus1 = setup("/sprites/axe/boy_axe_up_1", gp.tile_size, gp.tile_size * 2);
            atacsus2 = setup("/sprites/axe/boy_axe_up_2", gp.tile_size, gp.tile_size * 2);
            atacjos1 = setup("/sprites/axe/boy_axe_down_1", gp.tile_size, gp.tile_size * 2);
            atacjos2 = setup("/sprites/axe/boy_axe_down_2", gp.tile_size, gp.tile_size * 2);
            atacdreapta1 = setup("/sprites/axe/boy_axe_right_1", gp.tile_size * 2, gp.tile_size);
            atacdreapta2 = setup("/sprites/axe/boy_axe_right_2", gp.tile_size * 2, gp.tile_size);
            atacstanga1 = setup("/sprites/axe/boy_axe_left_1", gp.tile_size * 2, gp.tile_size);
            atacstanga2 = setup("/sprites/axe/boy_axe_left_2", gp.tile_size * 2, gp.tile_size);
        }
    }
    // Metoda pentru actualizarea poziției și stării jucătorului
    public void update() {
        if(attacking)
        {
            attacking();
        }
        else if(keyH.downPressed || keyH.leftPressed || keyH.upPressed || keyH.rightPressed || keyH.enterPressed) {
            if (keyH.upPressed) {
                direction = "sus";
            } else if (keyH.downPressed) {
                direction = "jos";
            } else if (keyH.leftPressed) {
                direction = "stanga";
            } else if (keyH.rightPressed) {
                direction = "dreapta";
            }
            collisionOn = false;
            gp.cChecker.checkTile(this);
//            gp.cChecker.checkDesign(this);
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            int iTileIndex = gp.cChecker.checkEntity(this,gp.iTile);

            gp.eHandler.checkEvent();

            // Actualizarea poziției jucătorului în funcție de direcție și coliziuni
            if (!collisionOn && !keyH.enterPressed) {
                switch (direction) {
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

            if(keyH.enterPressed && !attackCanceled)
            {
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 4)
                    spriteNum = 1;
                else
                    spriteNum++;
                spriteCounter = 0;
            }
        }

        if(gp.keyH.shotKeyPressed && !projectile.alive && shotAvailableCounter == 30 && projectile.haveResource(this))
        {

            projectile.set(worldX, worldY, direction, true, this);

            projectile.subtractResource(this);

            for(int i =  0; i < gp.projectile[1].length; i++)
            {
                if(gp.projectile[gp.currentMap][i] == null)
                {
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;
        }

        if(invincible)
        {
            invincibleCounter++;
            if(invincibleCounter > 60)
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if(shotAvailableCounter < 30)
        {
            shotAvailableCounter++;
        }

        if(life > maxLife)
        {
            life = maxLife;
        }

        if(mana > maxMana)
        {
            mana = maxMana;
        }

        if(life <= 0)
        {
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum--;
        }
    }
    // Metoda pentru colectarea obiectelor de pe hartă
    public void pickUpObject(int index) {
        if(index != 999)
        {

            if(gp.obj[gp.currentMap][index].type == type_pickupOnly)
            {
                gp.obj[gp.currentMap][index].use(this);
                gp.obj[gp.currentMap][index] = null;
            }
            else if(gp.obj[gp.currentMap][index].type == type_obstacle)
            {
                if(keyH.enterPressed) {
                    attackCanceled = true;
                    gp.obj[gp.currentMap][index].interact();
                }
            }
            else
            {
                String text;
                if(canObtainItem(gp.obj[gp.currentMap][index]))
                {
                    inventory.add(gp.obj[gp.currentMap][index]);
                    text = "Got a " + gp.obj[gp.currentMap][index].name + "!";
                }
                else
                {
                    text = "inventar full";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][index] = null;
            }

//            if(inventory.size() != inventorySize)
//            {
//
//
//                String objName = gp.obj[index].name;
//                switch (objName)
//                {
//                    case "key":
//                        gp.playSE(1); // Redare sunet
//                        hasKey++; // Incrementarea numărului de chei
//                        inventory.add(gp.obj[index]);
//                        text = "Ai colectat "+gp.obj[index].name+"!";
//                        gp.obj[index] = null; // Ștergere obiect de pe hartă
//                        break;
//                    case "usa":
//                        if(hasKey >= 3) // Verificare dacă jucătorul are suficiente chei pentru a deschide ușa
//                        {
//                            gp.playSE(2); // Redare sunet
//                            // Schimbarea imaginii ușii în cea deschisă
//                            gp.obj[index].jos1 = setup("/objects/usa_deschisa",width*gp.tile_size, height*gp.tile_size);
//                            gp.obj[index].collision = false; // Dezactivare coliziune pentru ușă
//                            hasKey -= 3; // Scăderea numărului de chei
//                            gp.ui.gameFinished = true; // Setare joc terminat
//                            gp.stopMusic(); // Oprire muzică
//                            gp.playSE(3); // Redare sunet
//                        }
//                        break;
//                    case "minge":
//                        coin++; // Incrementarea numărului de puncte
//                        text = "Ai colectat "+gp.obj[index].name+"!";
//                        gp.obj[index] = null; // Ștergere obiect de pe hartă
//                        break;
//                    default:
//                        inventory.add(gp.obj[index]);
//                        text = "Ai colectat "+gp.obj[index].name+"!";
//                        gp.obj[index] = null;
//                        break;
//                }
//            }
//            else {
//                text = "inventar full";
//            }
//            gp.ui.addMessage(text);

//            String objName = gp.obj[index].name;
//            switch (objName)
//            {
//                case "key":
//                    gp.playSE(1); // Redare sunet
//                    hasKey++; // Incrementarea numărului de chei
//                    gp.obj[index] = null; // Ștergere obiect de pe hartă
//                    break;
//                case "usa":
//                    if(hasKey >= 3) // Verificare dacă jucătorul are suficiente chei pentru a deschide ușa
//                    {
//                        gp.playSE(2); // Redare sunet
//                        // Schimbarea imaginii ușii în cea deschisă
//                        gp.obj[index].jos1 = setup("/objects/usa_deschisa",width*gp.tile_size, height*gp.tile_size);
//                        gp.obj[index].collision = false; // Dezactivare coliziune pentru ușă
//                        hasKey -= 3; // Scăderea numărului de chei
//                        gp.ui.gameFinished = true; // Setare joc terminat
//                        gp.stopMusic(); // Oprire muzică
//                        gp.playSE(3); // Redare sunet
//                    }
//                    break;
//                case "minge":
//                    coin++; // Incrementarea numărului de puncte
//                    gp.obj[index] = null; // Ștergere obiect de pe hartă
//                    break;
//            }
        }
    }
    public void interactNPC(int i) {
        if(gp.keyH.enterPressed)
        {
            if(i != 999)
            {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }
    public void contactMonster(int i) {
        if(i != 999)
        {
            if(!invincible && !gp.monster[gp.currentMap][i].dying) {
//                gp.playMusic(4);
                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if (damage < 0)
                {
                    damage = 0;
                }
                life -=damage;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i, Entity attacker, int attack, int knockPower) {
        if(i != 999)
        {
            if(!gp.monster[gp.currentMap][i].invincible)
            {
                if(knockPower > 0)
                {
                    setknockBack(gp.monster[gp.currentMap][i], attacker,knockPower);

                }
                int damage = attack - gp.monster[gp.currentMap][i].defense;

                if(damage < 0)
                {
                    damage = 0;
                }

                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(STR."\{damage} damage!");

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if(gp.monster[gp.currentMap][i].life <= 0)
                {
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage(STR."ai omorat \{gp.monster[gp.currentMap][i].name}!");
                    gp.ui.addMessage(STR."ai primit \{gp.monster[gp.currentMap][i].exp} exp!");
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void damageInteractiveTile(int i) {
        if(i != 999 && gp.iTile[gp.currentMap][i].destructible && gp.iTile[gp.currentMap][i].isCorrectItem(this) && !gp.iTile[gp.currentMap][i].invincible )
        {
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;
            if(gp.iTile[gp.currentMap][i].life <= 0)
                gp.iTile[gp.currentMap][i] = null;
        }
    }
    public void damageProjectile(int i){
        if(i!= 999)
        {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
//            generateParticle(projectile,projectile);
        }
    }
    public void checkLevelUp() {
        if(exp  >= nextLevelExp)
        {
            level++;
            nextLevelExp = nextLevelExp + 5;
            maxLife += 1;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = STR."You are level \{level} now!";
        }
    }
    public void selectItem() throws ImageSetupException {
        int itemIndex = gp.ui.getItemIndexOnSlot();

        if(itemIndex < inventory.size())
        {
            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_axe || selectedItem.type == type_sword)
            {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if(selectedItem.type == type_shield )
            {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable)
            {
                if(selectedItem.use(this))
                {
                    inventory.remove(itemIndex);
                }
            }
        }
    }
    public int searchItemInINventory(String itemName){
        int itemIndex = 999;

        for(int i = 0; i < inventory.size(); i++)
        {
            if(inventory.get(i).name.equals(itemName))
            {
                itemIndex = 1;
                break;
            }
        }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item){
        boolean canObtain = false;

        if(item.stackable)
        {
            int index = searchItemInINventory(item.name);

            if(index != 999)
            {
                inventory.get(index).amount++;
                canObtain = true;
            }
            else
            {
                if(inventory.size() != inventorySize)
                {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else
        {
            if(inventory.size() != inventorySize)
            {
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
    }
    // Metoda pentru desenarea jucătorului pe ecran
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX, tempScreenY = screenY;

        // Selectarea imaginii corespunzătoare direcției și stării jucătorului
        switch (direction)
        {
            case "sus":
                if(!attacking)
                {
                    if(spriteNum % 2 == 1) image = sus1;
                    if(spriteNum % 2 == 0) image = sus2;
                }
                if(attacking)
                {
                    tempScreenY = screenY - (gp.tile_size);
                    if(spriteNum % 2 == 1) image = atacsus1;
                    if(spriteNum % 2 == 0) image = atacsus2;
                }
                break;
            case "jos":
                if(!attacking)
                {
                    if(spriteNum % 2 == 1) image = jos1;
                    if(spriteNum % 2 == 0) image = jos2;
                }
                if(attacking)
                {
                    if(spriteNum % 2 == 1) image = atacjos1;
                    if(spriteNum % 2 == 0) image = atacjos2;
                }
                break;
            case "dreapta":
                if(!attacking)
                {
                    if(spriteNum % 2 == 0) image = mers1_dreapta;
                    if(spriteNum % 2 == 1) image = mers2_dreapta;
                }
                if(attacking)
                {
                    if(spriteNum % 2 == 0) image = atacdreapta1;
                    if(spriteNum % 2 == 1) image = atacdreapta2;
                }
                break;
            case "stanga":
                if(!attacking)
                {
                    if(spriteNum % 2 == 1) image = mers1_stanga;
                    if(spriteNum % 2 == 0) image = mers2_stanga;
                }
                if(attacking)
                {
                    tempScreenX = screenX - (gp.tile_size);
                    if(spriteNum % 2 == 1) image = atacstanga1;
                    if(spriteNum % 2 == 0) image = atacstanga2;
                }
                break;
//            case "stat":
//                if(keyH.last_pressed)
//                {
//                    if(!attacking)
//                    {
//                        if(spriteNum % 2 == 1) image = stat1_stanga;
//                        if(spriteNum % 2 == 0) image = stat2_stanga;
//                    }
//                    if(attacking)
//                    {
//                        tempScreenX = screenX - (gp.tile_size * 2);
//                        if(spriteNum % 2 == 1) image = atacstanga1;
//                        if(spriteNum % 2 == 0) image = atacstanga2;
//                    }
//                }
//                else
//                {
//                    if(!attacking)
//                    {
//                        if(spriteNum % 2 == 1) image = stat1_dreapta;
//                        if(spriteNum % 2 == 0) image = stat2_dreapta;
//                    }
//                    if(attacking)
//                    {
//                        if(spriteNum % 2 == 1) image = atacdreapta1;
//                        if(spriteNum % 2 == 0) image = atacdreapta2;
//                    }
//                }
//                break;
        }

        if(invincible)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        // Desenarea imaginii jucătorului pe ecran
        g2.drawImage(image, tempScreenX, tempScreenY,null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
