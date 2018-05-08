package frogger;

import javax.swing.ImageIcon;

/**
 * Klasa używająca klasy abstrakcyjnej log, odpowiedzialna za kłody na wodzie.
 * 
 * @author Karol Cichosz
 *
 */
public class Wood extends Log 
{
	public Wood(int x, int y, int speed, int l)
	{
		initWood(x,y,speed, l);
	}
	
	private void initWood(int x, int y, int speed, int l)
	{
		length=l;
		ImageIcon ii=new ImageIcon("resources/wood.png");
		img=ii.getImage();
		this.x=x;
		this.y=y;
		this.speed=speed;
	}
	
}
