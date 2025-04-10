public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {
        autoObjectSetter(0,10,10,new Orb_Object(gp));
        autoObjectSetter(1,12,15,new OrbHolder_Object(gp));
        autoObjectSetter(2,20,25,new SpeedWedge_Object(gp));
    }
    private void autoObjectSetter(int index, int mapX, int mapY, SuperObject object) {
        gp.obj[index] = object;
        gp.obj[index].worldx = mapX * gp.tileSize;
        gp.obj[index].worldy = mapY * gp.tileSize;
    }
    public void setNPC() {
        gp.npc[0] = new NPC_MysteriousFigure(gp);
        gp.npc[0].worldx = 22 * gp.tileSize;
        gp.npc[0].worldy = 22 * gp.tileSize;
    }
    private void autoNPCSetter(int index, int mapX, int mapY, Entity npc) {
        gp.npc[index] = npc;
        gp.npc[index].worldx = mapX * gp.tileSize;
        gp.npc[index].worldy = mapY * gp.tileSize;
    }

}
