import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    KeyHandler keyHandle;
    public final int screenx;
    public final int screeny;
    public int OrbCount = 0;
    BufferedImage image = null;

    public Player(GamePanel gp, KeyHandler keyHandle) {
        super(gp);
        this.keyHandle = keyHandle;
        screenx = (gp.screenWidth/2) - (gp.tileSize/2);
        screeny = (gp.screenHeight/2)- (gp.tileSize/2);
        solidArea = new Rectangle();
        solidArea.x = 20;
        solidArea.y = 16;
        solidArea.width = 2;
        solidArea.height = 18;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }
    public boolean isAdjacentToNPC2(int npcIndex, boolean includeDiagonals) {
        boolean isAdjacent = false;

        // Ensure the NPC index is valid
        if (npcIndex < 0 || npcIndex >= gp.npc.length) {
            return isAdjacent;
        }

        Entity npc = gp.npc[npcIndex];

        // Calculate tile positions of the player and the NPC
        int playerTileX = worldx / gp.tileSize;
        int playerTileY = worldy / gp.tileSize;
        int npcTileX = npc.worldx / gp.tileSize;
        int npcTileY = npc.worldy / gp.tileSize;

        int dx = playerTileX - npcTileX;
        int dy = playerTileY - npcTileY;

        if (includeDiagonals) {
            isAdjacent = (Math.abs(dx) == 1 && Math.abs(dy) == 1);
        } else {
            isAdjacent = (dx == 0 && Math.abs(dy) == 1) || (Math.abs(dx) == 1 && dy == 0);
        }

        // Set NPC direction based on dx and dy
        if (isAdjacent) {
            if (dx == 1) {
                npc.direction = "right3";
            } else if (dx == -1) {
                npc.direction = "left3";
            } else if (dy == 1) {
                npc.direction = "down3";
            } else if (dy == -1) {
                npc.direction = "up3";
            } else if (dx == 1 && dy == 1) {
                npc.direction = "down_right"; // Replace with your desired direction
            } else if (dx == -1 && dy == 1) {
                npc.direction = "down_left"; // Replace with your desired direction
            } else if (dx == 1 && dy == -1) {
                npc.direction = "up_right"; // Replace with your desired direction
            } else if (dx == -1 && dy == -1) {
                npc.direction = "up_left"; // Replace with your desired direction
            }
        }

        return isAdjacent;
    }


    public boolean isAdjacentToNPC(int npcIndex) {
        boolean isAdjacent = false;
        // Ensure the NPC index is valid
        if (npcIndex < 0 || npcIndex >= gp.npc.length) {
            return isAdjacent;
        }

        Entity npc = gp.npc[npcIndex];

        // Calculate the tile positions of the player and the NPC
        int playerTileX = worldx / gp.tileSize;
        int playerTileY = worldy / gp.tileSize;
        int npcTileX = npc.worldx / gp.tileSize;
        int npcTileY = npc.worldy / gp.tileSize;

        if ((playerTileX == npcTileX && playerTileY == npcTileY - 1) ) {
    gp.npc[npcIndex].direction = "up3";
    isAdjacent = true;
} else if ((playerTileX == npcTileX && playerTileY == npcTileY + 1)) {
    gp.npc[npcIndex].direction = "down3";
            isAdjacent = true;
} else if ((playerTileX == npcTileX - 1 && playerTileY == npcTileY)) {
    gp.npc[npcIndex].direction = "left3";
            isAdjacent = true;
} else if ((playerTileX == npcTileX + 1 && playerTileY == npcTileY)) {
    gp.npc[npcIndex].direction = "right3";
            isAdjacent = true;
}
        return isAdjacent;
    }
    public int checkCollisionWithNPC() {
        for (int i = 0; i < gp.npc.length; i++) {
            if (gp.npc[i] != null && isAdjacentToNPC2(i,false)){
               // gp.npc[i].facePlayer(this);
                return i; // Return the index of the adjacent NPC
            }
            if (gp.collisionChecker.checkEntity(this , gp.npc) != 999) {
                gp.npc[i].facePlayer(this);
                return i;
            }
        }
        return 999; // Return -1 if no adjacent NPC is found
    }


    public void pickUpObject(int i) {
        if (i != 999) {
            gp.obj[i].playerInteraction(this, gp);
        }

    }
    public void interactNPC(int i) {
        if (i != 999) {
            if (gp.keyHandle.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyHandle.enterPressed = false;
    }

    public void setDefaultValues() {
        worldx = gp.tileSize * 13;
        worldy = gp.tileSize * 20;

        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        up1 = setup("Images/New Piskel-3.png.png");
        up2 = setup("Images/New Piskel-4.png.png");
        down1 = setup("Images/New Piskel-1.png.png");
        down2 = setup("Images/New Piskel-2.png.png");
        left1 = setup("Images/New Piskel-5.png.png");
        left2 = setup("Images/New Piskel-6.png.png");
        right1 = setup("Images/New Piskel-7.png.png");
        right2 = setup("Images/New Piskel-8.png.png");
        up3 = setup("Images/New Piskel-11.png.png");
        down3 = setup("Images/New Piskel-10.png.png");
        left3 = setup("Images/New Piskel-12.png.png");
        right3 = setup("Images/New Piskel-13.png.png");

    }
    public void update() {
        if (keyHandle.downPressed == true || keyHandle.leftPressed == true || keyHandle.rightPressed == true || keyHandle.upPressed == true || keyHandle.enterPressed == true) {
            if (keyHandle.upPressed == true) {
                direction = "up";
                worldy -= speed;
            } else if (keyHandle.downPressed == true) {
                direction = "down";
                worldy += speed;
            } else if (keyHandle.leftPressed == true) {
                direction = "left";
                worldx -= speed;
            } else if (keyHandle.rightPressed == true) {
                direction = "right";
                worldx += speed;
            } else if (keyHandle.enterPressed == true) {
            }
            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            int objIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            int npcIndex2 = checkCollisionWithNPC();
            interactNPC(npcIndex2);
            if (collisionOn == true && keyHandle.enterPressed == false) {
                switch (direction) {
                    case "up": worldy += speed; break;
                    case "down":worldy -= speed; break;
                    case "left": worldx += speed; break;
                    case "right":worldx -= speed; break;
                }

            }
            keyHandle.enterPressed = false;
            spriteCounter++;
            if (spriteCounter >= 20) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {
        if (gp.gameState != gp.pauseState && gp.gameState != gp.titleState) {
            if ((keyHandle.lastKeyPressed != null) && !(keyHandle.downPressed == true || keyHandle.leftPressed == true || keyHandle.rightPressed == true || keyHandle.upPressed == true)) {
                switch (keyHandle.lastKeyPressed) {
                    case "up" -> image = up3;
                    case "down" -> image = down3;
                    case "left" -> image = left3;
                    case "right" -> image = right3;
                }
            } else {
                if (gp.gameState == gp.playState)
                switch (direction) {
                    case "up":
                        if (spriteNum == 1) {
                            image = up1;
                        } else if (spriteNum == 2) {
                            image = up2;
                        }
                        break;
                    case "down":
                        if (spriteNum == 1) {
                            image = down1;
                        } else if (spriteNum == 2) {
                            image = down2;
                        }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            image = left1;
                        } else if (spriteNum == 2) {
                            image = left2;
                        }
                        break;
                    case "right":
                        if (spriteNum == 1) {
                            image = right1;
                        } else if (spriteNum == 2) {
                            image = right2;
                        }
                        break;
                    case "up3":
                        image = up3;
                    break;
                    case "down3": image = down3;
                    break;
                    case "left3":image = left3;
                    break;
                    case "right3":image = right3;
                    break;
                }
            }

        }g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null);
    }

}
