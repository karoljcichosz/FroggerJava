package frogger;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
/**
 * Klasa odpowiedzialna za wodę na planszy.
 * @author Karol Cichosz
 *
 */
public class Water 
{
	private Image img;
	int x;
	int y;
	
	public Water()
	{
		initWater();
	}
	/**
	 * Metoda ustawia obiektowi grafikę, jak i wspórzędne.
	 */
	public void initWater()
	{
		ImageIcon ii=new ImageIcon("resources/water.gif");
		img=ii.getImage();
		x=0;
		y=90;
	}
	/**
	 * Metoda zwraca współrzędną x.
	 * @return zmienna x.
	 */
	public int getX() 
	{
		return x;
	}
	/**
 	 * Metoda zwraca współrzędną y. 
 	 * @return zmienna y.
 	 */
	public int getY() 
	{
		return y;
	}
	/**
	 * Metoda zwraca prostokąt używany potem do kontroli kolizji.
	 * @return nowy prostokąt.
	 */
	public Rectangle getBounds() 
	{
		return new Rectangle(x,y,810,360);
	}
	/**
	 * Metoda zwraca obecnie używaną przez obiekt grafikę.
	 * @return zmienna img.
	 */
	public Image getImg() 
	{
		// TODO Auto-generated method stub
		return img;
	}
}

