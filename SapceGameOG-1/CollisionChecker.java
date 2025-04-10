public class CollisionChecker {
    public GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;
        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                // Calculate solidArea positions
                int entitySolidAreaX = entity.worldx + entity.solidArea.x;
                int entitySolidAreaY = entity.worldy + entity.solidArea.y;
                int objectSolidAreaX = target[i].worldx + target[i].solidArea.x;
                int objectSolidAreaY = target[i].worldy + target[i].solidArea.y;

                // Set the solidArea positions
                entity.solidArea.x = entitySolidAreaX;
                entity.solidArea.y = entitySolidAreaY;
                target[i].solidArea.x = objectSolidAreaX;
                target[i].solidArea.y = objectSolidAreaY;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if (entity.solidArea.intersects(target[i].solidArea)) {
                    entity.collisionOn = true;
                        index = i;
                    }

                // Reset the solidArea positions
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    public void checkPlayer(Entity entity) {
        int entitySolidAreaX = entity.worldx + entity.solidArea.x;
        int entitySolidAreaY = entity.worldy + entity.solidArea.y;
        int objectSolidAreaX = gp.player.worldx + gp.player.solidArea.x;
        int objectSolidAreaY = gp.player.worldy + gp.player.solidArea.y;

        // Set the solidArea positions
        entity.solidArea.x = entitySolidAreaX;
        entity.solidArea.y = entitySolidAreaY;
        gp.player.solidArea.x = objectSolidAreaX;
        gp.player.solidArea.y = objectSolidAreaY;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }

        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
        }

        // Reset the solidArea positions
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

    }

    public int checkObject(Entity entity, Boolean player) {
        int index = 999;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                // Calculate solidArea positions
                int entitySolidAreaX = entity.worldx + entity.solidArea.x;
                int entitySolidAreaY = entity.worldy + entity.solidArea.y;
                int objectSolidAreaX = gp.obj[i].worldx + gp.obj[i].solidArea.x;
                int objectSolidAreaY = gp.obj[i].worldy + gp.obj[i].solidArea.y;

                // Set the solidArea positions
                entity.solidArea.x = entitySolidAreaX;
                entity.solidArea.y = entitySolidAreaY;
                gp.obj[i].solidArea.x = objectSolidAreaX;
                gp.obj[i].solidArea.y = objectSolidAreaY;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                    if (gp.obj[i].collision) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }

                // Reset the solidArea positions
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultx;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaulty;
            }
        }
        return index;
    }

    public void checkTile(Entity entity) {
        int leftWorldX = entity.worldx + entity.solidArea.x;
        int rightWorldX = entity.worldx + entity.solidArea.x + entity.solidArea.width;
        int topWorldY = entity.worldy + entity.solidArea.y;
        int bottomWorldY = entity.worldy + entity.solidArea.y + entity.solidArea.height;

        int leftCol = leftWorldX / gp.tileSize;
        int rightCol = rightWorldX / gp.tileSize;
        int topRow = topWorldY / gp.tileSize;
        int bottomRow = bottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                topRow = (topWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumber[leftCol][topRow];
                tileNum2 = gp.tileManager.mapTileNumber[rightCol][topRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "down":
                bottomRow = (bottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumber[leftCol][bottomRow];
                tileNum2 = gp.tileManager.mapTileNumber[rightCol][bottomRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "left":
                leftCol = (leftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumber[leftCol][topRow];
                tileNum2 = gp.tileManager.mapTileNumber[leftCol][bottomRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case "right":
                rightCol = (rightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNumber[rightCol][topRow];
                tileNum2 = gp.tileManager.mapTileNumber[rightCol][bottomRow];
                if (gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
