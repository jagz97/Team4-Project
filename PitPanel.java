import java.awt.BorderLayout;

import javax.swing.*;

public class PitPanel extends JPanel{

	private BoardModel model;
	
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
	}
	
}
