package frogger;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
/**
 * Klasa Frog jest klasa odpowiedzialna za "głównego bohatera" gry.
 * @author Karol Cichosz
 *
 */
public class Frog 
{
	private int dx;
	private int dy;
	private int x;
	private int y;
	private int hp;
	private int score=0;
	private Image img;
	private Image img_j;
	private Character LastKey=null;
	private boolean jump=false;
	private String ld="none";
	
	public Frog(int x, int y)
	{
		initFrog(x,y);
	}
	/**
	 * Metoda ustawia żabie obraz, a także współrzędne x,y oraz hp.
	 * @param nx poczatkowa współrzędna x.
	 * @param ny poczatkowa współrzędna y.
	 */
	private void initFrog(int nx, int ny)
	{
		ImageIcon ii=new ImageIcon("resources/frog.png");
		img=ii.getImage();
		x=nx;
		y=ny;
		hp=6;
	}
	
	/**
	 * Metoda która na podstawie obecnych współrzędnych, oraz zmiennych zawierajacych informację o ile zmienić współrzędne, 
	 * oblicza nowe współrzędne po wykonanym ruchu.
	 * 
	 */
	public void move()
	{
		x+=dx;
		y+=dy;
	}
	/**
	 * Metoda zeruje zmienna score.
	 */
	public void emptyscore()
	{
		score=0;
	}
	/**
	 * Metoda zwraca ilość życia jakie ma obiekt.
	 * @return int z ilościa życia.
	 */
	public int getHP()
	{
		return hp;
	}
	/**
	 * Metoda zwiększa ilość żyć.
	 */
	public void upHP()
	{
		hp=hp+2;
	}
	/**
	 * Metoda zmiejsza ilość żyć.
	 */
	public void downHP()
	{
		hp--;
	}
	
	/**
	 * Metoda zwraca obecna współrzęna x.
	 * @return współrzędna x.
	 */
	public int getX()
	{
		return x;
	}
	/**
	 * Metoda zwraca obecna współrzędna y.
	 * @return współrzędna y.
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Metoda zwraca obecna grafikę używana przez obiekt
	 * @return używana grafika.
	 */
	public Image getImg()
	{
		if(jump)
			return img_j;
		else
			return img;
	}
	/**
	 * Metoda używana w przypadku "śmierci" obiektu, przenosi obiekt na wyznaczone współrzędne i zmniejsza życie.
	 * @param ix nowa współrzędna x.
	 * @param iy nowa współrzędna y.
	 */
	public void death(int ix, int iy)
	{
		setXY(ix, iy);
		downHP();
	}
	/**
	 * Metoda przenosi obiekt na wyznaczone współrzędne.
	 * @param ix nowa współrzędna x.
	 * @param iy nowa współrzędna y;
	 */
	public void setXY(int ix, int iy)
	{
		x=ix;
		y=iy;
	}
	
	/**
	 * Metoda zwraca prostokat używany potem do kontroli kolizji.
	 * @return nowy prostokat.
	 */
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,img.getWidth(null),img.getHeight(null));
	}
	
	/**
	 * Metoda służy do zwiększania zmiennej score obiektu.
	 * @param s wartość o jaka zwiększamy zmienna score;
	 */
	public void addScore(int s)
	{
		score=score+s;
	}
	
	/**
	 * Metoda zwraca wartość zmiennej score obiektu.
	 * @return zmienna score.
	 */
	public int getScore()
	{
		return score;
	}
	/**
	 * Metoda zwraca stringa z informacja w która stronę obiekt ostatnio wykonał ruch;
	 * @return string o wartości "up", "down", "left" lub "right"
	 */
	public String lastMove()
	{
		return ld;
	}
	/**
	 * Metoda mówi jak ma zachować się obiekt po naciśnięciu odpowiedniego klawisza.
	 * Odpowiedzialna za poruszanie się obiektu po planszy.
	 * Dzięki zmiennej LastKey i odpowiednim if'om sprawia że nie ważne jak długo trzymamy przycisk żaba poruszy się tylko raz.
	 * @param e KeyEvent
	 */
	public void KeyPressed(KeyEvent e)
	{
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_UP &&(LastKey==null||LastKey!=e.getKeyChar()))
		{
			LastKey=e.getKeyChar();
			if(y-90>=0)
			{
				dy=-90;
				ld="up";
				move();
			}
		}
		if(key==KeyEvent.VK_DOWN &&(LastKey==null||LastKey!=e.getKeyChar()))
		{
			LastKey=e.getKeyChar();
			if(y+90<=900)
			{
				dy=90;
				ld="down";
				move();
			}
		}
		if(key==KeyEvent.VK_LEFT &&(LastKey==null||LastKey!=e.getKeyChar()))
		{
			LastKey=e.getKeyChar();
			if(x-90>=0)
			{
				dx=-90;
				ld="left";
				move();
			}
		}
		if(key==KeyEvent.VK_RIGHT &&(LastKey==null||LastKey!=e.getKeyChar()))
		{
			LastKey=e.getKeyChar();
			if(x+90<810)
			{
				dx=90;
				ld="right";
				move();
			}
		}
	}
	
	/**
	 * Metoda mówi jak ma zachować się obiekt po zwolnieniu klawisza.
	 * Czyści zmienna LastKey;
	 * @param e KeyEvent
	 */
	public void KeyReleased(KeyEvent e)
	{
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_UP)
		{
			dy=0;
			LastKey=null;
		}
		if(key==KeyEvent.VK_DOWN)
		{
			dy=0;
			LastKey=null;
		}
		if(key==KeyEvent.VK_LEFT)
		{
			dx=0;
			LastKey=null;
		}
		if(key==KeyEvent.VK_RIGHT)
		{
			dx=0;
			LastKey=null;
		}
	}
}
