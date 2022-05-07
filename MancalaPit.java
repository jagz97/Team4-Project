package mancala;
/**
 * @Author Surafel Abebe, Wilson
 *
 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import javax.swing.*;

public class MancalaPit extends JPanel {

	private BoardModel model;
	private int currentStoneCount;
	private int prevStoneCount;
	private MyListener listener;
	private int pitType;

	private static final int width = 80;
	private static final int height = 80;

	/**
	 * Constructor for the Mancala Pits
	 * @param x
	 * @param model takes the model
	 * @param type different type of pits
	 */
	MancalaPit(int x, BoardModel model, int type) {
		pitType = type;
		this.model = model;
		currentStoneCount = x;
		prevStoneCount = x;
		setPreferredSize(new Dimension(width, height));
	}

	/**
	 * Sets the type of pit to be drawn
	 * @param x
	 */
	public void setType(int x) {
		pitType = x;
	}

	/**
	 * Gets the selected type of pit
	 * @return the selected pit type
	 */
	public int getType() {
		return pitType;
	}

	/**
	 * Sets the pit's current stone count to 0 after saving it's previous state.
	 */

	public void clear() {
		prevStoneCount = currentStoneCount;
		currentStoneCount = 0;
		repaint();
	}

	/**
	 * Clears the stones in the pit
	 */
	public void clearLeavePrev() {
		currentStoneCount = 0;
	}

	/**
	 * Attaches mouse listener to pits
	 */
	public void addListener() {
		MyListener listeners = new MyListener();
		listener = listeners;
		addMouseListener(listeners);
	}

	/**
	 * Removes mouse listener accordingly from the pits
	 */
	public void removeListener() {
		this.removeMouseListener(listener);
		listener = null;
	}

	/**
	 * Inner class for MouseListener Events
	 */
	private class MyListener extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			MancalaPit[] pits = model.getModel();
			int i = 0;
			while (i < pits.length && pits[i] != MancalaPit.this)
				i++;
			model.move(i);
		}
	}

	/**
	 * Sets the pit's current stone count after saving it's previous state.
	 *
	 * @param x - number of stones in the pit after the change
	 */

	public void setCurrentStone(int x) {
		prevStoneCount = currentStoneCount;
		currentStoneCount = x;
		repaint();
	}

	/**
	 * Returns the current stone count of the pit.
	 *
	 * @return - the current stone count
	 */

	public int getCurrentStone() {
		return currentStoneCount;
	}

	public void setPrevStoneCount(int a) {
		prevStoneCount = a;
	}

	/**
	 * Returns the stone count of the pit in it's previous state.
	 *
	 * @return - the previous state's stone count
	 */

	public int getPrevstonecount() {
		return prevStoneCount;
	}

	/**
	 * Adds a number of stones to the current stone count after saving the previous state.
	 *
	 * @param x - number of stones to add
	 */

	public void add(int x) {
		prevStoneCount = currentStoneCount;
		currentStoneCount += x;
		repaint();
	}

	/**
	 * Sets the current stone count to it's previous state.
	 */

	public void revert() {
		currentStoneCount = prevStoneCount;
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		double hSize = width;
		double vSize = height;
		double y = 0;
		if (pitType > 1) {
			Ellipse2D ellipse = new Ellipse2D.Double(0, 5, hSize, vSize * 2 + 5);
			g2.draw(ellipse);
			y = 10;
		} else {
			Ellipse2D ellipse = new Ellipse2D.Double(0, 0, hSize, vSize);
			g2.draw(ellipse);
			y = 5;
		}
		g2.setColor(Color.BLACK);
		double tempHSize = hSize;
		double tempVSize = vSize;
		if ((currentStoneCount > 10 && pitType < 2) || (currentStoneCount > 20)) {
			tempHSize = hSize / 2;
			tempVSize = vSize / 2;
		}
		double x = hSize / 2 - tempHSize / 10 - 2;
		for (int j = 0; j < currentStoneCount; j++) {
			if (j % 2 == 1) x = hSize / 2 + 2;
			else x = hSize / 2 - tempHSize / 10 - 2;
			Ellipse2D marbles = new Ellipse2D.Double(x, y, tempHSize / 10, tempVSize / 10);
			g2.fill(marbles);
			g2.draw(marbles);
			if (j % 2 == 1) y += tempVSize / 5;
		}
	}

}
