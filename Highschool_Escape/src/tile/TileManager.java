package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class TileManager extends Tile
{
    private static TileManager instance;
    private GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;
    boolean drawPath = true;

    private TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[48];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        loadmap("/maps/map1.txt",0);
        loadmap("/maps/map2.txt",1);
        loadmap("/maps/map3.txt",2);
        getTileImage();
    }

    public static TileManager getTileManager(GamePanel gp) {
        if (instance == null) {
            instance = new TileManager(gp);
        }
        return instance;
    }
    public void getTileImage() {

        setup(0, "000", false);
        setup(1, "colt1", true);
        setup(2, "colt2", true);
        setup(3, "colt3", true);
        setup(4, "colt4", true);
        setup(5, "margine1", true);
        setup(6, "margine2", true);
        setup(7, "margine3", true);
        setup(8, "margine4", true);
        setup(9, "perete1_jos_dreapta", true);
        setup(10, "perete1_jos_mijloc", true);
        setup(11, "perete1_jos_stanga", true);
        setup(12, "perete1_mid_dreapta", true);
        setup(13, "perete1_mid_mijloc", true);
        setup(14, "perete1_mid_stanga", true);
        setup(15, "perete1_sus_dreapta", true);
        setup(16, "perete1_sus_mijloc", true);
        setup(17, "perete1_sus_stanga", true);
        setup(18, "podea1(1)", false);
        setup(19, "podea1(2)", false);
        setup(20, "podea2 (1)", false);
        setup(21, "podea2 (2)", false);
        setup(22, "varf1", true);
        setup(23, "varf2", true);
        setup(24, "varf3", true);
        setup(25, "varf4", true);
        setup(26,"www (1)", true);
        setup(27,"www (11)", true);
        setup(28,"www (12)", true);
        setup(29,"www (13)", true);
        setup(30,"www (14)", true);
        setup(31,"www (15)", true);
        setup(32,"www (16)", true);
        setup(33,"www (17)", true);
        setup(34,"www (18)", true);
        setup(35,"www (19)", false);
        setup(36,"www (20)", true);
        setup(37,"www (21)", true);
        setup(38,"www (22)", true);
        setup(39,"www (23)", true);
        setup(40,"www (24)", true);
        setup(41,"www (25)", true);
        setup(42,"www (26)", true);
        setup(43,"www (27)", true);
        setup(44,"www (28)", true);
        setup(45,"www (29)", true);
        setup(46,"www (30)", true);
        setup(47,"www (31)", true);

//        try
//        {
//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/exterior.png")));
//            setup(0,"exterior",false);
//
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/podea2 (1).png")));
//
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/podea2 (2).png")));
//
//            tile[23] = new Tile();
//            tile[23].collision = true;
//            tile[23].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/perete1_sus_dreapta.png")));
//
//            tile[3] = new Tile();
//            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/perete1_sus_mijloc.png")));
//
//            tile[4] = new Tile();
//            tile[4].collision = true;
//            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/perete1_sus_stanga.png")));
//
//            tile[5] = new Tile();
//            tile[5].collision = true;
//            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/perete1_mid_dreapta.png")));
//
//            tile[6] = new Tile();
//            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/perete1_mid_mijloc.png")));
//
//            tile[7] = new Tile();
//            tile[7].collision = true;
//            tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/perete1_mid_stanga.png")));
//
//            tile[8] = new Tile();
//            tile[8].collision = true;
//            tile[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/perete1_jos_dreapta.png")));
//
//            tile[9] = new Tile();
//            tile[9].collision = true;
//            tile[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/perete1_jos_mijloc.png")));
//
//            tile[10] = new Tile();
//            tile[10].collision = true;
//            tile[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/perete1_jos_stanga.png")));
//
//            tile[11] = new Tile();
//            tile[11].collision = true;
//            tile[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/varf1.png")));
//
//            tile[12] = new Tile();
//            tile[12].collision = true;
//            tile[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/varf2.png")));
//
//            tile[13] = new Tile();
//            tile[13].collision = true;
//            tile[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/varf3.png")));
//
//            tile[14] = new Tile();
//            tile[14].collision = true;
//            tile[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/varf4.png")));
//
//            tile[15] = new Tile();
//            tile[15].collision = true;
//            tile[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/colt1.png")));
//
//            tile[16] = new Tile();
//            tile[16].collision = true;
//            tile[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/colt2.png")));
//
//            tile[17] = new Tile();
//            tile[17].collision = true;
//            tile[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/colt3.png")));
//
//            tile[18] = new Tile();
//            tile[18].collision = true;
//            tile[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/colt4.png")));
//
//            tile[19] = new Tile();
//            tile[19].collision = true;
//            tile[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/margine1.png")));
//
//            tile[20] = new Tile();
//            tile[20].collision = true;
//            tile[20].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/margine2.png")));
//
//            tile[21] = new Tile();
//            tile[21].collision = true;
//            tile[21].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/margine3.png")));
//
//            tile[22] = new Tile();
//            tile[22].collision = true;
//            tile[22].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/margine4.png")));
//
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }
    }

    public void setup(int index, String imagePath, boolean collision)
    {
        UtilityTool uTool = new UtilityTool();

        try
        {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(STR."/tiles/\{imagePath}.png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tile_size, gp.tile_size);
            tile[index].collision = collision;
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void loadmap(String s, int map)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(s);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col< gp.maxWorldCol && row< gp.maxWorldRow)
            {
                String line = br.readLine();
                while(col < gp.maxWorldCol)
                {
                    String[] number = line.split("\\s+");
                    int num = Integer.parseInt(number[col]);
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {

//        int worldCol = 0;
//        int worldRow = 0;
//
//        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
//        {
//            int tileNum = mapTileNum[worldCol][worldRow];
//
//            int worldX = worldCol * gp.tile_size;
//            int worldY = worldRow * gp.tile_size;
//            int screenX = worldX - gp.player.worldX + gp.player.screenX;
//            int screenY = worldY - gp.player.worldY + gp.player.screenY;
//
//            if(worldX + gp.tile_size > gp.player.worldX - gp.player.screenX &&
//                    worldX - gp.tile_size < gp.player.worldX + gp.player.screenX &&
//                    worldY + gp.tile_size > gp.player.worldY - gp.player.screenY &&
//                    worldY - gp.tile_size < gp.player.worldY + gp.player.screenY)
//            {
//                g2.drawImage(tile[tileNum].image, screenX,screenY,gp.tile_size,gp.tile_size,null);
//
//            }
//            worldCol++;
//            if(worldCol == gp.maxWorldCol)
//            {
//                worldCol =0;
//                worldRow++;
//            }
//        }

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tile_size;
            int worldY = worldRow * gp.tile_size;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tile_size > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tile_size < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tile_size > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tile_size < gp.player.worldY + gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX,screenY,null);

            }
            worldCol++;
            if(worldCol == gp.maxWorldCol)
            {
                worldCol =0;
                worldRow++;
            }
        }

        if(drawPath)
        {
            g2.setColor(Color.red);
            for(int i = 0; i < gp.pFinder.pathList.size(); i++)
            {
                int worldX = gp.pFinder.pathList.get(i).col * gp.tile_size;
                int worldY = gp.pFinder.pathList.get(i).row * gp.tile_size;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                g2.fillRect(screenX,screenY, gp.tile_size, gp.tile_size);
            }
        }

    }
}
