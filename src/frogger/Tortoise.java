package frogger;

import javax.swing.ImageIcon;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Klasa rozszerza klasę abstrakcyjną Log.
 * Odpowiedzialna za żółwie na wodzie.
 * @author Karol Cichosz
 *
 */
public class Tortoise extends Log 
{
	private Image img_s;
	private boolean sub;
	public Tortoise(int x, int y, int speed, int l)
	{
		initTortoise(x,y,speed, l);
	}
	
	private void initTortoise(int x, int y, int speed, int l)
	{
		length=l;
		ImageIcon ii=new ImageIcon("resources/tortoise.png");
		img=ii.getImage();
		ii=new ImageIcon("resources/tort_sub.png");
		img_s=ii.getImage();
		sub=false;
		this.x=x;
		this.y=y;
		this.speed=speed;
	}
	/**
	 * Metoda pozwala na zmianę stanu z zanurzonego w niezanurzony i odwrotnie.
	 */
	public void submerge()
	{
		sub=!sub;
	}
	/**
	 * Metoda w zależności od obecnego stanu zanurzenia zwraca odpowiednią grafikę.
	 */
	public Image getImg()
	{
		if(sub)
			return img_s;
		else
			return img;
	}
	/**
	 * Metoda zwraca informacjęo tym czy obiekt jest zanurzony.
	 * @return zmienna sub.
	 */
	public boolean getSub()
	{
		return sub;
	}
	
}
