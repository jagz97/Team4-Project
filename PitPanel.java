import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		setPreferredSize(new Dimension(700,200));
	}
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		BoardModel model = new BoardModel();
		model.fillInitialBoard(4);
		PitPanel panel = new PitPanel(model);
		frame.add(panel);
		JButton button = new JButton("undo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				model.undo();
			}
		});
		frame.add(button,BorderLayout.PAGE_END);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
