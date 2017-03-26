package dkeep.logic;

import java.util.Random;

public class Ogre{

	private int ogre[];
	private int bat[];
	private int stunned;

	public Ogre(int ogrepos[],int batpos[]){ogre= ogrepos; bat=batpos; stunned=0; }

	public int[] getOgre(){return ogre; }

	public int[] getBat(){return bat; }

	public int getStunned() {return stunned; }

	public void setStunned() {stunned = 2; }

	public void OgreErase(Map m){
		char map[][]=m.getMap();
		map[ogre[0]][ogre[1]]=' ';
		map[bat[0]][bat[1]]=' '; }

	public void OgreMove(Map m){
		char map[][]=m.getMap();
		Random rand = new Random();
		int r=0, key=m.getKey(), ogremove, batmove;
		int keypos[]=m.getKeyPos();		
		if(stunned==0){
			while(r==0){ogremove = rand.nextInt(4); r = MOgre(ogremove, map); }
			r=0;
		} else{stunned=stunned-1; map[ogre[0]][ogre[1]]='8'; r=0;}		
		while(r==0){batmove = rand.nextInt(4); r= MBat(batmove,  map); }		
		if(key==1 && (ogre[0]==keypos[0] && ogre[1]==keypos[1])){map[keypos[0]][keypos[1]]='$'; }
		else if(key==1 && (bat[0]==keypos[0] && bat[1]==keypos[1])){map[keypos[0]][keypos[1]]='$'; } }

	private int MOgre(int ogremove, char[][] map){
		switch(ogremove){
		case 0:
			if(map[ogre[0]-1][ogre[1]]!='X' && map[ogre[0]-1][ogre[1]]!='I'&& map[ogre[0]-1][ogre[1]]!='S'){
				map[ogre[0]-1][ogre[1]]='O'; ogre[0]=ogre[0]-1; return 1; }
		case 1:
			if(map[ogre[0]][ogre[1]-1]!='X' && map[ogre[0]][ogre[1]-1]!='I' && map[ogre[0]][ogre[1]-1]!='S'){
				map[ogre[0]][ogre[1]-1]='O'; ogre[1]=ogre[1]-1; return 1; }
		case 2:
			if(map[ogre[0]+1][ogre[1]]!='X' && map[ogre[0]+1][ogre[1]]!='I' && map[ogre[0]+1][ogre[1]]!='S'){
				map[ogre[0]+1][ogre[1]]='O'; ogre[0]=ogre[0]+1; return 1; }
		case 3:
			if(map[ogre[0]][ogre[1]+1]!='X' && map[ogre[0]][ogre[1]+1]!='I' && map[ogre[0]][ogre[1]+1]!='S'){
				map[ogre[0]][ogre[1]+1]='O'; ogre[1]=ogre[1]+1; return 1; } } 
		return 0; }
	private int MBat(int batmove, char[][] map){
		switch(batmove){
		case 0:
			if(map[ogre[0]-1][ogre[1]]!='X' && map[ogre[0]-1][ogre[1]]!='I'&& map[ogre[0]-1][ogre[1]]!='S'){
				map[ogre[0]-1][ogre[1]]='*'; bat[0]=ogre[0]-1; bat[1]=ogre[1]; return 1; }
		case 1:
			if(map[ogre[0]][ogre[1]-1]!='X' && map[ogre[0]][ogre[1]-1]!='I' && map[ogre[0]][ogre[1]-1]!='S'){
				map[ogre[0]][ogre[1]-1]='*'; bat[1]=ogre[1]-1; bat[0]=ogre[0]; return 1; }
		case 2:
			if(map[ogre[0]+1][ogre[1]]!='X' && map[ogre[0]+1][ogre[1]]!='I' && map[ogre[0]+1][ogre[1]]!='S'){
				map[ogre[0]+1][ogre[1]]='*'; bat[0]=ogre[0]+1; bat[1]=ogre[1]; return 1; }
		case 3:
			if(map[ogre[0]][ogre[1]+1]!='X' && map[ogre[0]][ogre[1]+1]!='I' && map[ogre[0]][ogre[1]+1]!='S'){
				map[ogre[0]][ogre[1]+1]='*'; bat[0]=ogre[0]; bat[1]=ogre[1]+1; return 1; } }
		return 0; }
}