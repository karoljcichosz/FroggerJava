package frogger;

import javax.swing.*;

import frogger.HighScore.Score;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.EventQueue;
/**
 * Klasa odpowiedzialna za całą planszę gry, rysowanie, sprawdzanie kolizji itp.
 * @author Karol Cichosz
 *
 */
public class Board extends JPanel implements ActionListener
{
	private Timer timer;
	private Frog frog;
	private Water water;
	private HighScore HS=new HighScore();
	private ArrayList<Car> cars=new ArrayList<>();
	private ArrayList<Log> logs=new ArrayList<>();
	private ArrayList<Nest> nests=new ArrayList<>();
	private ArrayList<Rock> rocks=new ArrayList<>();
	private final int DELAY=10;
	private final int ifx=360;
	private final int ify=900;
	private final int NESTS=3;
	private final int LVL0=0;
	private final int LVL1=1600;
	private final int LVL2=3200;
	private final int LVL3=4000;
	private final int LVL4=4800;
	private final Font FS=new Font("Early GameBoy",1,20);
	private float r=(float) 0.05;
	private float g=(float) 0.21;
	private float b=(float) 0.05;
	private final Color CF=new Color(r,g,b);
	private char[] c={'A','A','A'};
	int i=0;
	int ic=0;
	int endscore=0;
	private boolean ingame;
	private boolean hs=false;
	private boolean list=false;
	private String abc;
	
	public Board()
	{
		initBoard();
	}
	/**
	 * Metoda inicjalizująca Board z odpowiednimi elementami (Nesty, Rocki, Logi itp.)
	 */
	private void initBoard()
	{
		ingame=true;
		setFocusable(true);
		addKeyListener(new KeyL());
		water=new Water();
		randomizeBoard(0);
		nests.add(new Nest(90,0));
		nests.add(new Nest(360,0));
		nests.add(new Nest(630,0));
		rocks.add(new Rock(0,0));
		rocks.add(new Rock(180,0));
		rocks.add(new Rock(270,0));
		rocks.add(new Rock(450,0));
		rocks.add(new Rock(540,0));
		rocks.add(new Rock(720,0));
		frog=new Frog(ifx,ify);
		timer=new Timer(DELAY, this);
		timer.start();
	}
	
	
	
