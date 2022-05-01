package mancala;
/*
  @author Jagjit Singh
 * Stragery to draw a boad Layout
 */

import javax.swing.*;
import java.awt.*;



public interface BoardLayout {
    void paintComponent(Graphics g);

    void pitsLabelB(Graphics2D g2);

    void pitsLabelA(Graphics2D g2);


}

