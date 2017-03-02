package dkeep.logic;

import java.util.Random;

public class Ogre {

	private int ogre[];
	private int bat[];
	
	public Ogre(int ogrepos[],int batpos[]){
		ogre= ogrepos;
		bat=batpos;
		
	}
	
	public int[] getOgre(){
		return ogre;
	}
	
	public int[] getBat(){
		return bat;
	}
	
	public void OgreMove(Map m){
		
		char map[][]=m.getMap();
		Random rand = new Random();
        int r;
		int ogremove, batmove;
		int keypos[]=m.getKeyPos();
		int key=m.getKey();
		r=0;
		
		
		while(r==0){
		ogremove = rand.nextInt(4);
		
		switch(ogremove){
		case 0:
			if(map[ogre[0]-1][ogre[1]]!='X' && map[ogre[0]-1][ogre[1]]!='I'&& map[ogre[0]-1][ogre[1]]!='S'){
				
				map[ogre[0]][ogre[1]]=' ';
				map[bat[0]][bat[1]]=' ';
				map[ogre[0]-1][ogre[1]]='O';
				ogre[0]=ogre[0]-1;
				r=1;
			}
			break;
		case 1:
			if(map[ogre[0]][ogre[1]-1]!='X' && map[ogre[0]][ogre[1]-1]!='I' && map[ogre[0]][ogre[1]-1]!='S'){
				
				map[ogre[0]][ogre[1]]=' ';
				map[bat[0]][bat[1]]=' ';
				map[ogre[0]][ogre[1]-1]='O';
				ogre[1]=ogre[1]-1;
				r=1;
			}
			break;
		case 2:
			if(map[ogre[0]+1][ogre[1]]!='X' && map[ogre[0]+1][ogre[1]]!='I' && map[ogre[0]+1][ogre[1]]!='S'){
				map[ogre[0]][ogre[1]]=' ';
				map[bat[0]][bat[1]]=' ';
				map[ogre[0]+1][ogre[1]]='O';
				ogre[0]=ogre[0]+1;
				r=1;
			}
			
			break;
		case 3:
			if(map[ogre[0]][ogre[1]+1]!='X' && map[ogre[0]][ogre[1]+1]!='I' && map[ogre[0]][ogre[1]+1]!='S'){
				map[ogre[0]][ogre[1]]=' ';
				map[bat[0]][bat[1]]=' ';
				map[ogre[0]][ogre[1]+1]='O';
				ogre[1]=ogre[1]+1;		
				r=1;
			}
			
		   break;
		default:
			break;
		 }
		}
		
		r=0;
		
		while(r==0){
		
		batmove = rand.nextInt(4);
		
		switch(batmove){
		case 0:
			if(map[ogre[0]-1][ogre[1]]!='X' && map[ogre[0]-1][ogre[1]]!='I'&& map[ogre[0]-1][ogre[1]]!='S'){
				
				map[ogre[0]-1][ogre[1]]='*';
				bat[0]=ogre[0]-1;
				bat[1]=ogre[1];
				r=1;
			}
			break;
		case 1:
			if(map[ogre[0]][ogre[1]-1]!='X' && map[ogre[0]][ogre[1]-1]!='I' && map[ogre[0]][ogre[1]-1]!='S'){
				map[ogre[0]][ogre[1]-1]='*';
				bat[1]=ogre[1]-1;
				bat[0]=ogre[0];
				r=1;
			}
			break;
		case 2:
			if(map[ogre[0]+1][ogre[1]]!='X' && map[ogre[0]+1][ogre[1]]!='I' && map[ogre[0]+1][ogre[1]]!='S'){
				map[ogre[0]+1][ogre[1]]='*';
				bat[0]=ogre[0]+1;
				bat[1]=ogre[1];
				r=1;
			}
			break;
		case 3:
			if(map[ogre[0]][ogre[1]+1]!='X' && map[ogre[0]][ogre[1]+1]!='I' && map[ogre[0]][ogre[1]+1]!='S'){
				map[ogre[0]][ogre[1]+1]='*';
				bat[0]=ogre[0];
				bat[1]=ogre[1]+1;
				r=1;
			}
		   break;
		default:
			break;
		}
		}
		
		if(key==1 && (ogre[0]==keypos[0] && ogre[1]==keypos[1])){
			map[keypos[0]][keypos[1]]='$';
		}
		else if(key==1 && (bat[0]==keypos[0] && bat[1]==keypos[0])){
			map[keypos[0]][keypos[1]]='$';
		}
		else if(key==1){
			map[keypos[0]][keypos[1]]='K';
		}
	}
}
