package MenuState;

import MenuStateEnum.Commandnumm;
import main.GamePanel;
import main.GameState;

import java.awt.*;

public class Choose_Character extends Menu {

    public Choose_Character(GamePanel gp) {
        super(gp);
        commandnum = Commandnumm.FirstCharackter;
    }
    public void draw(Graphics2D g2) {

        this.g2 = g2;

        if(gp.gs == GameState.Choose_Character) {
            drawScrean();
        }
    }

    private void drawScrean() {
        g2.setColor(new Color(49, 74, 102));
        g2.fillRect(0, 0, gp.screenwidth, gp.screenheight);
        //g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
        SetFont();

        String text = "Choose Your Character";
        int textX = getXforCentredText(text);
        int textY = gp.tilesize * 3;

        //Text Shadow
        g2.setColor(new Color(0, 0, 0));
        g2.drawString(text, textX + 5, textY + 5);

        //main.Main Text
        g2.setColor(new Color(240,182,118));
        g2.drawString(text, textX, textY);


        if(commandnum != Commandnumm.FirstCharackter){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
        }
        g2.drawImage(gp.utool.PlayerBoy[0],gp.tilesize*5,gp.tilesize*5,100,100,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));

        if(commandnum != Commandnumm.SecondCharackter){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
        }
        g2.drawImage(gp.utool.PlayerGirl[0],gp.tilesize*8,gp.tilesize*5,100,100,null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));



        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "Return to Main Menu";
        textX = getXforCentredText(text);
        textY += gp.tilesize*7;
        g2.drawString(text, textX, textY);
        if(commandnum != Commandnumm.Main_Menu) {
            g2.setColor(new Color(240,182,118));
            g2.drawString(text, textX, textY);
        } else {
            g2.setColor(new Color(66, 222, 222));
            g2.drawString(text, textX, textY);
        }
    }
}
