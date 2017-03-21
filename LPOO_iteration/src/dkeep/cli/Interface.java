package dkeep.cli;

import java.util.Scanner;

public class Interface {
	public static String read(Scanner s)
	{
		String in;
		in= s.nextLine();
		return in;
	}
	public static char input(Scanner s)
	{
		String in=read(s);
		char[] c;
		c=in.toCharArray();
		if(1>c.length)
			return '\0';
		return c[0];
	}
	public static void clearScrn()
	{
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}
