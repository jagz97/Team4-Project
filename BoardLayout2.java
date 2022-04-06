import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class BoardLayout2 extends JPanel {

        private static final int x = 150;
        private static final int y = 200;
        private static final int width = 650;
        private static final int height = 400;
        private static final boolean raised = true;

    public void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(Color.orange);
                g2.fill3DRect(x, y, width, height, raised);


                g2.setFont(new Font("Times New Roman", Font.BOLD, 30));

                g2.setColor(Color.blue);
                g2.drawString("PLAYER A", 400, 675);
                g2.setColor(Color.BLACK);
                pitsLabelA(g2);


                g2.setColor(Color.RED);
                g2.drawString("PLAYER B", 400, 675/4);
                g2.setColor(Color.BLACK);
                pitsLabelB(g2);


                AffineTransform at = new AffineTransform();
                at.setToRotation(Math.toRadians(90));
                g2.setTransform(at);

                g2.drawString("MANCALA A", 325, -825);
                g2.drawString("MANCALA B", 325, -100);



    }

    private void pitsLabelB(Graphics2D g2) {
        for (int i = 1, j = 1; i < 6; i++, j += 100) {

            g2.drawString("B" + i, 660 - j, 550 / 2);


        }
    }

    private void pitsLabelA(Graphics2D g2) {
        for (int i = 5, j = 1; i >= 1; i--, j += 100) {

            g2.drawString("A" + i, 660 - j, 550);

        }
    }

}
