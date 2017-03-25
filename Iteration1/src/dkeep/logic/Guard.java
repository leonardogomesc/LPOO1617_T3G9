package dkeep.logic;

import java.util.Random;

public class Guard{
	private int guard[][];
	private int guardpos;
	private int type; //rookie-0 /drunken-1 /suspicious-2
	private int direction;
	private int sleep;

	public Guard(int guardarray[][], int t){
		guardpos=0;
		guard=guardarray;
		type = t;
		direction=1;
		sleep=-1; }

	public int[][] getGuard(){return guard; }

	public int getGuardpos(){return guardpos; }
	

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