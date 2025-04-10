import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;

    public String mapPath;

    int mapTileNumber[][];
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
        mapTileNumber = new int[gp.maxWorldCol][gp.maxWorldRow];
        changeMap("Maps/Map2.txt");
        loadMap(mapPath);
    }
    public void changeMap(String mapPath) {
        this.mapPath = mapPath;
    }
    public void getTileImage() {
            setup(2, "Tiles/brick.png", false);
            setup(3, "Tiles/medieval_wooden_pathway.png", true);
            setup(0,"Tiles/grass1.png", false);
            setup(1,"Tiles/tree.png", true);
    }
    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String mapname) {
        try {
            InputStream map = getClass().getResourceAsStream(mapname);
            BufferedReader br = new BufferedReader(new InputStreamReader(map));
            for (int row = 0; row < gp.maxWorldRow; row++) {
                String line = br.readLine();
                String[] tokens = line.split(" ");
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    mapTileNumber[col][row] = Integer.parseInt(tokens[col]);
                }
            }
br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {

    int worldCol = 0;
    int worldRow = 0;
    while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
        int tileNum = mapTileNumber[worldCol][worldRow];
        int worldx = worldCol * gp.tileSize;
        int worldy = worldRow * gp.tileSize;
        int screenx = worldx - gp.player.worldx + gp.player.screenx;
        int screenxy= worldy - gp.player.worldy + gp.player.screeny;
        if (worldx + gp.tileSize > gp.player.worldx - gp.player.screenx &&
                worldx - gp.tileSize < gp.player.worldx + gp.player.screenx &&
                worldy + gp.tileSize> gp.player.worldy - gp.player.screeny &&
        worldy - gp.tileSize < gp.player.worldy + gp.player.screeny) {
            g2.drawImage(tile[tileNum].image, screenx, screenxy, gp.tileSize, gp.tileSize, null);
        }
        worldCol++;
        if (worldCol == gp.maxWorldCol) {
            worldCol = 0;
            worldRow++;
        }
    }

    }
}
