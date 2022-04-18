
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;


public class GameFrame extends JFrame
{
	
	private Layout layout;
	
	public GameFrame(JPanel buttons, Layout gameSpace, String message) 
	{
		super();
		updateFrame(buttons, gameSpace, message);
		this.setMinimumSize(new Dimension(1500,1000));
	}
	
	public void updateFrame(JPanel buttons, Layout gameSpace, String message) 
	{
		getContentPane().removeAll();
		JLabel label = new JLabel(message);
		add(label, BorderLayout.PAGE_START);
		add(gameSpace, BorderLayout.CENTER);
		add(buttons, BorderLayout.PAGE_END);
	}
	
	public void screen2(boolean choice) 
	{
		JButton button1 = new JButton("3");
		JButton button2 = new JButton("4");
		JPanel panel = new JPanel();
		String label = "How many stones per pit?";
		panel.add(button1);
		panel.add(button2);
		BoardModel model = new BoardModel();
		button1.addActionListener(getActionListener2(3));
		button2.addActionListener(getActionListener2(4));
		if (choice) layout = new BoardLayout(model.getModel());
		else layout = new BoardLayout2();
		updateFrame(panel, layout , label);
		revalidate();
		repaint();
	}
	
	public void screen3(int x) 
	{
		JButton button1 = new JButton("Undo");
		JButton button2 = new JButton("End Turn");
		JPanel panel = new JPanel();
		String label = "Player X turn";
		//JLabel label = new JLabel("Player X turn");
		panel.add(button1);
		panel.add(button2);
		updateFrame(panel, layout, label);
//		BorderLayout layout = (BorderLayout)getLayout();
//		remove(layout.getLayoutComponent(BorderLayout.PAGE_END));
//		remove(layout.getLayoutComponent(BorderLayout.PAGE_START));
//		add(label, BorderLayout.PAGE_START);
//		add(panel, BorderLayout.PAGE_END);
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
		GameFrame frame = new GameFrame(panel, new Layout(), label);
		button1.addActionListener(frame.getActionListener(true));
		button2.addActionListener(frame.getActionListener(false));
		frame.pack();
		frame.setVisible(true);
	}
	
	public ActionListener getActionListener(boolean choice) 
	{
		return new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				screen2(choice);
			}
		};
	}
	
	public ActionListener getActionListener2(int x) 
	{
		return new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				screen3(x);
			}
		};
	}
}
