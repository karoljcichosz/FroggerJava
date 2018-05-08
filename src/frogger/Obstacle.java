package frogger;

import java.awt.Image;
import java.awt.Rectangle;
/**
 * Interface używany przez klasy Rock jak i Car.
 * @author Karol Cichosz
 *
 */
public interface Obstacle 
{
	public void move();
	public int getX();
	public int getY();
	public Rectangle getBounds();
	public Image getImg();
	
}
