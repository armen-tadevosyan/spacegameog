import javax.imageio.ImageIO;
import java.io.IOException;

public class Orb_Object extends SuperObject{
    GamePanel gp;
    @Override
    public void playerInteraction(Player player, GamePanel gp) {
        player.OrbCount++;
        gp.obj[0] = null;
    }
    public Orb_Object(GamePanel gp) {
        collision = false;
        name = "key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("Objects/orb.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
