package frogger;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
/**
 * Klasa odpowiedzialna za kamienie na planszy.
 * @author Karol Cichosz
 *
 */
public class Rock implements Obstacle
{
	private int x;
	private int y;
	private Image img;
	
	public Rock(int x, int y)
	{
		initRock(x,y);
	}
	
	public void initRock(int nx, int ny)
	{
		x=nx;
		y=ny;
		ImageIcon ii=new ImageIcon("resources/rock.png");
		img=ii.getImage();
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,90,90);
	}

	@Override
	public Image getImg() {
		// TODO Auto-generated method stub
		return img;
	}

}
