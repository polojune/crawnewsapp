package crawnewsapp;

public class MyUtil {
        public static String increaseOid(String input) {
        	 int temp = Integer.parseInt(input);
        	 temp++; 
        	 String s = String.format("%03d", temp); 
        	 return s; 
        }
        public static String increaseAid(String input) {
        	int temp = Integer.parseInt(input);
        	temp = temp + 100;
        	String s = String.format("%010d", temp); 
        	
        	return s;
        }
}
