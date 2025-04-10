
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    GamePanel gp;
    public MouseHandler(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int code = e.getButton();
        int x = e.getPoint().x;
        int y = e.getPoint().y;
        int width = 1;
        int height = 1;
        Rectangle rect = new Rectangle(x,y,width,height);
        if (e.getClickCount() == 1) {
gp.gameState = gp.pauseState;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent event) {
if (event.getClickCount() != 0) {
    gp.gameState = gp.pauseState;
}
    }
}
