package main;

import java.awt.*;

public class EventHandler {

    GamePanel gp;
    EventRect[][][] eventRect;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    public EventHandler(GamePanel gp)
    {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while( map < gp.maxMap && col < gp.maxWorldCol && row <gp.maxWorldRow)
        {
            eventRect[map][col][row]= new EventRect();
            eventRect[map][col][row].x = gp.tile_size/3;
            eventRect[map][col][row].y = gp.tile_size/3;
            eventRect[map][col][row].width = 16;
            eventRect[map][col][row].height = 16;
            eventRect[map][col][row].eventDefaultX= eventRect[map][col][row].x;
            eventRect[map][col][row].eventDefaultY = eventRect[map][col][row].y;
            col++;

            if(col == gp.maxWorldCol)
            {
                col = 0;
                row++;
                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent()
    {
        int xDistance = Math.abs(gp.player.worldX-previousEventX);
        int yDistance = Math.abs(gp.player.worldY-previousEventY);
        int distance = Math.max(xDistance,yDistance);
        if(distance > gp.tile_size)
        {
            canTouchEvent = true;
        }

        if(canTouchEvent)
        {

            if(hit(1,30, 16,"any") || hit(1,48, 16,"any"))
            {
                healingPool(gp.dialogueState);
            }
            else if( hit(0,9,6,"any") )
            {
                teleport(1,50,14);
            }
            else if ( hit(1,28,7,"any") || hit(1,29,7,"any"))
            {
                teleport(2, 56,24);
            }
            else if( hit(2,28,6,"any") || hit(2,29,6,"any")  )
            {
                gp.ui.gameFinished = true;
            }
        }
    }

    public boolean hit(int map,int col, int row, String reqDirection)
    {
        boolean hit = false;

        if(map == gp.currentMap)
        {
            gp.player.solid_area.x = gp.player.worldX + gp.player.solid_area.x;
            gp.player.solid_area.y = gp.player.worldY + gp.player.solid_area.y;
            eventRect[map][col][row].x = col*gp.tile_size + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.tile_size + eventRect[map][col][row].y;

            if(gp.player.solid_area.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone)
            {
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any"))
                {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solid_area.x = gp.player.solidAreaDeafaultX;
            gp.player.solid_area.y = gp.player.solidAreaDeafaultY;

            eventRect[map][col][row].x = eventRect[map][col][row].eventDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventDefaultY;
        }

        return hit;
    }

    public void damagePit(int gameState)
    {
        gp.gameState = gameState;
        gp.ui.currentDialogue= "damage";
        gp.player.life -= 1;
//        eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }
    public void healingPool(int gamestate)
    {
            gp.gameState = gamestate;
            gp.player.attackCanceled = true;
            gp.ui.currentDialogue = "heal & mana";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            canTouchEvent = false;
    }
    public void teleport(int map, int col, int row)
    {
        gp.currentMap = map;
        gp.player.worldX = gp.tile_size*col;
        gp.player.worldY = gp.tile_size*row;
    }
}
