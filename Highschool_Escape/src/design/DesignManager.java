package design;

import main.GamePanel;
import main.UtilityTool;
import tile.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class DesignManager extends Design
{
    private static DesignManager instance;
    private GamePanel gp;
    public Design[] design;
    public int[][] mapDesignNum;

    private DesignManager(GamePanel gp) {
        this.gp = gp;
        design = new Design[80];
        mapDesignNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadmap("/maps/design.txt");
        getTileImage();
    }

    public static DesignManager getDesignManager(GamePanel gp) {
        if (instance == null) {
            instance = new DesignManager(gp);
        }
        return instance;
    }
    public void getTileImage()

    {
//        try
//        {
//            design[0] = new Design();
//            design[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/masa/masa1.png")));
//            setup(0,"masa/masa1",true);
//
//            design[1] = new Design();
//            design[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/masa/masa2.png")));
//
//            design[2] = new Design();
//            design[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/masa/masa3.png")));
//
//            design[3] = new Design();
//            design[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/masa/masa4.png")));
//
//            design[4] = new Design();
//            design[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/scaun/scaun2.png")));
//
//            design[5] = new Design();
//            design[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/scaun/scaun1.png")));
//
//            design[6] = new Design();
//            design[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/biblioteca/biblioteca (1).png")));
//
//            design[7] = new Design();
//            design[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/biblioteca/biblioteca (2).png")));
//
//            design[8] = new Design();
//            design[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/biblioteca/biblioteca (3).png")));
//
//            design[9] = new Design();
//            design[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/biblioteca/biblioteca (4).png")));
//
//            design[10] = new Design();
//            design[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/biblioteca/biblioteca (5).png")));
//
//            design[11] = new Design();
//            design[11].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/biblioteca/biblioteca (6).png")));
//
//            design[12] = new Design();
//            design[12].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/minibiblioteca/capac (1).png")));
//
//            design[13] = new Design();
//            design[13].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/minibiblioteca/capac (2).png")));
//
//            design[14] = new Design();
//            design[14].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/minibiblioteca/gol (1).png")));
//
//            design[15] = new Design();
//            design[15].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/minibiblioteca/gol (2).png")));
//
//            design[16] = new Design();
//            design[16].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/minibiblioteca/full (1).png")));
//
//            design[17] = new Design();
//            design[17].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/minibiblioteca/full (2).png")));
//
//            design[18] = new Design();
//            design[18].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/minibiblioteca/semi (1).png")));
//
//            design[19] = new Design();
//            design[19].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/minibiblioteca/semi (2).png")));
//
//            design[20] = new Design();
//            design[20].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/harta/harta (1).png")));
//
//            design[21] = new Design();
//            design[21].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/harta/harta (2).png")));
//
//            design[22] = new Design();
//            design[22].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/harta/harta (3).png")));
//
//            design[23] = new Design();
//            design[23].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/harta/harta (4).png")));
//
//            design[24] = new Design();
//            design[24].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/harta/harta (5).png")));
//
//            design[25] = new Design();
//            design[25].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/harta/harta (6).png")));
//
//            design[26] = new Design();
//            design[26].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tabla/tabla (1).png")));
//
//            design[27] = new Design();
//            design[27].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tabla/tabla (2).png")));
//
//            design[28] = new Design();
//            design[28].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tabla/tabla (3).png")));
//
//            design[29] = new Design();
//            design[29].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tabla/tabla (4).png")));
//
//            design[30] = new Design();
//            design[30].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tabla/tabla (5).png")));
//
//            design[31] = new Design();
//            design[31].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tabla/tabla (6).png")));
//
//            design[32] = new Design();
//            design[32].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/clasa/clasa (2).png")));
//
//            design[33] = new Design();
//            design[33].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/clasa/clasa (1).png")));
//
//            design[34] = new Design();
//            design[34].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/cos/cos (1).png")));
//
//            design[35] = new Design();
//            design[35].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/cos/cos (2).png")));
//
//            design[36] = new Design();
//            design[36].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/cos/cos (3).png")));
//
//            design[37] = new Design();
//            design[37].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/cos/cos (4).png")));
//
//            design[38] = new Design();
//            design[38].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/stativ/stativ (1).png")));
//
//            design[39] = new Design();
//            design[39].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/stativ/stativ (2).png")));
//
//            design[40] = new Design();
//            design[40].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/stativ/stativ (3).png")));
//
//            design[41] = new Design();
//            design[41].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/hidrant/hidrant.png")));
//
//            design[42] = new Design();
//            design[42].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/mop/mop (1).png")));
//
//            design[43] = new Design();
//            design[43].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/mop/mop (2).png")));
//
//            design[44] = new Design();
//            design[44].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/matura/matura (1).png")));
//
//            design[45] = new Design();
//            design[45].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/matura/matura (2).png")));
//
//            design[46] = new Design();
//            design[46].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/dulap/vestiar (3).png")));
//
//            design[47] = new Design();
//            design[47].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/dulap/vestiar (2).png")));
//
//            design[48] = new Design();
//            design[48].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/dulap/vestiar (1).png")));
//
//            design[49] = new Design();
//            design[49].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/ceas.png")));
//
//            design[50] = new Design();
//            design[50].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/catedra/catedra (1).png")));
//
//            design[51] = new Design();
//            design[51].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/catedra/catedra (2).png")));
//
//            design[52] = new Design();
//            design[52].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/catedra/catedra (3).png")));
//
//            design[53] = new Design();
//            design[53].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/catedra/catedra (4).png")));
//
//            design[54] = new Design();
//            design[54].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/catedra/catedra (5).png")));
//
//            design[55] = new Design();
//            design[55].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/catedra/catedra (6).png")));
//
//            design[56] = new Design();
//            design[56].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/catedra/scaun (1).png")));
//
//            design[57] = new Design();
//            design[57].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/catedra/scaun (2).png")));
//
//            design[58] = new Design();
//            design[58].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/instrumente/microfon (1).png")));
//
//            design[59] = new Design();
//            design[59].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/instrumente/microfon (2).png")));
//
//            design[60] = new Design();
//            design[60].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/instrumente/chitara (1).png")));
//
//            design[61] = new Design();
//            design[61].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/instrumente/chitara (2).png")));
//
//            design[62] = new Design();
//            design[62].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/instrumente/toba (1).png")));
//
//            design[63] = new Design();
//            design[63].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/instrumente/toba (2).png")));
//
//            design[64] = new Design();
//            design[64].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/instrumente/toba (3).png")));
//
//            design[65] = new Design();
//            design[65].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/instrumente/toba (4).png")));
//
//            design[66] = new Design();
//            design[66].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/instrumente/pian (1).png")));
//
//            design[67] = new Design();
//            design[67].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/instrumente/pian (2).png")));
//
//            design[68] = new Design();
//            design[68].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/instrumente/pian (3).png")));
//
//            design[69] = new Design();
//            design[69].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/instrumente/pian (4).png")));
//
//            design[70] = new Design();
//            design[70].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tablouri/mare (1).png")));
//
//            design[71] = new Design();
//            design[71].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tablouri/mare (2).png")));
//
//            design[72] = new Design();
//            design[72].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tablouri/mare (3).png")));
//
//            design[73] = new Design();
//            design[73].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tablouri/mare (4).png")));
//
//            design[74] = new Design();
//            design[74].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tablouri/mare (5).png")));
//
//            design[75] = new Design();
//            design[75].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tablouri/mare (6).png")));
//
//            design[76] = new Design();
//            design[76].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tablouri/mic (1).png")));
//
//            design[77] = new Design();
//            design[77].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tablouri/mic (2).png")));
//
//            design[78] = new Design();
//            design[78].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tablouri/mic (3).png")));
//
//            design[79] = new Design();
//            design[79].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/design/tablouri/mic (4).png")));
//
//
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
        setup(0, "masa/masa1", true);
        setup(1, "masa/masa2", true);
        setup(2, "masa/masa3", true);
        setup(3, "masa/masa4", true);
        setup(4, "scaun/scaun2", true);
        setup(5, "scaun/scaun1", true);
        setup(6, "biblioteca/biblioteca (1)", true);
        setup(7, "biblioteca/biblioteca (2)", true);
        setup(8, "biblioteca/biblioteca (3)", true);
        setup(9, "biblioteca/biblioteca (4)", true);
        setup(10, "biblioteca/biblioteca (5)", true);
        setup(11, "biblioteca/biblioteca (6)", true);
        setup(12, "minibiblioteca/capac (1)", true);
        setup(13, "minibiblioteca/capac (2)", true);
        setup(14, "minibiblioteca/gol (1)", true);
        setup(15, "minibiblioteca/gol (2)", true);
        setup(16, "minibiblioteca/full (1)", true);
        setup(17, "minibiblioteca/full (2)", true);
        setup(18, "minibiblioteca/semi (1)", true);
        setup(19, "minibiblioteca/semi (2)", true);
        setup(20, "harta/harta (1)", true);
        setup(21, "harta/harta (2)", true);
        setup(22, "harta/harta (3)", true);
        setup(23, "harta/harta (4)", true);
        setup(24, "harta/harta (5)", true);
        setup(25, "harta/harta (6)", true);
        setup(26, "tabla/tabla (1)", true);
        setup(27, "tabla/tabla (2)", true);
        setup(28, "tabla/tabla (3)", true);
        setup(29, "tabla/tabla (4)", true);
        setup(30, "tabla/tabla (5)", true);
        setup(31, "tabla/tabla (6)", true);
        setup(32, "clasa/clasa (2)", true);
        setup(33, "clasa/clasa (1)", true);
        setup(34, "cos/cos (1)", true);
        setup(35, "cos/cos (2)", true);
        setup(36, "cos/cos (3)", true);
        setup(37, "cos/cos (4)", true);
        setup(38, "stativ/stativ (1)", true);
        setup(39, "stativ/stativ (2)", true);
        setup(40, "stativ/stativ (3)", true);
        setup(41, "hidrant/hidrant", true);
        setup(42, "mop/mop (1)", true);
        setup(43, "mop/mop (2)", true);
        setup(44, "matura/matura (1)", true);
        setup(45, "matura/matura (2)", true);
        setup(46, "dulap/vestiar (3)", true);
        setup(47, "dulap/vestiar (2)", true);
        setup(48, "dulap/vestiar (1)", true);
        setup(49, "ceas", true);
        setup(50, "catedra/catedra (1)", true);
        setup(51, "catedra/catedra (2)", true);
        setup(52, "catedra/catedra (3)", true);
        setup(53, "catedra/catedra (4)", true);
        setup(54, "catedra/catedra (5)", true);
        setup(55, "catedra/catedra (6)", true);
        setup(56, "catedra/scaun (1)", true);
        setup(57, "catedra/scaun (2)", true);
        setup(58, "instrumente/microfon (1)", true);
        setup(59, "instrumente/microfon (2)", true);
        setup(60, "instrumente/chitara (1)", true);
        setup(61, "instrumente/chitara (2)", true);
        setup(62, "instrumente/toba (1)", true);
        setup(63, "instrumente/toba (2)", true);
        setup(64, "instrumente/toba (3)", true);
        setup(65, "instrumente/toba (4)", true);
        setup(66, "instrumente/pian (1)", true);
        setup(67, "instrumente/pian (2)", true);
        setup(68, "instrumente/pian (3)", true);
        setup(69, "instrumente/pian (4)", true);
        setup(70, "tablouri/mare (1)", true);
        setup(71, "tablouri/mare (2)", true);
        setup(72, "tablouri/mare (3)", true);
        setup(73, "tablouri/mare (4)", true);
        setup(74, "tablouri/mare (5)", true);
        setup(75, "tablouri/mare (6)", true);
        setup(76, "tablouri/mic (1)", true);
        setup(77, "tablouri/mic (2)", true);
        setup(78, "tablouri/mic (3)", true);
        setup(79, "tablouri/mic (4)", true);

    }

    public void setup(int index, String imagePath, boolean collision)
    {
        UtilityTool uTool = new UtilityTool();

        try
        {
            design[index] = new Design();
            design[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(STR."/design/\{imagePath}.png")));
            design[index].image = uTool.scaleImage(design[index].image, gp.tile_size, gp.tile_size);
            design[index].collision = collision;
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadmap(String s)
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
                    mapDesignNum[col][row] = num;
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

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {
            int tileNum = mapDesignNum[worldCol][worldRow];
            if(tileNum!= -1)
            {
                int worldX = worldCol * gp.tile_size;
                int worldY = worldRow * gp.tile_size;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if(worldX + gp.tile_size > gp.player.worldX - gp.player.screenX &&
                        worldX - gp.tile_size < gp.player.worldX + gp.player.screenX &&
                        worldY + gp.tile_size > gp.player.worldY - gp.player.screenY &&
                        worldY - gp.tile_size < gp.player.worldY + gp.player.screenY)
                {
                    g2.drawImage(design[tileNum].image, screenX,screenY,null);

                }
            }
            worldCol++;
            if(worldCol == gp.maxWorldCol)
            {
                worldCol =0;
                worldRow++;
            }
        }
    }
}
