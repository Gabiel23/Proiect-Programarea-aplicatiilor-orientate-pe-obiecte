package main;

import entity.NPC;
import inamici.Doamna;
import inamici.Orc;
import object.*;
import tile_interactive.InteractiveTile;
import tile_interactive.Masa;

// Această clasă se ocupă de plasarea obiectelor în joc.
public class AssetSetter {

    GamePanel gp;

    // Constructorul clasei AssetSetter
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // Metoda pentru plasarea obiectelor în joc
    public void setObject() throws ImageSetupException {
        // Crearea și plasarea cheilor în joc
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new Chest(this.gp, new Axe(this.gp));
        gp.obj[mapNum][i].worldX = 51 * gp.tile_size;
        gp.obj[mapNum][i++].worldY = 23 * gp.tile_size;

        gp.obj[mapNum][i] = new Chest(this.gp, new Key(this.gp));
        gp.obj[mapNum][i].worldX = 34 * gp.tile_size;
        gp.obj[mapNum][i++].worldY = 22 * gp.tile_size;

        gp.obj[mapNum][i] = new Chest(this.gp, new Key(this.gp));
        gp.obj[mapNum][i].worldX = 27 * gp.tile_size;
        gp.obj[mapNum][i++].worldY = 22 * gp.tile_size;

        gp.obj[mapNum][i] = new Usa(this.gp, 1);
        gp.obj[mapNum][i].worldX = 8 * gp.tile_size;
        gp.obj[mapNum][i++].worldY = 5 * gp.tile_size;

        gp.obj[mapNum][i] = new Minge(this.gp);
        gp.obj[mapNum][i].worldX = 28 * gp.tile_size;
        gp.obj[mapNum][i++].worldY = 15 * gp.tile_size;

        gp.obj[mapNum][i] = new Minge(this.gp);
        gp.obj[mapNum][i].worldX = 43 * gp.tile_size;
        gp.obj[mapNum][i++].worldY = 7 * gp.tile_size;

        gp.obj[mapNum][i++] = new Scaun(gp, 48, 9);
        gp.obj[mapNum][i++] = new Scaun(gp, 51, 9);
        gp.obj[mapNum][i++] = new Scaun(gp, 45, 13);
        gp.obj[mapNum][i++] = new Scaun(gp, 50, 13);
        gp.obj[mapNum][i++] = new Scaun(gp, 50, 15);
        gp.obj[mapNum][i++] = new Scaun(gp, 50, 17);
        gp.obj[mapNum][i++] = new Scaun(gp, 47, 17);
        gp.obj[mapNum][i++] = new Scaun(gp, 50, 19);
        gp.obj[mapNum][i++] = new Scaun(gp, 42, 17);
        gp.obj[mapNum][i++] = new Scaun(gp, 42, 19);
        gp.obj[mapNum][i++] = new Scaun(gp, 42, 21);
        gp.obj[mapNum][i++] = new Scaun(gp, 42, 23);
        gp.obj[mapNum][i++] = new Scaun(gp, 39, 17);
        gp.obj[mapNum][i++] = new Scaun(gp, 36, 17);
        gp.obj[mapNum][i++] = new Scaun(gp, 33, 17);
        gp.obj[mapNum][i++] = new Scaun(gp, 33, 19);
        gp.obj[mapNum][i++] = new Scaun(gp, 33, 21);
        gp.obj[mapNum][i++] = new Scaun(gp, 33, 23);
        gp.obj[mapNum][i++] = new Scaun(gp, 30, 23);
        gp.obj[mapNum][i++] = new Scaun(gp, 36, 23);
        gp.obj[mapNum][i++] = new Scaun(gp, 38, 21);
        gp.obj[mapNum][i++] = new Scaun(gp, 26, 21);
        gp.obj[mapNum][i++] = new Scaun(gp, 26, 19);
        gp.obj[mapNum][i++] = new Scaun(gp, 26, 17);

        gp.obj[mapNum][i++] = new Scaun(gp, 23, 15);
        gp.obj[mapNum][i++] = new Scaun(gp, 21, 19);
        gp.obj[mapNum][i++] = new Scaun(gp, 18, 11);
        gp.obj[mapNum][i++] = new Scaun(gp, 18, 9);
        gp.obj[mapNum][i++] = new Scaun(gp, 14, 8);
        gp.obj[mapNum][i++] = new Scaun(gp, 14, 6);

        gp.obj[mapNum][i++] = new Mini(gp, 22, 23);
        gp.obj[mapNum][i++] = new Mini(gp, 24, 23);
        gp.obj[mapNum][i++] = new Mini(gp, 26, 23);
        gp.obj[mapNum][i++] = new Biblioteca(gp, 46, 21);
        gp.obj[mapNum][i++] = new Biblioteca(gp, 48, 21);

        mapNum = 1;
        i = 0;

        int j = 30;

        gp.obj[mapNum][i++] = new Wc(gp, j++, 7);
        gp.obj[mapNum][i++] = new Pisoar(gp, j++, 7);
        gp.obj[mapNum][i++] = new Chiuveta(gp, j++, 7);

        gp.obj[mapNum][i++] = new Wc(gp, j++, 7);
        gp.obj[mapNum][i++] = new Pisoar(gp, j++, 7);
        gp.obj[mapNum][i++] = new Chiuveta(gp, j++, 7);

        gp.obj[mapNum][i++] = new Wc(gp, j++, 7);
        gp.obj[mapNum][i++] = new Pisoar(gp, j++, 7);
        gp.obj[mapNum][i++] = new Chiuveta(gp, j++, 7);

        gp.obj[mapNum][i++] = new Wc(gp, j++, 7);
        gp.obj[mapNum][i++] = new Pisoar(gp, j++, 7);
        gp.obj[mapNum][i++] = new Chiuveta(gp, j++, 7);

        gp.obj[mapNum][i++] = new Wc(gp, j++, 7);
        gp.obj[mapNum][i++] = new Pisoar(gp, j++, 7);
        gp.obj[mapNum][i++] = new Chiuveta(gp, j++, 7);

        gp.obj[mapNum][i++] = new Wc(gp, j++, 7);
        gp.obj[mapNum][i++] = new Pisoar(gp, j++, 7);
        gp.obj[mapNum][i++] = new Chiuveta(gp, j++, 7);

        gp.obj[mapNum][i++] = new Wc(gp, j++, 7);
        gp.obj[mapNum][i++] = new Pisoar(gp, j++, 7);
        gp.obj[mapNum][i++] = new Chiuveta(gp, j++, 7);

        j = 31;

        gp.obj[mapNum][i++] = new Geam(gp, j++, 5);
        j += 2;
        gp.obj[mapNum][i++] = new Geam(gp, j++, 5);
        j += 2;
        gp.obj[mapNum][i++] = new Geam(gp, j++, 5);
        j += 2;
        gp.obj[mapNum][i++] = new Geam(gp, j++, 5);
        j += 2;
        gp.obj[mapNum][i++] = new Geam(gp, j++, 5);
        j += 2;
        gp.obj[mapNum][i++] = new Geam(gp, j++, 5);
        j += 2;
        gp.obj[mapNum][i++] = new Geam(gp, j++, 5);
        j += 2;

        gp.obj[mapNum][i++] = new Mop(gp, 50, 15);
        gp.obj[mapNum][i++] = new Galeata(gp, 49, 15);
        gp.obj[mapNum][i++] = new Hidrant(gp, 48, 16);

        gp.obj[mapNum][i++] = new Mop(gp, 28, 15);
        gp.obj[mapNum][i++] = new Galeata(gp, 29, 15);
        gp.obj[mapNum][i++] = new Hidrant(gp, 30, 16);

        gp.obj[mapNum][i] = new Chest(this.gp, new Key(this.gp));
        gp.obj[mapNum][i].worldX = 40* gp.tile_size;
        gp.obj[mapNum][i++].worldY = 15 * gp.tile_size;

        gp.obj[mapNum][i] = new Usa(this.gp, 2);
        gp.obj[mapNum][i].worldX = 28 * gp.tile_size;
        gp.obj[mapNum][i++].worldY = 6 * gp.tile_size;

        mapNum = 2;

        gp.obj[mapNum][i] = new Chest(this.gp, new Axe(this.gp));
        gp.obj[mapNum][i].worldX = (int)13.5* gp.tile_size;
        gp.obj[mapNum][i++].worldY = 19 * gp.tile_size;

        gp.obj[mapNum][i] = new Chest(this.gp, new Key(this.gp));
        gp.obj[mapNum][i].worldX = (int)28* gp.tile_size;
        gp.obj[mapNum][i++].worldY = 9 * gp.tile_size;

        gp.obj[mapNum][i] = new Usa(this.gp, 1);
        gp.obj[mapNum][i].worldX = (int)28* gp.tile_size;
        gp.obj[mapNum][i++].worldY = 5 * gp.tile_size;

    }
    public void setNPC() throws ImageSetupException {
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC(this.gp, 1);
        gp.npc[mapNum][i].worldX = gp.tile_size * 43;
        gp.npc[mapNum][i++].worldY = gp.tile_size * 9;

        mapNum = 2;
        i = 0;
        gp.npc[mapNum][i] = new NPC(this.gp,2);
        gp.npc[mapNum][i].worldX = gp.tile_size * 37;
        gp.npc[mapNum][i++].worldY = gp.tile_size * 18;
    }

