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
	
	public void HeroMove(Map m,String move){
	
		char map[][]=m.getMap();
		int keypos[]=m.getKeyPos();
		int key=m.getKey();
		int doors[][]=m.getDoors();
		
		char h='H';
		if(basher==1){
			h='A';
		}
		if(key==0 && m.getMapType()==2){
			h='K';
		}

		switch(move){
		case "w":
			if(map[hero[0]-1][hero[1]]!='X' && map[hero[0]-1][hero[1]]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]-1][hero[1]]=h;
				hero[0]=hero[0]-1;		
			}
			else if(m.getMapType()==2 && map[hero[0]-1][hero[1]]=='I' && key==0){
				map[hero[0]-1][hero[1]]='S';
			}
			break;
		case "a":
			if(map[hero[0]][hero[1]-1]!='X' && map[hero[0]][hero[1]-1]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]][hero[1]-1]=h;
				hero[1]=hero[1]-1;
			}
			else if(m.getMapType()==2 && map[hero[0]][hero[1]-1]=='I' && key==0){
				map[hero[0]][hero[1]-1]='S';
			}
			break;
		case "s":
			if(map[hero[0]+1][hero[1]]!='X' && map[hero[0]+1][hero[1]]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]+1][hero[1]]=h;
				hero[0]=hero[0]+1;
			}
			else if(m.getMapType()==2 && map[hero[0]+1][hero[1]]=='I' && key==0){
				map[hero[0]+1][hero[1]]='S';
			}
			break;
		case "d":
			if(map[hero[0]][hero[1]+1]!='X' && map[hero[0]][hero[1]+1]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]][hero[1]+1]=h;
				hero[1]=hero[1]+1;
			}
			else if(m.getMapType()==2 && map[hero[0]][hero[1]+1]=='I' && key==0){
				map[hero[0]][hero[1]+1]='S';
				
			}
			break;
		default:
			break;
		}

		
		if((hero[0]==keypos[0] && hero[1]==keypos[1]) && m.getMapType()==1){
			for(int i=0;i<doors.length;i++){
				map[doors[i][0]][doors[i][1]]='S';
				
			}
			m.setKey(0);
		}
		else if((hero[0]==keypos[0] && hero[1]==keypos[1]) && m.getMapType()==2){

			m.setKey(0);
			map[hero[0]][hero[1]]='K';
			
		}
		
		if(m.getMapType()==1 && !(hero[0]==keypos[0] && hero[1]==keypos[1])){
			map[keypos[0]][keypos[1]]='k';
		}
		
	}
}
