import java.util.Random;

public class NPC_MysteriousFigure extends Entity{
    int actionToggle = 1;
    public NPC_MysteriousFigure(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getNPCImage();
        setDialogue();
        dialogueCount = countDialogues();
    }
    public void setDialogue() {
        dialogues[0] = "AHAHAHHAA!";
        dialogues[1] = "-Oh hello there!";
        dialogues[2] = "Brug! My mom told me that I\n shouldn't talk to strangers.";
        dialogues[3] = "I miss my chickens!";
        dialogues[4] = "hello!";
        dialogues[5] = "Take a Risk!";
        dialogues[6] = "Have fun exploring!";
    }
    public void speak() {
        super.speak();
    }

    public void getNPCImage() {
        up1 = setup("npc/npc_up1.png.png");
        up2 = setup("npc/npc_up2.png.png");
        down1 = setup("npc/npc_down1.png.png");
        down2 = setup("npc/npc_down2.png.png");
        left1 = setup("npc/npc_left1.png.png");
        left2 = setup("npc/npc_left2.png.png");
        right1 = setup("npc/npc_right1.png.png");
        right2 = setup("npc/npc_right2.png.png");
        up3 = setup("npc/npc_up3.png.png");
        down3 = setup("npc/npc_down3.png");
        left3 = setup("npc/npc_left3.png.png");
        right3 = setup("npc/npc_right3.png.png");
    }
    @Override
    public void setAction() {
        if ((direction == "up3" || direction == "down3" || direction == "left3" || direction == "right3") && actionToggle == 1) {
            actionCounter = -60;
            actionToggle = 0;
        }
//        Random random2 = new Random();
  //      int random3 = random2.nextInt(10) + 1;
   //     actionCounter += random3;
        actionCounter++;
        if (actionCounter == 120) {
            actionToggle = 1;
            Random random = new Random();
            int i = random.nextInt(169) + 1;
            if (i <= 25) {
                collisionOn = false;
                direction = "up";
            } if (i > 25 && i <= 50) {
                collisionOn = false;
                direction = "down";
            } if (i > 50 && i <= 75) {
                collisionOn = false;
                direction = "left";
            } if (i > 75 && i <= 100) {
                collisionOn = false;
                direction = "right";
            } if (i > 100) {
                collisionOn = true;
                switch (lastDirection) {
                    case "up" -> direction = "up3";
                    case "down" -> direction = "down3";
                    case "left" -> direction = "left3";
                    case "right" -> direction = "right3";
                }
            }
            lastDirection = direction;
            actionCounter = 0;
        }
    }
}
