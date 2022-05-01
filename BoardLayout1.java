package mancala;

/*
  @author Jagjit Singh
 * Concrete Stratergy1
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class BoardLayout1 extends JPanel implements BoardLayout{


    private static final int x = 250;
    private static final int y = 100;
    private static final int width = 400;
    private static final int height = 600;
    private static final boolean raised = true;

            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(Color.GRAY);
                g2.fill3DRect(x, y, width, height, raised);


                g2.setFont(new Font("Times New Roman", Font.BOLD, 30));
                g2.setColor(Color.BLACK);
                g2.drawString("MANCALA A", x+100, y-10);
                g2.drawString("MANCALA B", x+100, y*7+ 25);

                g2.setColor(Color.blue);
                AffineTransform at = new AffineTransform();
                at.setToRotation(Math.toRadians(90));
                g2.setTransform(at);
                g2.drawString("PLAYER A", x+50,-(y*7)+ 25);


                pitsLabelA(g2); // player A pits label
                g2.setColor(Color.RED);
                g2.drawString("PLAYER B", x+50,-(y*2));
                pitsLabelB(g2); // player B pits label


            }

            public void pitsLabelB(Graphics2D g2) {

                g2.setColor(Color.BLACK);
                for (int i=5,j=1; i >= 1; i--,j +=100) {

                    g2.drawString("B"+i,(x*2)+75-j,-(y*6/2));

                }
            }

           public void pitsLabelA(Graphics2D g2) {
                g2.setColor(Color.BLACK);
                for (int i=1,j = 1; i<6;i++,j +=100) {

                    g2.drawString("A" + i,(x*2)+75-j,-(y*6));

                }


            }


    }










