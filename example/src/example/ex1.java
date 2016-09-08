package example;
import java.util.*;

public class ex1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "   7   +   6   +    666  ";
		String[] strs = str.split("\\+|-");
		for (String str1: strs){
			System.out.println(str1);
		}
		
	}

}
