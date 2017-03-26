package dkeep.logic;

import java.util.Random;
/**
 * Class that keeps the information of an ogre, its bat and the ogre's stunned status
 * @author Leonardo Capozzi
 * @author Ricardo Carvalho 
 *
 */
public class Ogre{

	private int ogre[];
	private int bat[];
	private int stunned;
	/**
	 * Creates an ogre object given its starting position and the bat's position
	 * @param ogrepos array containing the ogre's coordinates
	 * @param batpos array containing the ogre's bat coordinates
	 */
	public Ogre(int ogrepos[],int batpos[]){ogre= ogrepos; bat=batpos; stunned=0; }
	/**
	 * Gets the ogre's position
	 * @return array with ogre's coordinates
	 */
	public int[] getOgre(){return ogre; }
	/**
	 * Gets the ogre's bat position
	 * @return array with ogre's bat coordinates
	 */
	public int[] getBat(){return bat; }

	/**
	 * Gets the stunned status of the ogre
	 * @return int with the stunned status (0 if not stunned, different otherwise)
	 */
	public int getStunned() {return stunned; }

	/**
	 * Sets the ogre as stunned for 2 turns
	 */
	public void setStunned() {stunned = 2; }

	/**
	 * Clears the ogre's and its bat's cells in the map
	 * @param m level's map containing the ogre
	 */
	public void OgreErase(Map m){
		char map[][]=m.getMap();
		map[ogre[0]][ogre[1]]=' ';
		map[bat[0]][bat[1]]=' '; }

	/**
	 * Sets in motion the ogre and its bat's movement, randomly selecting a direction until it is valid
	 * @param m level's map containing the ogre
	 */
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