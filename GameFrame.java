package mancala;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GameFrame extends JFrame
{
	
	public GameFrame(JPanel buttons, JPanel gameSpace, String message) 
	{
		super();
		updateFrame(buttons, gameSpace, message);
		this.setMinimumSize(new Dimension(600,600));
	}
	
	public void updateFrame(JPanel buttons, JPanel gameSpace, String message) 
	{
		getContentPane().removeAll();
		JLabel label = new JLabel(message);
		add(label, BorderLayout.PAGE_START);
		add(gameSpace, BorderLayout.CENTER);
		add(buttons, BorderLayout.PAGE_END);
	}
	
	public void screen2() 
	{
		JButton button1 = new JButton("3");
		JButton button2 = new JButton("4");
		JPanel panel = new JPanel();
		String label = "How many stones per pit?";
		panel.add(button1);
		panel.add(button2);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				screen3();
			}
		});
		updateFrame(panel, new JPanel(), label);
		revalidate();
		repaint();
	}
	
	public void screen3() 
	{
		JButton button1 = new JButton("Undo");
		JButton button2 = new JButton("End Turn");
		JPanel panel = new JPanel();
		String label = "Player X turn";
		panel.add(button1);
		panel.add(button2);
		updateFrame(panel, new JPanel(), label);
		revalidate();
		repaint();
	}
	
	public static void main(String[] args) 
	{
		JButton button1 = new JButton("Style 1");
		JButton button2 = new JButton("Style 2");
		JPanel panel = new JPanel();
		String label = "Please choose a display style for your game.";
		panel.add(button1);
		panel.add(button2);
		GameFrame frame = new GameFrame(panel, new JPanel(), label);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				frame.screen2();
			}
		});
		frame.pack();
		frame.setVisible(true);
	}
}
