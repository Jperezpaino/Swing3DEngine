package es.noa.rad.demos.example13.com.stumpy.code.light.test;
import java.util.Random;


public class Rnd {
	
	private static Random rnd = new Random();
	
	public static double random( double num ){//random method may not be the best
    	return (num * 2)  * rnd.nextDouble() - num;
    }
	
	public static double randomPlus( double num ){//return only a positive number
		double temp = ((num * 2)  * rnd.nextDouble()) - num;
    	if( temp < 0 ) 
    		return temp * -1;
    	else
    		return temp;
    }
	
	public static int randomInt( double num ){//return only a positive number
		double temp = ((num * 2)  * rnd.nextDouble()) - num;
    	if( temp < 0 ) 
    		return (int) (temp * -1);
    	else
    		return (int) temp;
    }

}
