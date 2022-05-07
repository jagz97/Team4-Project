package mancala; /**
 * @author Wilson
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;

public class PitPanel extends JPanel{

	private BoardModel model;

	/**
	 * This method is responsible for drawing the pits on panel
	 * @param model
	 */
	public PitPanel(BoardModel model)
	{
		this.model = model;
		this.setLayout(new BorderLayout());
		JPanel playPits = new JPanel();
		MancalaPit[] pits = model.getCurrBoard();
		for (int i = 12; i > 6; i--)
		{
			playPits.add(pits[i]);
		}
		for (int i = 0; i < 6; i++)
		{
			playPits.add(pits[i]);
		}
		add(pits[6], BorderLayout.EAST);
		add(pits[13], BorderLayout.WEST);
		add(playPits, BorderLayout.CENTER);
		setPreferredSize(new Dimension(700,200));
	}


}
