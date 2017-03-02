package dkeep.logic;

import java.util.Scanner;

import dkeep.cli.Input;

public class Hero {
	private int hero[];
	private int basher;
	
	public Hero(int heropos[], int b){
		hero=heropos;
		basher=b;
	}

	public int[] getHero(){
		return hero;
	}

	public int getBasher() {
		return basher;
	}
	
	public void HeroMove(Map m,Scanner s){
		String move;
		Input i=new Input();
		char map[][]=m.getMap();
		int keypos[]=m.getKeyPos();
		int key=m.getKey();
		int doors[][]=m.getDoors();
		
		char h='H';
		if(basher==1){
			h='A';
		}
		if(key==0){
			h='K';
		}

		move= i.input(s);

		switch(move){
		case "w":
			if(map[hero[0]-1][hero[1]]!='X' && map[hero[0]-1][hero[1]]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]-1][hero[1]]=h;
				hero[0]=hero[0]-1;		
			}
			break;
		case "a":
			if(map[hero[0]][hero[1]-1]!='X' && map[hero[0]][hero[1]-1]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]][hero[1]-1]=h;
				hero[1]=hero[1]-1;
			}
			break;
		case "s":
			if(map[hero[0]+1][hero[1]]!='X' && map[hero[0]+1][hero[1]]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]+1][hero[1]]=h;
				hero[0]=hero[0]+1;
			}
			break;
		case "d":
			if(map[hero[0]][hero[1]+1]!='X' && map[hero[0]][hero[1]+1]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]][hero[1]+1]=h;
				hero[1]=hero[1]+1;
			}
			break;
		default:
			break;
		}

		
		if(key == 1){
			map[keypos[0]][keypos[1]]='k';
		}
		
		if(hero[0]==keypos[0] && hero[1]==keypos[1]){
			for(int i2=0;i2<doors.length;i2++){
				map[doors[i2][0]][doors[i2][1]]='S';
				
			}

			m.setKey(0);
			map[hero[0]][hero[1]]='K';
			
		}
	}
}
