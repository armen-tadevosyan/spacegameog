import javax.imageio.ImageIO;
import java.io.IOException;

public class OrbHolder_Object extends SuperObject{
    GamePanel gp;
    public boolean active = false;
    @Override
    public void playerInteraction(Player player, GamePanel gp) {
        if (player.OrbCount != 0) {
            try {
                image = ImageIO.read(getClass().getResourceAsStream("Objects/orbHolding-1.png.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.OrbCount--;
            active = true;
        }
    }
    public OrbHolder_Object(GamePanel gp) {
        name = "OrbHolder";
        try {
                image = ImageIO.read(getClass().getResourceAsStream("Objects/orbHolder-2.png.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
