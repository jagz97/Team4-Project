package mancala;
/*
 * @author Jagjit Singh
 * Concrete Strategy 1
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class BoardLayout1 extends JPanel implements BoardLayout {

    private static final int x = 150;
    private static final int y = 200;
    private static final int width = 650;
    private static final int height = 400;
    private static final boolean raised = true;

    /**
     * Constructor for the BoardLayout
     */
    BoardLayout1(){
        this.setSize(1300,1100);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.PINK);
        g2.fill3DRect(x+100, y-10, width+50, height, raised);


        g2.setFont(new Font("Times New Roman", Font.BOLD, 30));

        g2.setColor(Color.MAGENTA);
        g2.drawString("PLAYER A", height+100, width );
        g2.setColor(Color.WHITE);
        pitsLabelA(g2);




        g2.setColor(Color.magenta);
        g2.drawString("PLAYER B", height+100, (width)/4);
        g2.setColor(Color.WHITE);
        pitsLabelB(g2);



        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(90),0,0);
        g2.setTransform(at);
        g2.setColor(Color.DARK_GRAY);
        g2.drawString("MANCALA A", height-100, -(width+325));
        g2.drawString("MANCALA B", height-100, -(width-450));
        g2.dispose();



    }

    /**
     * This method draws the labels for Player B pits
     * @param g2
     */
    public void pitsLabelB(Graphics2D g2) {
        for (int i = 1, j = 1; i <= 6; i++, j += 85) {

            g2.drawString("B" + i, width+150 - j, 550 / 2);


        }
    }

    /**
     * This method draws the labels for player A pits
     * @param g2
     */
    public void pitsLabelA(Graphics2D g2) {
        for (int i = 6, j = 1; i >= 1; i--, j += 85) {

            g2.drawString("A" + i, width+150 - j, 525);

        }
    }

}











