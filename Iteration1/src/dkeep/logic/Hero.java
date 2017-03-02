package dkeep.logic;

import dkeep.cli.Input;

public class Hero {
	int hero[];

	public Hero(int heropos[]){
		hero=heropos;
	}

	public int[] getHero(){
		return hero;
	}

	public void HeroMove(Map m){
		String move;
		Input i=new Input();
		char map[][]=m.getMap();
		int keypos[]=m.getKeyPos();
		int doors[][]=m.getDoors();

		move= i.input();

		switch(move){
		case "w":
			if(map[hero[0]-1][hero[1]]!='X' && map[hero[0]-1][hero[1]]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]-1][hero[1]]='H';
				hero[0]=hero[0]-1;
			}
			break;
		case "a":
			if(map[hero[0]][hero[1]-1]!='X' && map[hero[0]][hero[1]-1]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]][hero[1]-1]='H';
				hero[1]=hero[1]-1;
			}
			break;
		case "s":
			if(map[hero[0]+1][hero[1]]!='X' && map[hero[0]+1][hero[1]]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]+1][hero[1]]='H';
				hero[0]=hero[0]+1;
			}
			break;
		case "d":
			if(map[hero[0]][hero[1]+1]!='X' && map[hero[0]][hero[1]+1]!='I'){
				map[hero[0]][hero[1]]=' ';
				map[hero[0]][hero[1]+1]='H';
				hero[1]=hero[1]+1;
			}
			break;
		default:
			break;
		}

		if(hero[0]==keypos[0] && hero[1]==keypos[1]){
			for(int i2=0;i2<doors.length;i2++){
				map[doors[i2][0]][doors[i2][1]]='S';
			}

			m.setKey(0);

		}
	}
}
