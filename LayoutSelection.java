package mancala;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jagjit Singh
 * Context of Stratergy Layout
 **/

class LayoutSelection extends JFrame {

    private BoardLayout layout;


    public JFrame frame1() {
        // Buttons to be added here according to this frame selection
        JFrame frame = new JFrame();

        JButton button = new JButton("Buttons Panel to be added");
        JButton button1 = new JButton("Buttons Panel to be added");

        JPanel panel = new JPanel();
        panel.add(button);

        JPanel panel1 = new JPanel();
        panel1.add(button1);



        layout = new BoardLayout1();
        frame.add((Component) layout,BorderLayout.CENTER);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(panel1,BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setSize(900, 900);

        return frame;
    }


    public JFrame frame2() {
        //Buttons to be added here according to this frame Selection
        JFrame frame = new JFrame();

        layout = new BoardLayout2();
        frame.add((Component) layout);

        frame.setSize(1000,800);
        frame.setVisible(true);

        return frame;
    }

}
