/**
 * @Author Surafel Abebe
 * 
 */

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class MancalaPit{
	
	private BoardModel model;
	private int currentStoneCount;
	private int prevStoneCount;
	
	MancalaPit(int x) 
	{
		currentStoneCount = x;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Mancala Pit");
		frame.setSize(1500, 800);
		
		DrawPit panel = new DrawPit();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void clear() 
	{
		currentStoneCount = 0;
	}
	
	public void set(int x) 
	{
		prevStoneCount = currentStoneCount;
		currentStoneCount = x;
	}
	
	public int get() 
	{
		return currentStoneCount;
	}
	
	
	public void add(int x) 
	{
		currentStoneCount += x;
	}
	
	public void revert() 
	{
		currentStoneCount = prevStoneCount;
	}
}


class DrawPit extends JPanel {
	
	private BoardModel model;
	private int currentStoneCount;
	private int prevStoneCount;
	
	
	public void revert() 
	{
		currentStoneCount = prevStoneCount;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// draw a rectangle
		double X = 150;
		double Y = 50;
		double hSize = 100;
		double vSize = 100;
	   // Integer stone = 4;
		
		for(int i =0; i <13;i++) {
			if(i==0) {
				
			   Ellipse2D ellipse = new Ellipse2D.Double(X-100,Y,hSize,vSize+300);
			   g2.draw(ellipse);
			 
				
			}
			else {
			   
				if(i==7) {
					Ellipse2D ellipse = new Ellipse2D.Double(X+50,Y,hSize,vSize+300);
					g2.draw(ellipse);
					Y+=200;
					X=150;
					
				}
				 Ellipse2D ellipse = new Ellipse2D.Double(X+50,Y,hSize,vSize);
					g2.draw(ellipse);
				
					double y = Y+5;
					for(int j =0; j < currentStoneCount;j++) {
						
						Ellipse2D marbles = new Ellipse2D.Double(X+100,y,hSize/10,vSize/10);
			
						y+=20;
						g2.draw(marbles);
					}
					X+=150;
			}
		}
		
	}
}
