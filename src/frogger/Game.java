package frogger;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.EventQueue;
/**
 * Główna klasa
 * @author Karol Cichosz
 *
 */
public class Game extends JFrame
{
	public Game()
	{
		initUI();
	}
	/**
	 * Metoda inicjalizuje okienko oraz dodaje do niego element klasy Board
	 */
	private void initUI()
	{
		add (new Board());
		setSize(810,1020);
		setTitle("Frogger");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run()
			{
				Game game=new Game();
				game.setVisible(true);
			}
		});
	}
}

//http://beepbox.co/#5s0kbl00e06t7m0a7g0fj7i0r1w1110f0000d1110c0000h0000v0000o3210b4h4j8h4h4h4i4xcN4h4h4h8i4h4h4h4h4h4h4h4h4h4p21QFzBdBdddJkF9TRxE3g6pW0Q1E3JFFFFJG8Q1E3g45dzXpDS7jBdkRjfCiFwsDasFGCKv9nST5MkPMWGp6CBCzPgulljjjn4tw5Pc1A8rcOa7u0fbL1w0