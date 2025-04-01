package main;

import DataBase.DatabaseManager;
import ai.PathFinder;
import design.DesignManager;
import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Acest panou de joc este responsabil pentru afișarea și actualizarea jocului Highschool Escape.
public class GamePanel extends JPanel implements Runnable {
    // Dimensiunile ecranului
    public DatabaseManager dbmng = new DatabaseManager("DB",this);
    final int original_tile_size = 16;
    final int scale = 3;
    public final int tile_size = original_tile_size * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 10;
    public final int screen_width = tile_size * maxScreenCol;
    public final int screen_height = tile_size * maxScreenRow;

    // Dimensiunile hărții
    public final int maxWorldCol = 60;
    public final int maxWorldRow = 32;
    public final int maxMap = 3;
    public int currentMap = 0;
    int FPS = 60; // Cadre pe secundă
    public TileManager tileM = TileManager.getTileManager(this);
    public DesignManager designM = DesignManager.getDesignManager(this);
    public KeyInput keyH = new KeyInput(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public EventHandler eHandler = new EventHandler(this);
    public PathFinder pFinder = new PathFinder(this);
    Thread gameThread;
    public Player player = Player.getPlayer(this, keyH);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Entity[][] obj = new Entity[maxMap][50];
    public Entity[][] npc = new Entity[maxMap][50];
    public Entity[][] monster = new Entity[maxMap][10];
    public InteractiveTile[][] iTile = new InteractiveTile[maxMap][50];
    public Entity[][] projectile = new Entity[maxMap][50];
//    public ArrayList<Entity> projectileList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();
    public int gameState;
    public int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterStare = 4;
    public final int gameOverState = 5;

    // Constructor pentru panoul de joc
    public GamePanel() throws ImageSetupException {
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.MAGENTA);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); // Adaugarea unui ascultător de tastatură
        this.setFocusable(true); // Setarea panoului de joc ca focalizabil pentru a putea primi evenimente de la tastatură
    }

    // Inițializarea jocului
    public void setUp_game() throws ImageSetupException {
        aSetter.setObject(); // Inițializarea obiectelor din joc
        aSetter.setNPC();
        aSetter.setInamici();
        aSetter.setInteractiveTile();
        playMusic(0); // Redarea muzicii
        stopMusic();
        gameState = titleState; // Setarea stării inițiale a jocului
    }
    public void retry() throws ImageSetupException {
        player.setDeaufaultValues();
        player.restoreLifeAndMan();
        aSetter.setNPC();
        aSetter.setInamici();

    }
    public void restart() throws ImageSetupException {
        player.setDeaufaultValues();
        player.setDefaultPosition();
        player.setItems();
        aSetter.setObject(); // Inițializarea obiectelor din joc
        aSetter.setNPC();
        aSetter.setInamici();
        aSetter.setInteractiveTile();
    }
    // Pornirea firului de executie pentru actualizarea și desenarea jocului
    public void StartGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Metoda pentru actualizarea jocului
    @Override
    public void run() {
        double drawInterval = (double) 1000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                try {
                    update();
                } catch (ImageSetupException e) {
                    throw new RuntimeException(e);
                }
                repaint();
                delta--;
            }
        }
    }
    public void loadData() {

        // Player Settings
        String tableName = "PlayerSave";

        // Extragere date din tabel
        dbmng.selectPlayerTable(tableName);
        gameState = playState;
    }

    public void saveData() throws ImageSetupException {

        // Entities
        String monsters = "";
        for(int i = 0; i < maxMap; ++i) {
            for(int j = 0; j < monster[i].length; ++j) {
                if(monster[i][j] != null) {
                    monsters += monster[i][j].worldX + ", " + monster[i][j].worldY + ", " + monster[i][j].life + ", ";
                } else {
                    monsters += -1 + ", " + -1 + ", " + -1 + ", ";
                }
            }
        }
        if(!monsters.isEmpty()) {
            monsters = monsters.substring(0, monsters.length() - 2);
        }

        String NPC = "";
        for(int i = 0; i < maxMap; ++i) {
            for(int j = 0; j < npc[i].length; ++j) {
                if(npc[i][j] != null) {
                    NPC += npc[i][j].worldX + ", " + npc[i][j].worldY + ", ";
                } else {
                    NPC += -1 + ", " + -1 + ", ";
                }
            }
        }
        if(!NPC.isEmpty()) {
            NPC = NPC.substring(0, NPC.length() - 2);
        }

        String objects = "";
        for(int i = 0; i < maxMap; ++i) {
            for(int j = 0; j < obj[i].length; ++j) {
                if(obj[i][j] != null) {
                    objects += obj[i][j].worldX + ", " + obj[i][j].worldY + ", ";
                } else {
                    objects += -1 + ", " + -1 + ", ";
                }
            }
        }
        if(!objects.isEmpty()) {
            objects = objects.substring(0, objects.length() - 2);
        }

        // Inventory
        String inventory = "";
        for(int i = 0; i < player.inventory.size(); ++i) {
            inventory += player.inventory.get(i).name + ", ";
        }
        if(!inventory.isEmpty()) {
            inventory = inventory.substring(0, inventory.length() - 2);
        }

        // Player Settings
        String tableName = "PlayerSave";

        // Creare tabel daca nu exista
        ArrayList<String> fields = new ArrayList<>();
        fields.add("PLAYERPOSX");
        fields.add("TEXT");
        fields.add("PLAYERPOSY");
        fields.add("TEXT");
        fields.add("CURRENTMAP");
        fields.add("TEXT");
        fields.add("DIRECTION");
        fields.add("TEXT");
        fields.add("LIFE");
        fields.add("TEXT");

        fields.add("INVENTORY");
        fields.add("TEXT");
        fields.add("MONSTERS");
        fields.add("TEXT");
        fields.add("NPC");
        fields.add("TEXT");
        fields.add("OBJECTS");
        fields.add("TEXT");
        dbmng.createPlayerTable(tableName, fields);

        // Adaugare date in tabel
        fields.clear();
        fields.add("PLAYERPOSX");
        fields.add("PLAYERPOSY");
        fields.add("CURRENTMAP");
        fields.add("DIRECTION");
        fields.add("LIFE");
        fields.add("INVENTORY");
        fields.add("MONSTERS");
        fields.add("NPC");
        fields.add("OBJECTS");
        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(player.worldX));
        values.add(String.valueOf(player.worldY));
        values.add(String.valueOf(currentMap));
        values.add(player.direction);
        values.add(String.valueOf(player.life));

        values.add(inventory);
        values.add(monsters);
        values.add(NPC);
        values.add(objects);
        dbmng.insertPlayerTable(tableName, fields, values);

        // Resetare joc dupa salvare in baza de date
        restart();
    }

    // Metoda pentru actualizarea stării jocului
    public void update() throws ImageSetupException {
        if (gameState == playState)
        {
            player.update();
            for(int i = 0; i < npc[1].length; i++)
            {
                if(npc[currentMap][i]!=null)
                {
                    npc[currentMap][i].update();
                }
            }

            for(int i = 0; i < monster[1].length; i++)
            {
                if(monster[currentMap][i] != null)
                {
                    if(monster[currentMap][i].alive && !monster[currentMap][i].dying)
                    {
                        monster[currentMap][i].update();
                    }
                    if(!monster[currentMap][i].alive)
                    {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }

            for(int i = 0; i < projectile[1].length; i++)
            {
                if(projectile[currentMap][i] != null)
                {
                    if(projectile[currentMap][i].alive)
                    {
                        projectile[currentMap][i].update();
                    }
                    if(!projectile[currentMap][i].alive)
                    {
                        projectile[currentMap][i] = null;
                    }
                }
            }
            for(int i = 0; i < iTile[1].length; i++)
            {
                if(iTile[currentMap][i] != null)
                {
                    iTile[currentMap][i].update();
                }
            }

        }
        if(gameState == pauseState)
        {

        }
    }

    // Metoda pentru desenarea jocului pe panoul de joc
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            ui.draw(g2); // Desenarea ecranului de titlu
        } else {
            // Desenarea hărții, obiectelor și a jucătorului
            tileM.draw(g2);

            if(currentMap == 0)
            {
                designM.draw(g2);

            }

            for(int i = 0; i < iTile[1].length; i++)
            {
                if(iTile[currentMap][i] != null)
                {
                    iTile[currentMap][i].draw(g2);
                }
            }

            entityList.add(player);
            for(int i = 0; i < npc[1].length; i++)
            {
                if(npc[currentMap][i] != null)
                {
                    entityList.add(npc[currentMap][i]);
                }
            }

            for(int i = 0; i < obj[1].length; i++)
            {
                if(obj[currentMap][i] != null)
                {
                    entityList.add(obj[currentMap][i]);
                }
            }

            for(int i = 0; i < monster[1].length; i++)
            {
                if(monster[currentMap][i] != null)
                {
                    entityList.add(monster[currentMap][i]);
                }
            }

            for(int i = 0; i < projectile[1].length; i++)
            {
                if(projectile[currentMap][i] != null)
                {
                    entityList.add(projectile[currentMap][i]);
                }
            }

//            for(int i = 0; i < particleList.size(); i++)
//            {
//                if(particleList.get(i) != null)
//                {
//                    entityList.add(particleList.get(i));
//                }
//            }

            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    return Integer.compare(e1.worldY,e2.worldY);
                }
            });

            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            entityList.clear();
            ui.draw(g2); // Desenarea interfeței utilizatorului
        }

        g2.dispose();
    }

    // Metoda pentru redarea muzicii
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    // Metoda pentru oprirea muzicii
    public void stopMusic() {
        music.stop();
    }

    // Metoda pentru redarea efectelor sonore
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