	/**
	 * Metoda sprawdza kolizję obiektu frog ze wszystkimi obiektami i wykonuje ich konsekwencje.
	 */
    public void checkCollisions() 
    {

        Rectangle rF = frog.getBounds();

        for (Car car : cars) 
        {
            Rectangle rC = car.getBounds();

            if (rF.intersects(rC)) 
            {
                frog.downHP();
                frog.death(ifx, ify);
            }
        }
        boolean flag=false;
        Rectangle rW=water.getBounds();
        if(rF.intersects(rW))
        {
        	flag=true;
        	for (Log log : logs) 
            {
                Rectangle rL = log.getBounds();

                if (rF.intersects(rL)&&!log.getSub()) 
                {
                    flag=false;
                }
            }
        }
        if(flag)
        {
        	frog.downHP();
            frog.death(ifx, ify);
        }
        
        int iwin=0;
        for (Nest nest : nests) 
        {
            Rectangle rN = nest.getBounds();
            if(nest.getState())
            	iwin++;
            if (rF.intersects(rN)) 
            {
            	if(!nest.getState())
            	{
            		nest.win();
            		frog.addScore(200);
            		frog.setXY(ifx, ify);
            	}
            	else
            	{
            		switch(frog.lastMove())
            		{
            		case "up": frog.setXY(frog.getX(), frog.getY()+90); break;
            		case "down": frog.setXY(frog.getX(), frog.getY()-90); break;
            		case "left": frog.setXY(frog.getX()+90, frog.getY()); break;
            		case "right": frog.setXY(frog.getX()-90, frog.getY()); break;
            		}
            	}
            }
        }
        win_reset(iwin);
        for(Rock rock: rocks)
        {
        	Rectangle rR=rock.getBounds();
        	if(rF.intersects(rR))
        	{
        		switch(frog.lastMove())
        		{
        		case "up": frog.setXY(frog.getX(), frog.getY()+90); break;
        		case "down": frog.setXY(frog.getX(), frog.getY()-90); break;
        		case "left": frog.setXY(frog.getX()+90, frog.getY()); break;
        		case "right": frog.setXY(frog.getX()-90, frog.getY()); break;
        		}
        	}
        }
        
    }
    
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(ingame)
			doDrawing(g);
		else
			drawGO(g);
		Toolkit.getDefaultToolkit().sync();
	}
	/**
	 * Metoda resetuje planszę w przypadku wypełnienia wszystkich 3 nestów.
	 * @param i ilośc nestów zapełnionych
	 */
	private void win_reset(int i)
	{
        if(i==3)
        {
        	for(Nest nest : nests)
        	{
        		nest.reset();
        	}
       
        frog.setXY(ifx, ify);
        frog.addScore(200);
        if(frog.getScore()>LVL0 && frog.getScore()<LVL1)
        	randomizeBoard(0);
        if(frog.getScore()>LVL1 && frog.getScore()<LVL2)
        	randomizeBoard(1);
        if(frog.getScore()>LVL2 && frog.getScore()<LVL3)
        	randomizeBoard(2);
        if(frog.getScore()>LVL3 && frog.getScore()<LVL4)
        	randomizeBoard(3);
        if(frog.getScore()>LVL4)
        	randomizeBoard(4);
        }
	}
	/**
	 * Metoda z przygotowanymi wariacjami początkowego stanu planszy w zależności od poziomu trudności.
	 * @param lvl poziom trudności.
	 */
	private void randomizeBoard(int lvl)
	{
		int c=0,w=0,t=0;
		cars.clear();
		logs.clear();
		switch(lvl)
		{
			case 0:
				cars.add(new Car(1100,540,-2));cars.add(new Car(500,540,-2));
				cars.add(new Car(1100,630,-2));cars.add(new Car(800,630,-2));//cars.add(new Car(500,630,-2));
				cars.add(new Car(-100,810,2));cars.add(new Car(100,810,2));
				cars.add(new Car(-100,720,3));cars.add(new Car(400,720,3));//cars.add(new Car(50,720,3));
				logs.add(new Wood(-100,90, 2, 3));		logs.add(new Wood(500,90, 2, 3)); 
				logs.add(new Wood(-100,180, -1, 3));		logs.add(new Wood(-100,180, -1, 3));
				logs.add(new Wood(-100,360, 1, 2));	logs.add(new Wood(500,360, 1, 2));
				logs.add(new Tortoise(360, 270, 0, 3)); logs.add(new Tortoise(90, 270, 0, 2));
				break;
			case 1: 
				cars.add(new Car(1100,540,-2));cars.add(new Car(500,540,-2));
				cars.add(new Car(1100,630,-2));cars.add(new Car(800,630,-2));//cars.add(new Car(500,630,-2));
				cars.add(new Car(-100,810,2));cars.add(new Car(100,810,2));
				cars.add(new Car(-100,720,3));cars.add(new Car(400,720,3));cars.add(new Car(50,720,3));
				logs.add(new Wood(-100,90, 2, 3));		logs.add(new Wood(500,90, 2, 3)); 
				logs.add(new Wood(-100,180, -1, 3));	
				logs.add(new Wood(-100,360, 1, 2));	logs.add(new Wood(500,360, 1, 2));
				logs.add(new Tortoise(360, 270, 0, 3)); logs.add(new Tortoise(90, 270, 0, 2));
				break;
			case 2: 
				cars.add(new Car(1100,540,-2));cars.add(new Car(500,540,-2));
				cars.add(new Car(1100,630,-2));cars.add(new Car(800,630,-2));cars.add(new Car(500,630,-2));
				cars.add(new Car(-100,810,2));cars.add(new Car(100,810,2));
				cars.add(new Car(-100,720,3));cars.add(new Car(400,720,3));//cars.add(new Car(50,720,3));
				logs.add(new Wood(-100,90, 2, 3));		logs.add(new Wood(500,90, 2, 3)); 
				logs.add(new Wood(-100,180, -1, 3));	
				logs.add(new Wood(-100,360, 1, 2));	logs.add(new Wood(500,360, 1, 2));
				logs.add(new Tortoise(360, 270, 0, 3)); 
				break;
			case 3: 
				cars.add(new Car(1100,540,-2));cars.add(new Car(500,540,-2));
				cars.add(new Car(1100,630,-2));cars.add(new Car(800,630,-2));cars.add(new Car(500,630,-2));
				cars.add(new Car(-100,810,2));cars.add(new Car(100,810,2));
				cars.add(new Car(-100,720,3));cars.add(new Car(400,720,3));cars.add(new Car(50,720,3));
				logs.add(new Wood(-100,90, 2, 3));		logs.add(new Wood(500,90, 2, 3)); 
				logs.add(new Wood(-100,180, -1, 3));	
				logs.add(new Wood(-100,360, 2, 2));	logs.add(new Wood(500,360, 2, 2));
				logs.add(new Tortoise(360, 270, 0, 3)); 
				break;
			case 4: 
				cars.add(new Car(1100,540,-2));cars.add(new Car(500,540,-2));
				cars.add(new Car(1100,630,-2));cars.add(new Car(800,630,-2));cars.add(new Car(500,630,-2));
				cars.add(new Car(-100,810,2));cars.add(new Car(100,810,2));
				cars.add(new Car(-100,720,3));cars.add(new Car(400,720,3));cars.add(new Car(50,720,3));
				logs.add(new Wood(-100,90, 3, 3));		logs.add(new Wood(500,90, 2, 3)); 
				logs.add(new Wood(-100,180, -2, 3));	
				logs.add(new Wood(-100,360, 1, 2));
				logs.add(new Tortoise(360, 270, 0, 3)); 
				break;
		}

	}
	/**
	 * Metoda odpowiedzialna za wyświetlanie w przypadku przegrania gry.
	 * @param g obiekt Graphics odpowiedzialny za rysowanie.
	 */
	private void drawGO(Graphics g)
	{	
		if(list==false && hs==false)
		{
			this.r=(float)0.32;
			this.b=(float)0.32;
			this.g=(float)0.65;
			g.setColor(new Color(this.r,this.g,this.b));
			g.fillRect(0, 0, 810, 990);
			String msg = "Game Over";
		    Font small = new Font("Early GameBoy",1,20);
		    FontMetrics fm = getFontMetrics(small);
	
		    g.setColor(CF);
		    g.setFont(small);
		    g.drawString(msg, (810 - fm.stringWidth(msg)) / 2, 810 / 2);
		    msg="press R to play again";
		    g.drawString(msg, (810 - fm.stringWidth(msg)) / 2, 810/2+60);
		    msg="press L to view HighScores";
		    g.drawString(msg, (810 - fm.stringWidth(msg)) / 2, 810/2+90);
		}
		if(hs)
		{
			this.r=(float)0.32;
			this.b=(float)0.32;
			this.g=(float)0.65;
			g.setColor(new Color(this.r,this.g,this.b));
			g.fillRect(0, 0, 810, 990);
			String msg = "Game Over";
		    Font small = new Font("Early GameBoy",1,20);
		    Font tiny = new Font("Early GameBoy",1,10);
		    FontMetrics fm = getFontMetrics(small);
	
		    g.setColor(CF);
		    g.setFont(small);
		    g.drawString(msg, (810 - fm.stringWidth(msg))/2, 810/2);
		    msg="use arrows to select your initials and accept with space";
		    g.setFont(tiny);
		    fm=getFontMetrics(tiny);
		    g.drawString(msg, (810 - fm.stringWidth(msg))/2, 810/2+60);
		    g.setFont(small);
		    g.drawChars(c, 0, 3, (810-70)/2, 810/2+120);
		}
		if(list)
		{
			int x=135;
			this.r=(float)0.32;
			this.b=(float)0.32;
			this.g=(float)0.65;
			g.setColor(new Color(this.r,this.g,this.b));
			g.fillRect(0, 0, 810, 990);
			Font small = new Font("Early GameBoy",1,20);
		    FontMetrics fm = getFontMetrics(small);
		    String msg="press R to play again";
			g.setColor(CF);
		    g.setFont(small);
		    g.drawString(msg, (810 - fm.stringWidth(msg)) / 2, 800);
			for(Score s: HS.scores)
			{
				x=x+45;
				msg=s.getScore();

			    g.drawString(msg, (810 - fm.stringWidth(msg))/2,x);
			}
		}
	}
	/**
	 * Metoda odpowiedzialna za wyświetlanie podczas gry.
	 * @param g obiekt klasy Graphics.
	 */
	private void doDrawing(Graphics g)
	{
		Graphics2D g2d=(Graphics2D) g;
		ImageIcon ii=new ImageIcon("resources/g1.png");
		g2d.drawImage(ii.getImage(), 0, 0,405,495,this);
		ii=new ImageIcon("resources/g2.png");
		g2d.drawImage(ii.getImage(), 405, 0,405,495,this);
		ii=new ImageIcon("resources/g3.png");
		g2d.drawImage(ii.getImage(), 0, 495,405,495,this);
		ii=new ImageIcon("resources/g4.png");
		g2d.drawImage(ii.getImage(), 405, 495,405,495,this);
		g2d.drawImage(water.getImg(), water.getX(), water.getY(), 810, 360, this);
		
		
		
		for (Nest n : nests) 
		{
                g.drawImage(n.getImg(), n.getX(), n.getY(), this);
		}
		
		for (Rock r : rocks) 
		{
                g.drawImage(r.getImg(), r.getX(), r.getY(), this);
		}
		
		for (Car c : cars) 
		{
			if(c.getSpeed()>0)
                g.drawImage(c.getImg(), c.getX(), c.getY(), this);
			else
				g.drawImage(c.getImg(), c.getX()+c.getImg().getWidth(null), c.getY(),-c.getImg().getWidth(null),c.getImg().getHeight(null), this);
        }
		for (Log l : logs) 
		{
			if(l.getSpeed()>0)
				for(int i=0; i<l.getL(); i++)
					g.drawImage(l.getImg(), l.getX()+i*l.getImg().getWidth(null), l.getY(), this);
			else
				for(int i=0; i<l.getL(); i++)
					g.drawImage(l.getImg(), l.getX()+l.getImg().getWidth(null)+i*l.getImg().getWidth(null), l.getY(),-l.getImg().getWidth(null),l.getImg().getHeight(null), this);
        }
		g.setColor(CF);
		g.setFont(FS);
		g2d.drawImage(frog.getImg(), frog.getX(), frog.getY(), this);
		g.drawString("SCORE: " + frog.getScore(), 20, 20);
		g.drawString("LIVES:", 20, 950);
		g.drawString(frog.getHP()/2 + "x", 45, 980);
	}
	/**
	 * Metoda odpowiedzialna za zmianę 3 liter umieszczanych przy wyniku jako inicjały.
	 * @param i numer literki
	 * @param x zmienna logiczna która mówi o tym czy zmienić na następną czy na poprzędnią
	 */
	public void moveLetters(int i, boolean x)
	{
		if(x)
			c[i]=(char)(65+(c[i]+1-65)%26);
		else
		{
			c[i]=(char)(65+(c[i]-1-65));
			if(c[i]<'A')
				c[i]='Z';
		}
		
	}
	/**
	 * Metoda zajmuję się obsługą klawiatury po przegraniu gry.
	 * 
	 * @param e KeyEvent
	 */
	public void getname(KeyEvent e)
	{	
		Character LastKey=null;
		int key=e.getKeyCode();
		if(hs)
		{
			
			if(key==KeyEvent.VK_UP &&(LastKey==null||LastKey!=e.getKeyChar()))
			{
				LastKey=e.getKeyChar();
				moveLetters(ic,true);
			}
			if(key==KeyEvent.VK_DOWN &&(LastKey==null||LastKey!=e.getKeyChar()))
			{
				LastKey=e.getKeyChar();
				moveLetters(ic,false);
				
			}
			if(key==KeyEvent.VK_RIGHT &&(LastKey==null||LastKey!=e.getKeyChar()))
			{
				LastKey=e.getKeyChar();
				ic++;
				if(ic==3) ic=0;
			}
			if(key==KeyEvent.VK_LEFT &&(LastKey==null||LastKey!=e.getKeyChar()))
			{
				LastKey=e.getKeyChar();
				ic--;
				if(ic==-1) ic=2;
			}
			if(key==KeyEvent.VK_SPACE &&(LastKey==null||LastKey!=e.getKeyChar()))
			{
				LastKey=e.getKeyChar();
				abc=""+c[0]+c[1]+c[2];
				HS.addScore(abc, endscore);
				HS.saveScoreFile();
				hs=false;
				list=true;
			}
		}
		if(list)
		{	
			if(key==KeyEvent.VK_R)
			{
				abc=""+c[0]+c[1]+c[2];
				HS.addScore(abc, endscore);
				frog.upHP();
				frog.upHP();
				frog.upHP();
				endscore=0;
				win_reset(3);
				frog.setXY(ifx, ify);
				frog.emptyscore();
				ingame=true;
				list=false;
				
			}
		}
		if(!hs&&!list&&!ingame)
		{
			if(key==KeyEvent.VK_L)
			{
				list=true;
			}
			if(key==KeyEvent.VK_R)
			{
				frog.upHP();
				frog.upHP();
				frog.upHP();
				endscore=0;
				win_reset(3);
				frog.setXY(ifx, ify);
				frog.emptyscore();
				ingame=true;
				list=false;
				
			}
		}
			
	}
	/**
	 * Metoda określa co ma się dziać w grze.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(frog.getHP()<1)
		{
			ingame=false;
			if(endscore==0)
				endscore=frog.getScore();
			if(HS.checkScore(endscore))
			{	
				hs=true;
			}
				
				
		}
		if(ingame)
		{
			for (Car c : cars)
	                c.move();
			for (Log l : logs)
				l.move();
			//frog.move();
			i++;
			if(i==200)
			{
				i=0;
				for(Log t : logs)
					t.submerge();
			}
			checkCollisions();
		}
		else
		{
			
		}
		repaint();
	}
	
	class KeyL extends KeyAdapter
	{
			@Override
			public void keyReleased(KeyEvent e)
			{
					frog.KeyReleased(e);
			}
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(ingame)
					frog.KeyPressed(e);
				else
					getname(e);
			}
	}
}
