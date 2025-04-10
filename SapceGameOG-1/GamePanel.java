import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public int maxWorldCol = 64;
    public int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    int FPS = 60;
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int titleState = 4;
    TileManager tileManager = new TileManager(this);
KeyHandler keyHandle = new KeyHandler(this);
MouseHandler mouseHandle = new MouseHandler(this);
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyHandle);
    // List Of Objects to Render
    public SuperObject obj[] = new SuperObject[10];
    // List of NPC to Render
    public Entity npc[] = new Entity[10];
    Sound music = new Sound();
    Sound soundfx = new Sound();
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);


public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyHandle);
    this.setFocusable(true);
}
public void setupAssets() {
    assetSetter.setObject();
    assetSetter.setNPC();
    playMusic(0);
    stopMusic();
    gameState = titleState;
}

public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
}
    @Override
    public void run() {
    //Delta method
    double drawInterval = 1000000000/FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    int drawCount = 0;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    //sleep method
//    double drawInterval = 1000000000/FPS;
//    double nextDrawTime = System.nanoTime() + drawInterval;
//    // updates info such as character info and positions
//while(gameThread != null) {
////    System.out.println("Running");
//    update();
//    repaint();
//    try {
//    double remainingTime = nextDrawTime - System.nanoTime();
//    remainingTime = remainingTime/1000000;
//
//    if (remainingTime < 0) {
//        remainingTime = 0;
//    }
//    Thread.sleep((long) remainingTime);
//    nextDrawTime += drawInterval;
//
//} catch (InterruptedException e){
//        e.printStackTrace();
//    }
//}
    }
    public void update() {
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            if (gameState == pauseState) {
            }
        }
    }

        private void drawAssets(Graphics2D g2) {
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2);
                }
            }
        }

    public void paintComponent(Graphics g) {
        long drawStart = 0;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawStart = System.nanoTime();
    if (gameState != titleState) {
        tileManager.draw(g2);
        drawAssets(g2);
        player.draw(g2);
    }
        ui.draw(g2);
long drawEnd = 0;
drawEnd = System.nanoTime();
long timePassed = drawEnd - drawStart;
if (keyHandle.showTime == true) {
    g2.setColor(Color.WHITE);
    g2.drawString("Draw Time: " + timePassed, 200, 200);
    System.out.println("Draw Time: " + timePassed);
}
g2.dispose();
    }
    public void playMusic(int i) {
    music.setFile(i);
    music.play();
    music.loop();
    }
    public void stopMusic() {
    music.stop();
    }
    public void playSE(int i) {
    soundfx.setFile(i);
    soundfx.play();
    }
}
