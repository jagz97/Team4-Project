/**
 * @Author Surafel Abebe
 * 
 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import javax.swing.*;

public class MancalaPit extends JPanel{
	
	private BoardModel model;
	private int currentStoneCount;
	private int prevStoneCount;
	private MyListener listener;
	private int pitType;
	
	MancalaPit(int x, BoardModel model) 
	{
		this.model = model;
		currentStoneCount = x;
		prevStoneCount = x;
		setPreferredSize(new Dimension(100,100));
	}
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setTitle("Mancala Pit");
//		frame.setSize(1500, 800);
//		
//		MancalaPit panel = new MancalaPit(10);
//		panel.setType(1);
//		frame.add(panel);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//	}
	
	private void setType(int x) 
	{
		pitType = x;
	}
	
	/**
	 * Sets the pit's current stone count to 0 after saving it's previous state.
	 */
	
	public void clear() 
	{
		prevStoneCount = currentStoneCount;
		currentStoneCount = 0;
		repaint();
	}
	
	public void addListener() 
	{
		MyListener listeners = new MyListener();
		listener = listeners;
		addMouseListener(listeners);
	}
	
	
	public void removeListener() 
	{
		this.removeMouseListener(listener);
		listener = null;
	}
	
	private class MyListener extends MouseAdapter 
	{
		public void mousePressed(MouseEvent event) 
		{
			MancalaPit[] pits = model.getModel();
			int i = 0;
			while (i < pits.length && pits[i] != MancalaPit.this)
				i++;
			model.move(i);
		}
	}
	
	/**
	 * Sets the pit's current stone count after saving it's previous state.
	 * @param x - number of stones in the pit after the change
	 */
	
	public void setCurrentStone(int x) 
	{
		prevStoneCount = currentStoneCount;
		currentStoneCount = x;
		repaint();
	}
	
	/**
	 * Returns the current stone count of the pit.
	 * @return - the current stone count
	 */
	
	public int getCurrentStone() 
	{
		return currentStoneCount;
	}
	
	/**
	 * Returns the stone count of the pit in it's previous state.
	 * @return - the previous state's stone count
	 */
	
	public int getPrevstonecount() 
	{
		return prevStoneCount;
	}
	
	/**
	 * Adds a number of stones to the current stone count after saving the previous state.
	 * @param x - number of stones to add
	 */
	
	public void add(int x) 
	{
		prevStoneCount = currentStoneCount;
		currentStoneCount += x;
		repaint();
	}
	
	/**
	 * Sets the current stone count to it's previous state.
	 */
	
	public void revert() 
	{
		currentStoneCount = prevStoneCount;
		repaint();
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		double X = 0;
		double Y = 0;
		double hSize = 100;
		double vSize = 100;
		double y = 0;
		if (pitType > 1) 
		{
			Ellipse2D ellipse = new Ellipse2D.Double(0,0,hSize,vSize+300);
			g2.draw(ellipse);
			y = Y + 150;
		}
		else 
		{
			Ellipse2D ellipse = new Ellipse2D.Double(0,0,hSize,vSize);
			g2.draw(ellipse);
			y = Y + 5;
		}
		double x = X + 35;
		for(int j =0; j < currentStoneCount;j++) {
			
			Ellipse2D marbles = new Ellipse2D.Double(x,y,hSize/10,vSize/10);

			y+=20;
			
			if(j>=5) {
				y-=40;
				x+=15;
				 marbles = new Ellipse2D.Double(x,y,hSize/10,vSize/10);
					g2.setColor(Color.BLACK);
					g2.fill(marbles);
					g2.draw(marbles);
				x-=15;
			}
			else {
				g2.setColor(Color.BLACK);
				g2.fill(marbles);
				g2.draw(marbles);
			}
			
		}
	}
	
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
//		// draw a rectangle
//		double X = 150;
//		double Y = 50;
//		double hSize = 100;
//		double vSize = 100;
//	   // Integer stone = 4;
//		
//		for(int i =0; i <13;i++) {
//			if(i==0) {
//				
//			   Ellipse2D ellipse = new Ellipse2D.Double(X-100,Y,hSize,vSize+300);
//			   g2.draw(ellipse);
//			 
//				
//			}
//			else {
//			   
//				if(i==7) {
//					Ellipse2D ellipse = new Ellipse2D.Double(X+50,Y,hSize,vSize+300);
//					g2.draw(ellipse);
//					Y+=200;
//					X=150;
//					
//				}
//				 Ellipse2D ellipse = new Ellipse2D.Double(X+50,Y,hSize,vSize);
//					g2.draw(ellipse);
//				
//					double y = Y+5;
//					double x = X+80;
//					for(int j =0; j < currentStoneCount;j++) {
//						
//						Ellipse2D marbles = new Ellipse2D.Double(x,y,hSize/10,vSize/10);
//			
//						y+=20;
//						
//						if(j>=5) {
//							y-=40;
//							x+=15;
//							 marbles = new Ellipse2D.Double(x,y,hSize/10,vSize/10);
//								g2.setColor(Color.BLACK);
//								g2.fill(marbles);
//								g2.draw(marbles);
//							x-=15;
//						}
//						else {
//							g2.setColor(Color.BLACK);
//							g2.fill(marbles);
//							g2.draw(marbles);
//						}
//						
//					}
//					X+=150;
//			}
//}
//}

}