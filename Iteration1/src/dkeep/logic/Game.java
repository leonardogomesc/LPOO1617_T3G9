package dkeep.logic;

import java.util.Scanner;

import dkeep.cli.Output;

public class Game {

	public static void main(String[] args) {
		Game a=new Game();
		a.run();

	}

	public void run(){
		Output o=new Output();
		int win=0;

		int hero[]={1,1};
		int doors[][]={{5,0},{6,0}};
		int key[]={8,7};
		int guard[][]={{1,8},{1,7},{2,7},{3,7},{4,7},{5,7},{5,6},{5,5},{5,4},{5,3},{5,2},{5,1},{6,1},{6,2},{6,3},{6,4},{6,5},{6,6},{6,7},{6,8},{5,8},{4,8},{3,8},{2,8}};

		char map[][]={{'X','X','X','X','X','X','X','X','X','X'},
				{'X','H',' ',' ','I',' ','X',' ','G','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{'X',' ','I',' ','I',' ','X',' ',' ','X'},
				{'X','X','X',' ','X','X','X',' ',' ','X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'I',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X',' ','X','X','X','X',' ','X'},
				{'X',' ','I',' ','I',' ','X','K',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}};

		Guard g=new Guard(guard);
		Hero h=new Hero(hero);

		Guard guards[]={g};
		Map m=new Map(map,guards,doors,key);

		while(win==0){

			o.output(m);

			h.HeroMove(m);

			g.GuardMove(m);


			if(losscheck(h,g)==1){
				win=2;
			}

			if(wincheck(h,m)==1){
				win=1;
			}

		}

		if(win==2){
			o.output(m);
			System.out.println("\nYou lost!");
		}
		else if(win==1){
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nYou win!");

		}


	}

	public int wincheck(Hero h, Map m){

		int hero[]=h.getHero();
		int doors[][]=m.getDoors();
		int result=0;

		for(int i =0;i<doors.length;i++){	
			if(hero[0]==doors[i][0] && hero[1]==doors[i][1]){
				result=1;
			}
		}

		return result;
	}

	public int losscheck(Hero h,Guard g){
		int hero[]=h.getHero();
		int guard[][]=g.getGuard();
		int guardpos=g.getGuardpos();
		int result=0;

		if((hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1]+1)||
				(hero[0]==guard[guardpos][0]-1 && hero[1]==guard[guardpos][1])||
				(hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1]-1)||
				(hero[0]==guard[guardpos][0]+1 && hero[1]==guard[guardpos][1])||
				(hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1])){
			result=1;
		}

		return result;

	}

	public int losscheck(Hero h, Ogre o){
		int hero[]=h.getHero();
		int ogre[]=o.getOgre();
		int bat[]=o.getBat();
		int result =0;

		if((hero[0]==ogre[0] && hero[1]==ogre[1]+1)||
				(hero[0]==ogre[0]-1 && hero[1]==ogre[1])||
				(hero[0]==ogre[0] && hero[1]==ogre[1]-1)||
				(hero[0]==ogre[0]+1 && hero[1]==ogre[1])||
				(hero[0]==ogre[0] && hero[1]==ogre[1]) ||
				(hero[0]==bat[0] && hero[1]==bat[1]+1)||
				(hero[0]==bat[0]-1 && hero[1]==bat[1])||
				(hero[0]==bat[0] && hero[1]==bat[1]-1)||
				(hero[0]==bat[0]+1 && hero[1]==bat[1])||
				(hero[0]==bat[0] && hero[1]==bat[1])){
			result=1;
		}

		return result;

	}

}
