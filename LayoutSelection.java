package mancala;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * @author Jagjit Singh
 * Context of Stratergy Layout
 **/

class LayoutSelection extends JFrame implements ChangeListener {

    private BoardLayout layout;
    BoardModel model = new BoardModel();
    JLabel display;

    /**
     * Executes the First Strategy
     * Contains the Controller and View
     *
     * @return the view and controller
     */
    public JFrame frame2() {

        JFrame frame = new JFrame();
        frame.setLayout(null);

        layout = new BoardLayout2();

        display = new JLabel();
        display.setLocation(500, 0);
        display.setSize(250, 50);

        PitPanel pit = new PitPanel(model);
        pit.setBackground(Color.yellow);
        pit.setLocation(250, 300);
        pit.setSize(700, 175);

        JPanel newPanel = new JPanel();
        JLabel label2 = new JLabel("Player x turn");
        JButton button3 = new JButton("undo");

        newPanel.add(label2);
        newPanel.add(button3);

        button3.addActionListener(e -> {
            model.undo();
        });

        JPanel panel = new JPanel();
        JLabel label = new JLabel("How many Stones per pit?");
        JButton button = new JButton("3");
        JButton button1 = new JButton("4");
        panel.add(label);
        panel.add(button);
        button.addActionListener(e -> {
            //three stone per pit selection button
            display.setText("Player A's Turn");
            frame.remove(panel);
            frame.add(newPanel);
            newPanel.setLocation(300, 700);
            newPanel.setSize(500, 100);

            frame.validate();
            model.fillInitialBoard(3);

        });
        panel.add(button1);
        button1.addActionListener(e -> {
            display.setText("Player A's Turn");
            frame.remove(panel);
            frame.add(newPanel);
            newPanel.setLocation(300, 700);
            newPanel.setSize(500, 100);

            frame.validate();
            model.fillInitialBoard(4);
        });

        panel.setSize(500, 100);
        panel.setLocation(250, 700);
        frame.add(display);
        frame.add(panel);
        model.addListener(this);
        frame.add(pit);
        frame.add((Component) layout);
        frame.setSize(1200, 800);
        frame.setVisible(true);

        return frame;
    }


    /**
     * Executes the First Strategy
     * Contains the Controller and View
     *
     * @returns the view and controller
     */
    public JFrame frame1() {

        JFrame frame = new JFrame();
        frame.setLayout(null);

        layout = new BoardLayout1();

        display = new JLabel();
        display.setLocation(500, 0);
        display.setSize(250, 50);


        PitPanel pit = new PitPanel(model);
        pit.setBackground(Color.yellow);
        pit.setLocation(250, 300);
        pit.setSize(700, 175);

        JPanel newPanel = new JPanel();

        JButton button3 = new JButton("undo");
        newPanel.add(button3);
        button3.addActionListener(e -> {
            model.undo();
        });

        JPanel panel = new JPanel();
        JLabel label = new JLabel("How many Stones per pit?");
        JButton button = new JButton("3");
        JButton button1 = new JButton("4");
        panel.add(label);
        panel.add(button);
        button.addActionListener(e -> {
            //three stone per pit selection button
            display.setText("Player A's Turn");
            frame.remove(panel);
            frame.add(newPanel);
            newPanel.setLocation(300, 700);
            newPanel.setSize(500, 100);

            frame.validate();
            model.fillInitialBoard(3);

        });
        panel.add(button1);
        button1.addActionListener(e -> {
            display.setText("Player A's Turn");
            frame.remove(panel);
            frame.add(newPanel);
            newPanel.setLocation(300, 700);
            newPanel.setSize(500, 100);

            frame.validate();
            model.fillInitialBoard(4);
        });

        panel.setSize(500, 100);
        panel.setLocation(250, 700);
        frame.add(display);
        frame.add(panel);
        model.addListener(this);

        frame.add(pit);
        frame.add((Component) layout);

        frame.setSize(1200, 800);
        frame.setVisible(true);

        return frame;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        display.setText(model.getMessage());

    }


}
