package mancala;
/*
  @author Jagjit Singh
 * Concrete Stratergy1
 */
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class BoardLayout2 extends JPanel implements BoardLayout {

        private static final int x = 150;
        private static final int y = 200;
        private static final int width = 650;
        private static final int height = 400;
        private static final boolean raised = true;
    BoardLayout2(){
        this.setSize(1100,1100);
    }
    public void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(Color.orange);
                g2.fill3DRect(x, y, width, height, raised);


                g2.setFont(new Font("Times New Roman", Font.BOLD, 30));

                g2.setColor(Color.blue);
                g2.drawString("PLAYER A", height, width +25);
                g2.setColor(Color.BLACK);
                pitsLabelA(g2);




                g2.setColor(Color.RED);
                g2.drawString("PLAYER B", height, (width+25)/4);
                g2.setColor(Color.BLACK);
                pitsLabelB(g2);



                AffineTransform at = new AffineTransform();
                at.setToRotation(Math.toRadians(90),0,0);
                g2.setTransform(at);

                g2.drawString("MANCALA A", height-75, -(width+175));
                g2.drawString("MANCALA B", height-75, -(width-550));
                g2.dispose();



    }

    public void pitsLabelB(Graphics2D g2) {
        for (int i = 1, j = 1; i < 6; i++, j += 100) {

            g2.drawString("B" + i, width+10 - j, 550 / 2);


        }
    }

    public void pitsLabelA(Graphics2D g2) {
        for (int i = 5, j = 1; i >= 1; i--, j += 100) {

            g2.drawString("A" + i, width+10 - j, 550);

        }
    }

}

class Main{
    public static void main(String[] args) {
        //BoardLayout layout = new BoardLayout1();
        BoardLayout layout2 = new BoardLayout2();


        JButton button = new JButton("click me");
        button.setBackground(Color.BLACK);
        JPanel buttonp = new JPanel();
        buttonp.add(button);


        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add((Component) layout2, BorderLayout.CENTER);
        frame.add(buttonp,BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
