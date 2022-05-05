package mancala;

import javax.swing.*;
import java.awt.*;

class Mian {

    public static void main(String[] args) {

        LayoutSelection layout = new LayoutSelection();
        JPanel btnPanel = new JPanel();
        JFrame frame = new JFrame();








        JButton buttonL1 = new JButton("BoardLayout1");
        buttonL1.addActionListener(e ->{
            layout.frame1().setResizable(false);

        });

        JButton buttonL2 = new JButton("BoardLayout");
        buttonL2.addActionListener(e -> layout.frame2().setResizable(false));

        btnPanel.add(buttonL1);
        btnPanel.add(buttonL2);


        frame.add(btnPanel);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
