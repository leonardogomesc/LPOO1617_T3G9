package dkeep.cli;

import java.io.IOException;
import java.util.Scanner;

import dkeep.logic.Game;

public class Game2 {
	private Game g;
	public static void main(String[] args) {
		Game2 a=new Game2();
		a.run(); }

	public void run(){
		
		Scanner s = new Scanner(System.in);
		int win=0; 
		int info[]=new int[] {0,3}; g=new Game();
		try {g.nextLevel(info); } catch (IOException e) {e.printStackTrace(); }
		while(win==0){win=play(s, info);}
		s.close(); }

	private int play(Scanner s, int[] info ){
		Output o=new Output(); Input in=new Input();
		int gameState=-1; String move;
		o.output(g.getMap()); move=in.input(s);
		g.getHero().HeroMove(g.getMap(),move);
		if(g.getMap().getMapType()==1){
			g.getGuard().GuardMove(g.getMap());
			if(g.losscheck()==1){o.output(g.getMap()); System.out.println("\nYou lost!"); return 2; } }
		else if(g.getMap().getMapType()==2){g.OgreMove(); if(g.losscheckkeep()==1)
		{o.output(g.getMap()); System.out.println("\nYou lost!"); return 2; } }
		if(g.wincheck()==1){
			try {gameState=g.nextLevel(info); } catch (IOException e) {e.printStackTrace(); }
			if(gameState==0)
			{o.output(g.getMap()); System.out.println("\nYou won!"); return 1; } }
		return 0; }
}