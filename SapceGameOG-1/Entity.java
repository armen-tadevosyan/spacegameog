import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    GamePanel gp;
    public int worldx, worldy;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, up3, down3, left3, right3;
    BufferedImage image = null;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public boolean collisionOn = false;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public int actionCounter = 0;
    String dialogues[] = new String[20];
    public int dialogueNum = 0;
    public int dialogueCount = 0;
    public String lastDirection = "down";
    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    public void setAction() {}
    public void speak() {
        gp.ui.currentDialogue = dialogues[dialogueNum];
        dialogueNum++;
        if (dialogueNum > dialogueCount - 1) {
            dialogueNum--;
        }
    }
    public void update() {
        setAction();
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this,false);
        gp.collisionChecker.checkPlayer(this);
        if (!collisionOn) {
            switch (direction) {
                case "up": worldy -= speed; break;
                case "down":worldy += speed; break;
                case "left": worldx -= speed; break;
                case "right":worldx += speed; break;
            }

        }
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
    public void draw(Graphics2D g2) {
            int screenx = worldx - gp.player.worldx + gp.player.screenx;
            int screenxy= worldy - gp.player.worldy + gp.player.screeny;
            if (worldx + gp.tileSize > gp.player.worldx - gp.player.screenx &&
                    worldx - gp.tileSize < gp.player.worldx + gp.player.screenx &&
                    worldy + gp.tileSize> gp.player.worldy - gp.player.screeny &&
                    worldy - gp.tileSize < gp.player.worldy + gp.player.screeny) {
                if (gp.gameState != gp.pauseState) {
                    switch (direction) {
                        case "up":
                            if (spriteNum == 1) {
                                image = up1;
                            } else if (spriteNum == 2) {
                                image = up2;
                            }
                            break;
                        case "down":
                            if (spriteNum == 1d) {
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
                        case "up3": image = up3;
                        break;
                        case "down3": image = down3;
                        break;
                        case "left3": image = left3;
                        break;
                        case "right3": image = right3;
                        break;
                    }
                }
        g2.drawImage(image, screenx, screenxy, gp.tileSize, gp.tileSize, null);
            }

    }
    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaled = null;
        try {
            scaled = ImageIO.read(getClass().getResourceAsStream(imagePath));
            scaled = uTool.scaleImage(scaled, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaled;
    }
    public void teleport(int x, int y) {
        this.worldx = x;
        this.worldy = y;
    }
    public int countDialogues() {
        int thisCount = 0;
        for (String i:dialogues) {
            if (i != null) {
                thisCount++;
            }
        }
        return thisCount;
    }
    public void facePlayer(Player player) {
        switch (player.direction) {
            case "up" -> direction = "down3";
            case "down" -> direction = "up3";
            case "left" -> direction = "right3";
            case "right" -> direction = "left3";
        }
        }
}
