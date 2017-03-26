package dkeep.logic;

import java.util.Random;
/**
 * Class that saves the guard info in a Dungeon type level, keeps record of the guard's path, its current position, its personality and if it is asleep in case of a drunken personality
 * @author Leonardo Capozzi
 * @author Ricardo Carvalho
 *
 */
public class Guard{
	private int guard[][];
	private int guardpos;
	private int type; //rookie-0 /drunken-1 /suspicious-2
	private int direction;
	private int sleep;
/**
 * Creates a guard given its personality and its successive positions
 * @param guardarray guard's path, array that contains the guard's successive coordinates 
 * @param t guard's personality can be rookie(0), drunken(1) or suspicious(2)
 */
	public Guard(int guardarray[][], int t){
		guardpos=0;
		guard=guardarray;
		type = t;
		direction=1;
		sleep=-1; }
/**
 * Gets the guard path
 * @return array containing the guard's successive positions
 */
	public int[][] getGuard(){return guard; }
/**
 * Gets the guard's current position in it's path
 * @return index of guard's current coordinates in the path's array
 */
	public int getGuardpos(){return guardpos; }
	
/**
 * Moves the guard to it's next position in the path given its type (a rookie's next position index is current+1,
 *  a drunken may be asleep and may change movement direction when he wakes up, a suspicious guard may change its direction randomly);
 *  the method also updates the guard's position in the map.
 * @param m Map of the current level
 */
	public void GuardMove(Map m){
		char map[][]=m.getMap(); int random=-1; Random rand = new Random();
		map[guard[guardpos][0]][guard[guardpos][1]]=' ';
		switch(type){
		case 1:
			if((sleep<=0)&&(random=rand.nextInt(5))==0){sleep=5; }
			if(sleep>0){ map[guard[guardpos][0]][guard[guardpos][1]]='g'; sleep=sleep-1; return; }
			else if(sleep==0){
					random=rand.nextInt(2); if(random==0){direction=0-direction;}
					sleep=sleep-1; } break;
		case 2: random=rand.nextInt(5); if(random==0){direction=0-direction; } }
		guardpos=guardpos+direction;
		if((guardpos==guard.length)){guardpos=0; }
		else if((guardpos==-1)){guardpos=guard.length-1; }
		map[guard[guardpos][0]][guard[guardpos][1]]='G';} } 