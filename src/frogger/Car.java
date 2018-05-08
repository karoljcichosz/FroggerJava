package frogger;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
/**
 * Klasa odpowiedzialna za samochody poruszające się po planszy.
 * @author Karol Cichosz
 *
 */
public class Car implements Obstacle
{
	private int x;
	private int y;
	private int speed;
	private Image img;
	
	public Car(int x, int y, int speed)
	{
		initCar(x,y,speed);
	}
	/**
	 * Metoda ustawia samochodowi obraz, a także współrzędne x,y oraz prędkość.
	 * @param x początkowa współrzędna x.
	 * @param y początkowa współrzędna y.
	 * @param speed szybkość samochodu.
	 */
	private void initCar(int x, int y, int speed)
	{
		ImageIcon ii=new ImageIcon("resources/car.png");
		img=ii.getImage();
		this.x=x;
		this.y=y;
		this.speed=speed;
	}
	/**
	 * Metoda zwraca prędkość danego obiektu.
	 * @return zmienna speed.
	 */
	public int getSpeed()
	{
		return speed;
	}

	/**
	 * Metoda odpowiedzialna za zmianę współrzędnych obiektu, czyli ruch.
	 */
	public void move() 
	{
		x+=1*speed;
		if(x>720 && speed>0)
			x=0-img.getWidth(null);
		if(x<-img.getWidth(null) && speed<0)
			x=720+img.getWidth(null);
	}
	/**
	 * Metoda zwraca współrzędną x.
	 */
	public int getX() 
	{
		return x;
	}
	/**
	 * Metoda zwraca współrzędną y.
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
		return new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
	}
	/**
	 * Metoda zwraca obecnie używaną przez obiekt grafikę.
	 */
	public Image getImg() 
	{
		// TODO Auto-generated method stub
		return img;
	}
	
}