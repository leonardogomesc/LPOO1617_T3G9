package dkeep.cli;

import java.util.Scanner;

import dkeep.logic.Guard;
import dkeep.logic.Hero;
import dkeep.logic.Map;
import dkeep.logic.Ogre;

public class Game {

	public static void main(String[] args) {
		Game a=new Game();
		a.run();

	}

	public void run(){
		Output o=new Output();
		Input in=new Input();
		Scanner s = new Scanner(System.in);
		int win=0;
		String move;

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
				{'X',' ','I',' ','I',' ','X','k',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}};

		Guard g=new Guard(guard, 1);
		Hero h=new Hero(hero, 0);


		Map m=new Map(map,doors,key,1);



		while(win==0){

			o.output(m);

			move=in.input(s);
			
			h.HeroMove(m,move);

			g.GuardMove(m);


			if(losscheck(h,g,m)==1){
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
			run2();

		}

		s.close();


	}

	public void run2(){
		Output out=new Output();
		Input in=new Input();
		Scanner s = new Scanner(System.in);
		String move; 
		int win=0;
		int i;

		int hero[]={7,1};
		int doors[][]={{1,0}};
		int key[]={1,7};
		int ogre[]={1,4};
		int bat[]={2,4};
		

		char map[][]=  {{'X','X','X','X','X','X','X','X','X'},
						{'I',' ',' ',' ','O',' ',' ','k','X'},
						{'X',' ',' ',' ','*',' ',' ',' ','X'},
						{'X',' ',' ',' ',' ',' ',' ',' ','X'},
						{'X',' ',' ',' ',' ',' ',' ',' ','X'},
						{'X',' ',' ',' ',' ',' ',' ',' ','X'},
						{'X',' ',' ',' ',' ',' ',' ',' ','X'},
						{'X','A',' ',' ',' ',' ',' ',' ','X'},
						{'X','X','X','X','X','X','X','X','X'}};


		Hero h=new Hero(hero, 1);
		Ogre o=new Ogre(ogre,bat);
		
		Ogre ogrearray[]={o};


		Map m=new Map(map,doors,key,2);
		
		
		while(win==0){
			out.output(m);

			move=in.input(s);
			
			h.HeroMove(m, move);
	
			for(i=0;i<ogrearray.length;i++){
				
				ogrearray[i].OgreErase(m);
			}
			
			if(m.getKey() == 1){
				map[key[0]][key[1]]='k';
			}
			
			for(i=0;i<ogrearray.length;i++){
				ogrearray[i].OgreMove(m);
			}
			
			for(i=0;i<ogrearray.length;i++){
				if(losscheck(h,ogrearray[i],m)==1){
					win=2;
				}
			}

			if(wincheck(h,m)==1){
				win=1;
			}
		}


		if(win==2){
			out.output(m);
			System.out.println("\nYou lost!");
		}
		else if(win==1){
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nYou win!");

		}


		s.close();
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

	public int losscheck(Hero h,Guard g,Map m){
		int hero[]=h.getHero();
		int guard[][]=g.getGuard();
		int guardpos=g.getGuardpos();
		char map[][]=m.getMap();
		int result=0;

		if (map[guard[guardpos][0]][guard[guardpos][1]]=='G'){
			
		if((hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1]+1)||
				(hero[0]==guard[guardpos][0]-1 && hero[1]==guard[guardpos][1])||
				(hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1]-1)||
				(hero[0]==guard[guardpos][0]+1 && hero[1]==guard[guardpos][1])||
				(hero[0]==guard[guardpos][0] && hero[1]==guard[guardpos][1])){
			result=1;
		}
		}

		return result;

	}

	public int losscheck(Hero h, Ogre o,Map m){
		int hero[]=h.getHero();
		int ogre[]=o.getOgre();
		int bat[]=o.getBat();
		char map[][]=m.getMap();
		int result =0;
		
		if (h.getBasher()==0){
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
		}
		else if(h.getBasher()==1){

			if(	(hero[0]==bat[0] && hero[1]==bat[1]+1)||
					(hero[0]==bat[0]-1 && hero[1]==bat[1])||
					(hero[0]==bat[0] && hero[1]==bat[1]-1)||
					(hero[0]==bat[0]+1 && hero[1]==bat[1])||
					(hero[0]==bat[0] && hero[1]==bat[1])){
				result=1;
			}
			
			if((hero[0]==ogre[0] && hero[1]==ogre[1]+1)||
					(hero[0]==ogre[0]-1 && hero[1]==ogre[1])||
					(hero[0]==ogre[0] && hero[1]==ogre[1]-1)||
					(hero[0]==ogre[0]+1 && hero[1]==ogre[1])||
					(hero[0]==ogre[0] && hero[1]==ogre[1])){
				o.setStunned();
				map[ogre[0]][ogre[1]]='8';
			}
		}
		return result;

	}

}