package example;
import java.util.*;

public class ex1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = " 4.0 * 1.0 +  ( 3.0 / 2.0 ) + - 5.0 ".trim();
		String[] strs = str.split("\\s+");
		for (String str1: strs){
			System.out.println(strs[0]);
		}
		
	}

}
