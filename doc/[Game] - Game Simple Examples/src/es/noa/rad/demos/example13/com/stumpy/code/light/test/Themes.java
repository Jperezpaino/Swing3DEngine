package es.noa.rad.demos.example13.com.stumpy.code.light.test;
import java.awt.Color;

public class Themes {
	
	public static Color BLANK = new Color(0,0,0,0);
	public static int GREEN = 2;
	public static int RED = 1;
	public static int BLUE = 0;
	public static int YELLOW = 3;
	public static int CYAN = 7;
	public static int PUPRLE = 6;

	public static Color getColor(int theme){
 		if(theme == 0)
 			return new Color((int)Rnd.randomPlus(55),(int)Rnd.randomPlus(100)+80,(int)Rnd.randomPlus(55)+200);
 		else if(theme == 1)
 			return new Color((int)Rnd.randomPlus(55)+200,(int)Rnd.randomPlus(55)+50,(int)Rnd.randomPlus(55)+50);
 		else if(theme == 2)
 			return new Color((int)Rnd.randomPlus(55),(int)Rnd.randomPlus(55)+200,(int)Rnd.randomPlus(155));
 		else if(theme == 3)
 			return new Color((int)Rnd.randomPlus(55)+200,(int)Rnd.randomPlus(55)+200,(int)Rnd.randomPlus(155));
 		else if(theme == 4)
 			return new Color((int)Rnd.randomPlus(130),(int)Rnd.randomPlus(100)+155,(int)Rnd.randomPlus(55)+200);
 		else if(theme == 5)
 			return new Color((int)Rnd.randomPlus(155)+100,(int)Rnd.randomPlus(55)+200,(int)Rnd.randomPlus(155));
 		else if(theme == 6)
 			return new Color((int)Rnd.randomPlus(55)+128,(int)Rnd.randomPlus(25)+25,(int)Rnd.randomPlus(90)+110);
 		else if(theme == 7)
 			return new Color((int)Rnd.randomPlus(25)+50,(int)Rnd.randomPlus(25)+175,(int)Rnd.randomPlus(55)+200);
 		return new Color((int)Rnd.randomPlus(255),(int)Rnd.randomPlus(255),(int)Rnd.randomPlus(255));
 			
 	}
}
