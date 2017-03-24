package dkeep.cli;

import java.io.IOException;
import java.util.Scanner;

import dkeep.logic.Game;
import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.Map;
import dkeep.logic.Ogre;

public class Game2 {

	public static void main(String[] args) {
		Game2 a=new Game2();
		a.run();

	}

	public void run(){
		Output o=new Output();
		Input in=new Input();
		Scanner s = new Scanner(System.in);
		int win=0;
		String move;

	
		int info[]=new int[] {0,3};
		
		Game g=new Game();
		
	
			try {
				g.nextLevel(info);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			int gameState=-1;

		while(win==0){

			o.output(g.getMap());

			move=in.input(s);
			
			g.getHero().HeroMove(g.getMap(),move);
			

			if(g.getMap().getMapType()==1){
				
				g.getGuard().GuardMove(g.getMap());
				
				if(g.losscheck()==1){
					win=2;
				}
			}
			else if(g.getMap().getMapType()==2){
				
				g.OgreMove();
				
				if(g.losscheckkeep()==1){
					win=2;
				}
			}

			if(g.wincheck()==1){
				
				try {
					gameState=g.nextLevel(info);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(gameState==0){
					win=1;
				}
				
			}

		}

		if(win==2){
			o.output(g.getMap());
			System.out.println("\nYou lost!");
		}
		else if(win==1){
			o.output(g.getMap());
			System.out.println("\nYou won!");

		}

		s.close();


	}
}