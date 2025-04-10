import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage og, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, og.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(og, 0 ,0, width, height, null);
        g2.dispose();
        return scaledImage;

    }

}
