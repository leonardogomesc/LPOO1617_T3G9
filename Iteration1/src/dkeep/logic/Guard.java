package dkeep.logic;

import java.util.Random;

public class Guard {
	private int guard[][];
	private int guardpos;
	private int type; //rookie-1 /drunken-2 /suspicious-3
	private int direction;
	private int sleep;

	public Guard(int guardarray[][], int t){
		guardpos=0;
		guard=guardarray;
		type = t;
		direction=1;
		sleep=-1;
	}

	public int[][] getGuard(){
		return guard;
	}

	public int getGuardpos(){
		return guardpos;
	}
	


	public void GuardMove(Map m){
		char map[][]=m.getMap();
		Random rand = new Random();
		int random=-1;
		
		if(type==1){
			 map[guard[guardpos][0]][guard[guardpos][1]]=' ';
			 
			if(guardpos==guard.length-1) {
				map[guard[0][0]][guard[0][1]]='G';
				guardpos=0;
			}
			else{
				map[guard[guardpos+1][0]][guard[guardpos+1][1]]='G';
				guardpos=guardpos+1;
			}
		} 
		else if(type==2){
			
			map[guard[guardpos][0]][guard[guardpos][1]]=' ';
			
			
			if(sleep==0||sleep==-1){
				random=rand.nextInt(5);
			}
			
			if(random==0){
				sleep=5;
			}
			
			if(sleep>0){
				map[guard[guardpos][0]][guard[guardpos][1]]='g';
				sleep=sleep-1;
			}
			else if (sleep<=0){
				if(sleep==0){
					random=rand.nextInt(2);
					
					if(random==0){
						if(direction==1){
							direction=-1;
						}
						else if (direction==-1){
							direction=1;
						}
					}
					
					sleep=sleep-1;
				}
				
				
				if((guardpos==guard.length-1)&&( direction==1)){
					map[guard[0][0]][guard[0][1]]='G';
					guardpos=0;
				}
				else if((guardpos==0)&&(direction==-1)){
					map[guard[guard.length-1][0]][guard[guard.length-1][1]]='G';
					guardpos=guard.length-1;
					
				}
				else if(direction==-1){
					map[guard[guardpos-1][0]][guard[guardpos-1][1]]='G';
					guardpos=guardpos-1;
				}
				else if(direction==1){
					map[guard[guardpos+1][0]][guard[guardpos+1][1]]='G';
					guardpos=guardpos+1;
				}
				
			}
			
		} 
		else if (type==3){
			random=rand.nextInt(5);
			

			if(random==0){
				if(direction==1){
					direction=-1;
				}
				else if (direction==-1){
					direction=1;
				}
			}
			
			map[guard[guardpos][0]][guard[guardpos][1]]=' ';
			
			if((guardpos==guard.length-1)&&( direction==1)){
				map[guard[0][0]][guard[0][1]]='G';
				guardpos=0;
			}
			else if((guardpos==0)&&(direction==-1)){
				map[guard[guard.length-1][0]][guard[guard.length-1][1]]='G';
				guardpos=guard.length-1;
				
			}
			else if(direction==-1){
				map[guard[guardpos-1][0]][guard[guardpos-1][1]]='G';
				guardpos=guardpos-1;
			}
			else if(direction==1){
				map[guard[guardpos+1][0]][guard[guardpos+1][1]]='G';
				guardpos=guardpos+1;
			}

			
		}

	}
}

