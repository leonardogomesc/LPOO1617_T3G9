package dkeep.cli;

import java.util.Scanner;

public class Input {
	
	public String input(){
		 
	String a;
	
	Scanner s = new Scanner(System.in);
	
	a= s.nextLine();
	
	s.close();
	return a;
	}
}
