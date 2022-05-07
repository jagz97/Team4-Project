package mancala;
/*
 * @author Jagjit Singh
 */

import javax.swing.*;
import java.awt.*;


/**
 * Strategy to draw the BoardLayouts
 */
public interface BoardLayout {
    void paintComponent(Graphics g);

    void pitsLabelB(Graphics2D g2);

    void pitsLabelA(Graphics2D g2);


}



