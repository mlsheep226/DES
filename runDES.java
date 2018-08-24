import java.io.*;
import java.math.BigInteger;
/**
 * Main class for DES
 * @author MItchell Sheep
 * @since 4/16/2018
 * @version 2.0
 *
 */
public class runDES {
       public static int[] toBits(String m){
    	  byte[] out = m.getBytes();
    	  int[] x = new int[out.length];
    	  for (int i = 0; i < out.length; i++){
    		  x[i] = out[i];
    	  }
    	   return x;
       }
       public static char bitsToString(int x){
    	   char str = (char) x;
    	   return str;
       }
        public static void main( String args[]) throws Exception
        {
        	Out f = new Out();
        }
       
    }