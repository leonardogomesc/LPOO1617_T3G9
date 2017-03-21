package dkeep.cli;

import java.io.IOException;
import java.util.Scanner;

import dkeep.logic.*;


public class ConsolePlay {


	private Game game;
	public static void main(String[] args) {
		ConsolePlay play=new ConsolePlay();
		play.runGame();

	}
	private void runGame()
	{
		this.game=new Game();
		int gameState =-1, levelState=0;
		Scanner s = new Scanner(System.in);
		char move;
		boolean done=false;
		int[] info=new int[2];
		
		while(!done)
		{			
			try
			{
				System.out.println("Choose guard type (0-rookie|1-drunken|2-suspicious):");
				info[0]=Integer.parseInt(Interface.read(s));
				System.out.println("Choose number of ogres (between 1 and 5):");
				info[1]=Integer.parseInt(Interface.read(s));
			} catch(Exception e) {
				
			}
			Interface.clearScrn();
			if(((info[0]>=0) &&(info[0]<=2)) && ((info[1]>=0)&&(info[1]<=5)))
				done=true;
		}
		
		
		
		while(true)
		{
			try {
				gameState=this.game.nextLevel(info);
				game.printMap();
			} catch (IOException e) {
				System.out.println(".map file not found!\n Exiting game...");
				s.close();
				return;
			}
			if(0==gameState)
			{
				Interface.clearScrn();
				System.out.println("You won!!!! ;)");
				s.close();
				return;
			}
			levelState=0;
			while (Game.WIN!=levelState)
			{
				move=Interface.input(s);
				levelState=this.game.moveHero(move);
				if(Game.LOSS==levelState)
				{
					Interface.clearScrn();
					System.out.println("You lost... :(\nBetter luck next time!");
					s.close();
					return;
				}
				game.printMap();
			}
		}
		
		
	}

}
