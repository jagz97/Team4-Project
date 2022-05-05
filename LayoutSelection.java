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

        JFrame frame = new JFrame();
        frame.setLayout(null);


        layout = new BoardLayout1();//panel of BoardLayout



        DrawPit pit = new DrawPit();
        pit.setBackground(Color.yellow);
        pit.setLocation(275,150);
        pit.setSize(650,250);

        JPanel newPanel = new JPanel();
        JLabel label2 = new JLabel("Player x turn");
        JButton button3 = new JButton("undo");
        JButton button4 = new JButton("end");
        newPanel.add(label2);
        newPanel.add(button3);
        newPanel.add(button4);




        JPanel panel = new JPanel();
        JLabel label = new JLabel("How many Stones per pit?");
        JButton button = new JButton("3");
        JButton button1 = new JButton("4");
        panel.add(label);
        panel.add(button);
        button.addActionListener(e -> {
            frame.remove(panel);
            frame.add(newPanel,BorderLayout.SOUTH);


            frame.validate();

        });
        panel.add(button1);

        frame.add(pit);
        frame.add((Component) layout); //add BoardLayout Panel to frame

        frame.add(panel);
        frame.setSize(1000,800);
        frame.setVisible(true);


        return frame;
    }


    public JFrame frame2() {
        //This is vertical layout currently not functional
        JFrame frame = new JFrame();
        frame.setLayout(null);

        layout = new BoardLayout2();





        DrawPit pit = new DrawPit();
        pit.setBackground(Color.yellow);
        pit.setLocation(150,275);
        pit.setSize(650,250);

        JPanel newPanel = new JPanel();
        JLabel label2 = new JLabel("Player x turn");
        JButton button3 = new JButton("undo");
        JButton button4 = new JButton("end");
        newPanel.add(label2);
        newPanel.add(button3);
        newPanel.add(button4);

        button3.addActionListener(e -> {
            //Undo button action listener here
        });

        button4.addActionListener(e -> {
            //end gama button action listener here

        });






        JPanel panel = new JPanel();
        JLabel label = new JLabel("How many Stones per pit?");
        JButton button = new JButton("3");
        JButton button1 = new JButton("4");
        panel.add(label);
        panel.add(button);
        button.addActionListener(e -> {
            frame.remove(panel);
            frame.add(newPanel);
            newPanel.setLocation(250,700);
            newPanel.setSize(500,100);

            frame.validate();

        });
        panel.add(button1);
        button1.addActionListener(e -> {
            // 4 stones per pit selection
        });

        panel.setSize(500,100);
        panel.setLocation(250,700);
        frame.add(panel);

        frame.add(pit);
        frame.add((Component) layout);

        frame.setSize(1000,800);
        frame.setVisible(true);

        return frame;
    }

}
