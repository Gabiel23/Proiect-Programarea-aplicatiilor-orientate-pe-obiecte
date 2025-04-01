package main;

import entity.Entity;

// Această clasă se ocupă de verificarea coliziunilor dintre entități și obiectele din joc.
public class CollisionChecker {
    GamePanel gp;

    // Constructorul clasei CollisionChecker
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    // Metoda pentru verificarea coliziunilor cu tile-urile hărții
    public void checkTile(Entity entity) {
        // Calcularea coordonatelor colțurilor entității în lumea jocului
        int entityLeftWorldX = entity.worldX + entity.solid_area.x;
        int entityRightWorldX = entity.worldX + entity.solid_area.x + entity.solid_area.width;
        int entityTopWorldY = entity.worldY + entity.solid_area.y;
        int entityBottomWorldY = entity.worldY + entity.solid_area.y + entity.solid_area.height;

        // Calcularea coloanelor și rândurilor din harta de tile-uri unde se află colțurile entității
        int entityLeftCol = entityLeftWorldX / gp.tile_size;
        int entityRightCol = entityRightWorldX / gp.tile_size;
        int entityTopRow = entityTopWorldY / gp.tile_size;
        int entityBottomRow = entityBottomWorldY / gp.tile_size;

        int tileNum1, tileNum2;

        String direction = entity.direction;
        if(entity.knockBack)
        {
            direction = entity.knockBackDirection;
        }

        // Verificarea coliziunii în funcție de direcția entității
        switch (direction) {
            case "sus":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tile_size;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "jos":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tile_size;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "dreapta":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tile_size;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "stanga":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tile_size;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    // Metoda pentru verificarea coliziunilor cu design-ul nivelului
    public void checkDesign(Entity entity) {
        // Calcularea coordonatelor colțurilor entității în lumea jocului
        int entityLeftWorldX = entity.worldX + entity.solid_area.x;
        int entityRightWorldX = entity.worldX + entity.solid_area.x + entity.solid_area.width;
        int entityTopWorldY = entity.worldY + entity.solid_area.y;
        int entityBottomWorldY = entity.worldY + entity.solid_area.y + entity.solid_area.height;

        // Calcularea coloanelor și rândurilor din harta de design unde se află colțurile entității
        int entityLeftCol = entityLeftWorldX / gp.tile_size;
        int entityRightCol = entityRightWorldX / gp.tile_size;
        int entityTopRow = entityTopWorldY / gp.tile_size;
        int entityBottomRow = entityBottomWorldY / gp.tile_size;

        int tileNum1, tileNum2;

        // Verificarea coliziunii în funcție de direcția entității
        switch (entity.direction) {
            case "sus":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tile_size;
                tileNum1 = gp.designM.mapDesignNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.designM.mapDesignNum[entityRightCol][entityTopRow];

                if ((tileNum1 >= 0 && gp.designM.design[tileNum1].collision) || (tileNum2 >= 0 && gp.designM.design[tileNum2].collision)) {
                    entity.collisionOn = true;
                }

                break;
            case "jos":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tile_size;
                tileNum1 = gp.designM.mapDesignNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.designM.mapDesignNum[entityRightCol][entityBottomRow];

                if ((tileNum1 >= 0 && gp.designM.design[tileNum1].collision) || (tileNum2 >= 0 && gp.designM.design[tileNum2].collision)) {
                    entity.collisionOn = true;
                }

                break;
            case "dreapta":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tile_size;
                tileNum1 = gp.designM.mapDesignNum[entityRightCol][entityTopRow];
                tileNum2 = gp.designM.mapDesignNum[entityRightCol][entityBottomRow];

                if ((tileNum1 >= 0 && gp.designM.design[tileNum1].collision) || (tileNum2 >= 0 && gp.designM.design[tileNum2].collision)) {
                    entity.collisionOn = true;
                }

                break;
            case "stanga":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tile_size;
                tileNum1 = gp.designM.mapDesignNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.designM.mapDesignNum[entityLeftCol][entityBottomRow];

                if ((tileNum1 >= 0 && gp.designM.design[tileNum1].collision) || (tileNum2 >= 0 && gp.designM.design[tileNum2].collision)) {
                    entity.collisionOn = true;
                }

                break;
        }
    }

    // Metoda pentru verificarea coliziunilor cu obiectele din joc
    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        // Iterarea prin toate obiectele din joc pentru verificarea coliziunii cu entitatea
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null) {
                entity.solid_area.x = entity.worldX + entity.solid_area.x;
                entity.solid_area.y = entity.worldY + entity.solid_area.y;

                gp.obj[gp.currentMap][i].solid_area.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solid_area.x;
                gp.obj[gp.currentMap][i].solid_area.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solid_area.y;

                // Verificarea coliziunii între entitate și obiectul curent
                switch (entity.direction) {
                    case "sus":
                        entity.solid_area.y -= entity.speed;
                        break;
                    case "jos":
                        entity.solid_area.y += entity.speed;
                        break;
                    case "stanga":
                        entity.solid_area.x -= entity.speed;
                         break;
                    case "dreapta":
                        entity.solid_area.x += entity.speed;
                        break;
                }

                if (entity.solid_area.intersects(gp.obj[gp.currentMap][i].solid_area)) {
                    if (gp.obj[gp.currentMap][i].collision) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }
                // Resetarea pozițiilor entității și obiectului
                entity.solid_area.x = entity.solidAreaDeafaultX;
                entity.solid_area.y = entity.solidAreaDeafaultY;
                gp.obj[gp.currentMap][i].solid_area.x = gp.obj[gp.currentMap][i].solidAreaDeafaultX;
                gp.obj[gp.currentMap][i].solid_area.y = gp.obj[gp.currentMap][i].solidAreaDeafaultY;
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[][] target)
    {
        int index = 999;

        String direction = entity.direction;
        if(entity.knockBack)
        {
            direction = entity.knockBackDirection;
        }

        // Iterarea prin toate obiectele din joc pentru verificarea coliziunii cu entitatea
        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {
                entity.solid_area.x = entity.worldX + entity.solid_area.x;
                entity.solid_area.y = entity.worldY + entity.solid_area.y;

                target[gp.currentMap][i].solid_area.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solid_area.x;
                target[gp.currentMap][i].solid_area.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solid_area.y;

                // Verificarea coliziunii între entitate și obiectul curent
                switch (direction) {
                    case "sus":
                        entity.solid_area.y -= entity.speed;
                        break;
                    case "jos":
                        entity.solid_area.y += entity.speed;
                        break;
                    case "stanga":
                        entity.solid_area.x -= entity.speed;
                        break;
                    case "dreapta":
                        entity.solid_area.x += entity.speed;
                        break;
                }
                if (entity.solid_area.intersects(target[gp.currentMap][i].solid_area)) {
                    if(target[gp.currentMap][i] != entity)
                    {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                // Resetarea pozițiilor entității și obiectului
                entity.solid_area.x = entity.solidAreaDeafaultX;
                entity.solid_area.y = entity.solidAreaDeafaultY;
                target[gp.currentMap][i].solid_area.x = target[gp.currentMap][i].solidAreaDeafaultX;
                target[gp.currentMap][i].solid_area.y = target[gp.currentMap][i].solidAreaDeafaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity)
    {
        boolean contactPlayer = false;
        entity.solid_area.x = entity.worldX + entity.solid_area.x;
        entity.solid_area.y = entity.worldY + entity.solid_area.y;

        gp.player.solid_area.x = gp.player.worldX + gp.player.solid_area.x;
        gp.player.solid_area.y = gp.player.worldY + gp.player.solid_area.y;

        // Verificarea coliziunii între entitate și obiectul curent
        switch (entity.direction) {
            case "sus":
                entity.solid_area.y -= entity.speed;
                 break;
            case "jos":
                entity.solid_area.y += entity.speed;
                break;
            case "stanga":
                entity.solid_area.x -= entity.speed;
                break;
            case "dreapta":
                entity.solid_area.x += entity.speed;
                break;
        }
        if (entity.solid_area.intersects(gp.player.solid_area)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        // Resetarea pozițiilor entității și obiectului
        entity.solid_area.x = entity.solidAreaDeafaultX;
        entity.solid_area.y = entity.solidAreaDeafaultY;
        gp.player.solid_area.x = gp.player.solidAreaDeafaultX;
        gp.player.solid_area.y = gp.player.solidAreaDeafaultY;

        return contactPlayer;
    }

}
