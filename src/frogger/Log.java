package frogger;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
/** 
 * Klasa abstrakcyjna używana przez klasy Wood i Tortoise.
 * 
 * @author Karol Cichosz
 *
 */
public abstract class Log   
{

	protected int x;
	protected int y;
	protected int speed;
	protected Image img;
	protected int length;
	/**
	 * Metoda zwracająca prędkość obiektu.
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
		if(x>810 && speed>0)
			x=0-length*img.getWidth(null);
		if(x<-length*90 && speed<0)
			x=810;
	}
	/**
	 * Metoda zwraca obecnie używaną przez obiekt grafikę.
	 * @return zmienna img.
	 */
	public Image getImg()
	{
		return img;
	}
	/**
	 * Metoda zwraca obecną współrzędną x.
	 * @return zmienna x.
	 */
	public int getX() 
	{
		return x;
	}

	/**
	 * Metoda zwraca obecną współrzędną y.
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
		return new Rectangle(x,y,img.getWidth(null)*length,img.getHeight(null));
	}
	/** 
	 * Metoda zwraca długość danego obiektu (ilość segmentów);
	 * @return zmienna lenght.
	 */
	public int getL()
	{
		return length;
	}
	/**
	 * Metoda pozwala na zmianę stanu z zanurzonego w niezanurzony i odwrotnie.
	 */
	public void submerge() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Metoda zwraca informację o tym czy obiekt jest zanurzony.
	 * @return wartość logiczna.
	 */
	public boolean getSub()
	{
		return false;
	}
	
}
