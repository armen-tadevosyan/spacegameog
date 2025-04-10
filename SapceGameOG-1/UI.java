import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    Font font;
    Graphics2D g2;
    GamePanel gp;
    public String message = "";
    public boolean messageOn = false;
    public String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("Fonts/Roman SD.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        // Debugging statement
//        System.out.println("Game State: " + gp.gameState);

        // playState
        if (gp.gameState == gp.playState) {
            // Add drawing code for playState if necessary
        }

        // pauseState
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // dialogueState
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }
    public void drawTitleScreen() {
        Color color = new Color(0,0,0,200);
        g2.setColor(color);
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        String text2 = "PLANETS";
        String text3 = "Press Enter";
        int x = getXforCenteredText(text2);
        int y = (gp.screenHeight / 3)/2;
        int y2 = gp.screenHeight/3;
        g2.setColor(Color.GRAY);
        g2.drawString(text2, x+5,y+5);
        g2.setColor(Color.WHITE);
        g2.drawString(text2, x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
        int x2 = getXforCenteredText(text3);
        g2.drawString(text3, x2, y2);
        g2.drawImage(gp.player.down3.getScaledInstance(gp.tileSize,gp.tileSize,1),gp.player.screenx,gp.player.screeny,null);
    }

    public void drawDialogueScreen() {
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        // Debugging statement
//        System.out.println("Drawing Dialogue Screen");

        drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,24F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200); // Adjust alpha for transparency if needed
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.WHITE);
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