    public void setInamici() throws ImageSetupException {
        int mapNum = 1;
        int i = 0;

        i = 0;
        int j = 15;
        gp.monster[mapNum][i] = new Doamna(gp);
        gp.monster[mapNum][i].worldX = gp.tile_size*40;
        gp.monster[mapNum][i++].worldY = gp.tile_size*j--;
        gp.monster[mapNum][i] = new Doamna(gp);
        gp.monster[mapNum][i].worldX = gp.tile_size*40;
        gp.monster[mapNum][i++].worldY = gp.tile_size*j--;
        gp.monster[mapNum][i] = new Doamna(gp);
        gp.monster[mapNum][i].worldX = gp.tile_size*40;
        gp.monster[mapNum][i++].worldY = gp.tile_size*j--;
        gp.monster[mapNum][i] = new Doamna(gp);
        gp.monster[mapNum][i].worldX = gp.tile_size*40;
        gp.monster[mapNum][i++].worldY = gp.tile_size*j--;
        gp.monster[mapNum][i] = new Doamna(gp);
        gp.monster[mapNum][i].worldX = gp.tile_size*40;
        gp.monster[mapNum][i++].worldY = gp.tile_size*j--;
        gp.monster[mapNum][i] = new Doamna(gp);
        gp.monster[mapNum][i].worldX = gp.tile_size*40;
        gp.monster[mapNum][i++].worldY = gp.tile_size*j--;
        gp.monster[mapNum][i] = new Doamna(gp);
        gp.monster[mapNum][i].worldX = gp.tile_size*40;
        gp.monster[mapNum][i++].worldY = gp.tile_size*j--;
        mapNum = 2;
        gp.monster[mapNum][i] = new Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tile_size*37;
        gp.monster[mapNum][i++].worldY = gp.tile_size*8;

    }
    public void setInteractiveTile() throws ImageSetupException {
        int mapNum = 0;
        int i = 0;
        gp.iTile[mapNum][i++] = new Masa(gp, 46, 9, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 49, 9, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 43, 13, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 48, 13, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 48, 15, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 48, 17, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 45, 17, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 48, 19, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 40, 17, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 40, 19, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 40, 21, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 40, 23, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 37, 17, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 34, 17, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 31, 17, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 31, 19, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 31, 21, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 31, 23, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 28, 23, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 34, 23, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 36, 21, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 24, 21, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 24, 19, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 24, 17, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 24, 15, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 21, 15, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 19, 19, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 16, 11, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 16, 9, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 12, 8, 0);
        gp.iTile[mapNum][i++] = new Masa(gp, 12, 6, 0);


        mapNum = 2;
        gp.iTile[mapNum][i++] = new Masa(gp, 37,14, 1);
        gp.iTile[mapNum][i++] = new Masa(gp, 37,16,1);
    }
}
