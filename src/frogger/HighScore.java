package frogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * Klasa odpowiedzialna za zapisywanie i odczytywanie listy najwyższych wyników.
 * @author Karol Cichosz
 *
 */
public class HighScore 
{
	private String[] split;
	private static final String dir="resources/highscore.txt";
	private String line;
	ObjectOutputStream output=null;
	ObjectInputStream input=null;
	public ArrayList<Score> scores=new ArrayList<>();
	/**
	 * Klasa odpowiedzialna za pojedyńczy wynik.
	 * @author Karol Cichosz
	 *
	 */
	public class Score
	{
		public String name;
		public int score;
		
		public Score(String n, String s)
		{
			name=n;
			score=Integer.parseInt(s);
		}
		
		public Score(String n, int s)
		{
			name=n;
			score=s;
		}
		
		public String getScore()
		{
			String n=name+" "+score;
			return n;
		}
	}
	/**
	 * Metoda zapisujaca do pliku obecne rekordy.
	 */
	public void saveScoreFile()
	{
		try {
	        FileWriter fw=new FileWriter(dir);
	        BufferedWriter br=new BufferedWriter(fw);
	        for(Score s: scores)
	        {
	        	br.write(s.name+" "+s.score);
	        	br.newLine();
	        }
	        br.close();         
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + dir + "'");
        }
	}
	/**
	 * Metoda odczytujaca obecne rekordy z pliku.
	 */
	public void loadScoreFile() 
	{
	    try {
	        FileReader fr=new FileReader(dir);
	        BufferedReader br=new BufferedReader(fr);
	        while((line = br.readLine()) != null) 
	        {
                split=line.split(" ");
                scores.add(new Score(split[0],split[1]));
            }   
	        br.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                dir + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + dir + "'"); 
	        
	    }
	}
	
	public HighScore()
	{
		initHS();
	}
	/**
	 * Metoda do sprawdzenia czy wynik s załapuje się do tabeli najwyższych wyników.
	 * @param s sprawdzany wynik.
	 * @return true jeśli zalicza się, false jeśli nie.
	 */
	public boolean checkScore(int s)
	{
		int i=0;
		for(Score score: scores)
		{
			if(score.score<s || i>10) break;
			i++;
		}
		if(i<10)
		{
			return true;
		}
		return false;
	}
	/**
	 * Metoda dodaje wynik do listy najwyższych wyników i usuwa ostatni element.
	 * @param n String z inicjałami.
	 * @param s wartośc wyniku.
	 */
	public void addScore(String n, int s)
	{
		int i=0;
		for(Score score: scores)
		{
			if(score.score<s || i>10) break;
			i++;
		}
		if(i<=10)
		{
			scores.add(i, new Score(n,s));
			scores.remove(scores.size()-1);
		}
	}
	
	public void initHS()
	{
		loadScoreFile();
	}
	
}
