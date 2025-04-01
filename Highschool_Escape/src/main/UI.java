package main;

import entity.Entity;
import object.Heart;
import object.Key;
import object.Mana;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80;
    BufferedImage keyImage, heart_full, heart_half, heart_blank, crystal_full, crystal_blank;
    public boolean gameFinished;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public int commandNum = 0;
    public String currentDialogue = "";
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public int playerSlotCol = 0;
    public int platerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    public final int maxSlotCol = 5;
    public final int maxSlotRow = 4;

    public UI(GamePanel gp) throws ImageSetupException {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);

        Key key = new Key(gp);
        keyImage = key.jos1;

        Entity heart = new Heart(gp);
        heart_full = heart.image1;
        heart_half = heart.image2;
        heart_blank = heart.image3;

        Entity crystal = new Mana(gp);
        crystal_full = crystal.image1;
        crystal_blank = crystal.image2;
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);


        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
        }
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawMessage();

            if (gameFinished) {
                g2.setFont(arial_40);
                g2.setColor(Color.white);
                String text = "FINISH";
                int x = x = getXforCenteredText(text);
                ;
                int y = gp.screen_height / 2 - (gp.tile_size * 3);
                g2.drawString(text, x, y);

                text = STR."Your time:\{dFormat.format(playTime)}";
                x = getXforCenteredText(text);
                y = gp.screen_height / 2 + (gp.tile_size * 4);
                g2.drawString(text, x, y);

                g2.setFont(arial_80);
                g2.setColor(Color.yellow);
                text = "Congratulations";
                x = getXforCenteredText(text);
                y = gp.screen_height / 2 + (gp.tile_size * 3);
                g2.drawString(text, x, y);

                gp.gameThread = null;
            } else {
                g2.setFont(arial_40);
                g2.setColor(Color.WHITE);
//                g2.drawImage(keyImage, gp.tile_size / 2, gp.tile_size / 2 + gp.tile_size, null);
//                g2.drawString(STR."\{gp.player.hasKey}/3", 74, 113);

                if (gp.gameState == gp.playState)
                    playTime += (double) 1 / 60;
                g2.drawString(STR."Time: \{dFormat.format(playTime)}", gp.tile_size * 11, 65);
            }
        }

        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }

        if (gp.gameState == gp.characterStare) {
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
    }

    public void drawGameOverScreen() {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screen_width,gp.screen_height);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,110F));

        text = "Game Over";
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tile_size * 4;
        g2.drawString(text,x,y);
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tile_size*4;
        g2.drawString(text,x,y);
        if(commandNum == 0)
        {
            g2.drawString(">", x-40,y);
        }

        text = "Quit";
        x = getXforCenteredText(text);
        y+= 55;
        g2.drawString(text,x,y);
        if(commandNum == 1)
        {
            g2.drawString(">", x-40,y);
        }



    }
    public void drawCharacterScreen() {
        final int frameX = gp.tile_size;
        final int frameY = gp.tile_size;
        final int frameHeight = (int)(gp.tile_size*8.5);
        final int frameWidth = gp.tile_size*5;

        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));

        int textX = frameX + 20;
        int textY = frameY + gp.tile_size;
        final int lineHeight = 32;

        g2.drawString("Level", textX,textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strenght", textX,textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX,textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX,textY);
        textY += lineHeight + 20;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Shield", textX,textY);
        textY += lineHeight;

        int tailX = frameX + frameWidth - 32;
        textY = frameY + gp.tile_size;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life +"/"+gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana +"/"+gp.player.maxMana);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.jos1, tailX - gp.tile_size, textY - 15, null);
        textY += gp.tile_size;

        g2.drawImage(gp.player.currentShield.jos1, tailX - gp.tile_size, textY - 15, null);
    }
    public void drawInventory(Entity entity, boolean cursor) {

        int frameX = 0;
        int frameY= 0;
        int frameWidth= 0;
        int frameHeight= 0;
        int slotCol= 0;
        int slotRow= 0;

        if(entity == gp.player)
        {
            frameX = gp.tile_size*9;
            frameY = gp.tile_size;
            frameWidth = gp.tile_size * (maxSlotCol+1);
            frameHeight = gp.tile_size * (maxSlotRow+1);
            slotCol = playerSlotCol;
            slotRow = platerSlotRow;
        }

        drawSubWindow(frameX,frameY,frameWidth, frameHeight);


        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tile_size+3;

        for(int i = 0; i < gp.player.inventory.size(); i++)
        {
            if(gp.player.inventory.get(i) == gp.player.currentWeapon || gp.player.inventory.get(i) == gp.player.currentShield)
            {
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX,slotY,gp.tile_size, gp.tile_size,10,10);
            }
            g2.drawImage(gp.player.inventory.get(i).jos1, slotX, slotY, null);

            if(gp.player.inventory.get(i).amount > 1)
            {
                g2.setFont(g2.getFont().deriveFont(Font.BOLD,16F));
                int amountX;
                int amountY;

                String s = "" + gp.player.inventory.get(i).amount;
                amountX = getXforAlignToRightText(s,slotX + 44);
                amountY = slotY + gp.tile_size;

                g2.setColor(new Color(60,60,60));
                g2.drawString(s,amountX,amountY);

                g2.setColor(Color.white);
                g2.drawString(s,amountX-3,amountY-3);
            }
            slotX += slotSize;


            if(i % maxSlotCol == 4)
            {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

         if(cursor)
         {
             int cursorX = slotXstart + (slotSize * slotCol);
             int cursorY = slotYstart + (slotSize * slotRow);
             int cusorWidth = gp.tile_size;
             int cursorHeight = gp.tile_size;

             g2.setColor(Color.white);
             g2.setStroke(new BasicStroke(3));
             g2.drawRoundRect(cursorX,cursorY,cusorWidth,cursorHeight, 10,10);

             int dFrameX = frameX;
             int dFrameY = frameY + frameHeight;
             int dFrameWidth = frameWidth;
             int dFrameHeight = gp.tile_size*3;

             int textX = dFrameX +20;
             int textY = dFrameY + gp.tile_size;
             g2.setFont(g2.getFont().deriveFont(Font.BOLD,16F));

             int itemIndex = getItemIndexOnSlot();

             if(itemIndex < gp.player.inventory.size())
             {
                 drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
                 for(String line: gp.player.inventory.get(itemIndex).description.split("\n"))
                 {
                     g2.drawString(line, textX, textY);
                     textY += 32;
                 }
             }
         }
    }
    public int getItemIndexOnSlot()
    {
        return playerSlotCol + (platerSlotRow * maxSlotCol);
    }
    public void drawPlayerLife() {
        int x = gp.tile_size/2;
        int y = gp.tile_size/2;
        int i = 0;

        while(i<gp.player.maxLife/2)
        {
            g2.drawImage(heart_blank, x,y,null);
            i++;
            x += gp.tile_size;
        }

        x = gp.tile_size/2;
        i = 0;

        while(i<gp.player.life)
        {
            g2.drawImage(heart_half,x,y,null);
            i++;
            if(i < gp.player.life)
            {
                g2.drawImage(heart_full,x,y,null);
            }
            i++;
            x += gp.tile_size;
        }

        x = gp.tile_size/2 - 5;
        y = (int)(gp.tile_size * 1.5);
        i = 0;
        while(i < gp.player.maxMana)
        {
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }

        x = gp.tile_size/2 - 5;
        y = (int)(gp.tile_size * 1.5);
        i = 0;
        while(i < gp.player.mana)
        {
            g2.drawImage(crystal_full,x,y,null);
            i++;
            x += 35;
        }

    }
    public void drawMessage() {
        int messageX = gp.tile_size;
        int messageY = gp.tile_size * 4;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,16F));

        for(int i = 0; i < message.size(); i++)
        {
            if(message.get(i) != null)
            {
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
               g2.setColor(Color.white);
               g2.drawString(message.get(i), messageX, messageY);

               int counter = messageCounter.get(i) + 1;
               messageCounter.set(i,counter);
               messageY += gp.tile_size/2;

               if(messageCounter.get(i) > 180)
               {
                   message.remove(i);
                   messageCounter.remove(i);
               }
            }
        }

    }
    public void drawTitleScreen() {

        g2.setColor(new Color(20, 60, 90));
        g2.fillRect(0,0, gp.screen_width, gp.screen_height);

        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        String text = "Highschool Escape";
        int x = getXforCenteredText(text);
        int y = gp.tile_size * 2;

        //SHADOW
        g2.setColor(Color.black);
        g2.drawString(text,x+5,y+5);

        g2.setColor(Color.white);
        g2.drawString(text, x,y);


        //BOY IMAGE
        x = gp.screen_width/2-gp.tile_size*2;
        y += gp.tile_size/2-15;

        g2.drawImage(gp.player.jos1,x,y,gp.tile_size*5, gp.tile_size*4, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tile_size*5;
        g2.drawString(text,x,y);
        if(commandNum == 0) {
            g2.drawString(">",x-gp.tile_size,y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tile_size-10;
        g2.drawString(text,x,y);
        if(commandNum == 1) {
            g2.drawString(">",x-gp.tile_size,y);
        }

        text = "OPTIONS";
        x = getXforCenteredText(text);
        y += gp.tile_size-10;
        g2.drawString(text,x,y);
        if(commandNum == 2) {
            g2.drawString(">",x-gp.tile_size,y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tile_size-10;
        g2.drawString(text,x,y);
        if(commandNum == 3) {
            g2.drawString(">",x-gp.tile_size,y);
        }

    }
    public void drawPauseScreen() {
        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.screen_height/2;

        g2.drawString(text,x,y);
    }
    public void drawDialogueScreen() {
        int x = gp.tile_size*2;
        int y = gp.tile_size/2;
        int width =  gp.screen_width - (gp.tile_size*4);
        int height = gp.tile_size*3;

        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
        x+= gp.tile_size;
        y+= gp.tile_size;

        for(String line : currentDialogue.split("\n"))
        {
            g2.drawString(line,x,y);
            y += 40;
        }

    }
    public void drawSubWindow(int x, int y, int witdh, int height) {
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x,y,witdh, height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, witdh-10, height-10,25,25);
    }
    public int getXforCenteredText(String s) {
        int textLength;
        textLength = (int)g2.getFontMetrics().getStringBounds(s, g2).getWidth();
        int x = gp.screen_width/2 - textLength/2;
        return x;
    }
    public int getXforAlignToRightText(String s, int tailX) {
        int textLength;
        textLength = (int)g2.getFontMetrics().getStringBounds(s, g2).getWidth();
        return tailX - textLength;
    }
}
