import javax.imageio.ImageIO;
import java.io.IOException;

public class SpeedWedge_Object extends SuperObject{
    GamePanel gp;
    @Override
    public void playerInteraction(Player player, GamePanel gp) {
        player.speed += 2;
        gp.obj[2] = null;
    }
        public SpeedWedge_Object(GamePanel gp) {
            try {
                image = ImageIO.read(getClass().getResourceAsStream("Objects/SpeedWedge.png"));
                uTool.scaleImage(image, gp.tileSize, gp.tileSize);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
