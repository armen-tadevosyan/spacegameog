import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.security.Key;

public class KeyHandler implements KeyListener{
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    public boolean showTime = false;
    public String lastKeyPressed;
    public GamePanel gp;
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
int code = e.getKeyCode();
if (gp.gameState == gp.playState) {
    if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
        upPressed = true;
    }
    if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
        downPressed = true;

    }
    if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
        leftPressed = true;

    }
    if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
        rightPressed = true;
    }
    if (code == KeyEvent.VK_Q) {
            gp.gameState = gp.pauseState;

    }
    if (code == KeyEvent.VK_ENTER) {
        enterPressed = true;
    }
    //teleport test
    //if (code == KeyEvent.VK_Y) {
        //gp.player.teleport(200,200);
    //}
    if (code == KeyEvent.VK_T) {
        if (showTime == false) {
            showTime = true;
        } else if (showTime == true) {
            showTime = false;
        }
    }
} else if (gp.gameState == gp.pauseState) {
    if (code == KeyEvent.VK_Q) {
        {
            gp.gameState = gp.playState;
        }
    }
} else if (gp.gameState == gp.dialogueState) {
    if (code == KeyEvent.VK_ENTER) {
        gp.gameState = gp.playState;
    }
} else if (gp.gameState == gp.titleState) {
    if (code == KeyEvent.VK_ENTER) {
        gp.gameState = gp.playState;
        gp.playMusic(0);
    }
}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
            lastKeyPressed = "up";
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
            lastKeyPressed = "down";
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
            lastKeyPressed = "left";
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
            lastKeyPressed = "right";
        }
    }
}
