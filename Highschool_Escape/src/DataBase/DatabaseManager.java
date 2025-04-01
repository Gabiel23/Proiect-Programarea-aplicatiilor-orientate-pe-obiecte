package DataBase;

import main.GamePanel;
import main.ImageSetupException;
import object.Axe;
import object.Key;
import object.Shield;
import object.Sword;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private String databaseName;
    GamePanel gp;

    public DatabaseManager(String databaseName, GamePanel gp) {
        this.databaseName = databaseName;
        this.gp = gp;
    }

    public void createPlayerTable(String tableName, ArrayList<String> fields) {
        conn = null;
        stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (";
            for (int i = 0; i < fields.size(); i += 2) {
                sql += fields.get(i) + " " + fields.get(i + 1) + " NOT NULL, ";
            }
            sql = sql.substring(0, sql.length() - 2);
            sql += ");";
            stmt.execute(sql);
            stmt.close();
            conn.close();
            System.out.println("Tabelul " + tableName + " a fost creat cu succes.");
        } catch (ClassNotFoundException e) {
            System.out.println("Eroare: Class not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Eroare: Eroare la crearea tabelului.");
            e.printStackTrace();
        }
    }

    public void insertPlayerTable(String tableName, ArrayList<String> fields, ArrayList<String> values) {
        conn = null;
        stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            StringBuilder sql = new StringBuilder("INSERT OR IGNORE INTO " + tableName + " (");
            for(int i = 0; i < fields.size(); ++i) {
                sql.append("\"").append(fields.get(i)).append("\"").append(", ");
            }
            sql = new StringBuilder(sql.substring(0, sql.length() - 2));
            sql.append(") VALUES (");
            for (int i = 0; i < values.size(); ++i) {
                sql.append(STR."\"\{values.get(i)}\", ");
            }
            sql = new StringBuilder(sql.substring(0, sql.length() - 2));
            sql.append("); UPDATE ").append(tableName).append(" SET ");
            for(int i = 0; i < fields.size(); ++i) {
                sql.append("\"").append(fields.get(i)).append("\"").append("=").append("\"").append(values.get(i)).append("\"").append(", ");
            }

            sql = new StringBuilder(sql.substring(0, sql.length() - 2));
            stmt.executeUpdate(sql.toString());
            conn.commit();
            stmt.close();
            conn.close();
            System.out.println("Update la tabelul " + tableName + " cu succes.");
        } catch (ClassNotFoundException e) {
            System.out.println("Eroare: Class not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Eroare: Eroare la inserarea in tabel.");
            e.printStackTrace();
        }
    }
    public void selectPlayerTable(String tableName){
        conn = null;
        stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + tableName + ";");

            // Variables
            int playerX = 0;
            int playerY = 0;
            int currentMap = 0;
            String direction = "";
            int life = 0;
            String inventory = "";
            String monsters = "";
            String npc = "";
            String objects = "";


            while(rs.next()) {
                playerX = Integer.parseInt(rs.getString("PLAYERPOSX"));
                playerY = Integer.parseInt(rs.getString("PLAYERPOSY"));
                currentMap = rs.getInt("CURRENTMAP");
                direction = rs.getString("DIRECTION");
                life = rs.getInt("LIFE");
                inventory = rs.getString("INVENTORY");
                monsters = rs.getString("MONSTERS");
                npc = rs.getString("NPC");
                objects = rs.getString("OBJECTS");

            }
            gp.player.worldX = playerX;
            gp.player.worldY = playerY;
            gp.currentMap = currentMap;
            gp.player.direction = direction;
            gp.player.life = life;

            // Parse
            String[] arrayInventory = inventory.split(", ");
            for(int i = 0; i < arrayInventory.length; ++i) {
                switch(arrayInventory[i]) {
                    case "axe":
                        gp.player.inventory.add(new Axe(gp));
                        break;
                    case "key":
                        gp.player.inventory.add(new Key(gp));
                        break;

                }
            }
            String[] arrayMonsters = monsters.split(", ");
            int k = 0;
            for(int i = 0; i < gp.monster.length; ++i) {
                System.out.println(i);
                for(int j = 0; j < gp.monster[i].length; ++j) {
                    System.out.println(j);
                    if(gp.monster[i][j] != null) {
                        gp.monster[i][j].worldX = Integer.parseInt(arrayMonsters[k]);
                        gp.monster[i][j].worldY = Integer.parseInt(arrayMonsters[k + 1]);
                        gp.monster[i][j].life = Integer.parseInt(arrayMonsters[k + 2]);
                    }
                    System.out.println(k);
                    k += 3;

                }
            }
            String[] arrayNPC = npc.split(", ");
            k = 0;
            for(int i = 0; i < gp.npc.length; ++i) {
                for(int j = 0; j < gp.npc[i].length; ++j) {
                    if(gp.npc[i][j] != null) {
                        gp.npc[i][j].worldX = Integer.parseInt(arrayNPC[k]);
                        gp.npc[i][j].worldY = Integer.parseInt(arrayNPC[k + 1]);
                    }
                    k += 2;
                }
            }
            String[] arrayObjects = objects.split(", ");
            k = 0;
            for(int i = 0; i < gp.obj.length; ++i) {
                for(int j = 0; j < gp.obj[i].length; ++j) {
                    if(gp.obj[i][j] != null) {
                        gp.obj[i][j].worldX = Integer.parseInt(arrayObjects[k]);
                        gp.obj[i][j].worldY = Integer.parseInt(arrayObjects[k + 1]);
                    }
                    k += 2;
                }
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Eroare: Class not found.");
            e.printStackTrace();
        } catch(SQLException e) {
            System.out.println("Eroare: Eroare la extragerea datelor din tabel.");
            e.printStackTrace();
        } catch (ImageSetupException e) {
            throw new RuntimeException(e);
        }
    }
}