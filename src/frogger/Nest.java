package frogger;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
/**
 * Klasa odpowiedzialna za punkty finishu w grze.
 * @author Karol Cichosz
 *
 */
public class Nest 
{
	private int x,y;
	private boolean full = false;
	private Image img;
	
	public Nest(int x, int y)
	{
		initNest(x,y);
	}
	/**
	 * Inicjalizacja Nesta, ustawienie grafiki oraz współrzędnych.
	 * @param x nowy x.
	 * @param y nowy y.
	 */
	private void initNest(int x, int y)
	{
		ImageIcon ii=new ImageIcon("resources/nest.png");
		img=ii.getImage();
		this.x=x;
		this.y=y;
	}
	/**
	 * Metoda pozwala na zmianę obecnej grafiki obiektu oraz zmianę wartoości logicznej full na true.
	 */
	public void win()
	{
		ImageIcon ii=new ImageIcon("resources/win_nest.png");
		img=ii.getImage();
		full=true;
	}
	/**
	 * Metoda pozwala na zmian� obecnej grafiki obiektu na domyylny oraz zmianę wartości logicznej full na false.
	 */
	public void reset()
	{
		ImageIcon ii=new ImageIcon("resources/nest.png");
		img=ii.getImage();
		full=false;
	}
	/**
	 * Metoda zwraca prostokąt przypisany do tego obiektu, którego nastęępnie możemy użyć np przy sprawdzaniu kolizji z innymi obiektami.
	 * @return nowy prostokąt.
	 */
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
	}
	/**
	 * Metoda zwraca obecnie u�ywan� przez obiekt grafik�.
	 *
	 * @return zmienna img.
	 */
	public Image getImg() 
	{
		// TODO Auto-generated method stub
		return img;
	}
	/**
	 * Metoda zwraca wartość logiczną odpowiadającą wartości full.
	 * @return zmienna logiczna full.
	 */
	public boolean getState()
	{
		return full;
	}
	/**
	 * Metoda zwraca wspólrzędną x.
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
}

