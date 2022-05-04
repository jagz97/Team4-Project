/**
 * @Author Surafel Abebe
 * 
 */

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaPit extends JPanel implements ChangeListener{
	
	private BoardModel model;
	private int currentStoneCount;
	private int prevStoneCount;
	
	MancalaPit(int x) 
	{
		currentStoneCount = x;
		prevStoneCount = x;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Mancala Pit");
		frame.setSize(1500, 800);
		
		MancalaPit panel = new MancalaPit(10);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * Sets the pit's current stone count to 0 after saving it's previous state.
	 */
	
	public void clear() 
	{
		prevStoneCount = currentStoneCount;
		currentStoneCount = 0;
	}
	
	/**
	 * Sets the pit's current stone count after saving it's previous state.
	 * @param x - number of stones in the pit after the change
	 */
	
	public void setCurrentStone(int x) 
	{
		prevStoneCount = currentStoneCount;
		currentStoneCount = x;
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
	
	public int getPrevStoneCount() 
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
	}
	
	/**
	 * Sets the current stone count to it's previous state.
	 */
	
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
					double x = X+80;
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
					X+=150;
			}
}
}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}